package util.time;

import util.io.Print;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by lixuejiao on 16/8/26.
 */
public class DateUtil {

    //日期格式
    private static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    public static void main(String [] args) throws Exception{
        longToDate(1472238000000l);
        dateStrToLong("2016-08-31 17-49-30");
        getDate();
    }

    /**
     * 系统时间转成Date
     */
    private static void longToDate(long time) {
        Print.ln(f.format(new Date(time)));
    }

    /**
     * Date转成系统时间(long)
     */
    private static void dateStrToLong(String s) throws Exception{
        Print.ln(f.parse(s).getTime());
    }

    /**
     * 根据当前时间获得日期(eg:昨天,前天)
     */
    private static void getDate() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Print.ln(f.format(date));
        //昨天
        calendar.add(Calendar.DATE,-1);
        date=calendar.getTime();
        Print.ln(f.format(date));
    }
}
