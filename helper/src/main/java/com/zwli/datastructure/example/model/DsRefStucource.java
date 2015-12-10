package com.zwli.datastructure.example.model;

import java.io.Serializable;

public class DsRefStucource extends DsRef implements Serializable {

    private static final long serialVersionUID = 7752104123529344435L;
    private Integer courseId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
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
        DsRefStucource other = (DsRefStucource) obj;
        if (courseId == null) {
            if (other.courseId != null) {
                return false;
            }
        } else if (!courseId.equals(other.courseId)) {
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
        return "DsRefStucource [studentId=" + getStudentId() + ", courseId=" + courseId + "]";
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

}
