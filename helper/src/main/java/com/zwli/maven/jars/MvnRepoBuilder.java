package com.zwli.maven.jars;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MvnRepoBuilder {

    public static void buildMavenJarStructureForTAC(String pomFile, String tacLibPath, String repoPath) throws IOException,
            DocumentException {
        Map<String, Artifact> jarMap = extractJarFromPomFile(pomFile);

        File tacLibDir = new File(tacLibPath);
        File[] tacLibs = tacLibDir.listFiles();

        int count = 0;
        for (File file : tacLibs) {
            if (buildEachMavenJarStructureForTAC(repoPath, jarMap, file)) {
                count++;
            }

        }
        System.out.println("\ntotal lib size: " + tacLibs.length + ", copy lib num: " + count);
    }

    private static boolean buildEachMavenJarStructureForTAC(String repoPath, Map<String, Artifact> jarMap, File file)
            throws IOException {
        // System.out.println(file.getAbsolutePath());
        String jarName = file.getName();
        if (!jarMap.containsKey(jarName)) {
            log(" --- " + jarName);
            return false;
        }

        Artifact af = jarMap.get(jarName);

        String[] arr = af.groupId.split("\\.");
        // E:/_mvnRepo/com/google/inject/extensions/guice-assistedinject/3.0
        String jarFolder = repoPath + File.separator;
        for (String str : arr) {
            jarFolder += str + File.separator;
        }
        jarFolder += af.artifactId + File.separator + af.version;
        // System.out.println(jarFolder);
        createFolders(jarFolder);

        // String newJarFile = jarFolder + File.separator + jarName;
        String newJarFile = jarFolder + File.separator + af.artifactId + "-" + af.version + ".jar";
        // System.out.println(newJarFile);

        FileUtils.copyFile(file, new File(newJarFile));
        return true;
    }

    private static Map<String, Artifact> extractJarFromPomFile(String pomFile) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(pomFile));

        Element root = document.getRootElement();
        Element properties = root.element("properties");
        String gwtVersion = properties.elementText("gwtVersion");
        String talendRevision = properties.elementText("talendRevision");
        String talendVersion = properties.elementText("talendVersion").replace("${talendRevision}", talendRevision);

        PomProp pomProp = new PomProp(gwtVersion, talendRevision, talendVersion);

        Map<String, Artifact> jarMap = new HashMap<String, Artifact>();
        Element dependencies = root.element("dependencies");
        Iterator<?> it = dependencies.elementIterator("dependency");
        while (it.hasNext()) {
            Element dependency = (Element) it.next();
            Artifact artifact = extractArtifact(pomProp, dependency);
            jarMap.put(artifact.jarName, artifact);
        }

        return jarMap;
    }

    private static Artifact extractArtifact(PomProp pomProp, Element dependency) {
        String groupId = dependency.elementText("groupId");
        String artifactId = dependency.elementText("artifactId");
        String version = dependency.elementText("version");
        version = parseVersion(version, pomProp);
        String jarName = artifactId + "-" + version + ".jar";

        // if ("org.talend".equals(groupId) && !needToBeIgnored(artifactId)) {
        // // if ("[5.6.0,)".equals(version)) {
        // // version = "5.6.0.NB_r116121";
        // // }
        // jarName = groupId + "." + artifactId + "-" + version + ".jar";
        // // log(jarName);
        // }

        Artifact artifact = new Artifact(groupId, artifactId, version, jarName);
        return artifact;
    }

    // private static boolean needToBeIgnored(String artifactId) {
    // String[] tags = { "gwttoolkit" };
    // for (String t : tags) {
    // if (artifactId.contains(t)) {
    // return true;
    // }
    // }
    // return false;
    // }

    private static String parseVersion(String version, PomProp pomProp) {
        String newVersion = version;
        if (version.contains("${gwtVersion}")) {
            newVersion = version.replace("${gwtVersion}", pomProp.gwtVersion);
        } else if (version.contains("${talendRevision}")) {
            newVersion = version.replace("${talendRevision}", pomProp.talendRevision);
        } else if (version.contains("${talendVersion}")) {
            newVersion = version.replace("${talendVersion}", pomProp.talendVersion);
        }
        return newVersion;
    }

    private static void createFolders(String dir) {
        File f = new File(dir);

        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public static void main(String[] args) {
        callBuildMavenJarStructrueForTAC();
    }

    private static void callBuildMavenJarStructrueForTAC() {
        String pomFile = "D:/TalendGit/tac/org.talend.administrator/pom.xml";
        String tacLibPath = "E:/TUP/56/webapps/org.talend.administrator/WEB-INF/lib";
        String repoPath = "E:/Temp/toMavenRepo";
        try {
            try {
                FileUtils.deleteDirectory(new File(repoPath));
            } catch (Exception e) {
                e.printStackTrace();
            }

            MvnRepoBuilder.buildMavenJarStructureForTAC(pomFile, tacLibPath, repoPath);
            // MavenJarsHandler.extractJarFromPomFile(pomFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void log(String msg) {
        System.out.println(msg);
    }
}
