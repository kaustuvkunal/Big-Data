package com.kk.mapreduce.commonfriends;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CmnFrndReducer extends Reducer<Text, Text, Text, Text>
{

    public void reduce(Text key, Iterable<Text> values,
            Context context) throws IOException, InterruptedException
    {
        Set<String> set = new HashSet<>();
        for ( Text value : values)
        {
            Collections.addAll(set, value.toString().split(",")); 
            set.remove(",");
        }
        context.write(key, new Text(set.toString())  );
    }

}
