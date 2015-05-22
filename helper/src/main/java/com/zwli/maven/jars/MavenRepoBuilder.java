// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package com.zwli.maven.jars;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * created by zwli on Apr 28, 2014 Detailled comment
 */
public class MavenRepoBuilder {

    private static final String SUFFIX_JAR = ".jar";

    private static final String[] SPECIAL_JARS = { "gwttoolkit", "org.apache.karaf.cellar.management" };

    private static Map<String, Artifact> thirdJarMap;

    private static List<Artifact> talendJarList;

    private static boolean usualVersion = true;

    private static Map<String, String[]> specialJarFieldMap;

    static {
        init();
    }

    private static void init() {
        thirdJarMap = new HashMap<String, Artifact>();
        talendJarList = new ArrayList<Artifact>();

        specialJarFieldMap = new HashMap<String, String[]>();
        specialJarFieldMap.put("gwttoolkit", new String[] { "org.talend", "gwttoolkit", "1.6.0-SNAPSHOT" });
        specialJarFieldMap.put("org.apache.karaf.cellar.management", new String[] { "org.apache.karaf.cellar",
                "org.apache.karaf.cellar.management", "2.2.5-SNAPSHOT" });
    }

    public static void buildMavenJarStructureForTAC(String pomFile, String tacLibPath, String repoPath) throws IOException,
            DocumentException {
        extractJarFromPomFile(pomFile);

        File tacLibDir = new File(tacLibPath);
        File[] tacLibs = tacLibDir.listFiles();

        int count = 0;
        for (File file : tacLibs) {
            if (buildThirdJarForMavenRepo(repoPath, file)) {
                count++;
            } else if (buildTalendJarForMavenRepo(repoPath, file)) {
                count++;
            } else if (isSpecialJar(file) && buildSpecialJarForMavenRepo(repoPath, file)) {
                count++;
            } else {
                log(" \1 POM doesn't contain: " + file.getAbsolutePath());
            }
        }
        log("\ntotal lib size: " + tacLibs.length + ", copy lib num: " + count);
    }

    private static boolean buildSpecialJarForMavenRepo(String repoPath, File file) {
        String fileName = file.getName();

        String groupId = null;
        String artifactId = null;
        String version = null;

        for (String e : SPECIAL_JARS) {
            if (fileName.startsWith(e)) {
                String[] arr = specialJarFieldMap.get(e);
                groupId = arr[0];
                artifactId = arr[1];
                version = arr[2];
            }
        }

        Artifact af = new Artifact(groupId, artifactId, version);
        try {
            buildRepoJar(repoPath, file, af);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isSpecialJar(File file) {
        String name = file.getName();
        for (String e : SPECIAL_JARS) {
            if (name.startsWith(e)) {
                return true;
            }
        }
        return false;
    }

    private static boolean buildTalendJarForMavenRepo(String repoPath, File file) throws IOException {
        // log(" \1 " + file.getName());
        String version = null;
        String jarNamePrefix = null;
        Artifact af = null;
        for (Artifact e : talendJarList) {
            version = e.version.replace("[", "").replace(",)", "");

            jarNamePrefix = e.artifactId + "-" + version;
            if (file.getName().startsWith(jarNamePrefix)) {

                if (usualVersion && !isThirdJar(e.version)) {
                    String target = version; // e.g. 5.6.0
                    String name = file.getName();
                    // log(" \1 " + name);
                    version = name.substring(name.indexOf(target), name.lastIndexOf(SUFFIX_JAR));
                }

                // log(" \1 " + file.getName());
                af = new Artifact(e.groupId, e.artifactId, version, jarNamePrefix + SUFFIX_JAR);
                // af = e;
                buildRepoJar(repoPath, file, af);
                return true;
            }
        }
        return false;
    }

    private static boolean buildThirdJarForMavenRepo(String repoPath, File file) throws IOException {
        String jarName = file.getName();
        if (!thirdJarMap.containsKey(jarName)) {
            // log(" \1 " + jarName);
            return false;
        }

        Artifact af = thirdJarMap.get(jarName);
        buildRepoJar(repoPath, file, af);
        return true;
    }

    private static void buildRepoJar(String repoPath, File file, Artifact af) throws IOException {
        String[] arr = af.groupId.split("\\.");
        // E:/_mvnRepo/com/google/inject/extensions/guice-assistedinject/3.0
        String jarFolder = repoPath + File.separator;
        for (String str : arr) {
            jarFolder += str + File.separator;
        }
        jarFolder += af.artifactId + File.separator + af.version;
        createFolders(jarFolder);

        String newJarFile = jarFolder + File.separator + af.artifactId + "-" + af.version + SUFFIX_JAR;

        FileUtils.copyFile(file, new File(newJarFile));
    }

    private static void extractJarFromPomFile(String pomFile) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(pomFile));

        Element root = document.getRootElement();
        Element properties = root.element("properties");
        String gwtVersion = properties.elementText("gwtVersion");
        String talendRevision = properties.elementText("talendRevision");
        String talendVersion = properties.elementText("talendVersion").replace("${talendRevision}", talendRevision);

        PomProp pomProp = new PomProp(gwtVersion, talendRevision, talendVersion);

        Element dependencies = root.element("dependencies");
        Iterator<?> it = dependencies.elementIterator("dependency");
        while (it.hasNext()) {
            Element dependency = (Element) it.next();
            Artifact artifact = extractArtifact(pomProp, dependency);
            if (isThirdJar(artifact.version)) {
                // log(" \1 " + artifact);
                thirdJarMap.put(artifact.jarName, artifact);
            } else {
                talendJarList.add(artifact);
            }
        }

    }

    private static boolean isThirdJar(String version) {
        if (version.startsWith("[") && version.endsWith(",)")) {
            return false;
        }
        return true;
    }

    private static Artifact extractArtifact(PomProp pomProp, Element dependency) {
        String groupId = dependency.elementText("groupId");
        String artifactId = dependency.elementText("artifactId");
        String version = dependency.elementText("version");
        version = parseVersion(version, pomProp);
        String jarName = artifactId + "-" + version + SUFFIX_JAR;

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
        String pomFile = "D:/TacGit56/tac/org.talend.administrator/pom.xml";
        String tacLibPath = "E:/TUP/56/webapps/org.talend.administrator/WEB-INF/lib";
        // tacLibPath = "E:\\Download\\lib";
        String repoPath = "E:/Temp/toMavenRepo";
        try {
            FileUtils.deleteDirectory(new File(repoPath));

            MavenRepoBuilder.buildMavenJarStructureForTAC(pomFile, tacLibPath, repoPath);
            // MavenJarsHandler.extractJarFromPomFile(pomFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void log(String msg) {
        System.out.println(msg);
    }
}
