package com.zwli.datastructure.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zwli.datastructure.example.model.DsCourse;
import com.zwli.datastructure.example.model.DsPerson;

public class TestApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        testGetCoursesBaseOnFriendsOfUser(context);

        context.close();
    }

    static void testGetCoursesBaseOnFriendsOfUser(ConfigurableApplicationContext context) {
        Integer userId = 1;
        DsHandler handler = (DsHandler) context.getBean("handler");
        List<DsPerson> persons = handler.getPersonsByUserId(userId);
        // printList(persons);
        List<DsCourse> coursesOfUser = handler.getCourseByUserId(userId);
        // printList(coursesOfUser);

        Set<DsCourse> coursesOfFriends = new HashSet<DsCourse>();
        for (DsPerson d : persons) {
            List<DsCourse> courses = handler.getCourseByUserId(d.getId());
            // printList(courses);
            // System.out.println();
            coursesOfFriends.addAll(courses);
        }
        // printSet(coursesOfFriends);

        List<DsCourse> coursesNotAssignUser = new ArrayList<DsCourse>();
        for (DsCourse d : coursesOfFriends) {
            if (!coursesOfUser.contains(d)) {
                coursesNotAssignUser.add(d);
            }
        }

        Collections.sort(coursesNotAssignUser, new Comparator<DsCourse>() {

            @Override
            public int compare(DsCourse o1, DsCourse o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });
        printList(coursesNotAssignUser);
    }

    static <T> void print(T t) {
        System.out.println(t);
    }

    static <T> void printList(List<T> list) {
        for (T t : list) {
            System.out.println(t);
        }
    }

    static <T> void printSet(Set<T> set) {
        for (T t : set) {
            System.out.println(t);
        }
    }
}
