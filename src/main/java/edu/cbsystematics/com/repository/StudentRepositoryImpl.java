package edu.cbsystematics.com.repository;

import edu.cbsystematics.com.exception.StudentNotFoundException;
import edu.cbsystematics.com.model.Student;
import edu.cbsystematics.com.util.StudentUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final Map<Long, Student> studentsMap = new HashMap<>();
    private final StudentUtil studentUtil;

    public StudentRepositoryImpl(StudentUtil studentUtil) {
        this.studentUtil = studentUtil;
        // Load students from JSON file when creating the repository
        loadStudentsFromJson();
    }

    /**
     * Helper method to load students from the JSON file.
     * This method calls the StudentUtil to read the data from the JSON file.
     */
    private void loadStudentsFromJson() {
        List<Student> students = studentUtil.readStudentsFromJSON();
        studentsMap.clear();
        for (Student student : students) {
            studentsMap.put(student.getId(), student);
        }
    }

    @Override
    public void saveStudent(Student student) {
        // Generate a new ID for the student
        Long id = findNextAvailableId();
        student.setId(id);

        // Save the student to the map
        studentsMap.put(id, student);

        // Update the JSON file with the new list of students
        studentUtil.saveStudentToJSON(student);
        System.out.println("Saved student (" + student.getFirstname() + " " + student.getLastname() + ") with ID: " + student.getId());
    }

    @Override
    public void updateStudent(Student studentToUpdate) {
        Long id = studentToUpdate.getId();

        // Check if the student exists in the map
        if (!studentsMap.containsKey(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " not found.");
        }

        // Update the student in the map
        studentsMap.put(id, studentToUpdate);

        // Update the JSON file with the updated list of students
        studentUtil.saveStudentsToJSON(new ArrayList<>(studentsMap.values()));
        System.out.println("Updated student with ID: " + studentToUpdate.getId());
    }

    @Override
    public void deleteStudent(Long id) {
        // Check if the student exists in the map
        if (!studentsMap.containsKey(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " not found.");
        }

        // Delete the student from the map
        studentsMap.remove(id);

        // Update the JSON file with the updated list of students
        studentUtil.saveStudentsToJSON(new ArrayList<>(studentsMap.values()));
        System.out.println("Deleted student with ID: " + id);
    }

    @Override
    public Student getStudentById(Long id) {
        // Check if the student exists in the map
        if (!studentsMap.containsKey(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " not found.");
        }

        System.out.println("Getting student with ID: " + id);
        return studentsMap.get(id);
    }

    @Override
    public List<Student> getAllStudents() {
        System.out.println("Fetching all students.");
        return new ArrayList<>(studentsMap.values());
    }

    @Override
    public Long findNextAvailableId() {
        // Find the maximum ID currently used
        Long maxId = studentsMap.keySet().stream().max(Long::compareTo).orElse(0L);

        // Calculate the next available ID
        Long nextId = maxId + 1;

        // Print a message with the result
        System.out.println("Next available ID: " + nextId);

        return nextId;
    }

    @Override
    public int getTotalRecords() {
        return studentsMap.size();
    }

}