package edu.cbsystematics.com;

import edu.cbsystematics.com.model.Student;
import edu.cbsystematics.com.repository.StudentRepositoryImpl;
import edu.cbsystematics.com.util.StudentUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static final String JSON_FILE = "/students.json";

    public static void main(String[] args) {

        // Create an instance of the StudentUtil class
        StudentUtil studentUtil = new StudentUtil();

        // Create an instance of the StudentRepositoryImpl class
        StudentRepositoryImpl studentRepository = new StudentRepositoryImpl(studentUtil);

        // Creating list of new students
        List<Student> newStudents = createListOfStudents();

        // Saving the updated list of students to the JSON file
        studentUtil.saveStudentsToJSON(newStudents);

        // Getting a list of all students after adding new students
        List<Student> students1 = studentRepository.getAllStudents();
        printStudents(students1);

        long id = 2L;
        studentRepository.deleteStudent(id);

        // Getting a list of all students after deleting a student
        List<Student> students2 = studentRepository.getAllStudents();
        printStudents(students2);

    }

    // Method to create a list of 10 students
    private static List<Student> createListOfStudents() {
        List<Student> students = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Adding 10 students to the list
        students.add(new Student(1L,"John", "Doe", LocalDate.parse("15.05.1988", formatter), "john.doe@example.com", "+11234567890"));
        students.add(new Student(2L,"Jane", "Smith", LocalDate.parse("25.08.1998", formatter), "jane.smith@example.com", "+19876543210"));
        students.add(new Student(3L,"Michael", "Johnson", LocalDate.parse("10.03.1997", formatter), "michael.johnson@example.com", "+14567890123"));
        students.add(new Student(4L,"Emily", "Brown", LocalDate.parse("05.10.1996", formatter), "emily.brown@example.com", "+17890123456"));
        students.add(new Student(5L,"William", "Lee", LocalDate.parse("20.12.1982", formatter), "william.lee@example.com", "+12345678901"));
        students.add(new Student(6L,"Olivia", "Wang", LocalDate.parse("30.07.1985", formatter), "olivia.wang@example.com", "+19081726354"));
        students.add(new Student(7L,"James", "Kim", LocalDate.parse("08.04.1993", formatter), "james.kim@example.com", "+13456789012"));
        students.add(new Student(8L,"Sophia", "Chen", LocalDate.parse("18.06.2000", formatter), "sophia.chen@example.com", "+15678901234"));
        students.add(new Student(9L,"Alexander", "Liu", LocalDate.parse("01.11.1992", formatter), "alexander.liu@example.com", "+16789012345"));
        students.add(new Student(10L,"Ava", "Garcia", LocalDate.parse("12.09.1991", formatter), "ava.garcia@example.com", "+18901234567"));

        return students;
    }

    // Method to print the list of students
    private static void printStudents(List<Student> students) {
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-22s | %-10s | %-28s | %-16s |%n", "ID", "Full Name", "Birth", "Email", "Phone");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("| %-5d | %-22s | %-10s | %-28s | %-16s |%n",
                    student.getId(), student.getFirstname() + " " + student.getLastname(),
                    student.getBirth().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    student.getEmail(), student.getPhone());
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
    }

}