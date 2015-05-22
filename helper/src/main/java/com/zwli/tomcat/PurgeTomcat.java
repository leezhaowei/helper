package com.zwli.tomcat;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class PurgeTomcat {

    public static void main(String[] args) {
        // purgeSingle();
        purgeAll();
    }

    public static void purgeSingle() {
        String path = "E:/Tomcat/tomcat-7.0.40-tmp-8085/";
        List<String> list = new ArrayList<String>();
        list.add("org.talend.administrator");
        list.add("tacsrc");
        list.add("tactarget");
        list.add("tac");
        list.add("org.talend.repositorymanager");
        purge(path + "webapps", list);
        purge(path + "temp");
        purge(path + "work");
        purge(path + "logs");
    }

    public static void purgeAll() {
        for (int i = 7; i <= 3; i++) {
            String path = "E:/Tomcat/tomcat-7.0.40-tmp-808" + i + "/";
            List<String> list = new ArrayList<String>();
            list.add("org.talend.administrator");
            list.add("tacsrc");
            list.add("tactarget");
            list.add("tac");
            list.add("org.talend.repositorymanager");
            // purgeWebapps(path + "webapps", list);
            // purgeTemp(path + "temp", null);
            // purgeWork(path + "work", null);
            purge(path + "webapps", list);
            purge(path + "temp");
            purge(path + "work");
            purge(path + "logs");
        }
    }

    // public static void purgeWork(String workPath, final List<String> filterNameList) {
    // IllegalArgumentException e = new IllegalArgumentException("[" + workPath + "] is not a tomcat/work folder.");
    //
    // if (!workPath.endsWith("work")) {
    // throw e;
    // }
    // purge(workPath, filterNameList, e);
    // }
    //
    // public static void purgeTemp(String tempPath, final List<String> filterNameList) {
    // IllegalArgumentException e = new IllegalArgumentException("[" + tempPath + "] is not a tomcat/temp folder.");
    //
    // if (!tempPath.endsWith("temp")) {
    // throw e;
    // }
    // purge(tempPath, filterNameList, e);
    // }
    //
    // public static void purgeWebapps(String webappsPath, final List<String> filterNameList) {
    // IllegalArgumentException e = new IllegalArgumentException("[" + webappsPath +
    // "] is not a tomcat/webapps folder.");
    //
    // if (!webappsPath.endsWith("webapps")) {
    // throw e;
    // }
    // purge(webappsPath, filterNameList, e);
    // }

    public static void purge(String targetPath) {
        IllegalArgumentException e = new IllegalArgumentException("[" + targetPath + "] is not a child folder of tomcat.");
        purge(targetPath, null, e);
    }

    public static void purge(String targetPath, List<String> filterNameList) {
        IllegalArgumentException e = new IllegalArgumentException("[" + targetPath + "] is not a child folder of tomcat.");
        purge(targetPath, filterNameList, e);
    }

    private static void purge(String path, final List<String> filterNameList, IllegalArgumentException e) {
        File pathFile = new File(path);
        if (!pathFile.isDirectory()) {
            throw e;
        }

        FileFilter filter = null;
        if (null != filterNameList) {
            filter = new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    for (String filterName : filterNameList) {
                        if (pathname.getName().contains(filterName)) {
                            return true;
                        }
                    }
                    return false;
                }
            };
        }

        File[] targetFiles = null;
        if (null != filter) {
            targetFiles = pathFile.listFiles(filter);
        } else {
            targetFiles = pathFile.listFiles();
        }
        deleteFileOrDirectory(targetFiles);
    }

    private static void deleteFileOrDirectory(File[] targetFiles) {
        for (File file : targetFiles) {
            if (file.isDirectory()) {
                try {
                    FileUtils.deleteDirectory(file);
                } catch (IOException e) {
                    log("* Fail to delete directory [" + file.getAbsolutePath() + "]. error=" + e.getMessage());
                }
            } else {
                file.delete();
            }
            log(">>> [" + file.getAbsolutePath() + "] has been deleted.");
        }
    }

    static void log(String msg) {
        System.out.println(msg);
    }
}
