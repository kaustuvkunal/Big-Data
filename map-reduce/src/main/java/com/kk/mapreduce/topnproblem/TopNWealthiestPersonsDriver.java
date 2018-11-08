package com.kk.mapreduce.topnproblem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import com.kk.mapreduce.DeleteExistingHadoopOutput;
import com.kk.mapreduce.DeleteExistingOutputPath;

/**
 *  MapReduce Driver class to find top N highest paid employees.
 *  <p>
 * @author Kaustuv Kunal
 * @since 31-Oct-2018 3:35:04 PM
 * @version 1.0
 */
public class TopNWealthiestPersonsDriver
{
    private static Logger log = Logger.getLogger(TopNWealthiestPersonsDriver.class);
    
    /**
	 * 
	 * @param args
	 *            Two arguments first input directory second output
	 * @throws IOException
	 *             If an input or output exception occurred
	 * @throws ClassNotFoundException
	 *             If mapper ,reducer or combiners classes are not present
	 * @throws InterruptedException
	 *             If any interrupt occurred
	 */
	public static void main(String[] args)
			throws IOException, ClassNotFoundException, InterruptedException
	{
	    // Reading properties
        
	    Properties prop = new Properties();

        try
        {
            // Reading properties
            prop.load(new FileInputStream(
                    "src/main/resources/config.properties"));
        }
        catch (Exception e)
        {
            log.info("config file  reading error " + e.getMessage());
        }

	    Configuration conf = new Configuration();
        // Set the value of N
        conf.set("Value_Of_N", prop.getProperty("topn.value"));
        // Specify if Data-set contains Header
        conf.set("Header_Flag", prop.getProperty("topn.input.header.flag"));
        conf.set("Header_content",
                prop.getProperty("topn.input.header.content"));
        
        
		Job job = Job.getInstance(conf, "TopNWealthiestPersonsDriver");
		job.setJarByClass(TopNWealthiestPersonsDriver.class);
		
		 Path in = new Path("/Users/kaustuv/employee_data");
         Path out = new Path("/Users/kaustuv/aaaaour");

		// Delete HDFS output folder if exist already
		DeleteExistingHadoopOutput deleteExistingHadoopOutput = new DeleteExistingHadoopOutput(
				out, conf);
		deleteExistingHadoopOutput.removeHDFSFolderIfExists();

		// Delete output folder for stand-alone execution
		DeleteExistingOutputPath deleteExistingOutputPath = new DeleteExistingOutputPath(
				out.toString());
		deleteExistingOutputPath.removeFolderifExists();

		FileInputFormat.setInputPaths(job, in);
		FileOutputFormat.setOutputPath(job, out);

		job.setNumReduceTasks(1);

		job.setMapperClass(TopNMapper.class);
		job.setCombinerClass(TopNCombiner.class);
		job.setReducerClass(TopNWealthiestPersonsReducer.class);

		// Key sorting in descending order
		job.setSortComparatorClass(LongWritable.DecreasingComparator.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		job.waitForCompletion(true);
	}
}
