

Writables


üIn distributed systems, data spend lots of time doing inter node transfer hence undergoes frequent serialisation & de-serialisation


üStandard java data type are not suitable for this


üTo overcome, hadoop defines their own datatype  known as writable


üWritableCompareble is a writable which is also comparable 



üAll MapReduce keys are instance of writablecomparable  and all
values are instance of Writable.










Custom
Writable














üUser can write their own writable type by implementing writable
interface*


üWritable interface defines two methods write & readFields.


WritableComparable interface is a sub interface of the Writable and java.lang.Comparable interfaces

This packages contain following customWritable 
 FileLineWritable.java - Filename & Line 
 IntFloatPair.java
 TextPair.java














