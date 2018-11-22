/**
 * 
 */
package com.kk.mapreduce.inputformat;

import java.io.IOException;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import com.kk.mapreduce.recordreader.PdfRecordReader;

/**
 *  
 *
 * @date 15-Oct-2018
 */
public class PDFInputFormat extends FileInputFormat {

	@Override
	public RecordReader createRecordReader(
			InputSplit split, TaskAttemptContext context) throws IOException,
			InterruptedException {

		return new PdfRecordReader();
	}

}

