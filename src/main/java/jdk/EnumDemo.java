package jdk;

import java.util.Arrays;

/**
 * Created by lixuejiao on 16/9/2.
 */
public class EnumDemo {

    public static void main(String[] a) throws Exception{
        Type t = Type.getType(3);
        System.out.println(t);
    }

    public enum Type {

        // HTML段落
        HTML_SEGMENT(1),

        // 图片
        IMAGE(2);

        int _value;
        Type(int value) { _value = value; }
        public int value() { return _value; }

        public static Type getType(int _value)
        {
            Type[] types = Type.values();
            return Arrays.stream(types)
                    .filter(type -> type.value() == _value)
                    .findAny().get();
        }
    }
}
