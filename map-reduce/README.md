MapReduce Tutarial

Login used : Log4J

Execution command 
hadoop jar <jar-path> <driver-class-path> <hdfs-input-path> <hdfs-output-path>



Example 

Total sort Using custom partitioner
hadoop jar target/map-reduce-1.0-SNAPSHOT.jar com.kk.mapreduce.sort.SortByCountryDriver /Users/kaustuv/countrylistcopy.txt /Users/kaustuv/CountrySort-output


Total sort Using total order partitioner and input sampler 

hadoop jar target/map-reduce-1.0-SNAPSHOT.jar com.kk.mapreduce.sort.TotalSortByCountryDriver /Users/kaustuv/countrylistcopy.txt /Users/kaustuv/TotalPartitionerCountrySort 

WordCount

ToPN Problem