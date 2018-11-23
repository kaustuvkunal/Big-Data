












Combiner:
Itis mini reduce  for a single  Map task.
Itoptimises the processing by minimising the amount of data being flown from one node to another.
Use if reducer operation is commutative and associative
Specify combiner in driver as job.setCombinerClass(SomeReducer.class);
Conbiner Input and output key & value type are same.






