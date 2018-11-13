package com.kk.mapreduce.secondarysort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.kk.mapreduce.writables.IntFloatPair;

public class MaxTempSecondarySortMapper
        extends Mapper<LongWritable, Text, IntFloatPair, NullWritable>
{
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException
    {

        String[] line = value.toString().split("( )+");
        
        int year = Integer.parseInt(line[1]);
        float temp = Float.parseFloat(line[6]);

        context.write(new IntFloatPair(year, temp), NullWritable.get());
    }
}