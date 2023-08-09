package edu.cbsystematics.com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    private String firstname;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters")
    private String lastname;

    @NotNull(message = "Birth date cannot be empty")
    @Past(message = "Birth date should be a past date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Phone should not be empty")
    @Size(min = 10, max = 15, message = "Phone should be between 10 and 15 characters")
    private String phone;

    public Student(String firstname, String lastname, LocalDate birth, String email, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
    }

    // Calculation of the student's age
    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        if (birth != null) {
            return Period.between(birth, currentDate).getYears();
        } else {
            return 0;
        }
    }

}