

## Writables

- In distributed systems, data spend lots of time doing inter node transfer hence undergoes frequent serialisation & de-serialisation
- Standard java data type are not suitable for this
- To overcome, hadoop defines their own datatype  known as writable
- WritableCompareble is a writable which is also comparable 
- All MapReduce keys are instance of writablecomparable  and all
values are instance of Writable.
-User can write their own writable type by implementing writable interface

### Packages contain following customWritable 

- IntFloatPair : Custom writable of pair of  int & float type
- TextPair : Custom writable of pair of Text & Text type
- FileLineWritable.java :Custom writable of pair of LongWritable & String type




  





