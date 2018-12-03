/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kk.mapreduce.maxtempusingcombineinputformat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.Logger;

import com.kk.mapreduce.DeleteExistingHadoopOutput;

/**
 * Process large number of small weather data files to fetch yearly maximum
 * temperature
 * 
 */
public class CombineFileProcessDriver
{

    private static Logger logger = Logger
            .getLogger(CombineFileProcessDriver.class);

    public static void main(String[] args)
            throws IOException, InterruptedException, ClassNotFoundException
    {

        if (args.length != 2)
        {
            logger.error("Incorrect argument usage : ");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf,
                "MaxTemperature Using Combine File InputFormat");

        job.setJarByClass(CombineFileProcessDriver.class);

        Path hadoopInputPath = new Path(args[0]);
        Path hadoopOutputPath = new Path(args[1]);

        // Delete HDFS output folder if exist already(optional)
        DeleteExistingHadoopOutput deleteExistingHadoopOutput = new DeleteExistingHadoopOutput(
                hadoopOutputPath, conf);
        deleteExistingHadoopOutput.removeHDFSFolderIfExists();

        FileInputFormat.addInputPath(job, hadoopInputPath);
        job.setInputFormatClass(CFInputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);
        job.setMapperClass(CombineInputMaxTemperatureMapper.class);

        job.setReducerClass(MaxTemperatureReducer.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileOutputFormat.setOutputPath(job, hadoopOutputPath);
        job.setJarByClass(CombineFileProcessDriver.class);

        job.waitForCompletion(true);

    }

}
