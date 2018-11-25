package com.kk.mapreduce.topnproblem;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

/**
 * Mapper class to fetch employee data-set data and emits salary,id as K,V pair
 * 
 * @author Kaustuv Kunal
 * @since 31-Oct-2018 3:31:23 PM
 * @version 1.0
 */
public class TopNMapper extends Mapper<LongWritable, Text, LongWritable, Text>
{
    String                header;
    boolean               headerFlag;
    private static Logger log = Logger.getLogger(TopNMapper.class);

    @Override
    /**
     * Fetch configured parameters header_exist, header etc
     */
    

    public void map(LongWritable key, Text value, Context context)
            throws InterruptedException
    {

        try
        {
            String[] line = value.toString().split(",");
            int sal = Integer.parseInt(line[6]);
            String id = line[1];
            context.write(new LongWritable(sal), new Text(id));
        }
        catch (Exception e)
        {
            log.error("error in top n mapper for entry " +e.getMessage() + "value is"
                    + value.toString());
        }
    }
}