package com.kk.mapreduce.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * Reducer class for word count   
 * @version 1.0
 * @since  1.8 
 * 09-Nov-2018
 */
public class WCReducer extends Reducer<Text, IntWritable, Text, IntWritable>
{
	private IntWritable result = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
	{
		int sum = 0;
		for (IntWritable val : values)
		{
			sum += val.get();
		}
		result.set(sum);
		context.write(key, result);
	}
}
