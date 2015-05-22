package com.zwli.maven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongNameFilePurge {

    private static boolean printLog;

    public static void purgeLongNameFile(String targetFolderPath) {
        // File targetFolder = new File(targetFolderPath);
        String cmd = "dir /X";
        StringBuilder cmdLines = new StringBuilder();
        cmdLines.append("cd " + targetFolderPath).append("\n");
        cmdLines.append(cmd).append("\n");
        log(cmdLines.toString());

        Runtime r = Runtime.getRuntime();
        Process p = null;
        try {
            p = r.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        BufferedReader br = null;
        try {
            try {
                br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while (br.ready()) {
                    String t = br.readLine();
                    log(t);
                }
            } finally {
                if (null != br) {
                    br.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void log(String msg) {
        if (printLog) {
            log(msg, printLog);
        }
    }

    private static void log(String msg, boolean printLog) {
        if (printLog) {
            System.out.println(msg);
        }
    }

    public static void setPrintLog(boolean printLog) {
        LongNameFilePurge.printLog = printLog;
    }

    public static void main(String[] args) {
        LongNameFilePurge.setPrintLog(true);

        String targetFolderPath = "D:/TalendGit/tac/org.talend.administrator/target/org.talend.administrator-5.6.0-SNAPSHOT/editor/client/scripts/Core";
        LongNameFilePurge.purgeLongNameFile(targetFolderPath);
    }
}
