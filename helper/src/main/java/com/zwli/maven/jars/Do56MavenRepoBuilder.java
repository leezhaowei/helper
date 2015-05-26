package com.zwli.maven.jars;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class Do56MavenRepoBuilder {

    public static void main(String[] args) {
        callBuildMavenJarStructrueForTAC();
    }

    public static void callBuildMavenJarStructrueForTAC() {
        String pomFile = "D:/TacGit56/tac/org.talend.administrator/pom.xml";
        String tacLibPath = "E:/TUP/56/webapps/org.talend.administrator/WEB-INF/lib";
        // tacLibPath = "E:\\Download\\lib";
        String repoPath = "E:/toMavenRepo";
        try {
            FileUtils.deleteDirectory(new File(repoPath));

            MavenRepoBuilder.buildMavenJarStructureForTAC(pomFile, tacLibPath, repoPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
