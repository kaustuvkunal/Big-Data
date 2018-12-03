
## Finding Common Facebook Friend via MapReduce

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


## MapPhase
For each K,V where 
K is person 
V is her friend list 

emit comman friends for pair of friend, keepping the paiir of friend  in alphbetical order

######  Map Input 
```
A->B,C,D,F
```

###### Map Output
```
A,B -> C,D,F

A,C -> B,D,F

A,D -> B,C,F

A,F -> B,C,D
```

 
######  Map Input 
```
C->A,F
```
######  Output

```
A,C ->F

C,F ->A
```

## Reduce Phase
Merger all vlaues in a single set, this will eliminate the duplicate too

######  Reducer Input
```
A,C -> [(B,D,F),(F)]
```

######  Reducer Output
```
A,C -> B,D,F
```












