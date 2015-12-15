package com.zwli.string;

import java.util.Random;

/**
 * @author zwli 2007-11-05
 */
public class StringUtils {

    public static final String GBK = "GBK";

    public static final String GB2312 = "GB2312";

    public static final String UTF8 = "UTF-8";

    public static final String ISO8859_1 = "ISO-8859-1";

    public static final String UNICODE = "UNICODE";

    public synchronized static String getTransactionID(int len) {
        if (len == 0) {
            len = 18;
        }
        final String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer id = new StringBuffer();
        int index = 0;
        Random x = new Random(); // default seed is time in milliseconds
        final int N = chars.length();
        for (int i = 0; i < len; i++) { // generator six bit random astring;
            index = x.nextInt(N) + 1;
            id.append(chars.substring(index, (index + 1)));
        }
        return id.toString();
    }

    /**
     * 人民币大写转换
     *
     * <pre>
     *  设计方法如下:
     * 	需要注意的问题:
     * 	1.各个阿拉伯数字可以通过一个数组'壹','贰','叁'....表示.
     * 	2.对于大于10000和大于100000000的数字,可能出现'万','亿'字样
     * 	3.对于中间连续为0的数字,正确出现'零'的字样,但是有几种不同的情况需要处理
     * 	4.对于某个段的数字全零的情况,例如,整个万段都是0的情况-100000101,中间的0如何出现
     * 	5.角分的处理,如果不存在角分的话,应该出现'圆整'的字样
     * 	6.整数部分不存在的情况,即只有角分,应该没有'圆'的字样
     *
     * 	设计框架:
     * 	1.把数字转化成字符串处理,使用Java的时候,把一个double类型转化成一个字符串类型很简单,
     * 	调用    String.valueOf(double_var)即可得到,但是有一个问题,当你的数字大于10个位的时候,
     * 	也就是达亿的时候,他会转换成科学计数法的字串,解决方法就是把他转化成整形long.
     *
     * 	2.把数字分割成整数部分和小数部分分别处理,根据上面的方法,我们索性把double乘上100,
     * 	取后两位为小数部分,前面的为整数部分,得到
     * 	long l = (long)(d*100);
     * 	String strVal = String.valueOf(l);
     * 	String head = strVal.substring(0,strVal.length()-2);         //整数部分
     * 	String end = strVal.substring(strVal.length()-2);              //小数部分
     *
     * 	3.我们应该把钱数分成段,每四个一段,实际上得到的是一个二维数组,如下:
     * 	       仟        佰      拾        ''
     * 	''     $4       $3      $2         $1
     * 	万     $8       $7      $6         $5
     * 	亿     $12      $11     $10        $9
     *
     * 	    其中$i表示这个数字的第i个位置的数字,我们并不实际设定二维数组,我们得到的是数字的位置,
     * 	要处理的该产生什么样的表示法,很简单这种处理方式往往就是:设pos表示数字位置,pos/4 在那一个段
     * 	万以下段,万段,亿段.pos%4表示某一个段的段内位置,仟,佰,拾,由于叠加的缘故,即会有千万,百万,千亿等
     * 	出现,因此这种设计是成立的.这里面隐含了一个问题就是,我们当前的处理的最大数字达千亿位,
     * 	更大的数字用这种结构是不妥的,因为可能会有万亿,这时候推荐的想法是把这些设计成单维的数组结构,
     * 	从而取得叠加的表示.
     *
     * 	4.循环处理各个位的过程中,我们可以预想到,零的问题是最难解决的.
     * 	因为我们多个连续的零你只能出现一个表示,更有甚者,当某段全为0时,'零'还不能出现.
     * 	因此这些问题综合考虑得到以下代码.
     * </pre>
     */
    public static String changeToBig(double price) {
        char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
        char[] vunit = { '万', '亿' }; // 段名表示
        char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
        long lPrice = (long) (price * 100); // 转化成整形
        String sPrice = String.valueOf(lPrice); // 转化成字符串
        String head = sPrice.substring(0, sPrice.length() - 2); // 取整数部分
        String rail = sPrice.substring(sPrice.length() - 2); // 取小数部分

        String prefix = ""; // 整数部分转化的结果
        String suffix = ""; // 小数部分转化的结果
        // 处理小数点后面的数
        if (rail.equals("00")) { // 如果小数部分为0
            suffix = "整";
        } else {
            suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
        }
        // 处理小数点前面的数
        char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0') { // 标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0']; // 转化该数字表示
            if (idx > 0) {
                prefix += hunit[idx - 1];
            }
            if (idx == 0 && vidx > 0) {
                prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
            }
        }

        if (prefix.length() > 0) {
            prefix += '圆'; // 如果整数部分存在,则有圆的字样
        }
        return prefix + suffix; // 返回正确表示
    }
}
