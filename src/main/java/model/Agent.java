package model;

import java.time.LocalDateTime;

public class Agent {

    private int agentId;
    private String name;
    private String email;
    private String department;
    private LocalDateTime createdAt;

    public Agent() {
    }

    public Agent(int agentId, String name, String email,
                 String department, LocalDateTime createdAt) {

        this.agentId = agentId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.createdAt = createdAt;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
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