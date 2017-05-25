package com.esipovich.coffeeshop.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//можно убрать класс если в orders.xhtml не использовать toString
public class DateTimeUtil{
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String convertDateToString(Date date) {
        return dateFormat.format(date);
    }

}
