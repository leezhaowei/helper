package com.zwli.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 格式化、解析日期、时间<br>
 * <br>
 * <table width="400px">
 * <tr bgcolor="#ccccff">
 * <th align="left">格式</th>
 * <th align="left">结果</th>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td><code>yyyy-MM-dd</code></td>
 * <td>2007-11-07</td>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td>yyyy-MM-dd HH:mm:ss</td>
 * <td>2007-11-07 16:52:29</td>
 * </tr>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td>......</td>
 * <td>......</td>
 * </tr>
 * </table>
 * <br>
 * <br>
 * 解析字符串格式的日期获得<code> java.util.Date </code>类型 <br>
 * <br>
 * <table width="400px">
 * <tr bgcolor="#ccccff">
 * <td>stringDate</td>
 * <td>pattern</td>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td>2007-12-11</td>
 * <td>yyyy-MM-dd</td>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td>2007-12-12 15:30</td>
 * <td>yyyy-MM-dd HH:mm</td>
 * </tr>
 * <tr bgcolor="#ccccff">
 * <td>......</td>
 * <td>......</td>
 * </tr>
 * </table>
 *
 * @author 李赵伟 Create: 2007-11-07
 */
public class DateUtils {

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

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static int getDayOfMonth(int year, int month) {
        if (month < 1 || month > 12) {
            return -1;
        }

        int retn = 0;
        if (2 == month) {
            if (isLeapYear(year)) {
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

    /**
     * 获取指定日期的所处月份的第一天
     *
     * @param date
     *            指定日期。
     * @return 指定日期的所处月份的第一天
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return getFirstDayOfMonth(c).getTime();
    }

    /**
     * 获取指定日期的下一个月的第一天
     *
     * @param cal
     *            指定日历。
     * @return 指定日期的下一个月的第一天
     */
    public static Calendar getFirstDayOfNextMonth(Calendar cal) {
        cal.setTime(getNextMonth(cal.getTime()));
        cal.setTime(getFirstDayOfMonth(cal.getTime()));
        return cal;
    }

    /**
     * 获取指定日期的下一个月的第一天
     *
     * @param date
     *            指定日期。
     * @return 指定日期的下一个月的第一天
     */
    public static Date getFirstDayOfNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfNextMonth(cal).getTime();
    }

    /**
     * 获取指定日期的下一个星期的第一天
     *
     * @param cal
     *            指定日历。
     * @return 指定日期的下一个星期的第一天
     */
    public static Calendar getFirstDayOfNextWeek(Calendar cal) {
        cal.setTime(getNextWeek(cal.getTime()));
        cal.setTime(getFirstDayOfWeek(cal.getTime()));
        return cal;
    }

    /**
     * 获取指定日期的下一个星期的第一天
     *
     * @param date
     *            指定日期。
     * @return 指定日期的下一个星期的第一天
     */
    public static Date getFirstDayOfNextWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfNextWeek(cal).getTime();
    }

    /**
     * 获取指定日期的上一个月的第一天
     *
     * @param cal
     * @return
     */
    public static Calendar getFirstDayOfPreviousMonth(Calendar cal) {
        cal.setTime(getPreviousMonth(cal.getTime()));
        cal.setTime(getFirstDayOfMonth(cal.getTime()));
        return cal;
    }

    /**
     * 获取指定日期的上一个月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfPreviousMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfPreviousMonth(cal).getTime();
    }

    /**
     * 获取指定日期的上一个星期的星期一
     *
     * @param cal
     * @return
     */
    public static Calendar getFirstDayOfPreviousWeek(Calendar cal) {
        cal.setTime(getPreviousWeek(cal.getTime()));
        cal.setTime(getFirstDayOfWeek(cal.getTime()));
        return cal;
    }

    /**
     * 获取指定日期的上一个星期的星期一
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfPreviousWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfPreviousWeek(cal).getTime();
    }

    /**
     * 获取指定日期的所处星期的第一天<br>
     *
     * @param cal
     *            指定日历。
     * @return 指定日期的所处星期的第一天
     */
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

    /**
     * 获取指定日期的所处星期的第一天<br>
     *
     * @param date
     *            指定日期。
     * @return 指定日期的所处星期的第一天
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getFirstDayOfWeek(cal).getTime();
    }

    /**
     * 获取指定日期的所处月份的最后一天<br>
     * <br>
     * 1. 如果date在1月，则为31日<br>
     * 2. 如果date在2月，则为28日；如果date在闰年的2月，则为29日<br>
     * 3. 如果date在3月，则为31日<br>
     * 4. 如果date在4月，则为30日<br>
     * 5. 如果date在5月，则为31日<br>
     * 6. 如果date在6月，则为30日<br>
     * 7. 如果date在7月，则为31日<br>
     * 8. 如果date在8月，则为31日<br>
     * 9. 如果date在9月，则为30日<br>
     * 10. 如果date在10月，则为31日<br>
     * 11. 如果date在11月，则为30日<br>
     * 12. 如果date在12月，则为31日
     *
     * @param cal
     *            指定日历。
     * @return 指定日期的所处月份的最后一天
     */
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
        if ((cal.get(Calendar.MONTH) == Calendar.FEBRUARY) && (isLeapYear(cal.get(Calendar.YEAR)))) {
            cal.set(Calendar.DAY_OF_MONTH, 29);
        }
        return cal;
    }

    /**
     * 获取指定日期的所处月份的最后一天
     *
     * @param date
     *            指定日期。
     * @return 指定日期的所处月份的最后一天
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfMonth(cal).getTime();
    }

    /**
     * 获取指定日期的下一个月的最后一天
     *
     * @param cal
     *            指定日期。
     * @return 指定日期的下一个月的最后一天
     */
    public static Calendar getLastDayOfNextMonth(Calendar cal) {
        cal.setTime(getNextMonth(cal.getTime()));
        cal.setTime(getLastDayOfMonth(cal.getTime()));
        return cal;
    }

    /**
     * 获取指定日期的下一个月的最后一天
     *
     * @param date
     *            指定日期。
     * @return 指定日期的下一个月的最后一天
     */
    public static Date getLastDayOfNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfNextMonth(cal).getTime();
    }

    /**
     * 获取指定日期的下一个星期的最后一天
     *
     * @param cal
     *            指定日期。
     * @return 指定日期的下一个星期的最后一天
     */
    public static Calendar getLastDayOfNextWeek(Calendar cal) {
        cal.setTime(getNextWeek(cal.getTime()));
        cal.setTime(getLastDayOfWeek(cal.getTime()));
        return cal;
    }

    /**
     * 获取指定日期的下一个星期的最后一天
     *
     * @param date
     *            指定日期。
     * @return 指定日期的下一个星期的最后一天
     */
    public static Date getLastDayOfNextWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfNextWeek(cal).getTime();
    }

    /**
     * 获取指定日期的上一个月的最后一天
     *
     * @param cal
     * @return
     */
    public static Calendar getLastDayOfPreviousMonth(Calendar cal) {
        cal.setTime(getPreviousMonth(cal.getTime()));
        cal.setTime(getLastDayOfMonth(cal.getTime()));
        return cal;
    }

    /**
     * 获取指定日期的上一个月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfPreviousMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfPreviousMonth(cal).getTime();
    }

    /**
     * 获取指定日期的上一个星期的星期日
     *
     * @param cal
     * @return
     */
    public static Calendar getLastDayOfPreviousWeek(Calendar cal) {
        cal.setTime(getPreviousWeek(cal.getTime()));
        cal.setTime(getLastDayOfWeek(cal.getTime()));
        return cal;
    }

    /**
     * 获取指定日期的上一个星期的星期日
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfPreviousWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfPreviousWeek(cal).getTime();
    }

    /**
     * 获取指定日期的所处星期的最后一天<br>
     *
     * @param cal
     *            指定日历。
     * @return 指定日期的所处星期的最后一天
     */
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

    /**
     * 获取指定日期的所处星期的最后一天
     *
     * @param date
     *            指定日期。
     * @return 指定日期的所处星期的最后一天
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getLastDayOfWeek(cal).getTime();
    }

    /**
     * 获取指定日期的下一天
     *
     * @param cal
     *            指定日历。
     * @return 指定日期的下一天
     */
    public static Calendar getNextDay(Calendar cal) {
        cal.add(Calendar.DATE, 1);
        return cal;
    }

    /**
     * 获取指定日期的下一天
     *
     * @param date
     *            指定日期。
     * @return 指定日期的下一天
     */
    public static Date getNextDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextDay(cal).getTime();
    }

    /**
     * 获取指定日期的下一个月
     *
     * @param cal
     *            指定日历。
     * @return 指定日期的下一个月
     */
    public static Calendar getNextMonth(Calendar cal) {
        cal.add(Calendar.MONTH, 1);
        return cal;
    }

    /**
     * 获取指定日期的下一个月
     *
     * @param date
     *            指定日期。
     * @return 指定日期的下一个月
     */
    public static Date getNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextMonth(cal).getTime();
    }

    /**
     * 获取指定日期的下一个星期
     *
     * @param cal
     *            指定日历。
     * @return 指定日期的下一个星期
     */
    public static Calendar getNextWeek(Calendar cal) {
        cal.add(Calendar.DATE, 7);
        return cal;
    }

    /**
     * 获取指定日期的下一个星期
     *
     * @param date
     *            指定日期。
     * @return 指定日期的下一个星期
     */
    public static Date getNextWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextWeek(cal).getTime();
    }

    /**
     * 获取指定日期的后一个工作日<br>
     * <br>
     * 1.如果date是星期五，则加3天<br>
     * 2.如果date是星期六，则加2天, 否则加1天
     *
     * @param cal
     *            指定日历。
     * @return 指定日期的后一个工作日
     */
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

    /**
     * 获取指定日期的后一个工作日
     *
     * @param date
     *            指定日期。
     * @return 指定日期的后一个工作日
     */
    public static Date getNextWorkingDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getNextWorkingDay(cal).getTime();
    }

    /**
     * 获取指定日期的上一个月
     *
     * @param date
     * @return
     */
    public static Date getPreviousMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getPreviousMonth(cal).getTime();
    }

    /**
     * 获取指定日期的上一个月
     *
     * @param cal
     * @return
     */
    public static Calendar getPreviousMonth(Calendar cal) {
        cal.add(Calendar.MONTH, -1);
        return cal;
    }

    /**
     * 获取指定日期的前一天
     *
     * @param cal
     * @return
     */
    public static Calendar getPreviousDay(Calendar cal) {
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal;
    }

    /**
     * 获取指定日期的前一天
     *
     * @param date
     * @return
     */
    public static Date getPreviousDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return getPreviousDay(c).getTime();
    }

    /**
     * 获取指定日期的前一个星期
     *
     * @param cal
     * @return
     */
    public static Calendar getPreviousWeek(Calendar cal) {
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        return cal;
    }

    /**
     * 获取指定日期的前一个星期
     *
     * @param date
     * @return
     */
    public static Date getPreviousWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getPreviousWeek(cal).getTime();
    }

    /**
     * 得到指定日期的前一个工作日
     *
     * @param cal
     *            指定日历。
     * @return 指定日期的前一个工作日
     */
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

    /**
     * 得到指定日期的前一个工作日<br>
     * <br>
     * 1.如果date是星期日，则减3天<br>
     * 2.如果date是星期六，则减2天, 否则减1天
     *
     * @param date
     *            指定日期。
     * @return 指定日期的前一个工作日
     */
    public static Date getPreviousWorkingDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getPreviousWorkingDay(cal).getTime();
    }

    /**
     * 判断指定日期的年份是否是闰年
     *
     * @param cal
     *            指定日历
     * @return 是否闰年
     */
    public static boolean isLeapYear(Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    /**
     * 判断指定日期的年份是否是闰年
     *
     * @param date
     *            指定日期。
     * @return 是否闰年
     */
    public static boolean isLeapYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return isLeapYear(cal);
    }

    /**
     * 判断闰年<br>
     * <br>
     * 1. 被400整除是闰年<br>
     * 2. 能被4整除同时不能被100整除则是闰年
     *
     * @param year
     *            指定的年
     * @return 是否闰年
     */
    public static boolean isLeapYear(int year) {
        int r = year % 400;
        if (0 == r) {
            return true;
        }

        r = year % 4;
        int i = year % 100;
        return ((0 == r && 0 != i) ? true : false);
    }

    /**
     * 解析字符串格式的日期获得<code> java.util.Calendar </code>类型
     *
     * @param stringDate
     *            字符串格式的日期
     * @param pattern
     *            该日期的字符串表示形式
     * @return
     */
    public static Calendar parseCalendarByFormat(String stringDate, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDateByFormat(stringDate, pattern));
        return cal;
    }

    /**
     * 解析字符串格式的日期获得<code> java.util.Date </code>类型 <br>
     * <br>
     * <table width="400px">
     * <tr bgcolor="#ccccff">
     * <td>stringDate</td>
     * <td>pattern</td>
     * </tr>
     * <tr bgcolor="#ccccff">
     * <td>2007-12-11</td>
     * <td>yyyy-MM-dd</td>
     * </tr>
     * <tr bgcolor="#ccccff">
     * <td>2007-12-12 15:30</td>
     * <td>yyyy-MM-dd HH:mm</td>
     * </tr>
     * </table>
     *
     * @param stringDate
     *            字符串格式的日期
     * @param pattern
     *            该日期的字符串表示形式
     * @return
     */
    public static Date parseDateByFormat(String stringDate, String pattern) {
        Date date = null;
        sdf.applyPattern(pattern);
        try {
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化后的当前日期
     *
     * @param cal
     * @param pattern
     * @return
     */
    public static String toStringByFormat(Calendar cal, String pattern) {
        return toStringByFormat(cal.getTime(), pattern);
    }

    /**
     * 格式化后的当前日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String toStringByFormat(Date date, final String pattern) {
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    /**
     * 格式化后的当前日期<br>
     * <br>
     * <table>
     * <tr bgcolor="#ccccff">
     * <th align="left">格式</th>
     * <th align="left">结果</th>
     * </tr>
     * <tr bgcolor="#ccccff">
     * <td><code>yyyy-MM-dd</code></td>
     * <td>2007-11-07</td>
     * </tr>
     * <tr bgcolor="#ccccff">
     * <td>yyyy-MM-dd HH:mm:ss</td>
     * <td>2007-11-07 16:52:29</td>
     * </tr>
     * </table>
     *
     * @param pattern
     *            日期格式
     * @return 格式化后的当前日期
     */
    public static String toStringByFormat(final String pattern) {
        return toStringByFormat(new Date(), pattern);
    }

    /**
     * 格式化后的当前日期
     *
     * @param pattern
     *            日期格式
     * @param second
     *            时间毫秒数，<code> long </code>
     * @return 格式化后的当前日期
     */
    public static String toStringByFormat(final String pattern, long second) {
        return toStringByFormat(new Date(second), pattern);
    }

    /**
     * 格式化后的当前日期
     *
     * @param pattern
     *            日期格式
     * @param second
     *            时间毫秒数，<code> String </code>
     * @return 格式化后的当前日期
     */
    public static String toStringByFormat(final String pattern, String second) {
        Date date = new Date(Long.parseLong(second));
        return toStringByFormat(date, pattern);
    }

    /**
     * 测试
     */
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
