package com.zwli.maven;

import java.io.File;
import java.io.FileFilter;

public class LastUpdatedPurge {

    private static FileFilter filter = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            if (pathname.getName().endsWith("pom.lastUpdated")) {
                return true;
            }
            return false;
        }
    };

    public static void deleteLastUpdatedFiles(String mvnRepoPath, String targetFolderName) {
        File mvnRepoPathFolder = new File(mvnRepoPath);
        String[] dirs = mvnRepoPathFolder.list();
        for (String f : dirs) {
            String path = mvnRepoPath + "/" + f + "/" + targetFolderName;
            doDeleteLastUpdatedFile(path);
        }
    }

    private static void doDeleteLastUpdatedFile(String path) {
        File parent = new File(path);
        File[] files = parent.listFiles(filter);
        if (null != files) {
            File target = files[0];
            target.delete();
            log(target.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        String mvnRepoPath = "E:/_mvnRepo/org/talend/studio";
        String targetFolderName = "5.6.1.20141207_1530";
        targetFolderName = "6.0.0.SNAP_20141221_1649";
        deleteLastUpdatedFiles(mvnRepoPath, targetFolderName);
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}
