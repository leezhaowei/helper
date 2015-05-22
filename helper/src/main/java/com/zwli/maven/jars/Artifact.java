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
 */
public class Artifact {

    public String groupId;

    public String artifactId;

    public String version;

    public String jarName;

    public Artifact(String groupId, String artifactId, String version) {
        this(groupId, artifactId, version, null);
    }

    public Artifact(String groupId, String artifactId, String version, String jarName) {
        super();
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.jarName = jarName;
    }

    @Override
    public String toString() {
        return "[groupId=" + this.groupId + ", artifactId=" + this.artifactId + ", version=" + this.version + ", jarName="
                + this.jarName + "]";
    }
}
