package com.zwli.dbbackup;

public enum CommandType {

    MYSQL("mysqldump -u {userName} -p{password} --add-drop-database -B {databaseName} -r {backupPath}"),
    ORACLE("exp {userName}/{password}@{databaseName} file={backupPath}"),
    SQLSERVER("sqlcmd -U {userName} -P {password} -Q \"BACKUP DATABASE [{databaseName}] TO DISK='{backupPath}'\""),
    POSTGRESQL("pg_dump -f {backupPath} -U {userName} {databaseName}"), ;

    private String label;

    private CommandType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
