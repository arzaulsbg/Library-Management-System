# Library Management System

A comprehensive Java-based desktop application for managing library operations including book inventory, member management, book issuance/return, and transaction tracking.

## Project Overview

This Library Management System is a desktop application built using Java Swing for the graphical user interface and MySQL for persistent data storage. The application provides a complete solution for libraries to manage their books, members, and transactions efficiently.

## Features

### 1. **User Authentication**
- Secure login system for library staff
- User credential validation
- Session management

### 2. **Book Management**
- **Add Books**: Add new books to the library inventory with title, author, category, and quantity
- **View Books**: Browse all books in the library with their details
- **Book Inventory Tracking**: Keep track of book quantities and availability

### 3. **Member Management**
- **Add Members**: Register new library members with name and email
- **View Members**: List all registered library members
- **Member Information**: Maintain member records and contact details

### 4. **Book Transactions**
- **Issue Books**: Track books issued to members with transaction details
- **Return Books**: Process book returns and update inventory
- **Transaction History**: View all book transactions and their status

### 5. **Dashboard**
- Central hub for navigating between different features
- Quick access to all major operations
- User-friendly interface

## Project Structure

```
library-manage/
├── src/
│   ├── Main.java                 # Application entry point
│   ├── LoginFrame.java           # User login interface
│   ├── DashboardFrame.java       # Main dashboard
│   ├── Book.java                 # Book data model
│   ├── BookDAO.java              # Book database operations
│   ├── Member.java               # Member data model
│   ├── MemberDAO.java            # Member database operations
│   ├── User.java                 # User data model
│   ├── UserDAO.java              # User database operations
│   ├── Transaction.java          # Transaction data model
│   ├── TransactionDAO.java       # Transaction database operations
│   ├── AddBookFrame.java         # Add book interface
│   ├── ViewBooksFrame.java       # View books interface
│   ├── AddMemberFrame.java       # Add member interface
│   ├── ViewMembersFrame.java     # View members interface
│   ├── IssueBookFrame.java       # Issue book interface
│   ├── ReturnBookFrame.java      # Return book interface
│   ├── ViewTransactionsFrame.java# View transactions interface
│   └── DBConnection.java         # Database connection utility
└── lib/                          # External libraries and dependencies
```

## Technology Stack

- **Language**: Java
- **GUI Framework**: Java Swing
- **Database**: MySQL
- **JDBC**: MySQL JDBC Driver for database connectivity
- **Architecture**: DAO (Data Access Object) Pattern

## System Requirements

- **Java**: JDK 8 or higher
- **MySQL**: MySQL Server 5.7 or higher
- **Operating System**: Windows, macOS, or Linux

## Installation & Setup

### 1. Database Setup

Create a MySQL database named `library_db`:

```sql
CREATE DATABASE library_db;
USE library_db;

-- Create users table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20)
);

-- Create books table
CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100),
    category VARCHAR(50),
    quantity INT DEFAULT 0,
    added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create members table
CREATE TABLE members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(15),
    registered_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create transactions table
CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT NOT NULL,
    book_id INT NOT NULL,
    issue_date TIMESTAMP,
    return_date TIMESTAMP,
    status VARCHAR(20),
    FOREIGN KEY (member_id) REFERENCES members(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);
```

### 2. Configure Database Connection

Update the database credentials in the [config.properties](config.properties) file:

```properties
db.url=jdbc:mysql://localhost:3306/library_db
db.user=root
db.password=your_password
```

**Security Note**: The `config.properties` file is listed in `.gitignore` and should NOT be committed to version control. Each environment should have its own configuration file.

### 3. Compile the Project

```bash
javac -d bin src/*.java
```

### 4. Run the Application

```bash
java -cp bin Main
```

## Usage Guide

### Starting the Application

1. Run the application - the Login Frame will appear
2. Enter valid credentials to access the dashboard

### Adding a Book

1. Click "Add Book" on the dashboard
2. Enter book details (title, author, category, quantity)
3. Click "Add" to save

### Adding a Member

1. Click "Add Member" on the dashboard
2. Enter member information (name, email)
3. Click "Register" to save

### Issuing a Book

1. Click "Issue Book" on the dashboard
2. Select a member and book
3. Confirm the transaction

### Returning a Book

1. Click "Return Book" on the dashboard
2. Select the transaction to close
3. Confirm the return

### Viewing Records

- Click "View Books" to see all books and inventory
- Click "View Members" to see all registered members
- Click "View Transactions" to see all book transactions

## Architecture & Design Patterns

### DAO Pattern

The application uses the Data Access Object (DAO) pattern to separate business logic from database operations:

- **BookDAO.java**: Handles all book-related database operations
- **MemberDAO.java**: Handles all member-related database operations
- **UserDAO.java**: Handles user authentication
- **TransactionDAO.java**: Handles transaction management

### Model-View Architecture

- **Models**: `Book.java`, `Member.java` - Data classes
- **Views**: `*Frame.java` classes - GUI components
- **DAOs**: Database access layer

## Class Descriptions

| Class | Purpose |
|-------|---------|
| **Main** | Application entry point |
| **LoginFrame** | User authentication interface |
| **DashboardFrame** | Main navigation hub |
| **Book** | Book data model |
| **BookDAO** | Book CRUD operations |
| **Member** | Member data model |
| **MemberDAO** | Member CRUD operations |
| **IssueBookFrame** | Book issuance interface |
| **ReturnBookFrame** | Book return interface |
| **DBConnection** | MySQL connection manager |

## Future Enhancements

- Fine management for overdue books
- Book search and filtering
- Member activity reports
- Email notifications for renewals
- Backup and restore functionality
- Admin user role management
- Book rating system
- Inventory alerts

## Configuration

### Database Configuration File

The application reads database credentials from `config.properties`. This file is excluded from version control for security reasons.

**File Location**: `config.properties` (project root)

**Required Properties**:
- `db.url`: MySQL connection URL
- `db.user`: Database username
- `db.password`: Database password

**Example**:
```properties
db.url=jdbc:mysql://localhost:3306/library_db
db.user=root
db.password=your_secure_password
```

## Troubleshooting

### Database Connection Error

- Verify MySQL server is running
- Ensure `config.properties` file exists in the project root
- Check database credentials in `config.properties`
- Ensure `library_db` database exists
- Verify MySQL JDBC driver is in the classpath

### GUI Issues

- Ensure Java version is compatible (JDK 8+)
- Check that all dependencies are properly compiled
- Verify all .java files are in the src/ directory

## Dependencies

- **MySQL JDBC Driver**: mysql-connector-java-8.x.x.jar
- **Java Swing**: Built-in with JDK

## Notes

- Database credentials are stored in `config.properties` which is excluded from version control
- The `config.properties` file is not included in the repository - create it locally with your credentials
- Passwords should be hashed before storing in the database
- Implement proper error handling and logging for production use
- For production environments, consider using environment variables or secure vaults instead of property files

## License

This project is provided as-is for educational and personal use.

## Author

Mohammad Arzaul Haque
Computer Science Student(AI&DS)

---

**Last Updated**: 2026
