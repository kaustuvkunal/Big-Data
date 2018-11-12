package com.kk.mapreduce.partitioner;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.log4j.Logger;

/**
 * Custom partitioner class to partition text key space into two in order to generate 
 * total order sorting 
 * @author Kaustuv Kunal
 * @version 1.0
 * @since  1.8 
 * 12-Nov-2018
 */
public class TextSortPartitioner extends Partitioner<Text, NullWritable>
{
    private static Logger log = Logger.getLogger(TextSortPartitioner.class);

    public int getPartition(Text key, NullWritable value, int numReduceTasks)
    {
        if (numReduceTasks == 1)
            return 0;
        char c;

        try
        {
            c = Character.toUpperCase(key.toString().charAt(0));

            if (c >= 'A' && c <= 'K')
                return 0;
            else
                return 1;
        }
        catch (Exception e)
        {
            log.error("error in custom partitioner:Partitioner" + e.getMessage());
        }

        return 0;

    }
}