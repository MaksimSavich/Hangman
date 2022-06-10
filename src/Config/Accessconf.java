package Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Accessconf {

    public static String getValue(String x) throws IOException {
        String configFilePath = "src/Config/config.properties";
        FileInputStream propsInput = new FileInputStream(configFilePath);
        Properties prop = new Properties();
        prop.load(propsInput);

        return prop.getProperty(x);
    }

}
