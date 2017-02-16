/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package main.java.com.bracu.lab02;

import StudentNotFoundException;
import NoSuchDepartmentException;
import StudentAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;

/**
 *
 * @author Eshan
 */
public class StudentManager {

    List<String> departmentList;
    Scanner scanner = new Scanner(System.in);

    public StudentManager(List<String> b) {
        departmentList = b;
    }
    Map<Integer, Student> StudentDb = new HashMap<Integer, Student>();

    public void addStudent(String name, String department, List<String> courses) throws StudentAlreadyExistsException {
        if (StudentExists(name)) {
            throw new StudentAlreadyExistsException();
        }
        Student student = new Student();
        Student.Name = name;
        Student.Department = department;
        Student.Courses = courses;
        int size = StudentDb.size();
        StudentDb.put(size + 1, student);
        System.out.println("Student saved. " + StudentDb.size());

    }

    public void printAllStudents() {
        System.out.println(StudentDb.size());
        for (Integer key : StudentDb.keySet()) {
            System.out.println(key + ":\n" + StudentDb.get(key).Name + ":\n" + StudentDb.get(key).Department + ":\n" + StudentDb.get(key).printCourses());
        }

    }

    public void getStudent(String studentname) throws StudentNotFoundException {
        boolean exists = false;
        for (Integer key : StudentDb.keySet()) {
            Student student = StudentDb.get(key);
            if (Student.Name.equals(studentname)) {
                System.out.println(key + "\n" + StudentDb.get(key).Name + "\n" + StudentDb.get(key).Department + ":\n" + StudentDb.get(key).printCourses());
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new StudentNotFoundException("Student " + studentname + " does not exist");
        }

    }

    public boolean StudentExists(String name) {
        for (Integer key : StudentDb.keySet()) {
            Student student = StudentDb.get(key);
            if (student.Name.equals(name)) {
                return true;
            }
        }

        return false;
    }

    public void deleteStudent(String name) throws StudentNotFoundException {
        if (StudentExists(name)) {
            Iterator<Map.Entry<Integer, Student>> it = StudentDb.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Student> entry = it.next();
                if (name.equals(entry.getValue().Name)) {
                    it.remove();
                    System.out.println("Student successfully removed");
                    break;
                }
            }
        } else {
            throw new StudentNotFoundException("Faculty " + name + " does not exist");
        }

    }

    public void updateStudent(String studentName) throws StudentNotFoundException {
        if (StudentExists(studentName)) {
            Student temp;
            for (Integer key : StudentDb.keySet()) {
                Student student = StudentDb.get(key);
                if (student.Name.equals(studentName)) {
                    temp = student;
                    deleteStudent(student.Name);
                    while (true) {
                        System.out.println("Press 1 to update Student Name");
                        System.out.println("Press 2 to update Student department");
                        System.out.println("Press 3 to update student courses");
                        int val = scanner.nextInt();
                        String dummy = scanner.nextLine();
                        if (val == 1) {
                            System.out.println("Type new Student name");
                            temp.Name = (scanner.nextLine());
                        } else if (val == 3) {
                            student.printCourses();
                            while (true) {
                                System.out.println("Press a to remove courses");
                                System.out.println("Press b to add courses");
                                String value = scanner.nextLine();
                                if (value.equals("a")) {
                                    System.out.println("Type the course Name");
                                    String ma = scanner.nextLine();
                                    temp.Courses.remove(ma);
                                } else if (value.equals("b")) {
                                    System.out.println("Type the course Name");
                                    String ma = scanner.nextLine();
                                    temp.Courses.add(ma);
                                }
                                System.out.println("Press 'y' to update more, press 'n' otherwise");
                                String st = scanner.nextLine();
                                if (st.equals("n")) {
                                    break;
                                }
                            }
                        } else if (val == 2) {
                            System.out.println("Type new Student department");
                            String dp = scanner.nextLine();
                            if (departmentList.contains(dp)) {
                                temp.Department = dp;
                            } else {
                                try {
                                    throw new NoSuchDepartmentException();
                                } catch (NoSuchDepartmentException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        System.out.println("Press 'y' to update more, press 'n' otherwise");
                        String st = scanner.nextLine();
                        if (st.equals("n")) {
                            break;
                        }
                    }
                    try {
                        addStudent(temp.Name, temp.Department, temp.Courses);
                    } catch (StudentAlreadyExistsException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
            }

        } else {
            throw new StudentNotFoundException("Faculty " + studentName + " does not exist");
        }
    }
}
