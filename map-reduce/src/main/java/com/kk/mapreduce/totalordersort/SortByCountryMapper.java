package com.kk.mapreduce.totalordersort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

/**
 * Mapper class to take countrylist text format file and returns (countryname,
 * NullWritable) pair
 * 
 * @author Kaustuv
 *
 * @date 14-Oct-2018
 * 
 */

public class SortByCountryMapper
        extends Mapper<LongWritable, Text, Text, NullWritable>
{
    private Logger log = Logger.getLogger(SortByCountryMapper.class);

    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException
    {

        try
        {
            String country = value.toString().split(",")[1].replace("\"", "");

            context.write(new Text(country), NullWritable.get());
        }
        catch (Exception e)
        {
            log.error("error in input line" + e.getMessage());

        }

    }
}