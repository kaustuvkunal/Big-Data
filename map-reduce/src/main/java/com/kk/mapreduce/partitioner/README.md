
### Partitioner

- Partitioner class partitions the keys of intermediate map output
- They ensure identical keys go to same reducer
- Total number of partitions is equal to number of reducer
- Default partition is hash function



#### MyCustomPartitioner.java 
Implements custom partitioner

 







