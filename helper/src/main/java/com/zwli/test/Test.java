package com.zwli.test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // testChar();
        // test();
        // testTransactionID();
        // testPrime();
        a();
        // b();
    }

    static double rad(double d) {
        d = d * Math.PI / 180.0;
        // System.out.println("rad = " + d);
        return d;
    }

    static void b() {
        String a = "minx=106.38116455078125&miny=29.42595020372733&maxx=106.71514892578125&maxy=29.684246166853697";
        String b = "重庆,29.5549144, 106.548425";
        account(a, b);
    }

    static void account(String a, String b) {
        String[] c = a.split("&");
        String[] d = b.split(",");
        System.out.println(Arrays.toString(c));
        System.out.println(Arrays.toString(d));

        double x = Double.parseDouble(d[2]), y = Double.parseDouble(d[1]);
        System.out.println("x = " + x + ", y = " + y);
        double minx = Double.parseDouble(c[0].split("=")[1]);
        double miny = Double.parseDouble(c[1].split("=")[1]);
        double maxx = Double.parseDouble(c[2].split("=")[1]);
        double maxy = Double.parseDouble(c[3].split("=")[1]);
        System.out.println("minx = " + minx + ", miny = " + miny + ", maxx = " + maxx + ", maxy = " + maxy);
        double subx = x - minx, suby = y - miny, addx = maxx - x, addy = maxy - y;
        System.out.println("subx = " + subx + ", suby = " + suby + ", addx = " + addx + ", addy = " + addy);
        // System.out.println("rminx = " + rad(minx)/4 + ", rminy = " +
        // rad(miny)/4
        // + ", rmaxx = " + rad(maxx)/4 + ", rmaxy = " + rad(maxy)/4);
        System.out.println("================================================");
    }

    static void a() {
        String s = "0,全国;010,北京;021,上海;022,天津;023,重庆;0311,石家庄;0312,保定;0314,承德;0310,邯郸;0315,唐山"
                + ";0335,秦皇岛;0317,沧州;0318,衡水;0316,廊坊;0319,邢台;0313,张家口;0351,太原;0355,长治;0352,大同"
                + ";0356,晋城;0354,晋中;0357,临汾;0358,吕梁;0349,朔州;0350,忻州;0359,运城;0353,阳泉;0471,呼和浩特"
                + ";0472,包头;0476,赤峰;0477,鄂尔多斯;0470,呼伦贝尔;0475,通辽;0474,乌兰察布;0473,乌海;0482,兴安盟"
                + ";024,沈阳;0411,大连;0412,鞍山;0415,丹东;0413,抚顺;0416,锦州;0417,营口;0414,本溪;0428,朝阳"
                + ";0418,阜新;0429,葫芦岛;0419,辽阳;0427,盘锦;0410,铁岭;0431,长春;0432,吉林;0436,白城;0439,白山"
                + ";0437,辽源;0434,四平;0438,松原;0435,通化;0451,哈尔滨;0459,大庆;0452,齐齐哈尔;0454,佳木斯;0457,大兴安岭"
                + ";0456,黑河;0468,鹤岗;0467,鸡西;0453,牡丹江;0464,七台河;0455,绥化;0469,双鸭山;0458,伊春;025,南京;0512,苏州"
                + ";0519,常州;0518,连云港;0523,泰州;0510,无锡;0516,徐州;0514,扬州;0511,镇江;0517,淮安;0513,南通;0527,宿迁"
                + ";0515,盐城;0571,杭州;0574,宁波;0573,嘉兴;0575,绍兴;0577,温州;0580,舟山;0572,湖州;0579,金华;0578,丽水;0576,台州"
                + ";0551,合肥;0553,芜湖;0556,安庆;0552,蚌埠;0558,亳州;0565,巢湖;0566,池州;0550,滁州;0558,阜阳;0559,黄山;0561,淮北"
                + ";0554,淮南;0564,六安;0555,马鞍山;0557,宿州;0562,铜陵;0563,宣城;0591,福州;0592,厦门;0595,泉州;0597,龙岩;0593,宁德"
                + ";0599,南平;0594,莆田;0598,三明;0596,漳州;0791,南昌;0797,赣州;0792,九江;0798,景德镇;0796,吉安;0799,萍乡;0793,上饶"
                + ";0790,新余;0795,宜春;0701,鹰潭;0531,济南;0532,青岛;0631,威海;0535,烟台;0536,潍坊;0538,泰安;0543,滨州;0534,德州"
                + ";0546,东营;0530,菏泽;0537,济宁;0635,聊城;0539,临沂;0634,莱芜;0633,日照;0533,淄博;0632,枣庄;020,广州;0755,深圳"
                + ";0756,珠海;0769,东莞;0757,佛山;0752,惠州;0750,江门;0760,中山;0754,汕头;0759,湛江;0768,潮州;0762,河源;0663,揭阳"
                + ";0668,茂名;0753,梅州;0763,清远;0751,韶关;0660,汕尾;0662,阳江;0766,云浮;0758,肇庆;0898,海口;0898,三亚;0771,南宁"
                + ";0779,北海;0771,崇左;0770,防城港;0773,桂林;0775,贵港;0778,河池;0774,贺州;0772,柳州;0772,来宾;0777,钦州;0775,玉林"
                + ";0774,梧州;0371,郑州;0379,洛阳;0378,开封;0374,许昌;0372,安阳;0375,平顶山;0392,鹤壁;0391,焦作;0391,济源;0395,漯河"
                + ";0377,南阳;0393,濮阳;0398,三门峡;0370,商丘;0373,新乡;0376,信阳;0396,驻马店;0394,周口;027,武汉;0710,襄樊;0719,十堰"
                + ";0714,黄石;0711,鄂州;0718,恩施;0713,黄冈;0716,荆州;0724,荆门;0722,随州;0717,宜昌;0728,天门;0728,潜江;0728,仙桃"
                + ";0712,孝感;0715,咸宁;0731,长沙;0730,岳阳;0732,湘潭;0736,常德;0735,郴州;0743,凤凰;0734,衡阳;0745,怀化;0738,娄底"
                + ";0739,邵阳;0737,益阳;0746,永州;0733,株洲;0744,张家界;028,成都;0816,绵阳;0832,资阳;0827,巴中;0838,德阳;0818,达州"
                + ";0826,广安;0839,广元;0833,乐山;0830,泸州;0833,眉山;0832,内江;0817,南充;0812,攀枝花;0825,遂宁;0831,宜宾;0835,雅安"
                + ";0813,自贡;0851,贵阳;0853,安顺;0857,毕节;0856,铜仁;0852,遵义;0871,昆明;0877,玉溪;0878,楚雄;0872,大理;0873,红河"
                + ";0874,曲靖;0691,西双版纳;0870,昭通;0891,拉萨;0892,日喀则;0983,山南;029,西安;0915,安康;0917,宝鸡;0916,汉中;0914,商洛"
                + ";0919,铜川;0913,渭南;0910,咸阳;0911,延安;0912,榆林;0931,兰州;0943,白银;0932,定西;0935,金昌;0937,酒泉;0939,陇南"
                + ";0930,临夏;0933,平凉;0930,庆阳;0935,武威;0938,天水;0936,张掖;0971,西宁;0972,海东;0970,海北;0974,海南;0951,银川"
                + ";0952,石嘴山;0953,吴忠;0953,中卫;0991,乌鲁木齐;0994,昌吉;0990,克拉玛依;0993,石河子;0995,吐鲁番;0937,嘉峪关;0888,丽江"
                + ";0837,阿坝藏族羌族自治州;0834,凉山彝族自治州;0898,陵水黎族自治县;0887,迪庆藏族自治州;0875,保山;0719,神农架林区;0570,衢州市"
                + ";0836,甘孜藏族自治州;0876,文山壮族苗族自治州;0692,德宏傣族景颇族自治州;";
        String[] a = s.split(";");
        String[] b;
        for (String element : a) {
            b = element.split(",");
            // System.out.print(b[1] + ",");
            System.out.println(b[1]);
        }
    }

    /** * 查找质数 */
    static void testPrime() {
        int size = 1000;
        StringBuilder p = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            if (isPrime(i)) {
                p.append(i + ", ");
            }
        }
        // String s = p.substring(0, p.length() - 2);
        String s = p.substring(p.length() - 20, p.length() - 2);
        System.out.println("质数：" + s);
        long st = System.currentTimeMillis();
        System.out.println(isPrime(999983));
        long time = System.currentTimeMillis() - st;
        System.out.println("Time: " + time);
    }

    /**
     * @param n
     * @return true, 质数；false, 非质数
     */
    static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static void testTransactionID() {
        int size = 100000;
        String id;
        Set<String> set = new HashSet<String>(size);
        for (int i = 0; i < size; i++) {
            id = getTransactionID();
            // System.out.println("id=" + id);
            set.add(id);
        }
        System.out.println(set.size());
    }

    /**
     * @return 生成序列号
     */
    public synchronized static String getTransactionID() {
        String sChars = "0123456789";
        // String sPswd = "";
        StringBuffer id = new StringBuffer();
        int iIndex = 0;
        // int iseq = 0;
        Random x = new Random(); // default seed is time in milliseconds
        for (int i = 0; i < 18; i++) { // generator six bit random astring;
            iIndex = x.nextInt(9) + 1;
            // sPswd = sPswd + sChars.substring(iIndex, (iIndex + 1));
            id.append(sChars.substring(iIndex, (iIndex + 1)));
        }
        // if (iseq >= 999) {
        // iseq = 0;
        // } else {
        // iseq++;
        // }
        // System.out.println(iseq);
        // String sSeq = Integer.toString(iseq);
        // if (sSeq.length() == 1) {
        // sSeq = "00" + sSeq;
        // } else if (sSeq.length() == 2) {
        // sSeq = "0" + sSeq;
        // }
        // return sPswd + sSeq;
        return id.toString();
    }

    static void test() {
        String str = "ａｂｃｄｅｆｇ１２３４５67890";
        // System.out.println(str);
        // str = sbcTodbc(str);
        // System.out.println(str);
        try {
            str = toDBC(new String(str.getBytes("gb2312")));
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            System.out.println(Integer.toHexString(c[i]));
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    static String sbcTodbc(String str) {
        final int n = 65248;
        int c;
        int t;
        char[] codes = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (256 < c) {
                t = c - n;
                if (0 <= t) {
                    codes[i] = (char) t;
                } else {
                    codes[i] = (char) c;
                }
            } else {
                codes[i] = (char) c;
            }
        }
        return String.valueOf(codes);
    }

    public static void testChar() {
        String s1 = "123";
        String s2 = "ａｂｃ";
        String s3 = "123ａｂｃ";
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        for (int i = 0; i < s1.length(); i++) {
            int j = s1.charAt(i);
            if (j > 256) {
                int temp = j - 65248;
                if (temp >= 0) {
                    System.out.print((char) j + "-->:" + (char) temp);
                } else {
                    System.out.print((char) j);
                }
            } else {
                System.out.print((char) j);
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < s2.length(); i++) {
            int j = s2.charAt(i);
            if (j > 256) {
                int temp = j - 65248;
                if (temp >= 0) {
                    System.out.print((char) j + "-->:" + (char) temp);
                } else {
                    System.out.print((char) j);
                }
            } else {
                System.out.print((char) j);
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < s3.length(); i++) {
            int j = s3.charAt(i);
            if (j > 256) {
                int temp = j - 65248;
                if (temp >= 0) {
                    System.out.print((char) j + "-->:" + (char) temp);
                } else {
                    System.out.print((char) j);
                }
            } else {
                System.out.print((char) j);
            }
            System.out.println();
        }
        System.out.println();

    }
}
