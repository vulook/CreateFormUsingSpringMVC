package edu.cbsystematics.com.controller;

import edu.cbsystematics.com.exception.StudentNotFoundException;
import edu.cbsystematics.com.model.Student;
import edu.cbsystematics.com.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.getAllStudents());
        model.addAttribute("totalRecords", studentRepository.getTotalRecords());
        return "student-list";
    }

    @GetMapping("/details/{id}")
    public String showStudentDetails(@PathVariable Long id, Model model) {
        Student student = studentRepository.getStudentById(id);
        if (student == null) {
            throw new StudentNotFoundException("Student not found.");
        }
        model.addAttribute("student", student);
        return "student-details";
    }

    @GetMapping("/form-for-save")
    public String saveStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-save";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) throws IOException {
        System.out.println("Attempting to save student: " + student);

        // Check for duplicate student based on name
        if (isDuplicateStudent(student)) {
            System.out.println("Duplicate student found. Cannot save.");
            return "redirect:/students/error?message=Duplicate student found";
        }

        // Parse the birthDate string into a LocalDate object using the parseDate method
        String birthDate = String.valueOf(student.getBirth());
        LocalDate birth = parseDate(birthDate);
        student.setBirth(birth);

        studentRepository.saveStudent(student);
        System.out.println("Student saved successfully.");
        return "redirect:/students/success?action=saved";
    }

    @GetMapping("/form-for-update/{id}")
    public String updateStudentForm(@PathVariable Long id, Model model) {
        Student student = studentRepository.getStudentById(id);
        if (student == null) {
            throw new StudentNotFoundException("Student not found.");
        }
        model.addAttribute("student", student);
        return "student-update";
    }

    @PutMapping("/update")
    public String updateStudent(@ModelAttribute Student student) throws IOException {
        Student existingStudent = studentRepository.getStudentById(student.getId());
        if (existingStudent == null) {
            throw new StudentNotFoundException("Student not found.");
        }

        // Check for duplicate student based on name
        if (isDuplicateStudent(student)) {
            System.out.println("Duplicate student found. Cannot update.");
            return "redirect:/students/error?message=Duplicate student found";
        }

        studentRepository.updateStudent(student);
        System.out.println("Student updated successfully.");
        return "redirect:/students/success?action=updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) throws IOException {
        System.out.println("Attempting to delete student with ID: " + id);
        Student existingStudent = studentRepository.getStudentById(id);

        if (existingStudent == null) {
            throw new StudentNotFoundException("Student not found.");
        }
        studentRepository.deleteStudent(id);
        System.out.println("Student with ID: " + id + " deleted successfully.");
        return "redirect:/students/success?action=deleted";
    }

    @GetMapping("/success")
    public String showSuccessPage(@RequestParam(required = false) String action, Model model) {
        int totalRecords = studentRepository.getTotalRecords();
        model.addAttribute("totalRecords", totalRecords);
        model.addAttribute("action", action);
        return "success-page";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public String handleStudentNotFoundException(StudentNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error-page";
    }

    @GetMapping("/error")
    public String showErrorPage(Model model) {
        model.addAttribute("errorMessage", "Oops! Something went wrong.");
        return "error-page";
    }

    private boolean isDuplicateStudent(Student student) {
        List<Student> allStudents = studentRepository.getAllStudents();
        for (Student existingStudent : allStudents) {
            if (!existingStudent.getId().equals(student.getId()) &&
                    existingStudent.getFirstname().equals(student.getFirstname()) &&
                    existingStudent.getLastname().equals(student.getLastname())) {
                // Found a duplicate student
                return true;
            }
        }
        // No duplicate student found
        return false;
    }

    private LocalDate parseDate(String date) {
        List<DateTimeFormatter> formatters = List.of(
                DateTimeFormatter.ofPattern("dd.MM.yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException ignored) {
                // Ignore exception
            }
        }

        throw new IllegalArgumentException("Invalid date format: " + date);
    }



}




