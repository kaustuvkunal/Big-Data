package com.kk.mapreduce.partitioner;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import com.kk.mapreduce.writables.IntFloatPair;

/**
 * Custom partitioner class for year wise partition
 * @author Kaustuv Kunal
 * @version 1.0
 * @since  1.8 
 * 13-Nov-2018
 */
public class MaxTempSecondrySortPartitioner extends Partitioner<IntFloatPair, NullWritable>
{
    /**
     * partition will ensure same year record will go to same reducer
     * 
     * @param key year,Temp pair
     * @param value nullwritable
     * @param numReduceTasks
     * @return
     * int
     */
    public int getPartition (IntFloatPair key, NullWritable value, int numReduceTasks)
    {
         
        return Math.abs((key.getFirst()*127)% numReduceTasks);
    }
}
