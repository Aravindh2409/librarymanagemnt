-- Create Database
CREATE DATABASE IF NOT EXISTS library_management;
USE library_management;

-- Create Books Table
CREATE TABLE IF NOT EXISTS books (
    bid BIGINT AUTO_INCREMENT PRIMARY KEY,
    bname VARCHAR(255) NOT NULL,
    bgenre VARCHAR(100) NOT NULL,
    bauthor VARCHAR(100) NOT NULL,
    published_year INT NOT NULL,
    total_copies INT NOT NULL,
    available_copies INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CHECK (available_copies >= 0 AND available_copies <= total_copies)
);

-- Create Students Table
CREATE TABLE IF NOT EXISTS students (
    sid BIGINT AUTO_INCREMENT PRIMARY KEY,
    sname VARCHAR(255) NOT NULL,
    sbranch VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Issues Table
CREATE TABLE IF NOT EXISTS issues (
    issue_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bid BIGINT NOT NULL,
    sid BIGINT NOT NULL,
    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'ISSUED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (bid) REFERENCES books(bid),
    FOREIGN KEY (sid) REFERENCES students(sid),
    CHECK (status IN ('ISSUED', 'RETURNED'))
);

-- Create Indexes
CREATE INDEX idx_book_genre ON books(bgenre);
CREATE INDEX idx_book_author ON books(bauthor);
CREATE INDEX idx_book_year ON books(published_year);
CREATE INDEX idx_student_branch ON students(sbranch);
CREATE INDEX idx_student_email ON students(email);
CREATE INDEX idx_issue_status ON issues(status);
CREATE INDEX idx_issue_student ON issues(sid);
CREATE INDEX idx_issue_book ON issues(bid);
CREATE INDEX idx_issue_due_date ON issues(due_date);
