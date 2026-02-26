package com.library.librarymanagement.service;

import com.library.librarymanagement.model.Book;
import com.library.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    
    public Book updateBook(Long bookId, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setBname(bookDetails.getBname());
            book.setBgenre(bookDetails.getBgenre());
            book.setBauthor(bookDetails.getBauthor());
            book.setPublishedYear(bookDetails.getPublishedYear());
            book.setTotalCopies(bookDetails.getTotalCopies());
            book.setAvailableCopies(bookDetails.getAvailableCopies());
            return bookRepository.save(book);
        }
        return null;
    }
    
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
    
    @Transactional(readOnly = true)
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }
    
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword);
    }
    
    @Transactional(readOnly = true)
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByBgenre(genre);
    }
    
    @Transactional(readOnly = true)
    public List<Book> getBooksByYear(Integer year) {
        return bookRepository.findByPublishedYear(year);
    }
    
    @Transactional(readOnly = true)
    public List<Book> getBooksByAvailableCopies(Integer minCopies) {
        return bookRepository.findByMinimumAvailableCopies(minCopies);
    }
    
    protected void increaseAvailableCopies(Book book, Integer count) {
        book.setAvailableCopies(book.getAvailableCopies() + count);
        bookRepository.save(book);
    }
    
    protected void decreaseAvailableCopies(Book book, Integer count) {
        book.setAvailableCopies(book.getAvailableCopies() - count);
        bookRepository.save(book);
    }
    
    @Transactional(readOnly = true)
    public Long getTotalBooks() {
        return bookRepository.getTotalBooksCount();
    }
    
    @Transactional(readOnly = true)
    public Long getRemainingBooks() {
        return bookRepository.getRemainingBooksCount();
    }
}
