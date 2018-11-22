package com.kk.mapreduce.recordreader;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import org.apache.hadoop.util.LineReader;

import com.kk.mapreduce.writables.FileLineWritable;
/**
 *
 * @author Kaustuv Kunal
 */
public class CFRecordReader extends RecordReader<FileLineWritable, Text>
{

  	private long startOffset;
	private long end; 
	private long pos; 
	private FileSystem fs;
	private Path path;
	private Path dPath;
	private FileLineWritable key = new FileLineWritable();
	private Text value;
	private long rlength;
	private FSDataInputStream fileIn;
	private LineReader reader;
	  
 
	public CFRecordReader(CombineFileSplit split,
	      TaskAttemptContext context, Integer index) throws IOException {
	    
			Configuration currentConf = context.getConfiguration();
		  	this.path = split.getPath(index);
		  	boolean isCompressed =  findCodec(currentConf ,path);
		  	
	
		  	fs = this.path.getFileSystem(currentConf);
		  	
		  	this.startOffset = split.getOffset(index);
	
		  	if(isCompressed){
		  		this.end = startOffset + rlength;
		  	}else{
		  		this.end = startOffset + split.getLength(index);
		  		dPath =path;
		  	}
		  	
		  	boolean skipFirstLine = false;
	    
	        fileIn = fs.open(dPath);
	        
	        if(isCompressed)  fs.deleteOnExit(dPath);
	        
	        if (startOffset != 0) {
	        	skipFirstLine = true;
	        	--startOffset;
	        	fileIn.seek(startOffset);
	        }
	        reader = new LineReader(fileIn);
	        if (skipFirstLine) {  
	        	startOffset += reader.readLine(new Text(), 0,
	        	(int)Math.min((long)Integer.MAX_VALUE, end - startOffset));
	        }
	        this.pos = startOffset;
	  }

	  public void initialize(InputSplit split, TaskAttemptContext context)
	      throws IOException, InterruptedException {
	  }

	  public void close() throws IOException { }

	  public float getProgress() throws IOException {
		    if (startOffset == end) {
		      return 0.0f;
		    } else {
		      return Math.min(1.0f, (pos - startOffset) / (float)
                  (end - startOffset));
		    }
	  }

	  public boolean nextKeyValue() throws IOException {
		    if (key.fileName== null) {
		      key = new FileLineWritable();
		      key.fileName = dPath.getName();
		    }
		    key.offset = pos;
		    if (value == null) {
		      value = new Text();
		    }
		    int newSize = 0;
		    if (pos < end) {
		      newSize = reader.readLine(value);
		      pos += newSize;
		    }
		    if (newSize == 0) {
		      key = null;
		      value = null;
		      return false;
		    } else {
		      return true;
		    }
	  }

	  public FileLineWritable getCurrentKey() 
	      throws IOException, InterruptedException {
		  return key;
	  }

	  public Text getCurrentValue() throws IOException, InterruptedException {
		  return value;
	  }
  
 
	
	
	private boolean findCodec(Configuration conf, Path p){
		
		CompressionCodecFactory factory = new CompressionCodecFactory(conf);
	    CompressionCodec codec = factory.getCodec(path);
	    
	    if (codec == null) 
	    	return false; 
	    else 
	    	return true;

	}
  
}