package com.kk.mapreduce.commonfriends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.Logger;

import com.kk.mapreduce.DeleteExistingHadoopOutput;

public class CmnFrndDriver
{

    private static Logger logger = Logger.getLogger(CmnFrndDriver.class);

    public static void main(String[] args) throws Exception
    {
        if (args.length != 2)
        {
            logger.error("Incorrect argument usage : ");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator",
                "\t");
        Job job = Job.getInstance(conf, "Common Friend");

        job.setJarByClass(CmnFrndDriver.class);

        Path hadoopInputPath = new Path(args[0]);
        Path hadoopOutputpath = new Path(args[1]);
        
     // To Delete HDFS output folder if exist already
        DeleteExistingHadoopOutput deleteExistingHadoopOutput = new DeleteExistingHadoopOutput(
                hadoopOutputpath, conf);
        deleteExistingHadoopOutput.removeHDFSFolderIfExists();

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setMapperClass(CmnFrndMapper.class);

        job.setReducerClass(CmnFrndReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(job, hadoopInputPath);
        FileOutputFormat.setOutputPath(job, hadoopOutputpath);
        job.setJarByClass(CmnFrndDriver.class);

        job.waitForCompletion(true);

    }
}
