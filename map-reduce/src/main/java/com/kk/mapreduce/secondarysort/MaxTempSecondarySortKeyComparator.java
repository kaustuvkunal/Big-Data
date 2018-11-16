package com.kk.mapreduce.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.kk.mapreduce.writables.IntFloatPair;
/**
 * Custom Sort key comparator class 
 * @author Kaustuv Kunal
 * @version 1.0
 * @since  1.8 
 * 13-Nov-2018
 */
public class MaxTempSecondarySortKeyComparator extends WritableComparator
{

    protected MaxTempSecondarySortKeyComparator()
    {
        super(IntFloatPair.class, true);
    }

 // sort years is ascending  then temprature in descending 
    @Override    
    public int compare(WritableComparable w1, WritableComparable w2)
    {
        IntFloatPair if1 = (IntFloatPair) w1;
        IntFloatPair if2 = (IntFloatPair) w2;
        int cmp = IntFloatPair.compare(if1.getFirst(), if2.getFirst());
        if (cmp != 0)
            return cmp;
        return IntFloatPair.compare(if2.getSecond(), if1.getSecond());
    }

}
