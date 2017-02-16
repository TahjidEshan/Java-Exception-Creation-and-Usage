//package main.java.com.bracu.lab02;

import FacultyInitialAlreadyExistsException;
import FacultyNotFoundException;
import NoSuchCourseException;
import NoSuchDepartmentException;
import DepartmentAlreadyExistsException;
import StudentNotFoundException;
import StudentAlreadyExistsException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Main {

    public void raiseArithmeticException(int n) throws ArithmeticException {

        int k = n / 0;
    }

    public void raiseArrayIndexOutOfBoundsException(char[] chars, int n)
            throws ArrayIndexOutOfBoundsException {

        chars[chars.length] = 'n';
    }

    public void raiseArrayStoreException(double d) throws ArrayStoreException {
        Object array[] = new String[2];
        array[0] = new Integer(0);

    }

    public void raiseClassCastException() throws ClassCastException {

        Object x = new String("2");
        System.out.println((double) x);

    }

    public void raiseIllegalArgumentException() throws IllegalArgumentException {
        String s = "s";
        int k = Integer.parseInt(s);
    }

    public void raiseIllegalMonitorStateException() throws IllegalMonitorStateException {
        String a = "0";
        try {
            a.wait();
        } catch (InterruptedException e) {
        }

    }

    public void raiseIllegalStateException() throws IllegalStateException {
        List<Integer> s = new ArrayList<>();
        s.add(13);
        Iterator it = s.iterator();
        while (it.hasNext()) {
            it.remove();
        }
    }

    public void raiseIllegalThreadStateException() throws IllegalThreadStateException {
        Runnable r = new MyRunnable();
        Thread thr = new Thread(r);
        thr.start();
        thr.start();

    }

    public void raiseNegativeArraySizeException() throws NegativeArraySizeException {

        int[] a = new int[-1];

    }

    public void raiseNullPointerException() throws NullPointerException {

        int[] num = null;
        int k = num.length;

    }

    public void raiseNumberFormatException() throws NumberFormatException {

        String s = "0.123";
        int a = Integer.parseInt(s);

    }

    public void raiseStringIndexOutOfBoundsException() throws StringIndexOutOfBoundsException {

        String s = "Eshan";
        char c = s.charAt(-1);
    }

    public void raiseIndexOutOfBoundsException() throws IndexOutOfBoundsException {

        String s = "Eshan";
        char c = s.charAt(-1);

    }

    public void raiseNoSuchCourseException() throws NoSuchCourseException {
        List<String> DepartmentList = new ArrayList<>();
        FacultyManager facultyManager = new FacultyManager(DepartmentList);
        CourseManager courseManager = new CourseManager(facultyManager);
        Course c = new Course("ABc", "qwe");
        courseManager.deleteCourse(c);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] commands = new String[]{""};

        List<String> DepartmentList = new ArrayList<>();
        FacultyManager facultyManager = new FacultyManager(DepartmentList);
        CourseManager courseManager = new CourseManager(facultyManager);
        StudentManager studentManager = new StudentManager(DepartmentList);
        String keyInput = "";
        while (true) {
            commands = new String[]{"faculty", "course"};

            keyInput = in.nextLine();

            String subs[] = keyInput.split(" ");

            if (subs.length < 2 && !keyInput.equals("exit")) {
                continue;
            }

            if (keyInput.equals("exit")) {
                System.exit(0);
            } else if (subs[0].equals("course")) {
                if (subs[1].equals("add")) {
                    System.out.print("Type dept name: ");
                    String deptName;
                    while (true) {
                     deptName = in.nextLine();
                        if (!DepartmentList.contains(deptName)) {
                            try {
                                throw new NoSuchDepartmentException();
                            } catch (NoSuchDepartmentException ex) {
                                ex.printStackTrace();
                            }
                            System.out.println("Specify correct department");
                        } else {
                            break;
                        }
                    }
                    System.out.print("Type course name: ");
                    String courseName = in.nextLine();
                    System.out.print("Type faculty name: ");
                    String facultyName = in.nextLine();

                    try {
                        courseManager.addCourse(deptName, courseName, facultyName);
                    } catch (FacultyNotFoundException ex) {
                        ex.printStackTrace();
                    }

                } else if (subs[1].equals("print")) {
                    courseManager.printAllCourses();
                } else if (subs[1].equals("delete")) {
                    System.out.print("Type course name: ");
                    keyInput = in.nextLine();
                    try {
                        Course course = courseManager.getCourse(keyInput);
                        courseManager.deleteCourse(course);
                    } catch (NoSuchCourseException ex) {
                        ex.printStackTrace();
                    }
                } else if (subs[1].equals("get")) {
                    System.out.print("Type course name: ");
                    keyInput = in.nextLine();
                    try {
                        Course course = courseManager.getCourse(keyInput);
                        System.out.println(course.getCourseName() + " (" + course.getCourseFaculty() + ")");
                    } catch (NoSuchCourseException ex) {
                        ex.printStackTrace();
                    }
                } else if (subs[1].equals("update")) {
                    System.out.print("Type course name: ");
                    keyInput = in.nextLine();
                    try {
                        courseManager.updateCourse(keyInput);
                    } catch (NoSuchCourseException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Valid sub-commands are: add, print, delete, get, update");
                }
            } else if (subs[0].equals("faculty")) {
                if (subs[1].equals("add")) {
                    System.out.print("Type faculty name: ");
                    String facultyName = in.nextLine();
                    System.out.print("Type department name: ");
                    String departmentName = in.nextLine();
                    if (!DepartmentList.contains(departmentName)) {
                        try {
                            throw new NoSuchDepartmentException();
                        } catch (NoSuchDepartmentException ex) {
                            ex.printStackTrace();
                        }

                    }
                    System.out.print("Type faculty initial: ");
                    String facultyInitial = in.nextLine();
                    try {
                        facultyManager.addFaculty(facultyInitial, facultyName, departmentName);
                    } catch (FacultyInitialAlreadyExistsException ex) {
                        ex.printStackTrace();
                    }
                } else if (subs[1].equals("print")) {
                    facultyManager.printAllFaculties();
                } else if (subs[1].equals("get")) {
                    System.out.println("Type faculty initial");
                    String str = in.nextLine();
                    try {
                        facultyManager.getFaculty(str);
                    } catch (FacultyNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else if (subs[1].equals("delete")) {
                    System.out.println("Type faculty initial");
                    String str = in.nextLine();
                    try {
                        facultyManager.deleteFaculty(str);
                    } catch (FacultyNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else if (subs[1].equals("update")) {
                    System.out.println("Type faculty initial");
                    String str = in.nextLine();
                    try {
                        facultyManager.updateFaculty(str);
                    } catch (FacultyNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Valid sub-commands are: add, print, delete, get,update");
                }
            } else if (subs[0].equals("department")) {
                if (subs[1].equals("add")) {
                    System.out.print("Type department name: ");
                    keyInput = in.nextLine();
                    boolean exists = false;
                    for (String key : courseManager.courseDb.keySet()) {
                        if (key.equals(keyInput)) {
                            exists = true;
                            try {
                                throw new DepartmentAlreadyExistsException();
                            } catch (DepartmentAlreadyExistsException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (!exists) {
                        DepartmentList.add(keyInput);
                        List<Course> courseList = new ArrayList<Course>();
                        courseManager.courseDb.put(keyInput, courseList);
                        System.out.println("Department saved");
                    }

                } else if (subs[1].equals("delete")) {
                    System.out.print("Type department name: ");
                    keyInput = in.nextLine();
                    DepartmentList.remove(keyInput);
                    boolean exists = false;
                    for (String key : courseManager.courseDb.keySet()) {
                        if (key.equals(keyInput)) {
                            courseManager.courseDb.remove(key);
                            for (Integer val : facultyManager.facultyDb.keySet()) {
                                Faculty faculty = facultyManager.facultyDb.get(val);
                                if (faculty.getDepartment().equals(key)) {
                                    try {
                                        facultyManager.deleteFaculty(faculty.facultyInitial);
                                    } catch (FacultyNotFoundException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }

                            exists = true;
                            System.out.println("Department deleted");
                            break;
                        }
                    }
                    if (!exists) {
                        try {
                            throw new NoSuchDepartmentException();
                        } catch (NoSuchDepartmentException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (subs[1].equals("get")) {
                    System.out.print("Type department name: ");
                    keyInput = in.nextLine();
                    boolean exists = false;
                    for (String key : courseManager.courseDb.keySet()) {
                        if (key.equals(keyInput)) {
                            List<Course> courseList = courseManager.courseDb.get(key);

                            String coursesToPrint = null;
                            for (Course course : courseList) {
                                if (coursesToPrint == null) {
                                    coursesToPrint = course.getCourseName();
                                } else {
                                    coursesToPrint = coursesToPrint + ", " + course.getCourseName();
                                }
                            }
                            System.out.println(key + ": " + coursesToPrint);
                            exists = true;
                        }
                    }
                    if (!exists) {
                        try {
                            throw new NoSuchDepartmentException();
                        } catch (NoSuchDepartmentException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (subs[1].equals("print")) {
                    courseManager.printAllCourses();
                } else {
                    System.out.println("Valid sub-commands are: add, print, delete, get");
                }
            } else if (subs[0].equals("student")) {
                if (subs[1].equals("add")) {
                    System.out.print("Type student name: ");
                    String studentName = in.nextLine();
                    System.out.print("Type department name: ");
                    String departmentName;
                    while (true) {
                     departmentName = in.nextLine();
                        if (!DepartmentList.contains(departmentName)) {
                            try {
                                throw new NoSuchDepartmentException();
                            } catch (NoSuchDepartmentException ex) {
                                ex.printStackTrace();
                            }
                            System.out.println("Specify correct department");
                        } else {
                            break;
                        }
                    }
                    System.out.print("Type courses: ");
                    List<String> courses = new ArrayList<>();
                    while (true) {
                        courses.add(in.nextLine());
                        System.out.println("Press 'n' to stop adding courses',press 'y' otherwise");
                        if (in.nextLine().equals("n")) {
                            break;
                        }
                    }
                    try {
                        studentManager.addStudent(studentName, departmentName, courses);
                    } catch (StudentAlreadyExistsException ex) {
                        ex.printStackTrace();
                    }
                } else if (subs[1].equals("print")) {
                    studentManager.printAllStudents();
                } else if (subs[1].equals("get")) {
                    System.out.println("Type student name");
                    String str = in.nextLine();
                    try {
                        studentManager.getStudent(str);
                    } catch (StudentNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else if (subs[1].equals("delete")) {
                    System.out.println("Type student name");
                    String str = in.nextLine();
                    try {
                        studentManager.deleteStudent(str);
                    } catch (StudentNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else if (subs[1].equals("update")) {
                    System.out.println("Type student name");
                    String str = in.nextLine();
                    try {
                        studentManager.updateStudent(str);
                    } catch (StudentNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Valid sub-commands are: add, print, delete, get,update");
                }
            }
        }
    }
}
