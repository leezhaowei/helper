package com.zwli.datastructure.example.model;

import java.io.Serializable;

public class DsRefStucource implements Serializable {

    private static final long serialVersionUID = 7752104123529344435L;
    private Integer studentId;
    private Integer courseId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
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
        DsRefStucource other = (DsRefStucource) obj;
        if (courseId == null) {
            if (other.courseId != null) {
                return false;
            }
        } else if (!courseId.equals(other.courseId)) {
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
        return "DsRefStucource [studentId=" + studentId + ", courseId=" + courseId + "]";
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

}
