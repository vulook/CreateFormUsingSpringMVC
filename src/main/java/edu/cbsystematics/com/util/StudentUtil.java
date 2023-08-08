package edu.cbsystematics.com.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.cbsystematics.com.exception.CreateFormUsingSpringMVCRuntimeException;
import edu.cbsystematics.com.model.Student;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static edu.cbsystematics.com.App.JSON_FILE;

@Component
public class StudentUtil {

    private final ObjectMapper objectMapper;

    public StudentUtil() {

        objectMapper = new ObjectMapper();

        // Register the JavaTimeModule
        objectMapper.registerModule(new JavaTimeModule());
    }

    public Path getJSONFilePath() {
        try {
            return Paths.get(Objects.requireNonNull(getClass().getResource(JSON_FILE)).toURI());
        } catch (URISyntaxException e) {
            throw new CreateFormUsingSpringMVCRuntimeException("StudentUtil: Error getting JSON file path.", e);
        }
    }

    public void saveStudentToJSON(Student student) {
        List<Student> students = readStudentsFromJSON();
        students.add(student);
        saveStudentsToJSON(students);
    }

    public void saveStudentsToJSON(List<Student> students) {
        try {
            String jsonString = objectMapper.writeValueAsString(students);
            Path filePath = getJSONFilePath();
            Files.createDirectories(filePath.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                writer.write(jsonString);
            }

            System.out.println("StudentUtil: save to JSON file.");
        } catch (IOException e) {
            throw new CreateFormUsingSpringMVCRuntimeException("StudentUtil: Error saving students to JSON file.", e);
        }
    }

    public List<Student> readStudentsFromJSON() {
        List<Student> students = new ArrayList<>();

        try {
            Path filePath = getJSONFilePath();
            if (Files.exists(filePath)) {
                String jsonContent = Files.readString(filePath);
                students = objectMapper.readValue(jsonContent, new TypeReference<List<Student>>() {});
                System.out.println("StudentUtil: read from JSON file.");

            } else {
                System.out.println("StudentUtil: JSON file does not exist.");
            }

        } catch (IOException e) {
            throw new CreateFormUsingSpringMVCRuntimeException("StudentUtil: Error reading students from JSON file.", e);
        }

        return students;
    }


}



