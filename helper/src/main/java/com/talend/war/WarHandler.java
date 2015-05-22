package com.talend.war;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.talend.nb.ZipUtil;

public class WarHandler {

    private static final String SUFFIX_WAR = ".war";

    private static final String WAR_DIR_NAME = "administrator";

    private static FileFilter warUnzipDirFilter = new FileFilter() {

        @Override
        public boolean accept(File f) {
            if (f.isDirectory() && f.getName().equalsIgnoreCase(WAR_DIR_NAME)) {
                return true;
            }
            return false;
        }
    };

    private static FileFilter buildWarFileFilter = new FileFilter() {

        @Override
        public boolean accept(File f) {
            if (f.getName().endsWith(SUFFIX_WAR)) {
                return true;
            }
            return false;
        }
    };

    public static void copyWarAndUnzip(String buildPath, String warPath) throws IOException {
        System.out.println("### start processing war ...");
        File buildWarDir = new File(buildPath);
        File[] files = buildWarDir.listFiles(buildWarFileFilter);
        if (null == files || files.length == 0) {
            throw new IllegalArgumentException("There is no build war file in [" + buildPath + "]");
        }

        File buildWarFile = files[0];

        File warDir = new File(warPath);
        if (!warDir.exists()) {
            warDir.mkdir();
        } else {
            files = warDir.listFiles(warUnzipDirFilter);
            for (File f : files) {
                // log(f);
                if (f.isDirectory()) {
                    FileUtils.deleteDirectory(f);
                    log("delete dir: " + f.getAbsolutePath());
                }
            }
        }

        String warUnzipPath = warPath + File.separator + WAR_DIR_NAME;

        ZipUtil.setPrintLog(true);
        ZipUtil.unzip(buildWarFile.getAbsolutePath(), warUnzipPath);
        System.out.println("### end processing war");
    }

    static void log(String msg) {
        System.out.println(" \1 " + msg);
    }

    static void log(Object msg) {
        System.out.println(" \1 " + msg);
    }

    public static void main(String[] args) {
        String buildPath = "E:/_mvnRepo/org/talend/administrator/5.6.0-SNAPSHOT";
        // buildPath = "E:/_mvnRepo/org/talend/administrator/5.6.0-tdi29573";
        String warPath = "E:/TAC/builds";

        try {
            copyWarAndUnzip(buildPath, warPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
