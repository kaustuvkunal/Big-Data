package com.kk.mapreduce.topnproblem;

import java.io.IOException;
import org.apache.log4j.Logger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Reducer class to emits first N keys only.
 * 
 * @author Kaustuv Kunal
 * @since 31-Oct-2018 5:18:46 PM
 * @version 1.0
 */
public class TopNSalariesReducer
        extends Reducer<LongWritable, Text, LongWritable, NullWritable>
{
    int                   iterationCount;
    int                   valueN;
    private static Logger log = Logger.getLogger(TopNSalariesReducer.class);

    @Override
    protected void setup(
            Reducer<LongWritable, Text, LongWritable, NullWritable>.Context context)
            throws IOException, InterruptedException
    {
        Configuration conf = context.getConfiguration();
        valueN = Integer.parseInt(conf.get("Value_Of_N"));
        iterationCount = 0;
    }

    public void reduce(LongWritable key, Iterable<Text> values, Context context)
    {
        if (iterationCount < valueN)
        {
            try
            {
                context.write(key, NullWritable.get());
                iterationCount++;
            }
            catch (Exception e)
            {
                log.error(e.getMessage());
            }
        }
    }

}