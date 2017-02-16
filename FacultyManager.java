//package main.java.com.bracu.lab02;

import FacultyInitialAlreadyExistsException;
import FacultyNotFoundException;
import NoSuchDepartmentException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class FacultyManager {

    Map<Integer, Faculty> facultyDb = new HashMap<Integer, Faculty>();
    Scanner scanner = new Scanner(System.in);
    List<String> dept;

    public FacultyManager(List<String> a) {
        dept = a;
    }

    public void addFaculty(String facultyInitial, String facultyName, String department)
            throws FacultyInitialAlreadyExistsException {
        if (facultyExists(facultyInitial)) {
            throw new FacultyInitialAlreadyExistsException();
        }

        Faculty faculty = new Faculty();
        faculty.setFacultyInitial(facultyInitial);
        faculty.setFacultyName(facultyName);
        if (dept.contains(department)) {
            faculty.setDepartment(department);
        } else {
            try {
                throw new NoSuchDepartmentException();
            } catch (NoSuchDepartmentException ex) {
                ex.printStackTrace();
            }
        }
        int size = facultyDb.size();
        facultyDb.put(size + 1, faculty);
        System.out.println("Faculty saved. " + facultyDb.size());
    }

    public void printAllFaculties() {
        System.out.println(facultyDb.size());
        for (Integer key : facultyDb.keySet()) {
            System.out.println(key + ":\n" + facultyDb.get(key).toString());
        }

    }

    public void getFaculty(String facultyInitial) throws FacultyNotFoundException {
        boolean exists = false;
        for (Integer key : facultyDb.keySet()) {
            Faculty faculty = facultyDb.get(key);
            if (faculty.getFacultyInitial().equals(facultyInitial)) {
                System.out.println(key + ":\n" + facultyDb.get(key).toString());
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new FacultyNotFoundException("Faculty " + facultyInitial + " does not exist");
        }

    }

    public boolean facultyExists(String facultyInitial) {
        for (Integer key : facultyDb.keySet()) {
            Faculty faculty = facultyDb.get(key);
            if (faculty.getFacultyInitial().equals(facultyInitial)) {
                return true;
            }
        }

        return false;
    }

    public void deleteFaculty(String facultyInitial) throws FacultyNotFoundException {
        if (facultyExists(facultyInitial)) {
            Iterator<Map.Entry<Integer, Faculty>> it = facultyDb.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Faculty> entry = it.next();
                if (facultyInitial.equals(entry.getValue().facultyInitial)) {
                    it.remove();
                    System.out.println("Faculty successfully removed");
                    break;
                }
            }
        } else {
            throw new FacultyNotFoundException("Faculty " + facultyInitial + " does not exist");
        }

    }

    public void updateFaculty(String facultyInitial) throws FacultyNotFoundException {
        if (facultyExists(facultyInitial)) {
            Faculty temp;
            for (Integer key : facultyDb.keySet()) {
                Faculty faculty = facultyDb.get(key);
                if (faculty.getFacultyInitial().equals(facultyInitial)) {
                    temp = faculty;
                    deleteFaculty(faculty.getFacultyInitial());
                    while (true) {
                        System.out.println("Press 1 to update faculty initial");
                        System.out.println("Press 2 to update faculty name");
                        System.out.println("Press 3 to update faculty department");
                        int val = scanner.nextInt();
                        String dummy = scanner.nextLine();
                        if (val == 1) {
                            System.out.println("Type new faculty initial");
                            temp.setFacultyInitial(scanner.nextLine());
                        } else if (val == 2) {
                            System.out.println("Type new faculty name");
                            temp.setFacultyName(scanner.nextLine());
                        } else if (val == 3) {
                            System.out.println("Type new faculty department");
                            String dp = scanner.nextLine();
                            if (dept.contains(dp)) {
                                temp.setDepartment(dp);
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
                        addFaculty(temp.facultyInitial, temp.facultyName, temp.department);
                    } catch (FacultyInitialAlreadyExistsException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
            }

        } else {
            throw new FacultyNotFoundException("Faculty " + facultyInitial + " does not exist");
        }
    }
}
