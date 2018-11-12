package com.kk.mapreduce.totalordersort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.kk.mapreduce.DeleteExistingHadoopOutput;
import com.kk.mapreduce.partitioner.TextSortPartitioner;

/**
 * MapReduce driver class to fetch alphabetical sorted country names using
 * custom partitioner
 * 
 * @author Kaustuv Kunal
 * @version 1.0
 * @since 1.8 12-Nov-2018
 */
public class SortByCountryDriver
{
    public static void main(String[] args) throws Exception
    {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "CountrySort using customPartitioner ");
        job.setJarByClass(SortByCountryDriver.class);
        
        Path hadoopInputPath = new Path(args[0]);
        Path hadoopOutputPath = new Path(args[1]);

        // Delete HDFS output folder if exist already(optional)
        DeleteExistingHadoopOutput deleteExistingHadoopOutput = new DeleteExistingHadoopOutput(
                hadoopOutputPath, conf);
        deleteExistingHadoopOutput.removeHDFSFolderIfExists();
        
        job.setMapperClass(SortByCountryMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        
        // custom partitioner  
        job.setPartitionerClass(TextSortPartitioner.class);
        
        //key set partitioned into two set
        job.setNumReduceTasks(2);

        job.setReducerClass(SortByCountryReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.addInputPath(job, hadoopInputPath);
        FileOutputFormat.setOutputPath(job, hadoopOutputPath);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
