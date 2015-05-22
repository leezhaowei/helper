package com.zwli.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用正则表达式的相关操作<br>
 * 创建日期：2007-11-15
 *
 * @author 李赵伟
 */
public class RegexUtils {

    /**
     * 使用给定的正则表达式匹配输入的次数
     *
     * @param str 输入
     * @param regex 正则表达式
     * @param print 打印开关
     * @return 匹配次数
     */
    public static final int matcheRegexp(String str, String regex, boolean print) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            if (print) {
                System.out.println(m.group());
            }
            i++;
        }
        return i;
    }
}
