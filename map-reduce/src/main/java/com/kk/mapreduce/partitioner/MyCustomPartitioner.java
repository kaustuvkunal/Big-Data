package com.kk.mapreduce.partitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * This Class is a Custom Partitioner class to partition key space into 
 * two part based on male and female
 * <p> 
 * @author Kaustuv Kunal
 * @version 1.0
 * @since  1.8 
 * 09-Nov-2018
 */
public  class MyCustomPartitioner extends Partitioner<Text, Text>
{
	public int getPartition (Text key, Text value, int numReduceTasks)
	{
		if (numReduceTasks == 0)
			return 0;
		if (key.equals(new Text("Male")))
			return 0;
		if (key.equals(new Text("Female")))
			return 1;
		
		return numReduceTasks;
	}
}