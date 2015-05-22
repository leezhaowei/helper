package com.zwli.svn;

import java.io.BufferedReader;
import java.io.FileReader;

public class SvnSshUtils {

    public static void main(String[] args) {
        buildCmdOfKill();
    }

    public static void buildCmdOfKill() {
        String file = "E:/Temp/testSvnSSH/123.txt";
        BufferedReader br = null;
        try {
            try {
                br = new BufferedReader(new FileReader(file));
                String t = "";
                while (br.ready()) {
                    t = br.readLine().toLowerCase();
                    if (null != t && t.trim().length() > 0) {
                        t = t.replaceAll("0:00", "").replaceAll("\\\\", "").replaceAll("sshd", "").replaceAll("zwli", "")
                                .replaceAll("\\|", "").replaceAll("\\_", "").replaceAll("\\:", "").replaceAll("\\[priv\\]", "")
                                .replaceAll("\\@notty", "").replaceAll("\\?", "").replaceAll("s", "").trim();
                        System.out.println("kill -9 " + t);
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
    }
}
