package com.library.librarymanagement.repository;

import com.library.librarymanagement.model.Issue;
import com.library.librarymanagement.model.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.book.bid = :bookId AND i.student.sid = :studentId AND i.status = 'ISSUED'")
    Long countActiveIssueForStudentAndBook(@Param("bookId") Long bookId, @Param("studentId") Long studentId);
    
    @Query("SELECT i FROM Issue i WHERE i.status = 'ISSUED' AND i.dueDate < CURRENT_DATE")
    List<Issue> findOverdueBooks();
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.status = 'ISSUED'")
    Long getIssuedBooksCount();
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.status = 'ISSUED' AND i.dueDate < CURRENT_DATE")
    Long getOverdueBooksCount();
    
    List<Issue> findByStudentSid(Long studentId);
    
    List<Issue> findByBookBid(Long bookId);
    
    @Query("SELECT i FROM Issue i WHERE i.status = 'ISSUED'")
    List<Issue> findActiveIssues();
    
    List<Issue> findByStatus(IssueStatus status);
    
    long countByStatus(String status);
}
