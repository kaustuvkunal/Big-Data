<h>TopN Problem using MapReduce</h>

MapRedcue can be used in problems where we need to find topN elements in a data sets
for example 
Finding top 10 salary of a company
Finding top 50 Higest paid employe
Finding top hastag of the day from twitter data set
Top 100 cited paper related to a particular topic 

 Note:
 While solving we hav to take account that values can be duplicate 
 two employee can have same salary, in such case 
 we can have more than n results with  all n+1 entries will have identical values 
 
 If we are calculatin top N salay then there will be 10 entries only 
 If we are to fetch top 10 highly paid employ it may emit more than 10 entries
 
 Soultion 1:
 
 Tree Map 
 Declare N-size tree map in setup method 
 Initialise in map method 
 emit in cleanup method 
 
 the tree map does not keep into acount duplicates so its not a feasible solution
 Howeverif you want to use tree sort we need to implement custom comparator to keep track of duplicates.
 Use single reducer approach and emit tree map entries in redcucer.
 
 Solution 2 :
 
Let map method emith key sorted by default 
Use combiner to fetch top N keys  of the map

In the reducer emit  respective key or values base on use case 


 
 



************
If there are multiple part-r-filies combing a single sorted file usibg unit command 
