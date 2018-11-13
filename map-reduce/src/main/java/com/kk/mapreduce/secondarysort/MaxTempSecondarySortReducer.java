package com.kk.mapreduce.secondarysort;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.kk.mapreduce.writables.IntFloatPair;

public class MaxTempSecondarySortReducer extends Reducer<IntFloatPair, 
NullWritable, IntFloatPair,  NullWritable>
{

    public void reduce(IntFloatPair key, Iterable<NullWritable> values,
            Context context) throws IOException, InterruptedException
    {
         
        context.write(key, NullWritable.get());
    }

}