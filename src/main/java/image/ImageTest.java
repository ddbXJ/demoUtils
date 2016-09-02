package image;

import util.io.IOUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * Created by lixuejiao on 16/9/1.
 * 获得一个图片文件的长宽 大小
 */
public class ImageTest {

    public static void main(String[] a) throws Exception{
        File picture = new File("/Users/lixuejiao/Documents/xuejiao/test.jpg");
        byte[] bytes = IOUtil.FileToBytes(picture);
        BufferedImage sourceImg = ImageIO.read(new ByteArrayInputStream(bytes));
        System.out.println(bytes.length);
        System.out.println(sourceImg.getWidth());
        System.out.println(sourceImg.getHeight());
    }


}
