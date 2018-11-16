package com.kk.mapreduce.distributedcache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTempDistributedCacheReducer
        extends Reducer<Text, FloatWritable, Text, Text>

{

    private int[] stopWords = new int[2];

    protected void setup(Context context)
            throws IOException, InterruptedException
    {
        super.setup(context);

         
        Configuration conf = context.getConfiguration();
        
        //if side defined using job configuration mechanism
       // Path file1path = new Path(conf.get("File_Path"));

        // if side defined using job configuration mechanism
         URI[] files = context.getCacheFiles();
         Path file1path = new Path(files[0]);

        // if side data file is in local file system
        // FileSystem fs = FileSystem.getLocal(context.getConfiguration());

        // if side data file is in HDFS
       FileSystem fs = FileSystem.get(context.getConfiguration());

        BufferedReader br = new BufferedReader(
                new InputStreamReader(fs.open(file1path)));

        int i = 0;
        try
        {
            String line;
            line = br.readLine();
            while (line != null)
            {
                stopWords[i++] = Integer.parseInt(line);

                // be sure to read the next line otherwise you'll get an
                // infinite loop
                line = br.readLine();
            }
        }
        finally
        {
            // you should close out the BufferedReader
            br.close();
        }

    }

    public void reduce(Text key, Iterable<FloatWritable> values,
            Context context) throws IOException, InterruptedException
    {

        float maxValue = Float.MIN_VALUE;
        for (FloatWritable value : values)
        {
            maxValue = Math.max(maxValue, value.get());
        }

        if (maxValue < stopWords[0])
            context.write(key, new Text(Float.toString(maxValue) + "---Cold"));
        else if (maxValue < stopWords[1])
            context.write(key, new Text(Float.toString(maxValue) + "---warm"));
        else
            context.write(key, new Text(Float.toString(maxValue) + "---hot"));

    }

}
