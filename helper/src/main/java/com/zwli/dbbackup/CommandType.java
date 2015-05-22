// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package com.zwli.dbbackup;

/**
 * DOC zwli class global comment. Detailed comment
 */
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
