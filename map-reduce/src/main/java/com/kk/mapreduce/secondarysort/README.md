
Secondary Sort 














What if our use case require us to sort values also ? 



üOption1 : Sort values inside reducer, faster but memory inefficient


ü Option 2 :  Define new key as combination of key &value, perform sorting & grouping of  new key in specific order  as per business requirement


1.Pair  key(K) and value(V) by implementing some custom pair writable (e.g. TextPair)


2. Implement custom partitioner class to partion key based on old key only.


3. Implement custom sortCompartor class,  to key wise sort map output.


4. Implement custom group comparator to specify input order to reducer K, <list of
values> .







Below Program demonstrate Secondary sort :
