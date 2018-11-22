package com.kk.mapreduce.commonfriends;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CmnFrndMapper extends Mapper<LongWritable, Text, Text, Text>
{
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException
    {
 
         
        String[] keyVal = value.toString().split("->", 2);
        
        String person = keyVal[0];
        String[] friends =keyVal[1].split(",");
        
        
         
        
        for(String frnd: friends)
        {
            String newKey ;
            if ( person.compareTo(frnd) < 0)
                newKey = person+"-"+frnd ;
            else 
                newKey = frnd+"-"+person ;
            
            context.write(new Text(newKey), new Text(keyVal[1].replaceAll(frnd, "")) );
        }
              

         
    }
}