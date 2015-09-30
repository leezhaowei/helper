package com.zwli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MainTest {

    public static void main(String[] args) {
        // a();
        // b();
        // c();
        // d();
        // e();
        // f();
        // g();
        // h();
        // i();
        // j();
        // k();
        // l();
        m();
    }

    static void m() {
        List<String> emptyList = Collections.emptyList();
        System.out.println("Create an empty immutable list: " + emptyList);
        
        emptyList = new ArrayList<String>();
        emptyList.add("Hello");
        emptyList.add("world!");
        
        System.out.println(emptyList);
    }

    static void l() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("a", 1);
        params.put("b", "String");
        params.put("c", new Date());
        params.put("d", 23.5D);
        System.out.println(params);
        Collection<?> col = params.values();
        System.out.println(col);
        System.out.println(Arrays.toString(col.toArray()));
        Object[] arr = params.entrySet().toArray();
        System.out.println(Arrays.toString(arr));
    }

    static void k() {
        String a = "\"aaaa\"";
        System.out.println(a.startsWith("\""));
        String b = "test=\"Hell \"o\" world\"";
        System.out.println(b);
        int n = b.indexOf("\"");
        System.out.println(n);
        a = b.substring(n + 1, b.length() - 1);
        System.out.println(a);
        a = b.substring(0, n) + a;
        System.out.println(a);
    }

    static void j() {
        double proportion = 0.9;
        String maxFailedTest = "100";
        Double d = Double.valueOf(maxFailedTest) / 100;
        System.out.println(d);
        System.out.println(d == proportion);
        System.out.println(proportion < d);
    }

    static void i() {
        String unzipFolder = "E:/Temp/testZip/tJava_test-0.1";
        File unzipFile = new File(unzipFolder);
        File[] dirs = unzipFile.listFiles(new FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory() && f.getName().endsWith(".tests")) {
                    return true;
                }
                return false;
            }
        });
        for (File file : dirs) {
            // System.out.println(file.getAbsolutePath());
            extractTestXml(file);
        }
    }

    static void extractTestXml(File dir) {
        if (dir.isDirectory()) {
            File[] testXmls = dir.listFiles();
            for (File f : testXmls) {
                System.out.println(f.getAbsolutePath());
            }
        }
    }

    static void h() {
        String s = "com.zwli";
        String t = s.replaceAll("\\.", "/");
        System.out.println(t);

        s = "mvn:com.zwli/p01_context_tjava_v60/0.1.0-SNAPSHOT/zip";
        t = s.substring(4, s.indexOf("/")).replaceAll("\\.", "/");
        System.out.println(t);
    }

    static void g() {
        String location = "svn+ssh://192.168.32.114/usr/local/svn/repos/test/P02";
        boolean b = location.startsWith("svn+ssh://");
        System.out.println(b);

        DecimalFormat df = new DecimalFormat("#.00");
        String s = df.format(4.56743);
        System.out.println(s);
    }

    static void f() {
        Calendar lastTime = Calendar.getInstance();
        lastTime.add(Calendar.MINUTE, -10);

        long period = Calendar.getInstance().getTimeInMillis() - lastTime.getTimeInMillis();
        System.out.println(period);
        System.out.println(period / (60 * 1000));

    }

    static void e() {
        String portsSrc = "[9111, 9112, 9999];[10222, 10223, 11110];[11333, 11334, 12221];[12444, 12445, 13332];[13555, 13556, 14443]";

        List<Integer[]> portList = new ArrayList<Integer[]>();
        String[] arr1 = portsSrc.split(";");
        for (String t1 : arr1) {
            String[] arr2 = t1.replace("[", "").replace("]", "").split(",");
            Integer[] ports = new Integer[arr2.length];
            for (int i = 0; i < arr2.length; i++) {
                ports[i] = Integer.parseInt(arr2[i].trim());
            }
            portList.add(ports);
        }
    }

    static void d() {
        String url = "http://127.0.0.1:7777/jobScreenshot?projectId=3&branch=trunk&jobId=_cVUvgISjEeKtTvhaJGp12g&jobVersion=0.1&disableClientCacheWithTimestamp=1402457721829";
        String[] arr = url.split("\\?");

        arr = arr[1].split("&");
        String jobId = null;
        for (String s : arr) {
            System.out.println(s);
            if (s.startsWith("jobId=")) {
                // System.out.println(s);
                jobId = s;
            }
        }
        if (null != jobId) {
            arr = jobId.split("=");
            jobId = arr[1];
        }
        // System.out.println(jobId);
    }

    static void c() {
        String path = "E:/Temp/BusinessException.txt";
        BufferedReader br = null;
        StringBuilder info = new StringBuilder();
        try {
            try {
                br = new BufferedReader(new FileReader(path));
                while (br.ready()) {
                    String t = br.readLine();
                    if (null != t && t.trim().length() != 0 && t.endsWith(")")) {
                        info.append(t).append("\n");
                    }
                }
            } finally {
                if (null != br) {
                    br.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log(info.toString());
        // String[] arr = info.toString().split(",");
        // // log(arr.length);
        // for (String t : arr) {
        // log(t);
        // }
    }

    static void b() {
        Properties p = System.getProperties();
        Iterator<?> it = p.keySet().iterator();
        while (it.hasNext()) {
            String property = (String) it.next();
            String value = p.getProperty(property);
            System.out.println(property + " = " + value);
        }
    }

    static void a() {
        double a = 0.5D;
        double i = a * 100;
        System.out.println((int) i);

        a = 0.345D;
        i = a * 100;
        System.out.println((int) i);

        a = 1.0D;
        i = a * 100;
        System.out.println((int) i);
    }

    static void log(int num) {
        log(String.valueOf(num));
    }

    static void log(String msg) {
        System.out.println(msg);
    }
}
