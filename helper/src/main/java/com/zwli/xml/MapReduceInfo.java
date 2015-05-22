package com.zwli.xml;

import java.io.Serializable;

public class MapReduceInfo implements Serializable {

    private static final long serialVersionUID = -5185772401299718478L;

    private String nodeName;

    private String tagName;

    private String groupId;

    private String mrId;

    private int width;

    private int height;

    private Coordinate coordinate;

    public MapReduceInfo(String nodeName, String tagName, String groupId, String mrId, int width, int height,
            Coordinate coordinate) {
        super();
        this.nodeName = nodeName;
        this.tagName = tagName;
        this.groupId = groupId;
        this.mrId = mrId;
        this.width = width;
        this.height = height;
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "MapReduceInfo [nodeName=" + this.nodeName + ", tagName=" + this.tagName + ", groupId=" + this.groupId + ", mrId="
                + this.mrId + ", width=" + this.width + ", height=" + this.height + ", coordinate=" + this.coordinate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.coordinate == null) ? 0 : this.coordinate.hashCode());
        result = prime * result + ((this.groupId == null) ? 0 : this.groupId.hashCode());
        result = prime * result + this.height;
        result = prime * result + ((this.mrId == null) ? 0 : this.mrId.hashCode());
        result = prime * result + ((this.nodeName == null) ? 0 : this.nodeName.hashCode());
        result = prime * result + ((this.tagName == null) ? 0 : this.tagName.hashCode());
        result = prime * result + this.width;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MapReduceInfo other = (MapReduceInfo) obj;
        if (this.coordinate == null) {
            if (other.coordinate != null) {
                return false;
            }
        } else if (!this.coordinate.equals(other.coordinate)) {
            return false;
        }
        if (this.groupId == null) {
            if (other.groupId != null) {
                return false;
            }
        } else if (!this.groupId.equals(other.groupId)) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.mrId == null) {
            if (other.mrId != null) {
                return false;
            }
        } else if (!this.mrId.equals(other.mrId)) {
            return false;
        }
        if (this.nodeName == null) {
            if (other.nodeName != null) {
                return false;
            }
        } else if (!this.nodeName.equals(other.nodeName)) {
            return false;
        }
        if (this.tagName == null) {
            if (other.tagName != null) {
                return false;
            }
        } else if (!this.tagName.equals(other.tagName)) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        return true;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getTagName() {
        return this.tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMrId() {
        return this.mrId;
    }

    public void setMrId(String mrId) {
        this.mrId = mrId;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
