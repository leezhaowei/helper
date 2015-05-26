package com.zwli.string;

import java.util.ArrayList;
import java.util.List;

public class StringTest {

    public static void main(String[] args) {
        // a();
        // b();
        // c();
        // d();
        e();
    }

    public static void e() {
        String a = "Test";
        String b = "Test";
        b = b.toUpperCase();
        System.out.println(a);
        System.out.println(b);
    }

    public static void d() {
        String a = "abc";
        // a = new String("sss");
        System.out.println(a);
        modify(a);
        System.out.println(a);
    }

    private static void modify(String s) {
        // s += "KKK";
        s = new String(s + "KKK");
        System.out.println(s);
    }

    public static void c() {
        String out = "\u4E00 \u9FA5 \u0800 \u4e00 \uac00 \ud7ff0";
        out = "[\u00A0, \u2007, \u202F]";
        System.out.println(out);
    }

    public static void b() {
        String fileName = "1386314607538_task_54133.zip";
        String name = fileName.substring(0, fileName.lastIndexOf(".zip"));
        System.out.println(name);

        String a = "1.2";
        String b = a.replace(".", "_");
        System.out.println(b);
    }

    public static void a() {
        String contextName = "Default";
        List<String> lines = new ArrayList<String>();
        lines.add("%~d0");
        lines.add(" cd %~dp0");
        lines.add(" java -Xms256M -Xmx1024M -cp ../lib/dom4j-1.6.1.jar;../lib/talend_file_enhanced_20070724.jar;../lib/systemRoutines.jar;../lib/userRoutines.jar;.;p01_context_job002_v54_0_1.jar; p01.p01_context_job002_v54_0_1.p01_context_job002_v54 --context=Test %* ");

        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("=================================");
        for (String line : lines) {
            String newLine = modifyContextIfNeeded(line, contextName);
            System.out.println(newLine);
        }
    }

    private static String modifyContextIfNeeded(String line, String contextName) {
        String contextFlag = "--context=";
        String endFlag = " %* ";

        if (!line.contains(contextFlag)) {
            return line;
        }

        String[] arr = line.split(contextFlag);
        String newLine = arr[0] + contextFlag + contextName + endFlag;

        return newLine;
    }
}
