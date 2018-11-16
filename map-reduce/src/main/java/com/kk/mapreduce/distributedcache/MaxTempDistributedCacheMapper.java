package com.kk.mapreduce.distributedcache;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTempDistributedCacheMapper
        extends Mapper<LongWritable, Text, Text, FloatWritable>
{
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException
    {

        String line = value.toString();
        String year = line.substring(4, 9);
        float temp = Float.parseFloat(line.substring(38, 43));

        context.write(new Text(year), new FloatWritable(temp));
    }
}