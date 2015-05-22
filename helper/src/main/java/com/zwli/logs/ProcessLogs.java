package com.zwli.logs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessLogs {

    public static void main(String[] args) {
        // processLogsTup923();
        processLogsTis54();
    }

    private static String WARN_KEYS = "FileSenderClient,JMXClient,ProjectManager";

    private static String ERROR_KEYS = "JMXClient";

    private static String INFO_KEYS = "TalendRemoteServiceServlet,RemoteTaskExecution,SvnProjectRefresher,ClusterManager,TacApplicationInitializer,JobServerStateChecker,"
            + "QuartzConnectionProvider,SchedulerInitializer,TalendSchedulerFactory,TalendJobStoreTX,AbstractDataCleaner,TaskExecutionHistoryBusiness,PatchOrVersionChecker,"
            + "EmbeddedDbBackup,TokenRequiredPolicy,EncryptionHandler,AdministratorLocationHelper,ServerMsgServiceImpl,RemoteDataRetreiver,JMXClient";

    private static String AT_KEYS = "javax.management.remote.JMXConnectorFactory,java.net.PlainSocketImpl,java.net.SocksSocketImpl,java.net.Socket,com.sun.jmx.remote.socket.SocketConnection,"
            + "com.sun.jmx.remote.generic.ClientSynchroMessageConnectionImpl,javax.management.remote.generic.GenericConnector,javax.management.remote.jmxmp.JMXMPConnector,"
            + "org.talend.monitoring.jmx.client.JMXClient,org.talend.monitoring.jmx.client.ServerInfoCentralizer";

    private static String KEYS = "java.net.ConnectException: Connection,java.net.MalformedURLException: Unsupported protocol: jmxmp";

    private static String tup923Keys = "TalendRemoteServiceServlet,JMXClient,FileSenderClient,TacApplicationInitializer,JobServerStateChecker,"
            + "SvnProjectRefresher,QuartzConnectionProvider,SchedulerInitializer,TalendSchedulerFactory,TalendJobStoreTX,AbstractDataCleaner,"
            + "TaskExecutionHistoryBusiness,EmbeddedDbBackup,PatchOrVersionChecker,AdministratorLocationHelper,EncryptionHandler,TokenRequiredPolicy,"
            + "ConfigHandler,PropertiesLoader,ServiceLocatorService,ClientCnxn,ServerMsgServiceImpl,RemoteDataRetreiver,\tat,Exception: ";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");

    private static String sequence() {
        String seq = sdf.format(new Date());
        return seq;
    }

    public static void processLogsTis54() {
        String inFileName = "f:/TAC/logs/tis54/tis54.log";
        String outFileName = "f:/TEMP/taclogs/tis54_" + sequence() + ".log";
        // outFileName = "e:/Tmp/aws/tis54_" + (new Date()).getTime() + ".log";

        // inFileName = "f:/TAC/logs/tac54/tac54B.log";
        // outFileName = "f:/TEMP/taclogs/tac54B_" + (new Date()).getTime() + ".log";
        //
        // inFileName = "f:/TAC/logs/tac54/tac54A.log";
        // outFileName = "f:/TEMP/taclogs/tac54A_" + (new Date()).getTime() + ".log";

        String[] arr = INFO_KEYS.split(",");
        StringBuilder key = new StringBuilder();
        for (String s : arr) {
            key.append("INFO  ").append(s).append(",");
        }
        arr = WARN_KEYS.split(",");
        for (String s : arr) {
            key.append("WARN  ").append(s).append(",");
        }
        arr = ERROR_KEYS.split(",");
        for (String s : arr) {
            key.append("ERROR ").append(s).append(",");
        }
        arr = AT_KEYS.split(",");
        for (String s : arr) {
            key.append("\tat ").append(s).append(",");
        }
        key.append(KEYS);

        String[] keys = key.toString().split(",");
        processLogs(inFileName, outFileName, keys);
    }

    public static void processLogsTup923() {
        String inFileName = "f:/TAC/logs/tis54/tup923.log";
        String outFileName = "f:/TEMP/taclogs/tup923_" + (new Date()).getTime() + ".log";
        String[] keys = tup923Keys.split(",");
        processLogs(inFileName, outFileName, keys);
    }

    private static void processLogs(String inFileName, String outFileName, String[] keys) {
        BufferedReader in = null;
        FileWriter out = null;
        boolean toOutput = true;
        try {
            try {
                in = new BufferedReader(new FileReader(inFileName));
                out = new FileWriter(outFileName);
                String line;
                while (in.ready()) {
                    line = in.readLine();
                    toOutput = checkLog(keys, line);
                    if (toOutput) {
                        out.write(line + "\n");
                    }
                }
                out.flush();
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkLog(String[] keys, String line) {
        if (null == line || line.isEmpty() || line.trim().isEmpty()) {
            return false;
        }
        for (String key : keys) {
            if (line.contains(key)) {
                return false;
            }
        }
        return true;
    }
}
