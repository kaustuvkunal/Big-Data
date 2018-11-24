package com.kk.mapreduce.topnproblem;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

/**
 * MapReduce Combiner class to emit first key,value pair for N input keys only
 * <p>
 * 
 * @author Kaustuv Kunal
 * @since 31-Oct-2018 4:26:56 PM
 * @version 1.0
 */
public class TopNCombiner
        extends Reducer<LongWritable, Text, LongWritable, Text>
{
    int                   itiratorCount;
    int                   valueOfN;
    private static Logger log = Logger
            .getLogger(TopNCombiner.class);

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
                    context.write(key, value);
               }
                itiratorCount++;
            }
            catch (Exception e)
            {
                log.error(e.getMessage());
            }
        }
    }
}