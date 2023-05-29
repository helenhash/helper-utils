package com.helen.date;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.IsoFields;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

@UtilityClass
public class DateUtils {

    /**
     * Convert from date to string with provided date format.
     *
     * @param date
     * @param format
     * @return
     */
    public static String toString(Date date, String format) {
        String value = StringUtils.EMPTY;
        if (date != null) {
            DateFormat f = new SimpleDateFormat(format);
            value = f.format(date);
        }
        return value;
    }

    /**
     * Convert date to String with any supported formats.
     *
     * @param date
     * @return
     */
    public static String toString(Date date) {
        AtomicReference<String> value = new AtomicReference<>(StringUtils.EMPTY);
        DateConstants.SUPPORTED_DATE_FORMATS.forEach(fmt -> {
            if (StringUtils.isEmpty(value.get())) {
                DateFormat f = new SimpleDateFormat(fmt);
                try {
                    value.set(f.format(date));
                } catch (DateTimeParseException e) {
                    value.set(StringUtils.EMPTY);
                }
            }
        });
        if (StringUtils.isEmpty(value.get())) {
            throw new RuntimeException("Unknown date format.");
        }
        return value.get();
    }

    public static String toString(LocalDate date, String format) {
        String value = StringUtils.EMPTY;
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            String formattedString = date.format(formatter);
        }
        return value;
    }

    public static Date getCurrentDate() {
        return Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.MIN)
                .atZone(ZoneId.systemDefault()).toInstant());
    }

    public static java.sql.Date toSqlDate(String date, String format) {
        java.sql.Date value = null;
        try {
            Date dt = new SimpleDateFormat(format).parse(date);
            value = new java.sql.Date(dt.getTime());
        } catch (ParseException e) {
            //log
        }
        return value;
    }

    /**
     * Convert a date string to LocalDate.
     *
     * @param date
     * @param format
     * @param defIfFail - set null if fail to convert.
     * @return
     */
    public static LocalDate toLocalDate(String date, String format, LocalDate defIfFail) {
        LocalDate value;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            value = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            value = defIfFail;
        }
        return value;
    }

    public static boolean isValidDate(String inDate, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static String changeDateFormat(String dateStr, String oldpattern, String newpattern) {
        String result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(oldpattern);
            Date d = sdf.parse(dateStr);
            sdf.applyPattern(newpattern);
            result = sdf.format(d);
        } catch (NullPointerException e) {
            // log
        } catch (ParseException e) {
            // log
        } catch (IllegalArgumentException e) {
            // log
        }
        return result;
    }

    public static Set<LocalDate> genDateRange(LocalDate from, LocalDate to) {
        Set<LocalDate> dates = new TreeSet<>();
        from = from.withDayOfMonth(1);
        to = to.withDayOfMonth(1);
        Period diff = Period.between(from, to);
        int numOfMonth = diff.getYears() * 12 + diff.getMonths();
        for (int i = 0; i <= numOfMonth; i++) {
            LocalDate d = from.plusMonths(i).withDayOfMonth(1);
            dates.add(d);
        }
        return dates;
    }

    public int getQuarterNumberByDate(LocalDate date) {
        if (date == null) {
            return 0;
        }
        return date.get(IsoFields.QUARTER_OF_YEAR);
    }

    public String convertNumberToWord(int num) {
        switch (num) {
            case 1:
                return "First";
            case 2:
                return "Second";
            case 3:
                return "Third";
            case 4:
                return "Fourth";
            default:
                return "NA";
        }
    }

    public String formatQuarter(LocalDate quarterDate, String pattern, String defIfNull) {
        if (quarterDate == null) {
            return defIfNull;
        }
        int quarter = quarterDate.get(IsoFields.QUARTER_OF_YEAR);
        switch (pattern) {
            case "Q-YYYY":
                String quarterInfo = "Q" + quarter + "-" + quarterDate.getYear();
                return quarterInfo;
            case "Quarter-YYYY":
                return convertNumberToWord(quarter) + " Quarter " + quarterDate.getYear();
            default:
                return defIfNull;
        }
    }

}
