
## Demonstration of How to solve Common Facebook Friend 

###### Sample Input
Consist of person-> its friend list e.g.

```
A->B,C,D,F
B->A,D
C->A,F
D->A,B
E->F
F->A,C
```

###### Sample Output 


## Execution Command from mapreduce directory 
`hadoop jar target/map-reduce-1.0-SNAPSHOT.jar com.kk.mapreduce.commonfriends.CmnFrndDriver <input_file_path>  <output_folder_path>`


## Solution 

## MapPhase
For each K,V where K is person & V is her friend list emit command friend for two friend , keep the order of paiir of friend chronological
###### Input 
A->B,C,D,F

###### Outout
A,B -> C,D,F
A,C -> B,D,F
A,D -> B,C,F
A,F -> B,C,D

###### Input 
C->A,F

######  Output
A,C ->F
C,F ->A

## ReducePhase
merger all vlaues in a single set, this will eliminate the duplicate too
Input
A,C -> [(B,D,F),(F)]
Output
A,C -> B,D,F












