package com.kk.mapreduce.topnproblem;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

/**
 * Reducer class to emit top N values.
 * <p>
 * If corresponding key of Nth value, has more values in its list of value it
 * emits other values as well.
 * <p>
 * 
 * @author Kaustuv Kunal
 * @since 31-Oct-2018 4:33:18 PM
 * @version 1.0
 */
public class TopNWealthiestPersonsReducer
        extends Reducer<LongWritable, Text, Text, LongWritable>
{
    int                   itiratorCount;
    int                   valueOfN;
    private static Logger log = Logger
            .getLogger(TopNWealthiestPersonsReducer.class);

    @Override
    protected void setup(Context context)
            throws IOException, InterruptedException
    {

        Configuration conf = context.getConfiguration();

        valueOfN = Integer.parseInt(conf.get("Value_Of_N"));

        itiratorCount = 0;
    }

    public void reduce(LongWritable key, Iterable<Text> values, Context context)
    {
        if (itiratorCount < valueOfN)
        {
            try
            {
                for (Text value : values)
                {
                    context.write(value, key);
                    itiratorCount++;
                }
            }
            catch (Exception e)
            {
                log.error(e.getMessage());
            }
        }
    }
}