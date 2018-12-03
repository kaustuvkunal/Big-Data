###	 TopN Problem using MapReduce</h>

MapRedcue can be used in problems when we need fetch topN elements in a data sets for example, 
-	Finding top 10 salary of a company
-	Finding top 50 higest paid employe
-	Finding top hastag of the day from twitter data set
-	Top 100 cited paper related to a particular topic 


 We have to take account of duplicate  values for example two employee can have same salary, in such case we can have more than n results with all n+1 entries as identical values. So, 
 -	If we are calculating top N salary then there will be 10 entries only 
 -	If we are to fetch top 10 highly paid employ it may emit more than 10 entries
 
######		Solution 1: Using Tree Map 
 -	Declare N-size tree map in setup method
 -	Initialize in map method 
 -	Emit in cleanup method
 -	Use single reducer and emit tree map entries in reducer 
 
 The tree map does not keep into account duplicates so its not a feasible solution
 However if you want to use tree sort we need to implement custom comparator to keep track of duplicates.
 
 
######	 Solution 2 : Using Combiner
- 	Map method emit sorted key (by default)
-	Use combiner to fetch top N keys of the map
-	In the reducer emit respective key or values base on our use case 



We have fetched 
-top N salary and 
-top N wealthiest   

 ### Execution Command  Top N salary

`hadoop jar <path-for-map-reduce-1.0-SNAPSHOT.jar> com.kk.mapreduce.topnproblem.TopNSalariesDriver  <input-Path>  <outputpath>`


### Execution Command  Top N highest paid employee
`hadoop jar <path-for-map-reduce-1.0-SNAPSHOT.jar> com.kk.mapreduce.topnproblem.TopNWealthiestPersonsDriver <input-Path>  <outputpath> `

 
 