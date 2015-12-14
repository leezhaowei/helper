package com.zwli.datastructure.example.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zwli.datastructure.example.dao.wrapper.IdWrapper;
import com.zwli.datastructure.example.model.DsCourse;
import com.zwli.datastructure.example.model.DsPerson;

public class TestDaoApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // testDsPersonDao(context);
        // testDsCourseDao(context);

        context.close();
    }

    static void testDsCourseDao(ConfigurableApplicationContext context) {
        DsCourseDao dao = (DsCourseDao) context.getBean("dsCourseDao");
        List<DsCourse> list = dao.listAll();
        printList(list);
        DsCourse e = dao.findById(new IdWrapper(2));
        System.out.println(e);
        List<Integer> idList = Arrays.asList(new Integer[] { 21, 22, 23 });
        list = dao.listCoursesByUserId(idList);
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
}
