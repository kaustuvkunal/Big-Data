package com.kk.test.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.kk.mapreduce.topnproblem.TopNMapper;
import com.kk.mapreduce.topnproblem.TopNSalariesReducer;
import com.kk.mapreduce.topnproblem.TopNWealthiestPersonsReducer;

public class TopNMapReduceTest
{
    MapDriver<LongWritable, Text, LongWritable, Text>                                   topNMapDriver;
    ReduceDriver<LongWritable, Text, LongWritable, NullWritable>                        topNSalaryReduceDriver;
    ReduceDriver<LongWritable, Text, Text, LongWritable>                                topNEmployeeReduceDriver;
    MapReduceDriver<LongWritable, Text, LongWritable, Text, LongWritable, NullWritable> topNSalaryMapReduceDriver;
    MapReduceDriver<LongWritable, Text, LongWritable, Text, Text, LongWritable>         topNEmployeeMapReduceDriver;

    @Before
    public void setUp()
    {
        TopNMapper mapper = new TopNMapper();
        TopNSalariesReducer salaryReducer = new TopNSalariesReducer();
        TopNWealthiestPersonsReducer employeeReducer = new TopNWealthiestPersonsReducer();
        topNMapDriver = MapDriver.newMapDriver(mapper);
        topNSalaryReduceDriver = ReduceDriver.newReduceDriver(salaryReducer);
        topNEmployeeReduceDriver = ReduceDriver
                .newReduceDriver(employeeReducer);
        topNSalaryMapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper,
                salaryReducer);
        topNEmployeeMapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper,
                employeeReducer);
    }

    @Test
    public void testMapper() throws IOException
    {

        topNMapDriver.withInput(new LongWritable(),
                new Text("1,1,A,55,3,5,1134"));
        topNMapDriver.withOutput(new LongWritable(1134), new Text("1"));
        topNMapDriver.runTest();

    }

}