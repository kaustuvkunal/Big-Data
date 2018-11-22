package com.kk.mapreduce.writables;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

/**
 * This class is writable implementation of two varible as  filename, and byteoffset 
 *  
 * @version 1.0
 * @since  1.8 
 * 09-Nov-2018
 */

public class FileLineWritable implements WritableComparable<FileLineWritable>
{

    public long offset;
    public String fileName;

    public void readFields(DataInput in) throws IOException
    {
        this.offset = in.readLong();
        this.fileName = Text.readString(in);
    }

    public void write(DataOutput out) throws IOException
    {
        out.writeLong(offset);
        Text.writeString(out, fileName);
    }

    //Compare file name first then offset
    
    public int compareTo(FileLineWritable fl)
    {
        int cmp = this.fileName.compareTo(fl.fileName);
        if (cmp != 0)
        {
            return cmp;
        }
        return (int) Math.signum((double) (this.offset - fl.offset));
    }

    
    @Override
    public int hashCode()
    {               // generated hashCode()
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + (int) (offset ^ (offset >>> 32));
        return result;
    }

     
    @Override
    public boolean equals(Object obj)
    {  // generated equals()
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        FileLineWritable other = (FileLineWritable) obj;
        if (fileName == null)
        {
            if (other.fileName != null)
            {
                return false;
            }
        }
        else if (!fileName.equals(other.fileName))
        {
            return false;
        }
        if (offset != other.offset)
        {
            return false;
        }
        return true;
    }
}
