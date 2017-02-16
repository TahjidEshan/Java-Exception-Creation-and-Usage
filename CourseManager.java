//package main.java.com.bracu.lab02;

import FacultyNotFoundException;
import NoSuchCourseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CourseManager {

    Map<String, List<Course>> courseDb = new HashMap<String, List<Course>>();
    Scanner scanner = new Scanner(System.in);
    FacultyManager facultyManager;

    public CourseManager(FacultyManager m) {
        facultyManager = m;
    }

    public void addCourse(String departmentName, String courseName, String facultyName)
            throws FacultyNotFoundException {

        Course course = new Course(courseName, facultyName);
        List<Course> courseList = courseDb.get(departmentName);
        if (courseList == null) {
            courseList = new ArrayList<Course>();
        }

        if (facultyManager.facultyExists(course.getCourseFaculty())) {
            courseList.add(course);
        } else {
            throw new FacultyNotFoundException("Faculty " + facultyName + " does not exist");
        }
        courseDb.put(departmentName, courseList);
        System.out.println("Course saved.");
    }

    public void deleteCourse(Course aCourse) throws NoSuchCourseException {
        boolean exists = false;
        for (String key : courseDb.keySet()) {
            List<Course> courseList = courseDb.get(key);

            for (Course course : courseList) {
                if (course.getCourseName().equals(aCourse.getCourseName())) {
                    courseList.remove(course);
                    exists = true;
                    courseDb.put(key, courseList);
                    System.out.println("Course deleted");
                    break;
                }
            }
        }

        if (!exists) {
            throw new NoSuchCourseException("Course '" + aCourse + "' does not exist.");
        }

    }

    public Course getCourse(String courseName) throws NoSuchCourseException {
        for (String key : courseDb.keySet()) {
            List<Course> courseList = courseDb.get(key);

            for (Course course : courseList) {
                if (course.getCourseName().equals(courseName)) {
                    return course;
                }
            }
        }
        throw new NoSuchCourseException("Course '" + courseName + "' does not exist.");
    }

    public void updateCourse(String courseName) throws NoSuchCourseException {
        boolean exists = false;
        for (String key : courseDb.keySet()) {
            List<Course> courseList = courseDb.get(key);

            for (Course course : courseList) {
                if (course.getCourseName().equals(courseName)) {
                    Course temp = course;
                    String department = key;
                    courseList.remove(course);
                    while (true) {
                        System.out.println("Press 1 to update Course Name");
                        System.out.println("Press 2 to update Course Faculty");
                        int val = scanner.nextInt();
                        String dummy = scanner.nextLine();
                        if (val == 1) {
                            System.out.println("Type new course name");
                            String str = scanner.nextLine();
                            temp.setCourseName(str);
                        } else if (val == 2) {
                            System.out.println("Type new faculty name");
                            String str = scanner.nextLine();
                            temp.setCourseFaculty(str);
                        }
                        try {
                            addCourse(key, temp.getCourseName(), temp.getCourseFaculty());
                        } catch (FacultyNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        System.out.println("Press 'y' to update more, press 'n' otherwise");
                        String st = scanner.nextLine();
                        if (st.equals("n")) {
                            break;
                        }
                    }
                    exists = true;
                    break;
                }
            }
        }

        if (!exists) {
            throw new NoSuchCourseException("Course '" + courseName + "' does not exist.");
        } else {
            System.out.println("Course info updated");
        }

    }

    public void printAllCourses() {

        for (String key : courseDb.keySet()) {
            List<Course> courseList = courseDb.get(key);

            String coursesToPrint = null;
            for (Course course : courseList) {
                if (coursesToPrint == null) {
                    coursesToPrint = course.getCourseName();
                } else {
                    coursesToPrint = coursesToPrint + ", " + course.getCourseName();
                }
            }
            System.out.println(key + ": " + coursesToPrint);
        }

    }

}
