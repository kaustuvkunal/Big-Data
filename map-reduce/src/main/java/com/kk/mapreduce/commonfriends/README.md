
## Sample program to Find Common Facebook Friend via MapReduce

###### Sample Input
Input file Consist of person and her friend list as `person->friend-list`

```
A->B,C,D,F
B->A,D
C->A,F
D->A,B
E->F
F->A,C
```

###### Sample Output 
Output file
```
A-B	[C, D, F]
A-C	[B, D, F]
A-D	[B, C, F]
A-F	[B, C, D, E, F]
B-D	[A, B, D]
C-F	[A, E, F]
E-F	[A, C, E, F]
```


## Execution Command  
`hadoop jar <path_for_map-reduce-1.0-SNAPSHOT.jar> com.kk.mapreduce.commonfriends.CmnFrndDriver <input_file_path>  <output_folder_path>`


###  Solution


## Map Phase
For each K,V where, 
K is person 
 and V is her list of friends 

emit common friends for each pair of friend, keeping the pair of friends in alphabetical order

######  Map Input-1 
```
A->B,C,D,F
```

###### Map Output-1
```
A,B -> C,D,F

A,C -> B,D,F

A,D -> B,C,F

A,F -> B,C,D
```

 
######  Map Input-2 
```
C->A,F
```
######  Map Output-2

```
A,C ->F

C,F ->A
```

## Reduce Phase
Merge all values in a single set, this will eliminate the duplicates too

######  Reducer Input
```
A,C -> [(B,D,F),(F)]
```

######  Reducer Output
```
A,C -> B,D,F
```












