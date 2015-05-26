package com.zwli.maven.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class SvnFolderHandler {

    private static String TARGET = ".svn";

    public static void removeSvnHideFolder(File dirFile) throws IOException {
        if (!dirFile.isDirectory()) {
            return;
        }

        String folderName = dirFile.getName();
        if (TARGET.equals(folderName)) {
            System.out.println(dirFile.getAbsolutePath());
            FileUtils.deleteDirectory(dirFile);
            return;
        }

        File[] list = dirFile.listFiles();
        for (File file : list) {
            removeSvnHideFolder(file);
        }
    }

    public static void main(String[] args) {
        callRemoveSvnHideFolder();
    }

    private static void callRemoveSvnHideFolder() {
        String dirPath = "E:/Temp/java";
        File dir = new File(dirPath);
        try {
            removeSvnHideFolder(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
