###	Processing Small Files in Hadoop

-	Hadoop is suitable for processing large files. What if we have many text files of small sizes ? With Text-Input-Format, input split will process one small file which is not efficient
- Solution is to define a  FileWritable  to take  file name along with its offset as key and use Combine-File-Input-Format which will pack multiple files into the same split  


## Execution Command 

`hadoop jar <path_for_map-reduce-1.0-SNAPSHOT.jar> com.kk.mapreduce.maxtempusingcombineinputformat.CombineFileProcessDriver <input_file_path>  <output_folder_path>`



Refer previous MaxTem program for sample inputs
</br>
https://github.com/kaustuvkunal/Big-Data/tree/master/map-reduce/src/main/java/com/kk/mapreduce/maxtemp
