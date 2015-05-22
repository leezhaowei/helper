package com.zwli.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.zwli.datetime.DateUtils;

/**
 * @author 李赵伟 Create: 10:18:03 AM May 12, 2008
 */
public class Tools {

    private static Map<String, String> citys = null;

    /**
     * KEY：城市 - VALUE：区号
     *
     * @return <code>Map&lt;String, String&gt;</code>
     */
    public static Map<String, String> citys() {
        if (null != citys) {
            return citys;
        }
        String s = "0,全国;010,北京;021,上海;022,天津;023,重庆;0311,石家庄;0312,保定;0314,承德;0310,邯郸;0315,唐山;0335,秦皇岛;0317,沧州;0318,衡水;0316,廊坊;0319,邢台;0313,张家口;0351,太原;0355,长治;0352,大同;0356,晋城;0354,晋中;0357,临汾;0358,吕梁;0349,朔州;0350,忻州;0359,运城;0353,阳泉;0471,呼和浩特;0472,包头;0476,赤峰;0477,鄂尔多斯;0470,呼伦贝尔;0475,通辽;0474,乌兰察布;0473,乌海;0482,兴安盟;024,沈阳;0411,大连;0412,鞍山;0415,丹东;0413,抚顺;0416,锦州;0417,营口;0414,本溪;0428,朝阳;0418,阜新;0429,葫芦岛;0419,辽阳;0427,盘锦;0410,铁岭;0431,长春;0432,吉林;0436,白城;0439,白山;0437,辽源;0434,四平;0438,松原;0435,通化;0451,哈尔滨;0459,大庆;0452,齐齐哈尔;0454,佳木斯;0457,大兴安岭;0456,黑河;0468,鹤岗;0467,鸡西;0453,牡丹江;0464,七台河;0455,绥化;0469,双鸭山;0458,伊春;025,南京;0512,苏州;0519,常州;0518,连云港;0523,泰州;0510,无锡;0516,徐州;0514,扬州;0511,镇江;0517,淮安;0513,南通;0527,宿迁;0515,盐城;0571,杭州;0574,宁波;0573,嘉兴;0575,绍兴;0577,温州;0580,舟山;0572,湖州;0579,金华;0578,丽水;0576,台州;0551,合肥;0553,芜湖;0556,安庆;0552,蚌埠;0558,亳州;0565,巢湖;0566,池州;0550,滁州;0558,阜阳;0559,黄山;0561,淮北;0554,淮南;0564,六安;0555,马鞍山;0557,宿州;0562,铜陵;0563,宣城;0591,福州;0592,厦门;0595,泉州;0597,龙岩;0593,宁德;0599,南平;0594,莆田;0598,三明;0596,漳州;0791,南昌;0797,赣州;0792,九江;0798,景德镇;0796,吉安;0799,萍乡;0793,上饶;0790,新余;0795,宜春;0701,鹰潭;0531,济南;0532,青岛;0631,威海;0535,烟台;0536,潍坊;0538,泰安;0543,滨州;0534,德州;0546,东营;0530,菏泽;0537,济宁;0635,聊城;0539,临沂;0634,莱芜;0633,日照;0533,淄博;0632,枣庄;020,广州;0755,深圳;0756,珠海;0769,东莞;0757,佛山;0752,惠州;0750,江门;0760,中山;0754,汕头;0759,湛江;0768,潮州;0762,河源;0663,揭阳;0668,茂名;0753,梅州;0763,清远;0751,韶关;0660,汕尾;0662,阳江;0766,云浮;0758,肇庆;0898,海口;0898,三亚;0771,南宁;0779,北海;0771,崇左;0770,防城港;0773,桂林;0775,贵港;0778,河池;0774,贺州;0772,柳州;0772,来宾;0777,钦州;0775,玉林;0774,梧州;0371,郑州;0379,洛阳;0378,开封;0374,许昌;0372,安阳;0375,平顶山;0392,鹤壁;0391,焦作;0391,济源;0395,漯河;0377,南阳;0393,濮阳;0398,三门峡;0370,商丘;0373,新乡;0376,信阳;0396,驻马店;0394,周口;027,武汉;0710,襄樊;0719,十堰;0714,黄石;0711,鄂州;0718,恩施;0713,黄冈;0716,荆州;0724,荆门;0722,随州;0717,宜昌;0728,天门;0728,潜江;0728,仙桃;0712,孝感;0715,咸宁;0731,长沙;0730,岳阳;0732,湘潭;0736,常德;0735,郴州;0743,凤凰;0734,衡阳;0745,怀化;0738,娄底;0739,邵阳;0737,益阳;0746,永州;0733,株洲;0744,张家界;028,成都;0816,绵阳;0832,资阳;0827,巴中;0838,德阳;0818,达州;0826,广安;0839,广元;0833,乐山;0830,泸州;0833,眉山;0832,内江;0817,南充;0812,攀枝花;0825,遂宁;0831,宜宾;0835,雅安;0813,自贡;0851,贵阳;0853,安顺;0857,毕节;0856,铜仁;0852,遵义;0871,昆明;0877,玉溪;0878,楚雄;0872,大理;0873,红河;0874,曲靖;0691,西双版纳;0870,昭通;0891,拉萨;0892,日喀则;0983,山南;029,西安;0915,安康;0917,宝鸡;0916,汉中;0914,商洛;0919,铜川;0913,渭南;0910,咸阳;0911,延安;0912,榆林;0931,兰州;0943,白银;0932,定西;0935,金昌;0937,酒泉;0939,陇南;0930,临夏;0933,平凉;0930,庆阳;0935,武威;0938,天水;0936,张掖;0971,西宁;0972,海东;0970,海北;0974,海南;0951,银川;0952,石嘴山;0953,吴忠;0953,中卫;0991,乌鲁木齐;0994,昌吉;0990,克拉玛依;0993,石河子;0995,吐鲁番;0937,嘉峪关;0888,丽江;0837,阿坝藏族羌族自治州;0834,凉山彝族自治州;0898,陵水黎族自治县;0887,迪庆藏族自治州;0875,保山;0719,神农架林区;0570,衢州市;0836,甘孜藏族自治州;0876,文山壮族苗族自治州;0692,德宏傣族景颇族自治州;";
        String[] a = s.split(";");
        String[] b = null;
        String t;
        citys = new HashMap<String, String>();
        for (String element : a) {
            t = element;
            b = t.split(",");
            citys.put(b[1], b[0]);
        }
        return citys;
    }

    /**
     * 根据记录总数和页面显示的数量计算翻页的次数
     *
     * @param tr 记录总数
     * @param pr 页面显示数量
     * @return 页面数量
     */
    public static int countTotalPage(int tr, int pr) {
        if (0 != tr) {
            // 总页数
            if ((tr % pr) == 0) {
                return (tr / pr);
            } else {
                return (tr / pr) + 1;
            }
        }
        return 0;
    }

    /**
     * 计算某一页的起始记录
     *
     * @param cp
     * @param pr
     * @return
     */
    public static int countStartRow(int cp, int pr) {
        return ((1 >= cp) ? 1 : ((cp - 1) * pr + 1));
    }

    /**
     * 请求服务方法：GET
     *
     * @param sUrl 地址
     * @return response
     */
    public static String GET(String sUrl) {
        URL url = null;
        URLConnection uc = null;
        HttpURLConnection huc = null;
        BufferedReader br = null;
        try {
            try {
                url = new URL(sUrl);
                uc = url.openConnection();
                huc = (HttpURLConnection) uc;
                huc.setRequestMethod("GET");
                huc.setDoInput(true);
                huc.setDoOutput(true);
                huc.connect();

                br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
                StringBuffer info = new StringBuffer("");
                String t = null;
                while ((t = br.readLine()) != null) {
                    info.append(t.trim());
                }
                return info.toString();
            } finally {
                if (br != null) {
                    br.close();
                }
                if (huc != null) {
                    huc.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 请求服务方法：POST
     *
     * @param sUrl 地址
     * @param xml 请求内容
     * @return response
     */
    public static String POST(String sUrl, String xml, String encoding) {
        if (!checkStr(encoding)) {
            encoding = "gbk";
        }
        URL url = null;
        URLConnection uc = null;
        HttpURLConnection huc = null;
        DataOutputStream dos = null;
        BufferedReader br = null;
        try {
            try {
                url = new URL(sUrl);
                uc = url.openConnection();
                huc = (HttpURLConnection) uc;

                huc.setRequestMethod("POST");
                huc.setDoInput(true);
                huc.setDoOutput(true);
                huc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                huc.setRequestProperty("Content-Length", xml.getBytes("iso-8859-1").length + "");

                dos = new DataOutputStream(huc.getOutputStream());
                huc.connect();
                dos.write(xml.getBytes("gbk"));
                dos.flush();

                br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
                StringBuffer info = new StringBuffer("");
                String t = null;
                while ((t = br.readLine()) != null) {
                    info.append(t.trim());
                }
                return info.toString();
            } finally {
                if (dos != null) {
                    dos.close();
                }
                if (br != null) {
                    br.close();
                }
                if (huc != null) {
                    huc.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据输入的日期，解析获得sd和ed之间包含的所有月的月初和月末两个日期<br>
     * 返回格式：月初月末,月初月末
     *
     * @param sd YYYYMMDD
     * @param ed YYYYMMDD
     * @return
     */
    public static String getDateForDetailMonth(String pattern, String sd, String ed) {
        String fmt = DateUtils.DateDayFormat1;
        if (checkStr(pattern)) {
            fmt = pattern;
        }
        Calendar sc = DateUtils.parseCalendarByFormat(sd, fmt);
        Calendar ec = DateUtils.parseCalendarByFormat(ed, fmt);

        String a = DateUtils.toStringByFormat(sc, fmt);

        long sl = sc.getTimeInMillis();
        long el = ec.getTimeInMillis();

        List<String> list = new ArrayList<String>();
        list.add(a);
        while (sl < el) {
            sc.add(Calendar.MONTH, 1);
            sl = sc.getTimeInMillis();
            if (sl <= el) {
                a = DateUtils.toStringByFormat(sc, fmt);
                // Out.pln(a);
                list.add(a);
            }
        }
        String t, r;
        Calendar cal;
        StringBuilder d = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            t = list.get(i);
            // Out.pln("Date = " + t);
            cal = DateUtils.parseCalendarByFormat(t, fmt);
            cal = DateUtils.getLastDayOfMonth(cal);
            if (cal.getTimeInMillis() > ec.getTimeInMillis()) {
                ec.add(Calendar.DAY_OF_MONTH, -1);
                r = DateUtils.toStringByFormat(ec, fmt);
            } else {
                r = DateUtils.toStringByFormat(cal, fmt);
            }
            d.append(t + r + ",");
        }
        t = d.substring(0, d.length() - 1);
        return t;
    }

    /**
     * 根据输入的日期，解析获得sd和ed之间包含的所有星期的星期一和星期日两个日期<br>
     * 返回格式：星期一星期日,星期一星期日
     *
     * @param sd YYYYMMDD
     * @param ed YYYYMMDD
     * @return
     */
    public static String getDateForDetailWeek(String sd, String ed) {
        final String fmt = DateUtils.DateDayFormat1;
        Calendar sc = DateUtils.parseCalendarByFormat(sd, fmt);
        Calendar ec = DateUtils.parseCalendarByFormat(ed, fmt);
        long sl = sc.getTimeInMillis();
        long el = ec.getTimeInMillis();

        Calendar nextWeek;
        String n;
        List<String> list = new ArrayList<String>();
        list.add(sd);
        while (sl < el) {
            nextWeek = DateUtils.getNextWeek(sc);
            sl = nextWeek.getTimeInMillis();
            if (sl < el) {
                n = DateUtils.toStringByFormat(nextWeek, fmt);
                list.add(n);
            }
            sc = nextWeek;
        }
        Calendar cal;
        String t, x, y;
        StringBuilder d = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            t = list.get(i);
            cal = DateUtils.parseCalendarByFormat(t, fmt);
            x = t;
            cal.add(Calendar.DAY_OF_WEEK, 6);
            if (cal.getTimeInMillis() < ec.getTimeInMillis()) {
                y = DateUtils.toStringByFormat(cal, fmt);
            } else {
                ec.add(Calendar.DAY_OF_WEEK, -1);
                y = DateUtils.toStringByFormat(ec, fmt);
            }
            d.append(x + y + ",");
        }
        t = d.substring(0, d.length() - 1);
        return t;
    }

    /**
     * 检查字符串
     *
     * @param str
     * @return 如果str!=null && str!=""，返回true；否则返回false。
     */
    public static final boolean checkStr(String str) {
        if (null == checkStr(str, null)) {
            return false;
        }
        return true;
    }

    /**
     * 检查字符串是否为<code>null</code>
     *
     * @param str
     * @return if str is null then return "".
     */
    public static final String checkStr(Object str) {
        if (str instanceof String) {
            return checkStr((String) str, "");
        }
        return "";
    }

    /**
     * 检查字符串是否为<code>null</code>
     *
     * @param str
     * @param show
     * @return if str is null then return show.
     */
    public static final String checkStr(Object str, String show) {
        if (str instanceof String) {
            return checkStr((String) str, show);
        }
        return show;
    }

    /**
     * 检查字符串是否为<code>null</code>
     *
     * @param str 需要检查的参数
     * @param view 如上一个参数为null则返回该参数的值
     * @return if str is null then return view.
     */
    public static final String checkStr(String str, String view) {
        boolean b = (null != str);
        return (b ? str.trim() : view.trim());
    }

    /**
     * 根据Key从属性文件（Properties）中获取Value
     *
     * @param p
     * @param key
     * @return
     */
    public static String get(Properties p, final String key) {
        return encoding(p.getProperty(key));
    }

    /**
     * 字符串编码：<code>iso-8859-1 => gbk</code>
     *
     * @param str
     * @return
     */
    public static String encoding(String str) {
        try {
            return new String(str.getBytes("iso-8859-1"), "gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检查字符串是否是数字
     *
     * @param s
     * @return
     */
    public static boolean isNum(String s) {
        char c;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查字符串中是否全部是大小写字母
     *
     * @param s
     * @return
     */
    public static boolean isLetter(String s) {
        char c;
        int len = s.trim().length();
        String t = s.trim();
        for (int i = 0; i < len; i++) {
            c = t.charAt(i);
            // Out.pln((int)c);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算百分比
     *
     * @param a
     * @param b
     * @return
     */
    public static String baiFenBi(String a, String b) {
        if (null != a && 0 != a.trim().length() && null != b && 0 != b.trim().length()) {
            double m = Double.parseDouble(a);
            double n = Double.parseDouble(b);
            if (0 == m) {
                return "0%";
            }
            if (m == n) {
                return "100%";
            }
            double r = m / n;
            return ((Math.round(r * 100)) + "%");
        }
        return "0%";
    }

    /**
     * @return 生成长度为21位的序列号（前16位为当前时间：YYYYMMDDHHMMSS）
     */
    public static final String generateKey() {
        return DateUtils.toStringByFormat(DateUtils.DateFormatFull) + generateKey(7, true);
    }

    /**
     * 生成唯一的序列号
     *
     * @param size 序列号长度
     * @param num true，为数字序列号；false，为数字加字母
     * @return 唯一的序列号
     */
    public synchronized static final String generateKey(int size, boolean num) {
        if (0 == size) {
            size = 21;
        }
        String chars = "0123456789abcdef";
        if (num) {
            chars = "0123456789";
        }
        Random random = new Random();
        StringBuffer key = new StringBuffer();
        int index = 0;
        int j = chars.length() - 1;
        for (int i = 0; i < size; i++) {
            index = random.nextInt(j) + 1;
            key.append(chars.substring(index, (index + 1)));
        }
        return key.toString();
    }

    /**
     * @param length 序号长度
     * @return 生成序列号
     */
    public synchronized static String getTransactionID(int length) {
        if (0 == length) {
            length = 21;
        }
        String sChars = "0123456789";
        // String sPswd = "";
        StringBuffer id = new StringBuffer();
        int iIndex = 0;
        // int iseq = 0;
        Random x = new Random();
        for (int i = 0; i < length; i++) {
            iIndex = x.nextInt(9) + 1;
            // sPswd = sPswd + sChars.substring(iIndex, (iIndex + 1));
            id.append(sChars.substring(iIndex, (iIndex + 1)));
        }
        // if (iseq >= 999) {
        // iseq = 0;
        // } else {
        // iseq++;
        // }
        // Out.pln(iseq);
        // String sSeq = Integer.toString(iseq);
        // if (sSeq.length() == 1) {
        // sSeq = "00" + sSeq;
        // } else if (sSeq.length() == 2) {
        // sSeq = "0" + sSeq;
        // }
        // return sPswd + sSeq;
        return id.toString();
    }

    /**
     * 判断质数
     *
     * <pre>
     * 一个数，如果只有1和它本身两个因数，这样的数叫做质数（或素数）。例如 2，3，5，7 是质数，而 4，6，8，9 则不是，
     * 后者称为合成数或合数。从这个观点可将整数分为两种，一种叫质数，一种叫合成数。（1不是质数，也不是合数）著名的高
     * 斯「唯一分解定理」说，任何一个整数。可以写成一串质数相乘的积。质数中除2是偶数外，其他都是奇数。
     * </pre>
     *
     * @param n
     * @return true, 质数；false, 非质数
     */
    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
