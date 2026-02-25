package com.lms.library.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    
    @Column(nullable = false)
    private String sname;
    
    @Column(nullable = false)
    private String sbranch;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public Student() {}
    
    public Student(String sname, String sbranch, String email, String phone) {
        this.sname = sname;
        this.sbranch = sbranch;
        this.email = email;
        this.phone = phone;
    }
    
    // Getters and Setters
    public Long getSid() {
        return sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
    
    public String getSname() {
        return sname;
    }
    
    public void setSname(String sname) {
        this.sname = sname;
    }
    
    public String getSbranch() {
        return sbranch;
    }
    
    public void setSbranch(String sbranch) {
        this.sbranch = sbranch;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sbranch='" + sbranch + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
