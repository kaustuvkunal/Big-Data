
Partitioner class partitions the keys of intermediate map output
They Ensure identical keys go to same reducer
Total number of partitions equal to number of reducer
Default partition is hash function



To implement custom partitioner,extend partitioner class and implement its getPartion() method







