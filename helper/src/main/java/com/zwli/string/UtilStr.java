package com.zwli.string;

import java.util.Random;

/**
 * @author Create by Jeffery Li 2015/12/16
 */
public class UtilStr {

    public static final String GBK = "GBK";

    public static final String GB2312 = "GB2312";

    public static final String UTF8 = "UTF-8";

    public static final String ISO8859_1 = "ISO-8859-1";

    public static final String UNICODE = "UNICODE";

    public static String uuid() {
        return uuid(27);
    }

    public static String uuid(int len) {
        final String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer uuid = new StringBuffer();
        synchronized (chars) {
            if (len == 0) {
                len = 18;
            }
            int index;
            Random r = new Random(); // default seed is time in milliseconds
            final int N = chars.length();
            for (int i = 0; i < len; i++) { // generator random string
                index = r.nextInt(N) + 1;
                uuid.append(chars.substring(index, (index + 1)));
            }
        }
        return uuid.toString();
    }
}
