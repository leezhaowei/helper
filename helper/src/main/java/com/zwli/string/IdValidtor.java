package com.zwli.string;

/**
 * <pre>
 *  公民身份号码是特征组合码,由十七位数字本体码和一位数字校验码组成.排列顺序从左至右依次
 *  为:六位数字地址码,八位数字出生日期码,三位数字顺序码和一位数字校验码。
 *
 *  1、地址码
 *  表示编码对象常住户口所在县（市、旗、区）的行政区划代码，按 GB/T 2260 的规定执行。
 *
 *  2、出生日期码
 *  表示编码对象出生的年、月、日，按 GB/T 7408 的规定执行。年、月、日代码之间不用分隔符。
 *  例：某人出生日期为 1966年10月26日，其出生日期码为 19661026。
 *
 *  3、顺序码
 *  表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，顺序码的
 *  奇数分配给男性，偶数千分配给女性。
 *
 *  4 、校验码
 *  校验码采用ISO 7064：1983，MOD 11-2 校验码系统。
 *
 *  （1）十七位数字本体码加权求和公式
 *  S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
 *  Ai:表示第i位置上的身份证号码数字值
 *  Wi:表示第i位置上的加权因子
 *  Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
 *
 *  （2）计算模
 *  Y = mod(S, 11)
 *
 *  （3）通过模得到对应的校验码
 *  Y:　　 0 1 2 3 4 5 6 7 8 9 10
 *  校验码: 1 0 X 9 8 7 6 5 4 3 2
 * </pre>
 *
 * @author zwli Create: 2:19:41 PM Dec 27, 2007
 */
public class IdValidtor {

    private IdValidtor() {
    }

    public static void main(String args[]) {
        String id = "130103198202010910";
        System.out.println(IdValidtor.verify(id));
    }

    /**
     * 表示第i位置上的加权因子<br>
     * wi = 2(n-1)(mod 11)
     */
    private static final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
    /** 校验码 */
    private static final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
    /** 第i位置上的身份证号码数字值 */
    private static int[] ai = new int[18];

    /**
     * 校验身份证的有效性
     *
     * @param identityid
     *            身份证号码
     * @return
     */
    public static boolean verify(String identityid) {
        if (identityid.length() == 15) {
            identityid = modifyID(identityid);
        }
        if (identityid.length() != 18) {
            return false;
        }
        String verify = identityid.substring(17, 18);
        if (verify.equals(getVerify(identityid))) {
            return true;
        }
        return false;
    }

    // get verify
    private static String getVerify(String eightid) {
        int remaining = 0;
        if (eightid.length() == 18) {
            eightid = eightid.substring(0, 17);
        }

        if (eightid.length() == 17) {
            int sum = 0;
            for (int i = 0; i < 17; i++) {
                String k = eightid.substring(i, i + 1);
                ai[i] = Integer.parseInt(k);
            }
            for (int i = 0; i < 17; i++) {
                sum = sum + wi[i] * ai[i];
            }
            remaining = sum % 11;
        }

        return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
    }

    // 15 update to 18
    private static String modifyID(String fifteenid) {
        String eightid = fifteenid.substring(0, 6);
        eightid = eightid + "19";
        eightid = eightid + fifteenid.substring(6, 15);
        eightid = eightid + getVerify(eightid);
        return eightid;
    }
}
