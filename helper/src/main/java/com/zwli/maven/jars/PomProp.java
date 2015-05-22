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
package com.zwli.maven.jars;

/**
 * created by zwli on Apr 28, 2014 Detailled comment
 * 
 */
public class PomProp {

    public String gwtVersion;

    public String talendRevision;

    public String talendVersion;

    public String tacVersion;

    public PomProp(String gwtVersion, String talendRevision, String talendVersion) {
        this.gwtVersion = gwtVersion;
        this.talendRevision = talendRevision;
        this.talendVersion = talendVersion;
    }

    @Override
    public String toString() {
        return "PomProp [gwtVersion=" + gwtVersion + ", talendRevision=" + talendRevision + ", talendVersion=" + talendVersion
                + "]";
    }
}
