package com.kk.mapreduce.maxtemp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapred.SkipBadRecords;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.log4j.Logger;

public class MaxTempCompressedOutputDriver
{

    private static Logger logger = Logger.getLogger(MaxTempCompressedOutputDriver.class);

    public static void main(String[] args) throws Exception
    {
        if (args.length != 2)
        {
            logger.error("Incorrect argument usage : ");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "MaxTemperature With compressed output");

        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);

        job.setJarByClass(MaxTempCompressedOutputDriver.class);

        job.setInputFormatClass(TextInputFormat.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);
        job.setMapperClass(MaxTemperatureMapper.class);

        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        
        SkipBadRecords.setMapperMaxSkipRecords(conf, 100);
        FileInputFormat.addInputPath(job, inputPath);

        SequenceFileOutputFormat.setOutputPath(job, outputPath);
        SequenceFileOutputFormat.setCompressOutput(job, true);
        SequenceFileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);

        job.waitForCompletion(true);

    }

}
