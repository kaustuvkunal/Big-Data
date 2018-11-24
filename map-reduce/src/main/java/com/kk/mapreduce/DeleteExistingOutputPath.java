package com.kk.mapreduce;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.log4j.Logger;

/**
 * Class to delete existing HDFS folder
 * 
 * @author Kaustuv
 * @version 1.0
 * @since 29-Oct-2018
 *
 */
public class DeleteExistingOutputPath
{
    private String outPath;
    private Logger logger = Logger.getLogger(DeleteExistingOutputPath.class);

    public String getOutPath()
    {
        return outPath;
    }

    public void setOutPath(String outPath)
    {
        this.outPath = outPath;
    }

    public DeleteExistingOutputPath(String outPath)
    {
        super();
        this.outPath = outPath;
    }

    public void removeFolderifExists() throws IOException
    {

        Path path = FileSystems.getDefault().getPath(outPath);

        // check weather folder exist
        if (Files.exists(path))
        {
            // Delete the files inside folder
            logger.info("Output folder exist...");
            File dir = new File(outPath);
            File[] files = dir.listFiles();
            logger.info("Deleting the content of output folder");
            for (File myFile : files)
            {
                myFile.delete();
            }
            // now delete the empty folder
            try
            {
                logger.info("Deleting Output folder...");
                Files.deleteIfExists(path);
                logger.info("Output folder deleted ");
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
            }

        }

    }

}
