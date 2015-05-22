package com.talend.metaservlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.talend.json.JSONException;
import org.talend.json.JSONObject;

public class MetaServletCaller {

    private String tacUrl;

    private String params;

    private static String host = "192.168.32.88";

    private static String url = "http://" + host + ":8080/org.talend.administrator/";

    public static void main(String[] args) throws Exception {
        // url = "http://127.0.0.1:7777";

        // createTasks();
        // createServer();
        // createProjectReference();
        // runTask();
        runTaskInLoop();
        // runTaskInThread();
    }

    public static void runTaskInThread() throws Exception {
        int size = 100;
        // size = 20;
        for (int i = 0; i < size; i++) {
            // if (i != 0 && i % 10 == 0) {
            // Thread.sleep(10000);
            // }
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        runTask();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            // Thread.sleep(3000);
        }
    }

    public static void runTaskInLoop() throws Exception {
        int size = 100;
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " > ");
            runTask();
        }
    }

    public static void runTask() throws Exception {
        StringBuilder cmdBuilder = new StringBuilder("{");
        cmdBuilder.append("'actionName':'runTask','authPass':'admin','authUser':'admin@company.com',");
        cmdBuilder.append("'mode':'synchronous',");
        cmdBuilder.append("'taskId':'97',");
        cmdBuilder.append("}");
        // System.out.println(">>> " + cmdBuilder.toString());

        MetaServletCaller caller = new MetaServletCaller();
        caller.tacUrl = url;
        JSONObject json = null;
        try {
            json = new JSONObject(cmdBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            System.exit(1);
        }
        caller.params = json.toString();
        // caller.printOptions();
        caller.execute();
    }

    public static void createProjectReference() throws Exception {
        StringBuilder cmdBuilder = new StringBuilder("{");
        cmdBuilder.append("'actionName':'createProjectReference','authPass':'admin','authUser':'zwli@talend.com',");
        cmdBuilder.append("'branch':'trunk',");
        cmdBuilder.append("'projectName':'p01',");
        cmdBuilder.append("'referenceProjects':'REF_P01/trunk,REF_P01/branches/branch01,REF_P02/trunk'");
        cmdBuilder.append("}");
        System.out.println(">>> " + cmdBuilder.toString());

        MetaServletCaller caller = new MetaServletCaller();
        caller.tacUrl = url;
        JSONObject json = null;
        try {
            json = new JSONObject(cmdBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            System.exit(1);
        }
        caller.params = json.toString();
        // caller.printOptions();
        caller.execute();
    }

    public static void createServer() throws Exception {
        String portsSrc = "[10222,10223,11110];[11333,11334,12221];[12444,12445,13332];[13555,13556,14443];[14666,14667,15554]";
        portsSrc = "[10222,10223,11110]";

        List<Integer[]> portList = new ArrayList<Integer[]>();
        String[] arr1 = portsSrc.split(";");
        for (String t1 : arr1) {
            String[] arr2 = t1.replace("[", "").replace("]", "").split(",");
            Integer[] ports = new Integer[arr2.length];
            for (int i = 0; i < arr2.length; i++) {
                ports[i] = Integer.parseInt(arr2[i].trim());
            }
            portList.add(ports);
        }

        int start = 1;
        int length = start + portList.size();
        for (int i = start; i < length; i++) {
            StringBuilder cmdBuilder = new StringBuilder(
                    "{'actionName':'addServer','authPass':'admin','authUser':'zwli@talend.com',");
            if (i <= 9) {
                cmdBuilder.append("'label':'SERVER_0" + i + "',");
            } else {
                cmdBuilder.append("'label':'SERVER_" + i + "',");
            }
            Integer[] ports = portList.get(i - start);
            cmdBuilder.append("'host':'" + host + "','commandPort':" + ports[0] + ",'filePort':" + ports[1]
                    + ",'monitoringPort':" + ports[2] + "}");
            System.out.println(">>> " + cmdBuilder.toString());

            MetaServletCaller caller = new MetaServletCaller();
            caller.tacUrl = url;
            JSONObject json = null;
            try {
                json = new JSONObject(cmdBuilder.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                System.exit(1);
            }
            caller.params = json.toString();
            // caller.printOptions();
            caller.execute();
        }
    }

    public static void createTasks() throws Exception {
        // -url http://192.168.32.88:8080/org.talend.administrator/
        // -json-params="{'actionName':'createTask','authPass':'admin','authUser':'zwli@talend.com','taskName':'A07','active':true,'applyContextToChildren':false,'description':'','projectName':'p01','branch':'trunk','jobName':'p1_log_1000Line_v51','contextName':'Default','jobVersion':'Latest','regenerateJobOnChange':false,'executionServerName':'server','onUnknownStateJob':'Wait','addStatisticsCodeEnabled':true,'execStatisticsEnabled':false}"
        int start = 1;
        int length = 10;
        for (int i = start; i <= length; i++) {
            StringBuilder cmdBuilder = new StringBuilder(
                    "{'actionName':'createTask','authPass':'admin','authUser':'zwli@talend.com',");
            if (i <= 9) {
                cmdBuilder.append("'taskName':'A0" + i + "',");
            } else {
                cmdBuilder.append("'taskName':'A" + i + "',");
            }
            cmdBuilder.append("'active':true,'applyContextToChildren':false,'description':'','projectName':'p01',");
            cmdBuilder.append("'branch':'trunk','jobName':'p01_log_1000Line_v56','contextName':'Default','jobVersion':'Latest',");
            cmdBuilder.append("'regenerateJobOnChange':false,'executionServerName':'server','onUnknownStateJob':'Wait',");
            cmdBuilder.append("'addStatisticsCodeEnabled':true,'execStatisticsEnabled':false}");
            // System.out.println(">>> " + commandBuilder.toString());

            MetaServletCaller caller = new MetaServletCaller();
            caller.tacUrl = url;
            JSONObject json = null;
            try {
                json = new JSONObject(cmdBuilder.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                System.exit(1);
            }
            caller.params = json.toString();
            // caller.printOptions();
            caller.execute();
        }
    }

    public void printOptions() {
        if (params != null) {
            System.out.println(" -> URL: " + tacUrl);
            try {
                System.out.println(" -> Json parameters:\n" + new JSONObject(params).toString(2));
            } catch (JSONException e) {
                System.err.println("Json param '" + params + "' is malformed (" + e.getMessage() + ")");
            }
        }
    }

    private void execute() throws JSONException, IOException {
        String response = execute(params, "metaServlet");
        System.out.println(response);
    }

    private String execute(String query, String servletPattern) throws IOException {
        BufferedReader in = null;
        try {
            String request = new String(Base64.encodeBase64(query.getBytes()), "UTF8");
            URL url = new URL(tacUrl + "/" + servletPattern + "?" + request);
            // System.out.println(" -> Complete request: " + url);

            in = new BufferedReader(new InputStreamReader(url.openStream()));

            String buffer = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                buffer += inputLine;
            }
            return buffer;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
