package com.zwli.research.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用正则表达式的相关操作<br>
 *
 * @author zwli 2007/11/15
 */
public class RegexUtils {

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
