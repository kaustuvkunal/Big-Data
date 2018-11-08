package com.kk.mapreduce;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
 

//Class for  read  properties form configuration file
public class ConfigProperties
{
    InputStream           inputStream;
    Properties            prop;
    private static Logger log = Logger.getLogger(ConfigProperties.class);

    public ConfigProperties()
    {
        try
        {
            prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader()
                    .getResourceAsStream(propFileName);

            if (inputStream != null)
            {
               log.info("Loading configuration properties file");
                prop.load(inputStream);
            }
            else
            {
                throw new FileNotFoundException("property file '" + propFileName
                        + "' not found in the classpath");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }

    }

    public Properties getProp()
    {
        return prop;
    }
    

}
