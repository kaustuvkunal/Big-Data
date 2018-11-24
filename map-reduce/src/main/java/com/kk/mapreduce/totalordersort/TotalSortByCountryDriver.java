package com.kk.mapreduce.totalordersort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;

/**
 * MapReduce driver class to fetch alphabetical sorted country names using input
 * sampler and total order partitioner
 * <p>
 * Here two MapReduce job run in sequence. First job is a Map-only job. It
 * process input based on business logic and fetches only the items to be
 * sorted.Job output format is sequence file format. Second job process first
 * job's output files using input sampler and total order partitioner, Partition
 * range is defined in the configuration file present @partitionerFilePath, and
 * output the sorted items in multiple files
 * <p>
 * The multiple part-r files can be combine to produce a single sorted files if
 * required
 * <p>
 * 
 * @author Kaustuv Kunal
 * @version 1.0
 * @since 1.8 12-Nov-2018
 */

public class TotalSortByCountryDriver
{
    public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();

        Path inputPath = new Path(args[0]);
        // First job's outputPath
        Path stageOutputPath = new Path(args[1] + "-stage");
        // Partition range are stored here
        Path partitionerFilePath = new Path(args[1] + "-partition");
        // Final sorted output
        Path sortedOutputPath = new Path(args[1] + "-out");

        // First job to extract values to be sorted
        Job samplingJob = Job.getInstance(conf,
                "TotalSortByCountry- MapOnly job ");
        samplingJob.setJarByClass(TotalSortByCountryDriver.class);

        // map only job         
        samplingJob.setMapperClass(SortByCountryMapper.class);
        samplingJob.setNumReduceTasks(0);

        samplingJob.setOutputKeyClass(Text.class);
        samplingJob.setOutputValueClass(NullWritable.class);

        // set input format and path
        FileInputFormat.addInputPath(samplingJob, inputPath);
        samplingJob.setInputFormatClass(TextInputFormat.class);

        // set output format and path
        FileOutputFormat.setOutputPath(samplingJob, stageOutputPath);
        samplingJob.setOutputFormatClass(SequenceFileOutputFormat.class);

        // run job return status
        int code = samplingJob.waitForCompletion(true) ? 1 : 0;

        // run total ordering job if first job successful
        if (code == 1)
        {
            // configure TotalOrderPartitioner & InputSmapler Job j
            Job sortingJob = Job.getInstance(conf,
                    "TotalSortByCountry -TotalOrderPartitioner & InputSmapler Job");
            sortingJob.setJarByClass(TotalSortByCountryDriver.class);

            
            sortingJob.setInputFormatClass(SequenceFileInputFormat.class);
            SequenceFileInputFormat.setInputPaths(sortingJob, stageOutputPath);
            TextOutputFormat.setOutputPath(sortingJob, sortedOutputPath);

            // This job uses identity Mapper
            sortingJob.setMapperClass(Mapper.class);
            sortingJob.setMapOutputKeyClass(Text.class);
            sortingJob.setMapOutputValueClass(NullWritable.class);

            //  Set number of reducer
            // number of partition = number of reducer -1
            //what if I do not set number of reducer
            sortingJob.setNumReduceTasks(4);
            sortingJob.setReducerClass(SortByCountryReducer.class);
            sortingJob.setOutputFormatClass(TextOutputFormat.class);

            // create total order partitioner file
            TotalOrderPartitioner.setPartitionFile(
                    sortingJob.getConfiguration(), partitionerFilePath);
            
            // Sample K,V should match with mapper output K,V
            InputSampler.Sampler<Text, NullWritable> inputSampler = new InputSampler.RandomSampler<Text, NullWritable>(
                    .001, 10, 5);
            InputSampler.writePartitionFile(sortingJob, inputSampler);
            sortingJob.setPartitionerClass(TotalOrderPartitioner.class);

            // run total ordering job
            System.exit(sortingJob.waitForCompletion(true) ? 0 : 2);
        }

    }
}
