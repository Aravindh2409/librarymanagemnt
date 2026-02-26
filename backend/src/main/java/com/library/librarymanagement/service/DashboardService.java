package com.library.librarymanagement.service;

import org.springframework.stereotype.Service;
import com.library.librarymanagement.dto.DashboardResponse;
import com.library.librarymanagement.repository.BookRepository;
import com.library.librarymanagement.repository.IssueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {
    
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    
    public DashboardResponse getDashboardStats() {
        long totalBooks = bookRepository.count();
        long issuedBooks = issueRepository.countByStatus("ISSUED");
        long remainingBooks = totalBooks - issuedBooks;
        return new DashboardResponse(totalBooks, issuedBooks, remainingBooks);
    }
}
   