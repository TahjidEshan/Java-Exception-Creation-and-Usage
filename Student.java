/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package main.java.com.bracu.lab02;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Eshan
 */
public class Student {

    static String Name;
    static String Department;
    static List<String> Courses = new ArrayList<>();
    static Iterator<String> it;

    public Student() {
    }

    public Student(String a, String b, List<String> c) {
        Name = a;
        Department = b;
        Courses = c;

    }

    public static String printCourses() {
        String str = " ";
        List<String> CourseList=Courses;
        it = CourseList.iterator();
        while (it.hasNext()) {
            if (str.equals(" ")) {
                str = it.next();
            }
            else
            {
                str=str+", "+it.next();
            }
        }
        return str;
    }

}
