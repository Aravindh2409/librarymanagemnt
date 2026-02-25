package com.lms.library.repository;

import com.lms.library.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Optional<Student> findByEmail(String email);
    
    List<Student> findBySbranch(String branch);
    
    @Query("SELECT s FROM Student s WHERE LOWER(s.sname) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchStudents(@Param("keyword") String keyword);
    
    @Query("SELECT COUNT(s) FROM Student s")
    Long getTotalStudentsCount();
}
