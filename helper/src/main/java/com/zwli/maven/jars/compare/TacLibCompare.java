package com.zwli.maven.jars.compare;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TacLibCompare {

    private static void compareDiffJarNames(String srcDir, String targetDir) {
        File srcFile = new File(srcDir);
        File targetFile = new File(targetDir);

        String[] srcNames = srcFile.list();
        String[] targetNames = targetFile.list();

        List<String> targetList = new ArrayList<String>(targetNames.length);
        for (String name : targetNames) {
            targetList.add(name);
        }

        int count = 0;
        for (String name : srcNames) {
            if (!targetList.contains(name)) {
                System.out.println(name);
                count++;
            }
        }
        System.out.println("total amount: " + count);
    }

    public static void main(String[] args) {
        callCompareDiffJarNames();
    }

    private static void callCompareDiffJarNames() {
        String srcDir = "E:/TAC/build/org.talend.administrator-6.0-SNAPSHOT/WEB-INF/lib";
        String targetDir = "E:/TUP/60/webapps/org.talend.administrator/WEB-INF/lib";
        compareDiffJarNames(srcDir, targetDir);
    }
}
