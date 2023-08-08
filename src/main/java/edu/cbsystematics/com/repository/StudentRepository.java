package edu.cbsystematics.com.repository;

import edu.cbsystematics.com.model.Student;

import java.io.IOException;
import java.util.List;

public interface StudentRepository {

    // Method to save a student to the file.
    void saveStudent(Student student) throws IOException;

    // Update an existing student's information.
    void updateStudent(Student studentToUpdate) throws IOException;

    // Delete a student by their ID.
    void deleteStudent(Long id) throws IOException;

    // Get a student by their ID.
    Student getStudentById(Long id);

    // Retrieve a list of all students.
    List<Student> getAllStudents();

    // Method to find the next available ID.
    Long findNextAvailableId();

    // Returns the number of records in the file.
    int getTotalRecords();

}
