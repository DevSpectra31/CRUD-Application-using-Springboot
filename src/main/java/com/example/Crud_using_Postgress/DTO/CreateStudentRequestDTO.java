package com.example.Crud_using_Postgress.DTO;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class CreateStudentRequestDTO {


    @NotBlank(message = "name is required")
    @Size(min = 2 , max = 50 , message = "name should be 2 t0 50 characters long")
    private String name;
    @Min(value = 18)
    private int age;

    @Email(message = "must be valid email")
    private String email;
    @NotNull(message = "roll no is required")
    private int roll_no;
    @NotBlank(message = "subject is required")
    private String subject;
    private LocalDateTime  createdAt;
    private LocalDateTime updatedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
