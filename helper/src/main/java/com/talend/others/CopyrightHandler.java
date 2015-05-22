package com.talend.others;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CopyrightHandler {

    public static void main(String[] args) {
        find();
    }

    public static FileFilter java = new FileFilter() {

        @Override
        public boolean accept(File f) {
            if (f.isFile() && f.getName().endsWith(".java")) {
                return true;
            }
            return false;
        }
    };

    public static void find() {
        String dir = "D:/TalendGit/tac/org.talend.administrator/src/main/java";
        File f = new File(dir);
        find(f);
    }

    private static void find(File f) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File file : files) {
                find(file);
            }
        } else if (f.isFile() && f.getName().endsWith(".java")) {
            boolean b = read(f);
            if (!b) {
                System.out.println(f.getAbsolutePath());
            }
        }
    }

    private static boolean read(File f) {
        BufferedReader br = null;
        try {
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                String t = null;
                while (br.ready()) {
                    t = br.readLine();
                    if (null != t && t.trim().length() > 0) {
                        if (t.contains("Copyright (C) 2006-2014 Talend Inc. - www.talend.com")) {
                            return true;
                        }
                    }
                }
            } finally {
                if (null != br) {
                    br.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
