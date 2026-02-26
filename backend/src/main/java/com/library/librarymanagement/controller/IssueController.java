package com.library.librarymanagement.controller;

import com.library.librarymanagement.model.Issue;
import com.library.librarymanagement.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.getIssueById(id));
    }

    @PostMapping("/issue-book")
    public ResponseEntity<Issue> issueBook(@RequestBody Map<String, Object> request) {
        Long bookId = Long.parseLong(request.get("bookId").toString());
        Long studentId = Long.parseLong(request.get("studentId").toString());
        
        Issue issue = issueService.issueBook(bookId, studentId);
        return ResponseEntity.ok(issue);
    }

    @PostMapping("/return-book")
    public ResponseEntity<Issue> returnBook(@RequestBody Map<String, Object> request) {
        Long issueId = Long.parseLong(request.get("issueId").toString());
        
        Issue issue = issueService.returnBook(issueId);
        return ResponseEntity.ok(issue);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Issue>> getActiveIssues() {
        return ResponseEntity.ok(issueService.getActiveIssues());
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Issue>> getOverdueBooks() {
        return ResponseEntity.ok(issueService.getOverdueBooks());
    }
}
