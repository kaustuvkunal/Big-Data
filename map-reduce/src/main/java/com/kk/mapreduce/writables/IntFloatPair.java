package com.kk.mapreduce.writables;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class IntFloatPair implements WritableComparable<IntFloatPair> {

    private int first;
    private float second;
    
    public IntFloatPair() {
    }
    
    public IntFloatPair(int first, float second) {
      set(first, second);
    }
    
    public void set(int first, float second) {
      this.first = first;
      this.second = second;
    }
    
    public int getFirst() {
      return first;
    }

    public float getSecond() {
      return second;
    }

    @Override
    public void write(DataOutput out) throws IOException {
      out.writeInt(first);
      out.writeFloat(second);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
      first = in.readInt();
      second = in.readFloat();
    }
    
    @Override
    public int hashCode() {
      return (int) (first * 163 + second);
    }
    
    @Override
    public boolean equals(Object o) {
      if (o instanceof IntFloatPair) {
        IntFloatPair ip = (IntFloatPair) o;
        return first == ip.first && second == ip.second;
      }
      return false;
    }

    @Override
    public String toString() {
      return first + "\t" + second;
    }
    
    @Override
    public int compareTo(IntFloatPair ip) {
      int cmp = compare(first, ip.first);
      if (cmp != 0) {
        return cmp;
      }
      return compare(second, ip.second);
    }
    
    /**
     * Convenience method for comparing two ints.
     */
    public static int compare(int a, int b) {
      return (a < b ? -1 : (a == b ? 0 : 1));
  }
    /**
     * Convenience method for comparing two floats.
     */
    
    public static int compare(float a, float b) {
        return (a < b ? -1 : (a == b ? 0 : 1));
    }
}
