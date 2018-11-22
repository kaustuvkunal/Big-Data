
package com.kk.mapreduce.combineinput;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.kk.mapreduce.inputformat.CFInputFormat;

/**
 * 
 * @author Kaustuv Kunal
 * @since 29-Oct-2018 11:02:50 AM
 * @version 1.0
 */

public class CombineFileMaxTempDriver
{

    /**
     * 
     * @param args
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args)
            throws IOException, InterruptedException, ClassNotFoundException
    {
        {
            // TODO Auto-generated method stub

            Configuration conf = new Configuration();

            Job job = Job.getInstance(conf,
                    "CombineFileInputFormat Implementation");

            job.setJarByClass(CombineFileMaxTempDriver.class);
            Path hadoopInputPath = new Path(args[0]);
            Path hadoopOutputpath = new Path(args[1]);

            job.setInputFormatClass(CFInputFormat.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(FloatWritable.class);
            job.setMapperClass(CombineInputMaxTemperatureMapper.class);
            job.setCombinerClass(MaxTemperatureReducer.class);
            job.setReducerClass(MaxTemperatureReducer.class);
            job.setOutputFormatClass(TextOutputFormat.class);

            FileInputFormat.addInputPath(job, hadoopInputPath);
            FileOutputFormat.setOutputPath(job, hadoopOutputpath);

            job.waitForCompletion(true);

        }

    }

}
