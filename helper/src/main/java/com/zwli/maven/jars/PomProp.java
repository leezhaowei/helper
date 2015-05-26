package com.zwli.maven.jars;

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
