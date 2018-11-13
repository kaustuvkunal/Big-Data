package com.kk.mapreduce.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.kk.mapreduce.writables.IntFloatPair;

/**
 * Custom Group comparator class to ensure every year has single key entry to reducer 
 * @author Kaustuv Kunal
 * @version 1.0
 * @since  1.8 
 * 13-Nov-2018
 */
public class MaxTempSecondarySortGroupComparator extends WritableComparator
{
    protected MaxTempSecondarySortGroupComparator()
    {
        super(IntFloatPair.class, true);
    }

    // Reducer input is grouped by year  
    @Override
    public int compare(WritableComparable w1, WritableComparable w2)
    {
        IntFloatPair if1 = (IntFloatPair) w1;
        IntFloatPair if2 = (IntFloatPair) w2;
        return IntFloatPair.compare(if1.getFirst(), if2.getFirst());
    }

}