package com.zwli.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zwli Create: 2007-11-07
 */
public class Dateutils {

    public static final String DateCNFormat = "yyyy年MM月dd日 HH时mm分ss秒";

    public static final String DateDayFormat = "yyyy-MM-dd";

    public static final String DateDayFormat1 = "yyyyMMdd";

    public static final String DateFileFormat = "yyyy-MM-dd_HH-mm-ss";

    public static final String DateFormatFull = "yyyyMMddHHmmss";

    public static final String DateMilliFormat = "yyyy-MM-dd HH:mm:ss,SSS";

    public static final String DateMilliFormat1 = "yyyyMMddHHmmssSSS";

    public static final String DateMinuteFormat = "yyyy-MM-dd HH:mm";

    public static final String DateSecondFormat = "yyyy-MM-dd HH:mm:ss";

    public static final String DateTimeGMT = "EEE, dd MMM yyyy HH:mm:ss z";

    private static final int[] DayArray = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private static final ThreadLocal<SimpleDateFormat> SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static int getDayOfMonth(int year, int month) {
        if (month < 1 || month > 12) {
            return -1;
        }

        int retn = 0;
        if (2 == month) {
            if (LeapYear.isLeapYear(year)) {
                retn = 29;
            } else {
                retn = DayArray[month - 1];
            }

        } else {
            retn = DayArray[month - 1];
        }

        return retn;
    }

    public static Calendar getFirstDayOfMonth(Calendar c) {
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c;
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return getFirstDayOfMonth(c).getTime();
    }

    public static Calendar getFirstDayOfNextMonth(Calendar cal) {
        cal.setTime(getNextMonth(cal.getTime()));
        cal.setTime(getFirstDayOfMonth(cal.getTime()));
        return cal;
    }

    public static Date getFirstDayOfNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfNextMonth(cal).getTime();
    }

    public static Calendar getFirstDayOfNextWeek(Calendar cal) {
        cal.setTime(getNextWeek(cal.getTime()));
        cal.setTime(getFirstDayOfWeek(cal.getTime()));
        return cal;
    }

    public static Date getFirstDayOfNextWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfNextWeek(cal).getTime();
    }

    public static Calendar getFirstDayOfPreviousMonth(Calendar cal) {
        cal.setTime(getPreviousMonth(cal.getTime()));
        cal.setTime(getFirstDayOfMonth(cal.getTime()));
        return cal;
    }

    public static Date getFirstDayOfPreviousMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfPreviousMonth(cal).getTime();
    }

    public static Calendar getFirstDayOfPreviousWeek(Calendar cal) {
        cal.setTime(getPreviousWeek(cal.getTime()));
        cal.setTime(getFirstDayOfWeek(cal.getTime()));
        return cal;
    }

    public static Date getFirstDayOfPreviousWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfPreviousWeek(cal).getTime();
    }

    public static Calendar getFirstDayOfWeek(Calendar cal) {
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            cal.add(Calendar.DATE, -6);
            break;
        case (Calendar.MONDAY):
            cal.add(Calendar.DATE, 0);
            break;
        case (Calendar.TUESDAY):
            cal.add(Calendar.DATE, -1);
            break;
        case (Calendar.WEDNESDAY):
            cal.add(Calendar.DATE, -2);
            break;
        case (Calendar.THURSDAY):
            cal.add(Calendar.DATE, -3);
            break;
        case (Calendar.FRIDAY):
            cal.add(Calendar.DATE, -4);
            break;
        case (Calendar.SATURDAY):
            cal.add(Calendar.DATE, -5);
            break;
        }
        return cal;
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfWeek(cal).getTime();
    }

    public static Calendar getLastDayOfMonth(Calendar cal) {
        switch (cal.get(Calendar.MONTH)) {
        case 0:
            cal.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 1:
            cal.set(Calendar.DAY_OF_MONTH, 28);
            break;
        case 2:
            cal.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 3:
            cal.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 4:
            cal.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 5:
            cal.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 6:
            cal.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 7:
            cal.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 8:
            cal.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 9:
            cal.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 10:
            cal.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 11:
            cal.set(Calendar.DAY_OF_MONTH, 31);
            break;
        }
        // 检查闰年
        if ((cal.get(Calendar.MONTH) == Calendar.FEBRUARY) && (LeapYear.isLeapYear(cal.get(Calendar.YEAR)))) {
            cal.set(Calendar.DAY_OF_MONTH, 29);
        }
        return cal;
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfMonth(cal).getTime();
    }

    public static Calendar getLastDayOfNextMonth(Calendar cal) {
        cal.setTime(getNextMonth(cal.getTime()));
        cal.setTime(getLastDayOfMonth(cal.getTime()));
        return cal;
    }

    public static Date getLastDayOfNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfNextMonth(cal).getTime();
    }

    public static Calendar getLastDayOfNextWeek(Calendar cal) {
        cal.setTime(getNextWeek(cal.getTime()));
        cal.setTime(getLastDayOfWeek(cal.getTime()));
        return cal;
    }

    public static Date getLastDayOfNextWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfNextWeek(cal).getTime();
    }

    public static Calendar getLastDayOfPreviousMonth(Calendar cal) {
        cal.setTime(getPreviousMonth(cal.getTime()));
        cal.setTime(getLastDayOfMonth(cal.getTime()));
        return cal;
    }

    public static Date getLastDayOfPreviousMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfPreviousMonth(cal).getTime();
    }

    public static Calendar getLastDayOfPreviousWeek(Calendar cal) {
        cal.setTime(getPreviousWeek(cal.getTime()));
        cal.setTime(getLastDayOfWeek(cal.getTime()));
        return cal;
    }

    public static Date getLastDayOfPreviousWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfPreviousWeek(cal).getTime();
    }

    public static Calendar getLastDayOfWeek(Calendar cal) {
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.SUNDAY):
            cal.add(Calendar.DATE, 0);
            break;
        case (Calendar.MONDAY):
            cal.add(Calendar.DATE, 6);
            break;
        case (Calendar.TUESDAY):
            cal.add(Calendar.DATE, 5);
            break;
        case (Calendar.WEDNESDAY):
            cal.add(Calendar.DATE, 4);
            break;
        case (Calendar.THURSDAY):
            cal.add(Calendar.DATE, 3);
            break;
        case (Calendar.FRIDAY):
            cal.add(Calendar.DATE, 2);
            break;
        case (Calendar.SATURDAY):
            cal.add(Calendar.DATE, 1);
            break;
        }
        return cal;
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfWeek(cal).getTime();
    }

    public static Calendar getNextDay(Calendar cal) {
        cal.add(Calendar.DATE, 1);
        return cal;
    }

    public static Date getNextDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextDay(cal).getTime();
    }

    public static Calendar getNextMonth(Calendar cal) {
        cal.add(Calendar.MONTH, 1);
        return cal;
    }

    public static Date getNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextMonth(cal).getTime();
    }

    public static Calendar getNextWeek(Calendar cal) {
        cal.add(Calendar.DATE, 7);
        return cal;
    }

    public static Date getNextWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextWeek(cal).getTime();
    }

    public static Calendar getNextWorkingDay(Calendar cal) {
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

    public static Date getNextWorkingDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextWorkingDay(cal).getTime();
    }

    public static Date getPreviousMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getPreviousMonth(cal).getTime();
    }

    public static Calendar getPreviousMonth(Calendar cal) {
        cal.add(Calendar.MONTH, -1);
        return cal;
    }

    public static Calendar getPreviousDay(Calendar cal) {
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal;
    }

    public static Date getPreviousDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return getPreviousDay(c).getTime();
    }

    public static Calendar getPreviousWeek(Calendar cal) {
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        return cal;
    }

    public static Date getPreviousWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getPreviousWeek(cal).getTime();
    }

    public static Calendar getPreviousWorkingDay(Calendar cal) {
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
        case (Calendar.MONDAY):
            cal.add(Calendar.DATE, -3);
            break;
        case (Calendar.SUNDAY):
            cal.add(Calendar.DATE, -2);
            break;
        default:
            cal.add(Calendar.DATE, -1);
            break;
        }
        return cal;
    }

    public static Date getPreviousWorkingDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getPreviousWorkingDay(cal).getTime();
    }

    public static Calendar parseCalendarByFormat(String stringDate, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDateByFormat(stringDate, pattern));
        return cal;
    }

    public static Date parseDateByFormat(String strDate, String pattern) {
        Date date = null;
        SDF.get().applyPattern(pattern);
        try {
            date = SDF.get().parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String toStringByFormat(Calendar cal, String pattern) {
        return toStringByFormat(cal.getTime(), pattern);
    }

    public static String toStringByFormat(Date date, final String pattern) {
        SDF.get().applyPattern(pattern);
        return SDF.get().format(date);
    }

    public static String toStringByFormat(final String pattern) {
        return toStringByFormat(new Date(), pattern);
    }

    public static String toStringByFormat(final String pattern, long second) {
        return toStringByFormat(new Date(second), pattern);
    }

    public static String toStringByFormat(final String pattern, String second) {
        Date date = new Date(Long.parseLong(second));
        return toStringByFormat(date, pattern);
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        String pattern = DateMilliFormat1; // "yyyyMMddHHmmssSSS";

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            System.out.println(toStringByFormat(new Date(), pattern));
        }
    }
}
