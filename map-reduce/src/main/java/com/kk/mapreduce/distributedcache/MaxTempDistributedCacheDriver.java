package com.kk.mapreduce.distributedcache;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import com.kk.mapreduce.DeleteExistingHadoopOutput;

public class MaxTempDistributedCacheDriver
{
    private static Logger logger = Logger
            .getLogger(MaxTempDistributedCacheDriver.class);

    public static void main(String[] args) throws Exception
    {
        if (args.length != 2)
        {
            logger.error("Incorrect argument usage : ");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        //  use when configuring  side data using job configuration
        // conf.set("File_Path", "LOCAL_DATASET_ABSOLUTE_PATH ");
        //e.g. LOCAL_DATASET_ABSOLUTE_PATH = /Users/kaustuv/datasetlocal.txt

        Job job = Job.getInstance(conf, "MaxTemperature");
        job.setJarByClass(MaxTempDistributedCacheDriver.class);

        Path hadoopInputPath = new Path(args[0]);
        Path hadoopOutputpath = new Path(args[1]);

        // Use when Distributed caching HDFS file
        job.addCacheFile(new Path("/Users/kaustuv/dataset.txt").toUri());

        //  Use when Distributed caching local file
        // job.addCacheFile(new
        // Path("file://LOCAL_DATASET_ABSOLUTE_PATH").toUri());

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FloatWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Delete HDFS output folder if exist already(optional)
        DeleteExistingHadoopOutput deleteExistingHadoopOutput = new DeleteExistingHadoopOutput(
                hadoopOutputpath, conf);
        deleteExistingHadoopOutput.removeHDFSFolderIfExists();

        job.setMapperClass(MaxTempDistributedCacheMapper.class);
        job.setReducerClass(MaxTempDistributedCacheReducer.class);

        FileInputFormat.addInputPath(job, hadoopInputPath);
        FileOutputFormat.setOutputPath(job, hadoopOutputpath);
        job.setJarByClass(MaxTempDistributedCacheDriver.class);

        job.waitForCompletion(true);

    }

}
