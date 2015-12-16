package com.zwli.datetime;

/**
 * @author Create by Jeffery Li 2015/12/16
 */
public class LeapYear {

    /**
     * validate leap year<br>
     * 1) year % 400 == 0<br>
     * 2) year % 4 == 0 && year % 100 != 0
     */
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        return ((year % 4 == 0 && year % 100 != 0) ? true : false);
    }
}
