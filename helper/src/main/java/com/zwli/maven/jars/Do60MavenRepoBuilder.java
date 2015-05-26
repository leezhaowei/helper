package com.zwli.maven.jars;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class Do60MavenRepoBuilder {

    public static void main(String[] args) {
        callBuildMavenJarStructrueForTAC();
    }

    public static void callBuildMavenJarStructrueForTAC() {
        String pomFile = "D:/TacGit60/tac/org.talend.administrator/pom.xml";
        String tacLibPath = "E:/TUP/60/webapps/org.talend.administrator/WEB-INF/lib";
        // tacLibPath = "E:\\Download\\lib";
        String repoPath = "E:/toMavenRepo";
        try {
            FileUtils.deleteDirectory(new File(repoPath));

            MavenRepoBuilder60.buildMavenJarStructureForTAC(pomFile, tacLibPath, repoPath);
            // MavenJarsHandler.extractJarFromPomFile(pomFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
