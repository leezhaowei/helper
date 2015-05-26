package com.zwli.maven.jars;

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
