package com.config_accessor;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class ConfigAccessor {
    private static final File locConfigFile = new File("config/config.local.properties");
    private static final File pubConfigFile = new File("config/config.properties");

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
           } else if (pubConfigFile.exists() && !pubConfigFile.isDirectory()) {
               return pubConfigFile;
           }
           throw new FileNotFoundException();
       }
       catch (FileNotFoundException exc){
           exc.printStackTrace();
           System.out.println("\u001B[41m" + "ERROR: \"config\" directory or config file do not exist" + "\u001B[0m\n" +
                              "\u001B[42m"+ "SOLUTION: create a \"config\" folder with a \"config.local.properties\" file" + "\u001B[0m");
       }
        return null;
    }

}
