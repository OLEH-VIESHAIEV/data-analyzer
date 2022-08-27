package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateUtil {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public static Date parseDate(String value) {
        Objects.requireNonNull(value);
        if (value.isEmpty()) {
            return null;
        }
        Date date = null;
        try {
            date = DATE_FORMATTER.parse(value);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't parse date from given value " + value);
        }
        return date;
    }
}
