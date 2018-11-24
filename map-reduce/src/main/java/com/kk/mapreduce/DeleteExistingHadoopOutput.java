package com.kk.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

/**
 * Class to delete existing folder
 * 
 * @author Kaustuv Kunal
 * @since 29-Oct-2018 10:08:45 AM
 * @version 1.0
 */
public class DeleteExistingHadoopOutput
{
    private Path          outPath;
    private Configuration conf;

    private Logger logger = Logger.getLogger(DeleteExistingHadoopOutput.class);

    public Configuration getConf()
    {
        return conf;
    }

    public DeleteExistingHadoopOutput(Path outPath, Configuration conf)
    {
        super();
        this.outPath = outPath;
        this.conf = conf;
    }

    public void setConf(Configuration conf)
    {
        this.conf = conf;
    }

    public Path getOutPath()
    {
        return outPath;
    }

    public void setOutPath(Path outPath)
    {
        this.outPath = outPath;
    }

    public void removeHDFSFolderIfExists() throws IOException
    {

        FileSystem hdfs = FileSystem.get(conf);
        if (hdfs.exists(outPath))
        {
            try
            {
                logger.info("Output HDFS folder exist .. deleting");
                hdfs.delete(outPath, true);
                logger.info("..deleted");
            }

            catch (Exception e)
            {
                logger.error(e.getMessage());
            }
        }
    }
}
