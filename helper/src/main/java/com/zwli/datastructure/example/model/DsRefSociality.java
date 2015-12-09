package com.zwli.datastructure.example.model;

import java.io.Serializable;

public class DsRefSociality implements Serializable {

    private static final long serialVersionUID = -1012326556549221593L;
    private Integer studentId;
    private Integer friendId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((friendId == null) ? 0 : friendId.hashCode());
        result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
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
        if (studentId == null) {
            if (other.studentId != null) {
                return false;
            }
        } else if (!studentId.equals(other.studentId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DsRefSociality [studentId=" + studentId + ", friendId=" + friendId + "]";
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

}
