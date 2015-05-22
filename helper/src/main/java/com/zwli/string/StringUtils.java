package com.zwli.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.zwli.regex.RegexUtils;

/**
 * 字符串操作方法<br>
 * 创建日期：2007-11-05
 *
 * @author 李赵伟
 */
public class StringUtils {

    public static final String GBK = "GBK";

    public static final String GB2312 = "GB2312";

    public static final String UTF8 = "UTF-8";

    public static final String ISO8859_1 = "ISO-8859-1";

    public static final String UNICODE = "UNICODE";

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
     *
     * @param price
     * @return
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

    /**
     * 以给定的<code> LENGTH </code>分割短信内容，返回分割后的字符串数组<br>
     * 以下是返回内容示例：
     *
     * <pre>
     * sSMS = &quot;abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz&quot;;
     * 1. 分割后的短信个数 &lt; 10
     * LENGTH = 10;
     * (1/6)abcdefghij
     * (2/6)klmnopqrst
     * (3/6)uvwxyzabcd
     * (4/6)efghijklmn
     * (5/6)opqrstuvwx
     * (6/6)yz = 7
     *
     * 2. 分割后的短信个数 &gt;= 10
     * LENGTH = 5;
     * (1/15)abcd
     * (2/15)efgh
     * (3/15)ijkl
     * ......
     * (13/15)tuv
     * (14/15)wxy
     * (15/15)z
     * </pre>
     *
     * @param sSMS 短信内容
     * @param LENGTH 每条短信的长度
     * @return 一组短信内容
     */
    public static String[] smsSplit(String sSMS, final int LENGTH) {
        int iContentLength = sSMS.trim().length();
        if (LENGTH >= iContentLength) {
            // 短信内容的长度小于等于LENGTH，不用分割直接返回
            return new String[] { sSMS };
        }

        final int iSMS_Num_9 = 9; // 分割9条短信
        int iSum = LENGTH * iSMS_Num_9; // 短信条数等于9的总长度，
        int iLength = 0;
        if (iContentLength > iSum) {
            // 源短信的长度大于iSum，表示源短信可以被分割的数量超过10条
            iLength = LENGTH - 1;
            iSum = iLength * iSMS_Num_9;
        } else if (iContentLength < iSum) {
            // 源短信的长度小于iSum，表示源短信可以被分割的数量小于等于9条
            iLength = LENGTH;
            iSum = iContentLength;
        }

        // 短信个数小于10
        String sSMS9 = sSMS.trim().substring(0, iSum);
        String[] aSMS9 = smsSplitByLength(sSMS9, iLength);

        if (iContentLength == iSum) {
            List<String> oList = new ArrayList<String>();
            String sTemp;
            final int iSMSNum = aSMS9.length;
            for (int i = 0; i < aSMS9.length; i++) {
                sTemp = "(" + (i + 1) + "/" + iSMSNum + ")" + aSMS9[i];
                oList.add(sTemp);
            }
            String[] aSMS = oList.toArray(new String[0]);
            return aSMS;
        }

        String sTemp1 = aSMS9[aSMS9.length - 1];
        String sTemp2 = aSMS9[aSMS9.length - 2];

        String sSMS10 = null;
        String[] aSMS10 = null;
        if (sTemp1.length() != sTemp2.length()) {
            String[] aTemp = aSMS9;
            aSMS9 = new String[aTemp.length - 1];
            System.arraycopy(aTemp, 0, aSMS9, 0, aTemp.length - 1);
            sSMS10 = sTemp1 + sSMS.trim().substring(iSum);
            aSMS10 = smsSplitByLength(sSMS10, iLength - 1);
        } else {
            sSMS10 = sSMS.trim().substring(iSum);
            aSMS10 = smsSplitByLength(sSMS10, iLength - 1);
        }

        List<String> oList = new ArrayList<String>();
        String sTemp;
        final int iSMSNum = aSMS9.length + aSMS10.length;
        for (int i = 0; i < aSMS9.length; i++) {
            sTemp = "(" + (i + 1) + "/" + iSMSNum + ")" + aSMS9[i];
            oList.add(sTemp);
        }
        for (int i = 0; i < (aSMS10.length); i++) {
            sTemp = "(" + (i + 10) + "/" + iSMSNum + ")" + aSMS10[i];
            oList.add(sTemp);
        }
        String[] aSMS = oList.toArray(new String[0]);
        return aSMS;
    }

    /**
     * 以给定的<code> LENGTH </code>分割短信内容，返回分割后的字符串数组<br>
     * <b>注意：返回数组中的最后一个元素的字符串长度不一定等于<code> LENGTH </code>，<br>
     * 即不考虑<code> sSMS.length % LENGTH != 0 </code></b>
     *
     * @param sSMS 短信内容
     * @param LENGTH 每条短信的长度
     * @return 一组短信内容
     */
    private static String[] smsSplitByLength(String sSMS, final int LENGTH) {
        int iContentLength = sSMS.trim().length();
        String sTemp;
        int x = 0;
        int y = 0;
        List<String> l = new ArrayList<String>();
        while (true) {
            y = x + LENGTH;
            if (y > iContentLength) {
                y = iContentLength;
            }
            sTemp = sSMS.substring(x, y);
            l.add(sTemp);
            x = y;
            if (x == iContentLength) {
                break;
            }
        }
        return l.toArray(new String[0]);
    }

    /**
     * 把十六进制表示的数字解析成为十进制数
     *
     * @param sn 十六进制字符串数字
     * @return
     */
    public static int hexToDec(String sn) {
        if (null == sn || 0 == sn.trim().length()) {
            throw new IllegalArgumentException("接收的参数 “" + sn + "” 不是十六进制表示形式！");
        }

        final String P = "0x";
        if (sn.toLowerCase().startsWith(P)) {
            final String regex = "[^\\da-fA-F]";
            int i = RegexUtils.matcheRegexp(sn.substring(2), regex, false);
            if (0 != i) {
                throw new IllegalArgumentException("接收的参数 “" + sn + "” 不是十六进制表示形式！");
            } else {
                char[] cs = sn.substring(2).toUpperCase().toCharArray();
                char c;
                for (int j = 0; j < cs.length; j++) {
                    c = cs[j];
                    i += toDec(c, (cs.length - j - 1));
                }
                // Out.pln("SRC: " + sn);
                // Out.pln("Dec: " + i);
                // Out.pln("Hex: 0x" + Integer.toHexString(i).toUpperCase());
                return i;
            }
        }
        return 0;
    }

    private static int toDec(char c, int i) {
        final double N = 16.0;
        int n = getNum(c);
        int r = (int) (n * Math.pow(N, i));
        return r;
    }

    private static int getNum(char c) {
        // 字符F转换成为数字为70
        if (c > 70) {
            return 0;
        }

        int i = 0;
        switch (c) {
        case 'A':
            i = 10;
            break;
        case 'B':
            i = 11;
            break;
        case 'C':
            i = 12;
            break;
        case 'D':
            i = 13;
            break;
        case 'E':
            i = 14;
            break;
        case 'F':
            i = 15;
            break;
        case 'a':
            i = 10;
            break;
        case 'b':
            i = 11;
            break;
        case 'c':
            i = 12;
            break;
        case 'd':
            i = 13;
            break;
        case 'e':
            i = 14;
            break;
        case 'f':
            i = 15;
            break;
        }
        if (0 == i) {
            // 字符0转换成为数字为48
            return c - 48;
        } else {
            return i;
        }
    }

    /**
     * 解析数字格式的字符串，得到<code> int </code>
     *
     * @param s
     * @return 数字，<code> int </code>
     */
    public static int parseInt(String s) {
        int count = s.length();
        char a;
        int r = 0, n = 0;
        for (int i = 0; i < count; i++) {
            a = s.charAt(i);
            n = a - 48;
            r += n * getNum(count - i - 1);
        }
        return r;
    }

    /**
     * 判断当前数字在哪一位上：个十百千万……
     */
    private static int getNum(int c) {
        if (0 == c) {
            return 1;
        }
        int r = 1;
        for (int i = 0; i < c; i++) {
            r *= 10;
        }
        return r;
    }

    /**
     * 字符：全角转半角 <br>
     * <br>
     * 注意：<br>
     * 如果<code> str = null </code>或空串，则返回<code> null </code>
     *
     * <pre>
     *  全角空格为12288，半角空格为32
     * 	其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     * </pre>
     *
     * @param str 全角字符
     * @return 半角字符
     * @throws UnsupportedEncodingException
     */
    public static String sbcTodbc(String str) throws UnsupportedEncodingException {
        if (null == str || 0 == str.trim().length()) {
            return null;
        }

        // StringBuilder r = new StringBuilder();
        // String t = "";
        // byte[] b = null;
        //
        // for (int i = 0; i < str.length(); i++) {
        // t = str.substring(i, i + 1);
        // b = t.getBytes(UNICODE);
        //
        // if (null != b) {
        // if (b[3] == -1) {
        // b[2] = (byte) (b[2] + 32);
        // b[3] = 0;
        //
        // // dbc = dbc + new String(b, UNICODE);
        // r.append(new String(b, UNICODE));
        // } else
        // // dbc = dbc + t;
        // r.append(t);
        // } else {
        // // dbc = str;
        // r.append(str);
        // }
        // }
        // // return dbc;
        // return r.toString();

        final int n = 65248;
        // int c;
        // int t;
        // char[] codes = new char[str.length()];
        // for (int i = 0; i < str.length(); i++) {
        // c = (int) str.charAt(i);
        // if (256 < c) {
        // t = c - n;
        // if (0 <= t)
        // codes[i] = (char) t;
        // else
        // codes[i] = (char) c;
        // } else
        // codes[i] = (char) c;
        // }
        // return String.valueOf(codes);

        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - n);
            }
        }
        return new String(c);
    }

    /**
     * 以<code> regex </code>分割字符串，获得字符串数组 <br>
     * <br>
     * 注意：<br>
     * 如果<code> str = null </code>或空串，则返回<code> null </code><br>
     * 如果<code> regex = null </code>或空串，则返回数组大小等于1，元素为<code> str </code>
     *
     * @param str 要分割的字符串
     * @param regex 分割条件
     * @return 分割后的字符串数组
     */
    public static String[] split(String str, final String regex) {
        if (null == str || 0 == str.trim().length()) {
            return null;
        }
        if (null == regex || 0 == regex.trim().length()) {
            return new String[] { str };
        }

        StringTokenizer st = new StringTokenizer(str, regex);
        String a[] = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            a[i] = st.nextToken();
            i++;
        }
        return a;
    }

    /**
     * 编码字符串
     *
     * @param st
     * @param decodingCharset 使用指定字符集解码
     * @return 异常，返回null；否则返回编码后的字符串
     */
    public static String toGBKString(String st, final String decodingCharset) {
        try {
            if (null == decodingCharset) {
                return new String(st.getBytes(), GBK);
            } else {
                return new String(st.getBytes(decodingCharset), GBK);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串替换方法
     *
     * @param src 原字符串
     * @param rep 被替换的内容
     * @param newstr 新内容
     * @return 新字符串
     */
    public static String replace(String src, String rep, String newstr) {
        int i = 0, j = 0;
        i = src.indexOf(rep);
        StringBuilder sb = new StringBuilder();
        while (j < src.length()) {
            if (i != -1) {
                sb.append(src.substring(j, i) + newstr);
                j = i + rep.length();
                i = src.indexOf(rep, j);
            } else {
                sb.append(src.substring(j));
                break;
            }
        }
        return sb.toString().trim();
    }

    // /**
    // * 将字符串转换成二进制字符串，以空格相隔
    // *
    // * @param str
    // * @return 字符串的二进制形式
    // */
    // public static String toBinary(String str) {
    // char[] strChar = str.toCharArray();
    // String result = null;
    // StringBuilder res = new StringBuilder();
    // for (int i = 0; i < strChar.length; i++) {
    // result = Integer.toBinaryString(strChar[i]) + " ";
    // res.append(result);
    // }
    // return res.toString();
    // }
    //
    // /**
    // * 将二进制字符串转换成<code> Unicode </code>字符串
    // *
    // * @param binstr
    // * @return 新字符串
    // */
    // public static String toString(String binstr) {
    // String[] tempStr = binstr.split(" ");
    // char[] tempChar = new char[tempStr.length];
    // for (int i = 0; i < tempStr.length; i++) {
    // tempChar[i] = toChar(tempStr[i]);
    // }
    // return String.valueOf(tempChar);
    // }
    //
    // /**
    // * 将二进制字符串转换为char
    // *
    // * @param binstr
    // * @return
    // */
    // private static char toChar(String binstr) {
    // int[] temp = binStringToIntArray(binstr);
    // int sum = 0;
    // int t = 0;
    // for (int i = 0; i < temp.length; i++) {
    // t = temp[temp.length - 1 - i] << i;
    // sum += t;
    // }
    // return (char) sum;
    // }
    //
    // /**
    // * 将二进制字符串转换成<code> int </code>数组
    // *
    // * @param binstr
    // * @return
    // */
    // private static int[] binStringToIntArray(String binstr) {
    // char[] temp = binstr.toCharArray();
    // int[] result = new int[temp.length];
    // for (int i = 0; i < temp.length; i++) {
    // result[i] = temp[i] - 48;
    // }
    // return result;
    // }
}
