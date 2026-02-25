package com.lms.library.repository;

import com.lms.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("SELECT b FROM Book b WHERE LOWER(b.bname) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(b.bauthor) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchBooks(@Param("keyword") String keyword);
    
    List<Book> findByBgenre(String genre);
    
    List<Book> findByPublishedYear(Integer year);
    
    @Query("SELECT b FROM Book b WHERE b.availableCopies >= :minCopies")
    List<Book> findByMinimumAvailableCopies(@Param("minCopies") Integer minCopies);
    
    @Query("SELECT COALESCE(SUM(b.totalCopies), 0) FROM Book b")
    Long getTotalBooksCount();
    
    @Query("SELECT COALESCE(SUM(b.availableCopies), 0) FROM Book b")
    Long getRemainingBooksCount();
}
