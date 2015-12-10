package com.zwli.datastructure.example.model;

import java.io.Serializable;

public class DsRefSociality extends DsRef implements Serializable {

    private static final long serialVersionUID = -1012326556549221593L;
    private Integer friendId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((friendId == null) ? 0 : friendId.hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
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
        DsRefSociality other = (DsRefSociality) obj;
        if (friendId == null) {
            if (other.friendId != null) {
                return false;
            }
        } else if (!friendId.equals(other.friendId)) {
            return false;
        }
        if (getStudentId() == null) {
            if (other.getStudentId() != null) {
                return false;
            }
        } else if (!getStudentId().equals(other.getStudentId())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DsRefSociality [studentId=" + getStudentId() + ", friendId=" + friendId + "]";
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

}
