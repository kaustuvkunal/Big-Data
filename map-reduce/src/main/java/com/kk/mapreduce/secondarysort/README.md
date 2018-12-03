
##	MapReduce Secondary Sort 

What if our use case require us to sort values also ? 

###### Option1 : Sort values inside reducer
 - Faster but memory inefficient

###### Option 2 :  Define new key as combination of key &value and perform sorting & grouping of new key in specific order as per business requirement 


 - 1.	Pair  key(K) and value(V) by implementing some custom pair writable (e.g. TextPair)
 - 2.	Implement custom partitioner class to partion key based on old key only. 
 - 3.	Implement custom sortCompartor class,  to key wise sort map output.
 - 4.	Implement custom group comparator to specify input order to reducer K, <list ofvalues> .


 ###		Execution Command  
`hadoop jar <path_for_map-reduce-1.0-SNAPSHOT.jar> com.kk.mapreduce.secondarysort.MaxTempSecondarySortDriver <input_file_path>  <output_folder_path>`

###	Sample input extract 

```
+   1943   7   18.6    11.7    ---     57.3   240.5
+   1943   8   18.1    12.0    ---     86.5   148.0
+   1943   9   15.8    10.0    ---    127.1   110.6
+   1943  10   13.6     8.3    ---    120.3    93.7
+   1943  11    9.7     5.9    ---     93.0    47.4
+   1943  12    6.9     2.7    ---     63.6    75.3
+   1944   1    9.2     5.8    ---     87.9    23.7
+   1944   2    6.5     3.1    ---     34.0    72.5
+   1944   3    8.6     3.1    ---     17.0   153.2
+   1944   4   12.7     6.6    ---     44.9   157.1
+   1944   5   13.8     7.0    ---     37.2   242.3
+   1944   6   15.4     9.9    ---     53.2   182.2
+   1944   7   18.2    12.6    ---     54.5   112.7
+   1944   8   19.7    12.8    ---    100.2   220.9
+   1944   9   15.7    10.3    ---     80.7   132.7
+   1944  10   12.1     7.8    ---    131.9    91.0
+   1944  11    9.5     5.8    ---    156.2    28.9
+   1944  12    7.7     4.3    ---     71.9    58.5
```

###	Sample output extract

```
1941	106.2
1942	183.9
1943	176.7
1944	156.2
1945	130.6
1946	152.3
```
 	
 All classes are  documented and self explanatory
