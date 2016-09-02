package util.prop;

import util.io.Print;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by lixuejiao on 16/9/2.
 * useage:
 *      PropHelper.PROPERTIES.getProperty("name")
 */
public class PropHelper {

    public static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(PropHelper.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] a) throws Exception{
        Properties prop = new Properties();
        try {
            //load a properties file from class path, inside static method
            prop.load(PropHelper.class.getClassLoader().getResourceAsStream("config.properties"));

            //get the property value and print it out
            Print.ln(prop.getProperty("name"));

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
