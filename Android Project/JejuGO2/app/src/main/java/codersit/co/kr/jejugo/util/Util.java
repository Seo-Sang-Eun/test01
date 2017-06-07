package codersit.co.kr.jejugo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by P200 on 2017-06-05.
 */

public class Util {

    public static String getCurrentDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    public static String getCurrentDate(String getType)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(getType);
        return sdf.format(new Date());
    }

    public static String getCurrentTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(new Date());
    }





}
