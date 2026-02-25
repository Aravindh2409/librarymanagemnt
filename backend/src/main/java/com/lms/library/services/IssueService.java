package com.lms.library.services;

import com.lms.library.models.Book;
import com.lms.library.models.Issue;
import com.lms.library.models.IssueStatus;
import com.lms.library.models.Student;
import com.lms.library.repository.BookRepository;
import com.lms.library.repository.IssueRepository;
import com.lms.library.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IssueService {
    
    @Autowired
    private IssueRepository issueRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private BookService bookService;
    
    /**
     * Issue a book to a student with proper validation and transaction safety
     * - Check availableCopies > 0
     * - Prevent duplicate issue (same student & same book with status=ISSUED)
     * - Decrease availableCopies
     * - Save issue
     */
    public Issue issueBook(Long bookId, Long studentId, LocalDate issueDate, LocalDate dueDate) {
        // Fetch book and student
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        
        if (!optionalBook.isPresent()) {
            throw new RuntimeException("Book not found with id: " + bookId);
        }
        
        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student not found with id: " + studentId);
        }
        
        Book book = optionalBook.get();
        Student student = optionalStudent.get();
        
        // Check if available copies > 0
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No available copies for book: " + book.getBname());
        }
        
        // Check if student already has an active issue for this book
        Long activeIssueCount = issueRepository.countActiveIssueForStudentAndBook(bookId, studentId);
        if (activeIssueCount > 0) {
            throw new RuntimeException("Student already has an active issue for this book");
        }
        
        // Create and save issue
        Issue issue = new Issue(book, student, issueDate, dueDate);
        issue.setStatus(IssueStatus.ISSUED);
        Issue savedIssue = issueRepository.save(issue);
        
        // Decrease available copies
        bookService.decreaseAvailableCopies(book, 1);
        
        return savedIssue;
    }
    
    /**
     * Return a book from a student with proper validation and transaction safety
     * - Check status is ISSUED
     * - Update returnDate
     * - Change status to RETURNED
     * - Increase availableCopies
     */
    public Issue returnBook(Long issueId, LocalDate returnDate) {
        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        
        if (!optionalIssue.isPresent()) {
            throw new RuntimeException("Issue not found with id: " + issueId);
        }
        
        Issue issue = optionalIssue.get();
        
        // Check if status is ISSUED
        if (issue.getStatus() != IssueStatus.ISSUED) {
            throw new RuntimeException("Book is already returned or not issued");
        }
        
        // Update return date and status
        issue.setReturnDate(returnDate);
        issue.setStatus(IssueStatus.RETURNED);
        Issue updatedIssue = issueRepository.save(issue);
        
        // Increase available copies
        bookService.increaseAvailableCopies(issue.getBook(), 1);
        
        return updatedIssue;
    }
    
    @Transactional(readOnly = true)
    public Issue getIssueById(Long issueId) {
        return issueRepository.findById(issueId).orElse(null);
    }
    
    @Transactional(readOnly = true)
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Issue> getActiveIssues() {
        return issueRepository.findActiveIssues();
    }
    
    @Transactional(readOnly = true)
    public List<Issue> getOverdueBooks() {
        return issueRepository.findOverdueBooks();
    }
    
    @Transactional(readOnly = true)
    public List<Issue> getIssuesByStudentId(Long studentId) {
        return issueRepository.findByStudentSid(studentId);
    }
    
    @Transactional(readOnly = true)
    public List<Issue> getIssuesByBookId(Long bookId) {
        return issueRepository.findByBookBid(bookId);
    }
    
    @Transactional(readOnly = true)
    public Long getIssuedBooksCount() {
        return issueRepository.getIssuedBooksCount();
    }
    
    @Transactional(readOnly = true)
    public Long getOverdueBooksCount() {
        return issueRepository.getOverdueBooksCount();
    }
}
