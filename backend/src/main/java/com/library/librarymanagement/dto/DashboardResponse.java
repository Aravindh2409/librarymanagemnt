package com.library.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DashboardResponse {
    private long totalBooks;
    private long issuedBooks;
    private long remainingBooks;
}
