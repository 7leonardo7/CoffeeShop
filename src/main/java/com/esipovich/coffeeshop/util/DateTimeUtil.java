package com.esipovich.coffeeshop.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Converter(autoApply = true)
public class DateTimeUtil implements AttributeConverter<String, Timestamp>{
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @Override
    public Timestamp convertToDatabaseColumn(String dateTime) {
        return Timestamp.valueOf(dateTime);
    }

    @Override
    public String convertToEntityAttribute(Timestamp timestamp) {
        return dateFormat.format(timestamp);
    }
}
