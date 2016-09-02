package easytest;

import util.io.Print;
import util.prop.PropHelper;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by lixuejiao on 16/9/2.
 */
public class Test {
    public static void main(String[] a) throws Exception{
        Print.ln(PropHelper.PROPERTIES.getProperty("name"));
    }
}
