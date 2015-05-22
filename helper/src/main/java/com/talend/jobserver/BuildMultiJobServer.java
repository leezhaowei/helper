package com.talend.jobserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.talend.nb.ZipUtil;

public class BuildMultiJobServer {

    private FileFilter zipFilter = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            if (pathname.getName().endsWith(".zip") && pathname.getName().startsWith("Talend-JobServer")) {
                return true;
            }
            return false;
        }
    };

    private List<int[]> portList;

    private int start;

    private int length;

    private StringBuilder portsBuilder = new StringBuilder();

    public BuildMultiJobServer(int start, int length) {
        super();
        this.start = start;
        this.length = length;
        buildPorts(start, length);
    }

    public void buildMultiJobServer(String jobServerPath) throws IOException {
        if (null == jobServerPath && "".equals(jobServerPath)) {
            jobServerPath = "E:/Temp/testJobServer";
        }

        File zipPath = new File(jobServerPath);
        File[] files = zipPath.listFiles(zipFilter);
        if (files.length != 1) {
            log(">>> There is need only one jobserver zip file in folder [" + jobServerPath + "]");
            return;
        }

        String[] newJobServerPaths = unzipJobServer(jobServerPath, files[0]);
        setupNewPortForEachJobServer(jobServerPath, newJobServerPaths);
    }

    private String[] unzipJobServer(String jobServerZipPath, File zipJobServerFile) throws IOException {
        String[] newJobServerPaths = new String[length - start];
        String zipFileName = zipJobServerFile.getName().replace(".zip", "");
        String parentUnzipFilePath = jobServerZipPath + "/" + zipFileName;
        for (int i = start; i < length; i++) {
            String path = parentUnzipFilePath + "-" + i;
            newJobServerPaths[i - start] = path;
        }

        for (String newJobServerPath : newJobServerPaths) {
            File targetFile = new File(newJobServerPath);
            if (targetFile.exists() && targetFile.isDirectory()) {
                FileUtils.deleteDirectory(targetFile);
                log("Deleted [" + targetFile.getAbsolutePath() + "]");
            }

            ZipUtil.unzip(zipJobServerFile.getAbsolutePath(), jobServerZipPath);
            File f = new File(parentUnzipFilePath);
            f.renameTo(targetFile);
        }

        return newJobServerPaths;
    }

    private void setupNewPortForEachJobServer(String jobServerPath, String[] newJobServerPaths) {
        String confFilePath = "/conf/TalendJobServer.properties";
        portsBuilder = new StringBuilder();
        for (int i = 0; i < (length - start); i++) {
            String confFile = newJobServerPaths[i] + confFilePath;
            processConfFile(confFile, portList.get(i));
            buildJobServerLaunchScript(jobServerPath, newJobServerPaths[i]);
        }

        String ports = portsBuilder.toString().replace(" ", "");
        System.out.println(ports);
        File f = new File(jobServerPath + "/readme.txt");
        FileWriter fw = null;
        try {
            try {
                fw = new FileWriter(f);
                fw.write(ports);
                fw.flush();
            } finally {
                if (null != fw) {
                    fw.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildJobServerLaunchScript(String jobServerPath, String newJobServerPath) {
        StringBuilder bat = new StringBuilder();
        bat.append("\nE:\n");
        bat.append("cd ").append(newJobServerPath).append("\n");
        bat.append("start_rs.bat").append("\n");

        File f = new File(newJobServerPath + ".bat");
        if (f.exists()) {
            f.delete();
        }

        FileWriter fw = null;
        try {
            try {
                fw = new FileWriter(f);
                fw.write(bat.toString());
                fw.flush();
            } finally {
                if (null != fw) {
                    fw.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildPorts(int start, int length) {
        portList = new ArrayList<int[]>();
        int COMMAND_SERVER_PORT = 8000;
        int FILE_SERVER_PORT = 8001;
        int MONITORING_PORT = 8888;

        for (int i = start; i < length; i++) {
            int n = 1111 * i;
            int[] m = { COMMAND_SERVER_PORT + n, FILE_SERVER_PORT + n, MONITORING_PORT + n };
            portList.add(m);
        }
    }

    private void processConfFile(String confFile, int[] ports) {
        File f = new File(confFile);
        File tmp = new File(confFile + ".tmp");

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmp)));

                while (br.ready()) {
                    String t = br.readLine();
                    // org.talend.remote.jobserver.server.TalendJobServer.COMMAND_SERVER_PORT=8000
                    // org.talend.remote.jobserver.server.TalendJobServer.FILE_SERVER_PORT=8001
                    // org.talend.remote.jobserver.server.TalendJobServer.MONITORING_PORT=8888
                    if (t.startsWith("org.talend.remote.jobserver.server.TalendJobServer.COMMAND_SERVER_PORT")) {
                        t = "org.talend.remote.jobserver.server.TalendJobServer.COMMAND_SERVER_PORT=" + ports[0];
                    } else if (t.startsWith("org.talend.remote.jobserver.server.TalendJobServer.FILE_SERVER_PORT")) {
                        t = "org.talend.remote.jobserver.server.TalendJobServer.FILE_SERVER_PORT=" + ports[1];
                    } else if (t.startsWith("org.talend.remote.jobserver.server.TalendJobServer.MONITORING_PORT")) {
                        t = "org.talend.remote.jobserver.server.TalendJobServer.MONITORING_PORT=" + ports[2];
                    }
                    bw.write(t + "\n");
                }
                bw.flush();
            } finally {
                if (null != br) {
                    br.close();
                }
                if (null != bw) {
                    bw.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        f.delete();
        tmp.renameTo(f);
        // log(Arrays.toString(ports));
        // System.out.print(Arrays.toString(ports) + ";");
        portsBuilder.append(Arrays.toString(ports)).append(";");
    }

    private void log(String msg) {
        System.out.println(">>> " + msg);
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public static void main(String[] args) {
        String jobServerZipPath = "e:/toJobServer";
        int start = 1;
        int end = 1;

        int length = start + end;
        BuildMultiJobServer builder = new BuildMultiJobServer(start, length);
        try {
            builder.buildMultiJobServer(jobServerZipPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
