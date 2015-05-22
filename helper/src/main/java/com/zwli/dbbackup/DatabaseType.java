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
