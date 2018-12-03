
##  Sorting data-set using MapReduce

- MapReduce is an ideal framework for sorting large data set
- Hadoop distributions is packaged with input sampler and TotalOrderPartitioner specially for sorting tasks.
- We can sort data set using custom partitioner (when key frequency is know to us) or using TotalOrderPartiioner
- Package implements both solution, where we have fetched sorted country names from a geographical data  set


### Sort data using Custom-Partitioner
 
 Driver Class : `SortByCountryDriver` 
 Define a custom partition for incremental partitioning and explictly set number of reducer equal to number of partitions.
 
### Sort data using Total-Order-Partitioner 
 
Driver Class : `TotalSortByCountryDriver`
Using InputSampler &  TotalOrderPartitioner
 

### Execution Command 

- For Sorting  using Custom-Partitioner 

`hadoop jar <path_for_map-reduce-1.0-SNAPSHOT.jar> com.kk.mapreduce.totalordersort.SortByCountryDriver <input_file_path>  <output_folder_path>`


- For Sorting  using TotalOrderPartitioner 

`hadoop jar <path_for_map-reduce-1.0-SNAPSHOT.jar> com.kk.mapreduce.totalordersort.TotalSortByCountryDriver <input_file_path>  <output_folder_path>`


###### Sample Input extract
 

```
 "106","Maldives","Republic of Maldives","Independent State",,,"Male","MVR","Rufiyaa","+960","MV","MDV","462",".mv"
"107","Mali","Republic of Mali","Independent State",,,"Bamako","XOF","Franc","+223","ML","MLI","466",".ml"
"108","Malta","Republic of Malta","Independent State",,,"Valletta","MTL","Lira","+356","MT","MLT","470",".mt"
"109","Marshall Islands","Republic of the Marshall Islands","Independent State",,,"Majuro","USD","Dollar","+692","MH","MHL","584",".mh"
"110","Mauritania","Islamic Republic of Mauritania","Independent State",,,"Nouakchott","MRO","Ouguiya","+222","MR","MRT","478",".mr"
"111","Mauritius","Republic of Mauritius","Independent State",,,"Port Louis","MUR","Rupee","+230","MU","MUS","480",".mu"
"112","Mexico","United Mexican States","Independent State",,,"Mexico","MXN","Peso","+52","MX","MEX","484",".mx"
"113","Micronesia","Federated States of Micronesia","Independent State",,,"Palikir","USD","Dollar","+691","FM","FSM","583",".fm"
"114","Moldova","Republic of Moldova","Independent State",,,"Chisinau","MDL","Leu","+373","MD","MDA","498",".md"
"115","Monaco","Principality of Monaco","Independent State",,,"Monaco","EUR","Euro","+377","MC","MCO","492",".mc"
"116","Mongolia",,"Independent State",,,"Ulaanbaatar","MNT","Tugrik","+976","MN","MNG","496",".mn"
```

###### Sample Output extract
Sorted country name
```
Luxembourg
Macau
Macedonia
Madagascar
Malawi
Malaysia
Maldives
Mali
Malta
Marshall Islands
Martinique
Mauritania
Mauritius
Mayotte
```



 All classes are documented and self explanatory

 






