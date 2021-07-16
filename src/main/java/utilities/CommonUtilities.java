package utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtilities {

    public static Properties loadProperties(String pathProperties) {
        try (InputStream input = new FileInputStream(pathProperties)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
