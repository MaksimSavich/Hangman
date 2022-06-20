package com.config_accessor;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class ConfigAccessor {
    private static final File locConfigFile = new File(System.getenv("HANGCONF"));
    //outputs value inside of a config file
    public static String getValue(String x) throws IOException {
        File tempFile = checkFile();
        FileInputStream propsInput = new FileInputStream(Objects.requireNonNull(tempFile));
        Properties prop = new Properties();
        prop.load(propsInput);
        return prop.getProperty(x);
    }

    //checks if config files exist within the config directory
    private static File checkFile(){
       try {
           if (locConfigFile.exists() && !locConfigFile.isDirectory()) {
               return locConfigFile;
           }
           throw new FileNotFoundException();
       }
       catch (FileNotFoundException exc){
           exc.printStackTrace();
           System.out.println("\u001B[41m" + "ERROR: can not find \"config\" file" + "\u001B[0m\n" +
                              "\u001B[42m"+ "SOLUTION: ensure config env is set to the correct file path" + "\u001B[0m");
       }
        return null;
    }

}
