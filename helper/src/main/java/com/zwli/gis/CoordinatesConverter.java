package com.zwli.gis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * Convert coordinate between GCJ-02 and BD-09 <br>
 * Created by Jeffery Li on 2015/12/11
 */
public class CoordinatesConverter {

    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    public static Coordinate gcj02ToBd09(double lon, double lat) {
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new Coordinate(bd_lon, bd_lat);
    }

    public static Coordinate bd09ToGcj02(double lon, double lat) {
        double x = lon - 0.0065, y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return new Coordinate(gg_lon, gg_lat);
    }

    public static void main(String[] args) {
        test();
    }

    static void test() {
        String fn = "D:/_work/Akzo Noble/a.txt";
        String to = "D:/_work/Akzo Noble/output1.txt";
        try {
            BufferedReader br = null;
            BufferedWriter bw = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(fn)));
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(to)));
                while (br.ready()) {
                    String t = br.readLine();
                    // System.out.println(t);
                    String[] arr = t.split(",");
                    if (arr != null && arr.length == 3) {
                        String str = transform(arr);
                        if (str != null) {
                            bw.write(str);
                        } else {
                            System.out.println(t);
                        }
                    } else {
                        // System.out.println(t);
                    }
                }
                bw.flush();
            } finally {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int latLen = 0;
    static int lonLen = 0;

    private static String transform(String[] arr) {
        try {
            double lat = Double.parseDouble(arr[1]);
            double lon = Double.parseDouble(arr[2]);
            Coordinate c = bd09ToGcj02(lon, lat);

            int len = Double.toString(c.lat).length();
            if (len == 18) {
                System.out.println(c.lat);;
            }
            len = Double.toString(c.lon).length();
            if (len == 18) {
                System.out.println(c.lon);;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(arr[0]).append(",");
            sb.append(c.lat).append(",").append(c.lon).append("\n");
            return sb.toString();
        } catch (Exception e) {
            // do nothing
        }
        return null;
    }

    static void testTransform() {
        // CN_1222 1 北京昌平区泓海丰北七家建材市场漆彩体验中心 北京市昌平区北七家建材市场油漆区32—33号 0 北京 北京 NORTH CN ZH '010-61760138 1 0
        // (lat)40.128054 (lon)116.423538 columbus_web_cn_dulux 01010003
        double lon = 116.423538;
        double lat = 40.128054;
        Coordinate c = bd09ToGcj02(lon, lat);
        System.out.println(c);
        Coordinate d = gcj02ToBd09(c.lon, c.lat);
        System.out.println(d);
    }
}
