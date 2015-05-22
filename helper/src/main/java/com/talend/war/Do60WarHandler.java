package com.talend.war;

import java.io.IOException;

public class Do60WarHandler {

    public static void main(String[] args) {
        String buildPath = "E:/_mvnRepo/org/talend/administrator/6.0.0-SNAPSHOT";
        String warPath = "E:/builds/60";

        try {
            WarHandler.copyWarAndUnzip(buildPath, warPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
