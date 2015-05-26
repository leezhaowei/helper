package com.zwli.dbbackup;

public enum DatabaseType {

    MYSQL("Mysql"),
    ORACLE("Oracle"),
    SQLSERVER("Sql Server"),
    POSTGRESQL("PostgreSQL"), ;

    private String label;

    private DatabaseType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static DatabaseType getDatabaseTypeByLabel(String label) {
        for (DatabaseType type : DatabaseType.values()) {
            if (type.getLabel().equals(label)) {
                return type;
            }
        }
        return null;
    }
}
