package com.kk.mapreduce.secondarysort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import com.kk.mapreduce.DeleteExistingHadoopOutput;
import com.kk.mapreduce.partitioner.MaxTempSecondrySortPartitioner;
import com.kk.mapreduce.writables.IntFloatPair;

/**
 * Class to solve Max Temp problem using SecondrySort
 * 
 * @since 1.8 12-Nov-2018
 */
public class MaxTempSecondarySortDriver
{
    private static Logger logger = Logger
            .getLogger(MaxTempSecondarySortDriver.class);

    public static void main(String[] args) throws Exception
    {
        if (args.length != 2)
        {
            logger.error("Incorrect argument usage : ");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf,
                "MaxTemperature solution using secondry sort");
        job.setJarByClass(MaxTempSecondarySortDriver.class);

        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);
        
     // Delete HDFS output folder if exist already(optional)
        DeleteExistingHadoopOutput deleteExistingHadoopOutput = new DeleteExistingHadoopOutput(
                outputPath, conf);
        deleteExistingHadoopOutput.removeHDFSFolderIfExists();

        job.setInputFormatClass(TextInputFormat.class);

        job.setMapperClass(MaxTempSecondarySortMapper.class);
        job.setPartitionerClass(MaxTempSecondrySortPartitioner.class);
        job.setSortComparatorClass(MaxTempSecondarySortKeyComparator.class);
        job.setGroupingComparatorClass(
                MaxTempSecondarySortGroupComparator.class);
        job.setReducerClass(MaxTempSecondarySortReducer.class);

        job.setOutputKeyClass(IntFloatPair.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        job.waitForCompletion(true);

    }
}
