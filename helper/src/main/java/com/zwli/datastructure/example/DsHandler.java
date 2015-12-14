package com.zwli.datastructure.example;

import java.util.ArrayList;
import java.util.List;

import com.zwli.datastructure.example.dao.DsCourseDao;
import com.zwli.datastructure.example.dao.DsPersonDao;
import com.zwli.datastructure.example.dao.DsRefDao;
import com.zwli.datastructure.example.dao.wrapper.IdWrapper;
import com.zwli.datastructure.example.model.DsCourse;
import com.zwli.datastructure.example.model.DsPerson;
import com.zwli.datastructure.example.model.DsRefSociality;
import com.zwli.datastructure.example.model.DsRefStucource;

public class DsHandler {

    private DsPersonDao dsPersonDao;
    private DsCourseDao dsCourseDao;
    private DsRefDao dsRefDao;

    public List<DsCourse> getCourseByUserId(Integer userId) {
        List<DsRefStucource> courseIdList = dsRefDao.listCourseIdsByUserId(userId);
        List<DsCourse> courses = new ArrayList<DsCourse>(courseIdList.size());
        for (DsRefStucource d : courseIdList) {
            DsCourse e = dsCourseDao.findById(new IdWrapper(d.getCourseId()));
            courses.add(e);
        }
        return courses;
    }

    public List<DsPerson> getPersonsByUserId(Integer userId) {
        List<DsRefSociality> friendIdList = dsRefDao.listFriendIdsByUserId(userId);
        List<DsPerson> friends = new ArrayList<DsPerson>(friendIdList.size());
        for (DsRefSociality d : friendIdList) {
            DsPerson p = dsPersonDao.findById(new IdWrapper(d.getFriendId()));
            friends.add(p);
        }
        return friends;
    }

    public void setDsPersonDao(DsPersonDao dsPersonDao) {
        this.dsPersonDao = dsPersonDao;
    }

    public void setDsCourseDao(DsCourseDao dsCourseDao) {
        this.dsCourseDao = dsCourseDao;
    }

    public void setDsRefDao(DsRefDao dsRefDao) {
        this.dsRefDao = dsRefDao;
    }
}
