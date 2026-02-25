package com.lms.library.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
public class Issue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bid", nullable = false)
    private Book book;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sid", nullable = false)
    private Student student;
    
    @Column(nullable = false)
    private LocalDate issueDate;
    
    @Column(nullable = false)
    private LocalDate dueDate;
    
    @Column(nullable = true)
    private LocalDate returnDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus status;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public Issue() {}
    
    public Issue(Book book, Student student, LocalDate issueDate, LocalDate dueDate) {
        this.book = book;
        this.student = student;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = IssueStatus.ISSUED;
    }
    
    // Getters and Setters
    public Long getIssueId() {
        return issueId;
    }
    
    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }
    
    public Book getBook() {
        return book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public LocalDate getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    public IssueStatus getStatus() {
        return status;
    }
    
    public void setStatus(IssueStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "Issue{" +
                "issueId=" + issueId +
                ", book=" + book.getBname() +
                ", student=" + student.getSname() +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status=" + status +
                '}';
    }
}
