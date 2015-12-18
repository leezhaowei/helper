package com.zwli.datetime; // NOPMD by jli on 15-12-18 PM3:48

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zwli Create: 2007-11-07
 */
public final class Dateutils {

    // "yyyy-MM-dd";
    // "yyyy-MM-dd HH:mm";
    // "yyyy-MM-dd HH:mm:ss,SSS";

    // "yyyy/MM/dd";
    // "yyyy/MM/dd HH:mm:ss";
    // "yyyy/MM/dd HH:mm:ss,SSS";
    // "EEE, dd MMM yyyy HH:mm:ss z";

    private static final int DAY_OF_MONTH_28 = 28;
    private static final int DAY_OF_MONTH_29 = 29;
    private static final int DAY_OF_MONTH_30 = 30;
    private static final int DAY_OF_MONTH_31 = 31;

    private static final int[] DAY_OF_MONTH_ARRAY = new int[] { DAY_OF_MONTH_31, DAY_OF_MONTH_28, DAY_OF_MONTH_31,
            DAY_OF_MONTH_30, DAY_OF_MONTH_31, DAY_OF_MONTH_30, DAY_OF_MONTH_31, DAY_OF_MONTH_31, DAY_OF_MONTH_30,
            DAY_OF_MONTH_31, DAY_OF_MONTH_30, DAY_OF_MONTH_31 };

    private static final ThreadLocal<SimpleDateFormat> SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd"); // NOPMD by jli on 15-12-18 PM3:53
        }
    };

    private Dateutils() {
    }

    public static int getDayOfMonth(final int year, final int month) {
        if (month < Calendar.JANUARY || month > Calendar.DECEMBER) {
            return -1; // NOPMD by jli on 15-12-18 PM3:55
        }
        int days;
        if (Calendar.FEBRUARY == month) {
            if (LeapYear.isLeapYear(year)) {
                days = DAY_OF_MONTH_29;
            } else {
                days = DAY_OF_MONTH_ARRAY[month - 1];
            }
        } else {
            days = DAY_OF_MONTH_ARRAY[month - 1];
        }
        return days;
    }

    public static Calendar getFirstDayOfMonth(final Calendar c) { // NOPMD by jli on 15-12-18 PM3:52
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c;
    }

    public static Date getFirstDayOfMonth(final Date date) {
        final Calendar c = Calendar.getInstance(); // NOPMD by jli on 15-12-18 PM3:52
        c.setTime(date);
        return getFirstDayOfMonth(c).getTime();
    }

    public static Calendar getFirstDayOfNextMonth(final Calendar cal) {
        cal.setTime(getNextMonth(cal.getTime()));
        cal.setTime(getFirstDayOfMonth(cal.getTime()));
        return cal;
    }

    public static Date getFirstDayOfNextMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfNextMonth(cal).getTime();
    }

    public static Calendar getFirstDayOfNextWeek(final Calendar cal) {
        cal.setTime(getNextWeek(cal.getTime()));
        cal.setTime(getFirstDayOfWeek(cal.getTime()));
        return cal;
    }

    public static Date getFirstDayOfNextWeek(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfNextWeek(cal).getTime();
    }

    public static Calendar getFirstDayOfPreviousMonth(final Calendar cal) {
        cal.setTime(getPreviousMonth(cal.getTime()));
        cal.setTime(getFirstDayOfMonth(cal.getTime()));
        return cal;
    }

    public static Date getFirstDayOfPreviousMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfPreviousMonth(cal).getTime();
    }

    public static Calendar getFirstDayOfPreviousWeek(final Calendar cal) {
        cal.setTime(getPreviousWeek(cal.getTime()));
        cal.setTime(getFirstDayOfWeek(cal.getTime()));
        return cal;
    }

    public static Date getFirstDayOfPreviousWeek(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfPreviousWeek(cal).getTime();
    }

    public static Calendar getFirstDayOfWeek(final Calendar cal) {
        switch (cal.get(Calendar.DAY_OF_WEEK)) { // NOPMD by jli on 15-12-18 PM5:47
        case Calendar.MONDAY:
            cal.add(Calendar.DATE, 0);
            break;
        case Calendar.TUESDAY:
            cal.add(Calendar.DATE, -1);
            break;
        case Calendar.WEDNESDAY:
            cal.add(Calendar.DATE, -2);
            break;
        case Calendar.THURSDAY:
            cal.add(Calendar.DATE, -3);
            break;
        case Calendar.FRIDAY:
            cal.add(Calendar.DATE, -4);
            break;
        case Calendar.SATURDAY:
            cal.add(Calendar.DATE, -5);
            break;
        default:
            cal.add(Calendar.DATE, -6);
        }
        return cal;
    }

    public static Date getFirstDayOfWeek(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfWeek(cal).getTime();
    }

    public static Calendar getLastDayOfMonth(final Calendar cal) {
        final int month = cal.get(Calendar.MONTH);
        switch (month) { // NOPMD by jli on 15-12-18 PM5:47
        case Calendar.FEBRUARY:
            cal.set(Calendar.DAY_OF_MONTH, DAY_OF_MONTH_28);
            break;
        case Calendar.MARCH:
        case Calendar.APRIL:
        case Calendar.AUGUST:
        case Calendar.OCTOBER:
            cal.set(Calendar.DAY_OF_MONTH, DAY_OF_MONTH_30);
            break;
        default:
            cal.set(Calendar.DAY_OF_MONTH, DAY_OF_MONTH_31);
        }
        if (month == Calendar.FEBRUARY && LeapYear.isLeapYear(cal.get(Calendar.YEAR))) {
            cal.set(Calendar.DAY_OF_MONTH, DAY_OF_MONTH_29);
        }
        return cal;
    }

    public static Date getLastDayOfMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfMonth(cal).getTime();
    }

    public static Calendar getLastDayOfNextMonth(final Calendar cal) {
        cal.setTime(getNextMonth(cal.getTime()));
        cal.setTime(getLastDayOfMonth(cal.getTime()));
        return cal;
    }

    public static Date getLastDayOfNextMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfNextMonth(cal).getTime();
    }

    public static Calendar getLastDayOfNextWeek(final Calendar cal) {
        cal.setTime(getNextWeek(cal.getTime()));
        cal.setTime(getLastDayOfWeek(cal.getTime()));
        return cal;
    }

    public static Date getLastDayOfNextWeek(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfNextWeek(cal).getTime();
    }

    public static Calendar getLastDayOfPreviousMonth(final Calendar cal) {
        cal.setTime(getPreviousMonth(cal.getTime()));
        cal.setTime(getLastDayOfMonth(cal.getTime()));
        return cal;
    }

    public static Date getLastDayOfPreviousMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfPreviousMonth(cal).getTime();
    }

    public static Calendar getLastDayOfPreviousWeek(final Calendar cal) {
        cal.setTime(getPreviousWeek(cal.getTime()));
        cal.setTime(getLastDayOfWeek(cal.getTime()));
        return cal;
    }

    public static Date getLastDayOfPreviousWeek(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfPreviousWeek(cal).getTime();
    }

    public static Calendar getLastDayOfWeek(final Calendar cal) {
        switch (cal.get(Calendar.DAY_OF_WEEK)) { // NOPMD by jli on 15-12-18 PM5:48
        case Calendar.MONDAY:
            cal.add(Calendar.DATE, 6);
            break;
        case Calendar.TUESDAY:
            cal.add(Calendar.DATE, 5);
            break;
        case Calendar.WEDNESDAY:
            cal.add(Calendar.DATE, 4);
            break;
        case Calendar.THURSDAY:
            cal.add(Calendar.DATE, 3);
            break;
        case Calendar.FRIDAY:
            cal.add(Calendar.DATE, 2);
            break;
        case Calendar.SATURDAY:
            cal.add(Calendar.DATE, 1);
            break;
        default:
            cal.add(Calendar.DATE, 0);
        }
        return cal;
    }

    public static Date getLastDayOfWeek(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfWeek(cal).getTime();
    }

    public static Calendar getNextDay(final Calendar cal) {
        cal.add(Calendar.DATE, 1);
        return cal;
    }

    public static Date getNextDay(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextDay(cal).getTime();
    }

    public static Calendar getNextMonth(final Calendar cal) {
        cal.add(Calendar.MONTH, 1);
        return cal;
    }

    public static Date getNextMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextMonth(cal).getTime();
    }

    public static Calendar getNextWeek(final Calendar cal) {
        cal.add(Calendar.DATE, 7);
        return cal;
    }

    public static Date getNextWeek(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextWeek(cal).getTime();
    }

    public static Calendar getNextWorkingDay(final Calendar cal) {
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
        case Calendar.FRIDAY:
            cal.add(Calendar.DATE, 3);
            break;
        case Calendar.SATURDAY:
            cal.add(Calendar.DATE, 2);
            break;
        default:
            cal.add(Calendar.DATE, 1);
            break;
        }
        return cal;
    }

    public static Date getNextWorkingDay(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextWorkingDay(cal).getTime();
    }

    public static Date getPreviousMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getPreviousMonth(cal).getTime();
    }

    public static Calendar getPreviousMonth(final Calendar cal) {
        cal.add(Calendar.MONTH, -1);
        return cal;
    }

    public static Calendar getPreviousDay(final Calendar cal) {
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal;
    }

    public static Date getPreviousDay(final Date date) {
        final Calendar c = Calendar.getInstance(); // NOPMD by jli on 15-12-18 PM3:51
        c.setTime(date);
        return getPreviousDay(c).getTime();
    }

    public static Calendar getPreviousWeek(final Calendar cal) {
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        return cal;
    }

    public static Date getPreviousWeek(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getPreviousWeek(cal).getTime();
    }

    public static Calendar getPreviousWorkingDay(final Calendar cal) {
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
        case Calendar.MONDAY:
            cal.add(Calendar.DATE, -3);
            break;
        case Calendar.SUNDAY:
            cal.add(Calendar.DATE, -2);
            break;
        default:
            cal.add(Calendar.DATE, -1);
            break;
        }
        return cal;
    }

    public static Date getPreviousWorkingDay(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getPreviousWorkingDay(cal).getTime();
    }

    public static Calendar parseCalendarByFormat(final String stringDate, final String pattern) throws ParseException {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(parseDateByFormat(stringDate, pattern));
        return cal;
    }

    public static Date parseDateByFormat(final String strDate, final String pattern) throws ParseException {
        SDF.get().applyPattern(pattern);
        return SDF.get().parse(strDate);
    }

    public static String toStringByFormat(final Calendar cal, final String pattern) {
        return toStringByFormat(cal.getTime(), pattern);
    }

    public static String toStringByFormat(final Date date, final String pattern) {
        SDF.get().applyPattern(pattern);
        return SDF.get().format(date);
    }

    public static String toStringByFormat(final String pattern) {
        return toStringByFormat(new Date(), pattern);
    }

    public static String toStringByFormat(final String pattern, final long second) {
        return toStringByFormat(new Date(second), pattern);
    }

    public static String toStringByFormat(final String pattern, final String second) {
        final Date date = new Date(Long.parseLong(second));
        return toStringByFormat(date, pattern);
    }

    public static void main(final String[] args) {
        test();
    }

    static final String PATTERN = "yyyyMMddHHmmssSSS"; // NOPMD by jli on 15-12-18 下午5:54

    private static void test() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (final InterruptedException e) {
            }
            System.out.println(toStringByFormat(new Date(), PATTERN)); // NOPMD by jli on 15-12-18 PM3:46
        }
    }
}
