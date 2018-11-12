package com.kk.mapreduce.totalordersort;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Identity Reducer class
 * 
 * @author Kaustuv
 *
 * 
 * 
 */
public class SortByCountryReducer
        extends Reducer<Text, NullWritable, Text, NullWritable>
{

    public void reduce(Text key, Iterable<NullWritable> values, Context context)
            throws IOException, InterruptedException
    {

        context.write(key, NullWritable.get());

    }
}
