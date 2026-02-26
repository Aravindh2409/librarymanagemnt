package com.library.librarymanagement.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;
    
    @Column(nullable = false)
    private String bname;
    
    @Column(nullable = false)
    private String bgenre;
    
    @Column(nullable = false)
    private String bauthor;
    
    @Column(nullable = false)
    private Integer publishedYear;
    
    @Column(nullable = false)
    private Integer totalCopies;
    
    @Column(nullable = false)
    private Integer availableCopies;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public Book() {}
    
    public Book(String bname, String bgenre, String bauthor, Integer publishedYear, 
                Integer totalCopies, Integer availableCopies) {
        this.bname = bname;
        this.bgenre = bgenre;
        this.bauthor = bauthor;
        this.publishedYear = publishedYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }
    
    // Getters and Setters
    public Long getBid() {
        return bid;
    }
    
    public void setBid(Long bid) {
        this.bid = bid;
    }
    
    public String getBname() {
        return bname;
    }
    
    public void setBname(String bname) {
        this.bname = bname;
    }
    
    public String getBgenre() {
        return bgenre;
    }
    
    public void setBgenre(String bgenre) {
        this.bgenre = bgenre;
    }
    
    public String getBauthor() {
        return bauthor;
    }
    
    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }
    
    public Integer getPublishedYear() {
        return publishedYear;
    }
    
    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
    
    public Integer getTotalCopies() {
        return totalCopies;
    }
    
    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }
    
    public Integer getAvailableCopies() {
        return availableCopies;
    }
    
    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", bgenre='" + bgenre + '\'' +
                ", bauthor='" + bauthor + '\'' +
                ", publishedYear=" + publishedYear +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                '}';
    }
}
