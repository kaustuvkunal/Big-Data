package com.kk.test.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.kk.mapreduce.wordcount.WCMapper;
import com.kk.mapreduce.wordcount.WCReducer;

public class WordCountMapperReducerTest {

	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

	@Before
	public void setUp() {
		WCMapper mapper = new WCMapper();
		WCReducer reducer = new WCReducer();
		mapDriver = MapDriver.newMapDriver(mapper);
		reduceDriver = ReduceDriver.newReduceDriver(reducer);
		mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
	}

	@Test
	public void testMapper() throws IOException {
		mapDriver.withInput(new LongWritable(), new Text("Hi Liza Hi mohan"));
		mapDriver.withOutput(new Text("Hi"), new IntWritable(1));
	    mapDriver.withOutput(new Text("Liza"), new IntWritable(1));
	    mapDriver.withOutput(new Text("Hi"), new IntWritable(1));
	    mapDriver.withOutput(new Text("mohan"), new IntWritable(1));
		mapDriver.runTest();
	}

	@Test
	public void testReducer() throws IOException {
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(1));
		values.add(new IntWritable(1));
		reduceDriver.withInput(new Text("Hi"), values);
		reduceDriver.withOutput(new Text("Hi"), new IntWritable(2));
		reduceDriver.runTest();
	}

	@Test
	public void testMapReduce() throws IOException {
		mapReduceDriver.withInput(new LongWritable(), new Text("Hi Liza Hi mohan"));
		 mapReduceDriver.withOutput(new Text("Hi"), new IntWritable(2));
		 mapReduceDriver.withOutput(new Text("Liza"), new IntWritable(1));
		 mapReduceDriver.withOutput(new Text("mohan"), new IntWritable(1));
		mapReduceDriver.runTest();
	}
}
