package com.zwli.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zwli Create: 10:18:03 AM May 12, 2008
 */
public class Tools {

    public static int countTotalPage(int total, int num) {
        if (0 != total) {
            if ((total % num) == 0) {
                return (total / num);
            } else {
                return (total / num) + 1;
            }
        }
        return 0;
    }

    public static int countStartRow(int currentPage, int num) {
        return ((1 >= currentPage) ? 1 : ((currentPage - 1) * num + 1));
    }

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

    public static String POST(String sUrl, String xml, String encoding) {
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
                huc.setRequestProperty("Content-Length", xml.getBytes("utf8").length + "");

                dos = new DataOutputStream(huc.getOutputStream());
                huc.connect();
                dos.write(xml.getBytes("utf8"));
                dos.flush();

                br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
                StringBuffer info = new StringBuffer();
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

    public static boolean isLetter(String s) {
        char c;
        int len = s.trim().length();
        String t = s.trim();
        for (int i = 0; i < len; i++) {
            c = t.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断质数 <br/>
     * 一个数，如果只有1和它本身两个因数，这样的数叫做质数（或素数）。例如 2，3，5，7 是质数，而 4，6，8，9 则不是，
     * 后者称为合成数或合数。从这个观点可将整数分为两种，一种叫质数，一种叫合成数。（1不是质数，也不是合数）著名的高 斯「唯一分解定理」说，任何一个整数。可以写成一串质数相乘的积。质数中除2是偶数外，其他都是奇数。
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
