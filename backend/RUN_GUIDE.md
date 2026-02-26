# Library Management System - Final Setup & Run Guide

## ✅ Prerequisites Completed

- ✅ Database schema created
- ✅ Backend code reviewed and cleaned (removed all duplicates)
- ✅ All compilation errors fixed:
  - Added Lombok dependency
  - Created DashboardResponse DTO
  - Fixed DashboardService
  - Added missing methods to repositories
- ✅ JAR file built successfully

---

## 🚀 Step-by-Step to Run the Application

### **Step 1: Ensure MySQL Database Exists**

Open MySQL and create the database:

```sql
CREATE DATABASE IF NOT EXISTS library_management;
```

**Note:** The application will auto-create all tables on startup (ddl-auto=update)

---

### **Step 2: Run the Application**

Navigate to the backend folder and choose ONE of these methods:

#### **Method 1: Using Maven (Recommended)**

```bash
cd "c:\Users\akshi\Downloads\library management\backend"
mvn spring-boot:run
```

#### **Method 2: Using JAR File**

```bash
cd "c:\Users\akshi\Downloads\library management\backend"
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

#### **Method 3: Using Maven Wrapper**

```bash
cd "c:\Users\akshi\Downloads\library management\backend"
./mvnw spring-boot:run
```

---

### **Step 3: Verify Application Started**

When the application starts, you'll see this in the console:

```
Started LibrarymanagementApplication in X.XXX seconds (process running with PID XXXX).
```

The application runs on: **http://localhost:8081**

---

## 📚 Available API Endpoints

### **Books APIs**
- `GET http://localhost:8081/api/books` - Get all books
- `GET http://localhost:8081/api/books/{id}` - Get book by ID
- `POST http://localhost:8081/api/books` - Add new book
- `PUT http://localhost:8081/api/books/{id}` - Update book
- `DELETE http://localhost:8081/api/books/{id}` - Delete book
- `GET http://localhost:8081/api/books/search?keyword=xyz` - Search books
- `GET http://localhost:8081/api/books/genre/{genre}` - Get books by genre

### **Students APIs**
- `GET http://localhost:8081/api/students` - Get all students
- `GET http://localhost:8081/api/students/{id}` - Get student by ID
- `POST http://localhost:8081/api/students` - Add new student
- `PUT http://localhost:8081/api/students/{id}` - Update student
- `DELETE http://localhost:8081/api/students/{id}` - Delete student
- `GET http://localhost:8081/api/students/search?keyword=xyz` - Search students

### **Issues APIs**
- `GET http://localhost:8081/api/issues` - Get all issues
- `GET http://localhost:8081/api/issues/{id}` - Get issue by ID
- `POST http://localhost:8081/api/issues/issue-book` - Issue a book to student
- `POST http://localhost:8081/api/issues/return-book` - Return an issued book
- `GET http://localhost:8081/api/issues/active` - Get active issues
- `GET http://localhost:8081/api/issues/overdue` - Get overdue books

### **Dashboard API**
- `GET http://localhost:8081/api/dashboard` - Get dashboard statistics

---

## 📋 Sample Request/Response Examples

### **Add a Book**

**Request:**
```json
POST http://localhost:8081/api/books
Content-Type: application/json

{
  "bname": "Harry Potter",
  "bgenre": "Fantasy",
  "bauthor": "J.K. Rowling",
  "publishedYear": 1997,
  "totalCopies": 5,
  "availableCopies": 5
}
```

### **Add a Student**

**Request:**
```json
POST http://localhost:8081/api/students
Content-Type: application/json

{
  "sname": "John Doe",
  "sbranch": "Computer Science",
  "email": "john@example.com",
  "phone": "9876543210"
}
```

### **Issue a Book**

**Request:**
```json
POST http://localhost:8081/api/issues/issue-book
Content-Type: application/json

{
  "bookId": 1,
  "studentId": 1,
  "issueDate": "2026-02-26",
  "dueDate": "2026-03-12"
}
```

---

## 🔧 Configuration (If MySQL Settings Are Different)

Edit: `c:\Users\akshi\Downloads\library management\backend\src\main\resources\application.properties`

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/library_management
spring.datasource.username=root
spring.datasource.password=your_password_here

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
# Hibernate will auto-detect the dialect from the database JDBC driver
```

---

## ❌ Troubleshooting

### **Error: Connection refused (MySQL)**
```
Solution: Start your MySQL server
- If using MySQL Service: net start MySQL80
- If using XAMPP: Start MySQL from control panel
```

### **Error: Access denied for user 'root'**
```
Solution: Update password in application.properties
spring.datasource.password=your_actual_password
```

### **Error: Port 8080 already in use**
```
Solution: Either kill the process using port 8080 or change the port:
Add to application.properties: server.port=8081
```

### **Error: Cannot connect to database**
```
Solution: Verify MySQL is running and database exists:
mysql -u root -p
CREATE DATABASE library_management;
```

---

## 📊 Database Tables Created Automatically

The application will create these tables on startup:

- `students` - Stores student information
- `books` - Stores book information
- `issues` - Stores book issue/return records

---

## 🎯 Next Steps

1. **Start your MySQL server**
2. **Run the application** using one of the methods above
3. **Test the APIs** using Postman or curl
4. **Build your frontend** to consume these APIs

---

**Your Library Management System is now ready to use!** 🎉
