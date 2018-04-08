package gui.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatedDate {
    public static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    public static final DateFormat EXPRESS_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MMM-dd", Locale.ENGLISH);

}
