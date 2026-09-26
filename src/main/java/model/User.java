package model;

import java.time.LocalDateTime;

public class User {

    private int userId;
    private String name;
    private String email;
    private String department;
    private LocalDateTime createdAt;

    public User() {
    }

    public User(int userId, String name, String email,
                String department, LocalDateTime createdAt) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}