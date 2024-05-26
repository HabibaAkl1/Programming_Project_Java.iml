package de.thd.dwd_data_retriever.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static Date getDateFromString(String dateString) {
        if (!dateString.equals("null")) {
            try {
                return simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                throw new RuntimeException("Konnte keine Date-Instanz vom dateString \"" + dateString + "\" ableiten!");
            }
        } else {
            return null;
        }
    }

}
