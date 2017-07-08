package com.xmg.pss.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/24 0024.
 */
public class DateUtil {
    private DateUtil(){}

    /**
     * 设置时间为一天的0时0分0秒
     * @param currentDate 要操作的时间对象
     * @return 设置好的时间对象
     */
    public static Date getBeginDate(Date currentDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    /**
     * 设置时间为一天的23时59分59秒
     * @param currentDate 要操作的时间对象
     * @return 设置好的时间对象
     */
    public static Date getEndDate(Date currentDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime();
    }
}
