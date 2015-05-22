package com.talend.war;

import java.io.IOException;

public class Do56WarHandler {

    public static void main(String[] args) {
        String buildPath = "E:/_mvnRepo/org/talend/administrator/5.6.0-SNAPSHOT";
        String warPath = "E:/builds/56";

        try {
            WarHandler.copyWarAndUnzip(buildPath, warPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
