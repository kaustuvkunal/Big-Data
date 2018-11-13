package com.kk.mapreduce.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.kk.mapreduce.writables.IntFloatPair;

public class MaxTempSecondarySortKeyComparator extends WritableComparator
{

    protected MaxTempSecondarySortKeyComparator()
    {
        super(IntFloatPair.class, true);
    }

 // sort first year then temp
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
