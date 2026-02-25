# Library Management System - Database & Business Logic Layer

## Project Structure

```
com.lms.library
├── models/
│   ├── Book.java                    (JPA Entity)
│   ├── Student.java                 (JPA Entity)
│   ├── Issue.java                   (JPA Entity with relationships)
│   └── IssueStatus.java             (Enum: ISSUED, RETURNED)
├── repository/
│   ├── BookRepository.java          (Spring Data JPA)
│   ├── StudentRepository.java       (Spring Data JPA)
│   └── IssueRepository.java         (Spring Data JPA)
├── services/
│   ├── BookService.java             (@Service, @Transactional)
│   ├── StudentService.java          (@Service, @Transactional)
│   └── IssueService.java            (@Service, @Transactional)
└── LibraryApplication.java          (Main Application)
```

## Database Schema

### Books Table
```sql
CREATE TABLE books (
    bid BIGINT AUTO_INCREMENT PRIMARY KEY,
    bname VARCHAR(255) NOT NULL,
    bgenre VARCHAR(100) NOT NULL,
    bauthor VARCHAR(100) NOT NULL,
    published_year INT NOT NULL,
    total_copies INT NOT NULL,
    available_copies INT NOT NULL,
    created_at TIMESTAMP
);
```

### Students Table
```sql
CREATE TABLE students (
    sid BIGINT AUTO_INCREMENT PRIMARY KEY,
    sname VARCHAR(255) NOT NULL,
    sbranch VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    created_at TIMESTAMP
);
```

### Issues Table
```sql
CREATE TABLE issues (
    issue_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bid BIGINT NOT NULL FOREIGN KEY,
    sid BIGINT NOT NULL FOREIGN KEY,
    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(20) NOT NULL (ISSUED|RETURNED),
    created_at TIMESTAMP
);
```

## Services Overview

### BookService
- `addBook(Book)` → Create new book
- `updateBook(Long, Book)` → Modify book details
- `deleteBook(Long)` → Remove book
- `getBookById(Long)` → Fetch single book
- `getAllBooks()` → Fetch all books
- `searchBooks(String)` → Search by name/author
- `getBooksByGenre(String)` → Filter by genre
- `getBooksByYear(Integer)` → Filter by year
- `getBooksByAvailableCopies(Integer)` → Filter by available copies
- `getTotalBooks()` → SUM(totalCopies)
- `getRemainingBooks()` → SUM(availableCopies)

### StudentService
- `addStudent(Student)` → Create new student
- `updateStudent(Long, Student)` → Modify student details
- `deleteStudent(Long)` → Remove student
- `getStudentById(Long)` → Fetch single student
- `getAllStudents()` → Fetch all students
- `getStudentsByBranch(String)` → Filter by branch
- `searchStudents(String)` → Search by name/email
- `getTotalStudents()` → COUNT all students

### IssueService (Core Business Logic)

#### Issue Book
```java
issueBook(Long bookId, Long studentId, LocalDate issueDate, LocalDate dueDate)
```
**Validations:**
- ✅ Check availableCopies > 0
- ✅ Prevent duplicate issue (same student & book with status=ISSUED)
- ✅ Decrease availableCopies atomically
- ✅ @Transactional ensures atomicity

**Throws Exception if:**
- Book not found
- Student not found
- No available copies
- Student already has active issue for same book

#### Return Book
```java
returnBook(Long issueId, LocalDate returnDate)
```
**Processing:**
- ✅ Check status is ISSUED
- ✅ Update returnDate
- ✅ Change status to RETURNED
- ✅ Increase availableCopies atomically
- ✅ @Transactional ensures atomicity

**Throws Exception if:**
- Issue not found
- Book already returned or not issued

#### Query Methods
- `getIssueById(Long)` → Fetch single issue
- `getAllIssues()` → Fetch all issues
- `getActiveIssues()` → Get all ISSUED books
- `getOverdueBooks()` → Get books with dueDate < today
- `getIssuesByStudentId(Long)` → Get student's issues
- `getIssuesByBookId(Long)` → Get book's issues
- `getIssuedBooksCount()` → COUNT(status=ISSUED)
- `getOverdueBooksCount()` → COUNT(status=ISSUED AND dueDate<today)

## Key Features

### Transactional Safety
All critical operations use `@Transactional`:
- Issue book: Atomic check + decrease copies
- Return book: Atomic status update + increase copies
- Prevents race conditions and data inconsistency

### Relationships
```
Issue ──> Book (ManyToOne, FetchType.EAGER)
Issue ──> Student (ManyToOne, FetchType.EAGER)
```

### Custom Queries (JPQL)
- Search with LOWER() and CONCAT() for case-insensitive matching
- Aggregate functions: SUM(), COUNT(), COALESCE()
- Date functions: CURRENT_DATE for overdue detection

## Database Setup

1. Create database:
```sql
CREATE DATABASE library_management;
```

2. Run schema script:
```bash
mysql -u root -p library_management < database_schema.sql
```

3. Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_management
spring.datasource.username=root
spring.datasource.password=your_password
```

## Running the Application

```bash
mvn clean install
mvn spring-boot:run
```

## Testing Business Logic

### Issue a Book
```java
Issue issue = issueService.issueBook(1L, 1L, LocalDate.now(), LocalDate.now().plusDays(14));
```

### Return a Book
```java
Issue returned = issueService.returnBook(1L, LocalDate.now());
```

### Get Dashboard Stats
```java
Long totalBooks = bookService.getTotalBooks();
Long issuedBooks = issueService.getIssuedBooksCount();
Long remainingBooks = bookService.getRemainingBooks();
Long overdueBooks = issueService.getOverdueBooksCount();
```

## Notes

- All entities use `Long` for IDs (BIGINT in MySQL)
- Dates use `LocalDate` for date-only operations
- Enums are persisted as STRING in database
- Foreign keys are enforced at database level
- Indexes created for frequently queried columns
- Connection pooling configured with HikariCP
- Logging configured for debugging
