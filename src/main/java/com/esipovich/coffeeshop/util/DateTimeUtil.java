package com.esipovich.coffeeshop.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil{
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static Date convertStringToDate(String dateString) {
        Date parsedDate = null;
        try{
            parsedDate = dateFormat.parse(dateString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return parsedDate;
    }

    public static String convertDateToString(Date date) {
        return dateFormat.format(date);
    }

}
