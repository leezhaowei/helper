package com.zwli.maven.ivy;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class IvyToPomMap {

    private static Map<String, String> groupIds;

    private static Map<String, String> versions;

    static {
        init();
    }

    private static void init() {
        URL urlConf = IvyToPomMap.class.getResource("conf.json");
        // System.out.println(urlGroupIds);

        JSONTokener tokener;
        try {
            tokener = new JSONTokener(urlConf.openStream());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        JSONObject jsonConf = new JSONObject(tokener);
        // System.out.println(jsonGI.toString(2));

        initGroupIds(jsonConf);
        initVersions(jsonConf);
    }

    private static void initGroupIds(JSONObject jsonConf) {
        groupIds = new HashMap<String, String>();

        JSONArray array = jsonConf.getJSONArray("groupIds");
        JSONObject json = null;
        for (int i = 0; i < array.length(); i++) {
            json = array.getJSONObject(i);
            // System.out.println(json.toString(2));

            storeGroupId(json);
        }
    }

    private static void storeGroupId(JSONObject json) {
        // "groupId" : "com.google.gwt",
        // "artifactIds" : ["gwt-servlet","gwt-user","gwtent","gwt-fx","gwt-crypto","gwttk"]
        String groupId = json.getString("groupId");
        JSONArray arr = json.getJSONArray("artifactIds");
        for (int i = 0; i < arr.length(); i++) {
            String artifactId = arr.getString(i);
            // System.out.println(artifactId);
            groupIds.put(artifactId, groupId);
        }
    }

    private static void initVersions(JSONObject jsonConf) {
        versions = new HashMap<String, String>();

        JSONArray array = jsonConf.getJSONArray("versions");
        JSONObject json = null;
        for (int i = 0; i < array.length(); i++) {
            json = array.getJSONObject(i);
            // System.out.println(json.toString(2));

            storeVersion(json);
        }
    }

    private static void storeVersion(JSONObject json) {
        String artifactId = json.getString("artifactId");
        String version = json.getString("version");
        versions.put(artifactId, version);
    }

    public static String getGroupId(String artifactId) {
        String groupId = groupIds.get(artifactId);
        return groupId;
    }

    public static void main(String[] args) {
    }
}
