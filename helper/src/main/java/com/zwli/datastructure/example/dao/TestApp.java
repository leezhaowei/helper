package com.zwli.datastructure.example.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zwli.datastructure.example.model.DsCourse;
import com.zwli.datastructure.example.model.DsPerson;
import com.zwli.jdbc.dao.Wrapper;

public class TestApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        testDsPersonDao(context);

        context.close();
    }

    static void testDsCourseDao(ConfigurableApplicationContext context) {
        DsCourseDao dsCourseDao = (DsCourseDao) context.getBean("dsCourseDao");
        List<DsCourse> list = dsCourseDao.listAll();
        printList(list);
    }

    static void testDsPersonDao(ConfigurableApplicationContext context) {
        DsPersonDao dao = (DsPersonDao) context.getBean("dsPersonDao");
        List<DsPerson> list = dao.listAll();
        printList(list);
        DsPerson e = dao.findById(new IdWrapper(1));
        print(e);
        List<Integer> idList = Arrays.asList(new Integer[] { 1, 2, 3 });
        list = dao.listFriendsByUserId(idList);
        printList(list);
    }

    static <T> void print(T t) {
        System.out.println(t);
    }

    static <T> void printList(List<T> list) {
        for (T t : list) {
            System.out.println(t);
        }
    }

    static class IdWrapper implements Wrapper {
        Integer value;

        IdWrapper(Integer _value) {
            value = _value;
        }

        @Override
        public Integer value() {
            return value;
        }
    }

}
