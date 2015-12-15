package com.zwli.research.dbbackup;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DatabaseBackupHandler {

    private static Logger log = Logger.getLogger(DatabaseBackupHandler.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

    private static DatabaseBackupHandler instance = new DatabaseBackupHandler();

    public static DatabaseBackupHandler getInstance() {
        return instance;
    }

    private DatabaseBackupHandler() {
        super();
    }

    public static void startDatabaseBackup(DatabaseBackup backup) throws BusinessException {
        if (!backup.isActive()) {
            return;
        }

        if (!checkDatabaseBackup(backup)) {
            throw new BusinessException("database.backup.error.invalidBackupPrm");
        }

        backupDatabase(backup);
    }

    private static boolean checkDatabaseBackup(DatabaseBackup backup) {
        DatabaseType type = DatabaseType.valueOf(backup.getDatabaseType());
        String pwd = EncryptionHandler.getInstance().decrypt(backup.getPassword());
        try {
            DbChecker.checkDbConnection(getDriverName(type), getUrl(type, backup.getDatabaseName()),
                    backup.getUsername(), pwd);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return false;
        }

        String dumpPath = backup.getDumpPath();
        if (null == dumpPath || "".equals(dumpPath)) {
            return false;
        }
        File file = new File(dumpPath);
        if (!file.isDirectory()) {
            return false;
        }
        return true;
    }

    private static String getDriverName(DatabaseType type) {
        String driverName = null;
        switch (type) {
        case MYSQL:
            driverName = SupportDBUrlType.MYSQL.getDriver();
            break;
        case ORACLE:
            driverName = SupportDBUrlType.ORACLE.getDriver();
            break;
        case SQLSERVER:
            driverName = SupportDBUrlType.SQLSERVER.getDriver();
            break;
        case POSTGRESQL:
            driverName = SupportDBUrlType.POSTGRESQL.getDriver();
            break;
        }
        return driverName;
    }

    private static String getUrl(DatabaseType type, String databaseName) {
        String url = null;
        switch (type) {
        case MYSQL:
            url = SupportDBUrlType.MYSQL.getUrl();
            break;
        case ORACLE:
            url = SupportDBUrlType.ORACLE.getUrl();
            break;
        case SQLSERVER:
            url = SupportDBUrlType.SQLSERVER.getUrl();
            break;
        case POSTGRESQL:
            url = SupportDBUrlType.POSTGRESQL.getUrl();
            break;
        }
        if (null != url && !"".equals(url)) {
            String placeholderAddress = "{ip_address}";
            String placeholderDbName = "{db_name}";
            String address = "localhost";
            url = url.replace(placeholderAddress, address);
            url = url.replace(placeholderDbName, databaseName);
        }
        return url;
    }

    public String getCommandByDatabase(DatabaseType type) {
        String toReturn = null;
        switch (type) {
        case MYSQL:
            toReturn = CommandType.MYSQL.getLabel();
            break;
        case ORACLE:
            toReturn = CommandType.ORACLE.getLabel();
            break;
        case SQLSERVER:
            toReturn = CommandType.SQLSERVER.getLabel();
            break;
        case POSTGRESQL:
            toReturn = CommandType.POSTGRESQL.getLabel();
            break;
        }
        return toReturn;
    }

    private static void backupDatabase(DatabaseBackup backup) throws BusinessException {
        String backupFile = getBackupFile(backup);
        String execCmd = null;
        if (DatabaseType.POSTGRESQL.name().equals(backup.getDatabaseType())) {
            execCmd = backupPgSql(backup);
            log.debug("### database backup command: " + execCmd);
        } else {
            execCmd = getBackupCommand(backup);
            log.debug("### database backup command: " + execCmd);
            if (!execCommand(backup.getDatabaseName(), execCmd, backupFile)) {
                throw new BusinessException("database.backup.error.failedBackup", execCmd);
            }
        }
        validateBackupFile(execCmd, backupFile);
    }

    private static void validateBackupFile(String execCmd, String filePath) {
        File f = new File(filePath);
        if (!f.exists() && f.length() < 0) {
            log.error("### Database backup failed, command: " + execCmd);
        } else {
            log.info("### Database backup succeeded, file location: " + filePath);
        }
    }

    private static String getBackupCommand(DatabaseBackup backup) {
        DatabaseType type = DatabaseType.valueOf(backup.getDatabaseType());
        String execCmd = getInstance().getCommandByDatabase(type);
        if (null != execCmd && !"".equals(execCmd)) {
            String placeholderUsername = "{userName}";
            String placeholderPwd = "{password}";
            String placeholderDbName = "{databaseName}";
            String placeholderPath = "{backupPath}";
            execCmd = execCmd.replace(placeholderUsername, backup.getUsername());
            execCmd = execCmd.replace(placeholderPwd, EncryptionHandler.getInstance().decrypt(backup.getPassword()));
            execCmd = execCmd.replace(placeholderDbName, backup.getDatabaseName());
            execCmd = execCmd.replace(placeholderPath, getBackupFile(backup));
        }
        return execCmd;
    }

    private static String getBackupFile(DatabaseBackup backup) {
        DatabaseType type = DatabaseType.valueOf(backup.getDatabaseType());
        String backupFile = null;
        switch (type) {
        case MYSQL:
            backupFile = "mysql_" + buildBackupFileName(backup.getDatabaseName()) + ".sql";
            break;
        case ORACLE:
            backupFile = "oracle_" + buildBackupFileName(backup.getDatabaseName()) + ".dump";
            break;
        case SQLSERVER:
            backupFile = "sqlserver_" + buildBackupFileName(backup.getDatabaseName()) + ".bak";
            break;
        case POSTGRESQL:
            backupFile = "postgresql_" + buildBackupFileName(backup.getDatabaseName()) + ".sql";
            break;
        }
        String dumpPath = adjustDumpPath(backup.getDumpPath());
        return dumpPath + backupFile;
    }

    private static String adjustDumpPath(String dumpPath) {
        String slash = "/";
        String backslash = "\\";
        if (!dumpPath.endsWith(slash) && !dumpPath.endsWith(backslash)) {
            dumpPath += File.separator;
        }
        return dumpPath;
    }

    private static synchronized String buildBackupFileName(String dbName) {
        return dbName + "_" + sdf.format(new Date());
    }

    private static synchronized boolean execCommand(String dbName, String execCmd, String dumpPath) {
        boolean ranOK = false;
        try {
            Runtime.getRuntime().exec(execCmd);
            ranOK = true;
            log.info("Database: [" + dbName + "] backup created successfully, backup file location: " + dumpPath);
        } catch (Throwable e) {
            ranOK = false;
            log.error("Database: [" + dbName + "] could not create the backup", e);
        }
        return ranOK;
    }

    public static String backupPgSql(DatabaseBackup backup) {
        BufferedReader reader = null;
        try {
            try {
                String backupFile = getBackupFile(backup);
                ProcessBuilder builder = new ProcessBuilder("pg_dump.exe", "-f", backupFile, "-U",
                        backup.getUsername(), backup.getDatabaseName());
                builder.environment().put("PGPASSWORD", EncryptionHandler.getInstance().decrypt(backup.getPassword()));
                builder.redirectErrorStream(true);
                Process p = builder.start();
                reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                StringBuilder line = new StringBuilder();
                while (reader.ready()) {
                    String t = reader.readLine();
                    if (null != t && !"".equals(t)) {
                        line.append(t);
                        line.append("\n");
                    }
                }
                if (line.length() > 0) {
                    log.error("### " + line.toString());
                }
                line = new StringBuilder();
                for (String c : builder.command()) {
                    line.append(c).append(" ");
                }
                return line.toString();
            } finally {
                if (null != reader) {
                    reader.close();
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static void main(String[] args) {
        String backupPath = "F:/TEMP/backups";
        testPg(backupPath);
    }

    static void testPg(String backupPath) {
        String dbName = "tis54";
        String dbUserName = "tisadmin";
        String dbPassword = EncryptionHandler.getInstance().encrypt("tisadmin");
        DatabaseBackup backup = new DatabaseBackup();
        backup.setActive(true);
        backup.setUsername(dbUserName);
        backup.setPassword(dbPassword);
        backup.setDatabaseName(dbName);
        backup.setDatabaseType(DatabaseType.POSTGRESQL.name());
        backup.setDumpPath(backupPath);
        // backupPgSql(backup);

        try {
            startDatabaseBackup(backup);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

}
