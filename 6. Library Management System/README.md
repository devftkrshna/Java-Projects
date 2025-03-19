# Library Management System - Detailed Explanation

## Project Overview

**Concepts Covered:**

- Object-Oriented Programming (Encapsulation, Inheritance, Polymorphism)
- Collections (ArrayList)
- Exception Handling
- File Handling (Optional, for persistent storage)

**Features:**

1. Add Book
2. Display Available Books
3. Borrow Book
4. Return Book
5. Exit Program

---

## Project Structure

### 1. Book Class

**Purpose:** Represents a single book with basic attributes.

**Attributes:**

- `String title`
- `String author`
- `boolean isBorrowed`

**Methods:**

- Constructor to initialize book
- `borrowBook()`
- `returnBook()`
- Getters for title, author, and borrow status

### 2. Library Class

**Purpose:** Manages a collection of books.

**Attributes:**

- `ArrayList<Book> books`

**Methods:**

- `addBook(Book book)`
- `displayBooks()`
- `borrowBook(String title)`
- `returnBook(String title)`

### 3. Main Class

**Purpose:** Provides CLI interface, interacts with users.

**Features:**

- Menu-driven system
- User input handling
- Exception Handling for invalid inputs

---

## Pseudocode Outline

```
class Book {
    attributes: title, author, isBorrowed
    methods:
        - borrowBook()
        - returnBook()
        - getters
}

class Library {
    attribute: books (ArrayList)
    methods:
        - addBook()
        - displayBooks()
        - borrowBook(title)
        - returnBook(title)
}

class Main {
    while true:
        display menu options
        take user choice
        switch case:
            case 1: add book
            case 2: display books
            case 3: borrow book
            case 4: return book
            case 5: exit
}
```

---

## Concepts Breakdown

### 1. **Encapsulation**

Encapsulation is the mechanism of hiding internal data and showing only necessary parts. Here, we use private variables in the `Book` class and provide public getters and methods.

### 2. **Inheritance & Polymorphism**

We can extend this project by adding different types of books like `Ebook` or `PrintedBook` extending `Book` class to demonstrate Inheritance and Polymorphism.

### 3. **ArrayList**

`ArrayList` stores the list of `Book` objects dynamically.

```java
ArrayList<Book> books = new ArrayList<>();
```

### 4. **Exception Handling**

We can wrap user input in `try-catch` blocks to handle invalid inputs (like entering a string when a number is expected).

```java
try {
    int choice = scanner.nextInt();
} catch (InputMismatchException e) {
    System.out.println("Invalid Input!");
}
```

### 5. **Scanner for Input**

Used to take user input.

```java
Scanner sc = new Scanner(System.in);
```

### 6. **Switch Case**

Used for menu-driven interaction.

```java
switch (choice) {
    case 1: // add book
    ...
}
```

---

## Optional Advanced Features

- File Handling to save books data persistently.
- Admin/User Role Management.
- Enhanced Exception Handling.
- Date handling (for borrowed date, due date).

---

```java
import java.util.*; 
// Importing all utility classes like Scanner, ArrayList, etc.

class Book { 
// Creating Book class to represent each book.

    private String title; 
    // Private variable to store book title.

    private String author; 
    // Private variable to store book author.

    private boolean isBorrowed; 
    // Boolean variable to track borrow status.

    public Book(String title, String author) { 
    // Constructor to initialize book title and author.
        this.title = title; 
        this.author = author;
        this.isBorrowed = false; 
        // Default: Book is not borrowed initially.
    }

    public String getTitle() { return title; } 
    // Getter for title.

    public String getAuthor() { return author; } 
    // Getter for author.

    public boolean isBorrowed() { return isBorrowed; } 
    // Getter to check if book is borrowed.

    public void borrow() { isBorrowed = true; } 
    // Marks book as borrowed.

    public void returnBook() { isBorrowed = false; } 
    // Marks book as returned.
}

class Library { 
// Library class manages the collection of books.

    private ArrayList<Book> books = new ArrayList<>(); 
    // Dynamic list to store Book objects.

    public void addBook(String title, String author) { 
    // Method to add a new book.
        books.add(new Book(title, author)); 
        // Adds new Book object to the list.
        System.out.println("Book added successfully!");
    }

    public void displayBooks() { 
    // Method to display all books.
        if (books.isEmpty()) { 
            System.out.println("No books available.");
            return;
        }
        for (int i = 0; i < books.size(); i++) { 
        // Loop through books.
            Book b = books.get(i);
            System.out.println((i + 1) + ". " + b.getTitle() + " by " + b.getAuthor() +
                    (b.isBorrowed() ? " [Borrowed]" : " [Available]"));
            // Display title, author and status.
        }
    }

    public void borrowBook(int index) { 
    // Method to borrow book based on index.
        if (index < 0 || index >= books.size()) { 
        // Validate index.
            System.out.println("Invalid book number.");
            return;
        }
        Book b = books.get(index);
        if (b.isBorrowed()) { 
            System.out.println("Book is already borrowed.");
        } else {
            b.borrow();
            System.out.println("You borrowed: " + b.getTitle());
        }
    }

    public void returnBook(int index) { 
    // Method to return book.
        if (index < 0 || index >= books.size()) { 
            System.out.println("Invalid book number.");
            return;
        }
        Book b = books.get(index);
        if (!b.isBorrowed()) { 
            System.out.println("Book was not borrowed.");
        } else {
            b.returnBook();
            System.out.println("You returned: " + b.getTitle());
        }
    }
}

public class LibraryManagementSystem { 
// Main class.

    public static void main(String[] args) { 
    // Main method.
        Library lib = new Library(); 
        // Create Library object.
        Scanner sc = new Scanner(System.in); 
        // Scanner for input.

        while (true) { 
        // Infinite loop for menu.
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try { 
            // Exception handling for invalid input.
                choice = sc.nextInt();
                sc.nextLine(); 
                // Consume newline.
            } catch (InputMismatchException e) { 
                System.out.println("Invalid input! Enter number.");
                sc.next(); 
                continue; 
            }

            switch (choice) { 
            // Menu options.
                case 1 -> { 
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    lib.addBook(title, author);
                }
                case 2 -> lib.displayBooks();
                case 3 -> {
                    lib.displayBooks();
                    if (lib.books.isEmpty()) break;
                    System.out.print("Enter book number to borrow: ");
                    int num = sc.nextInt();
                    lib.borrowBook(num - 1);
                }
                case 4 -> {
                    lib.displayBooks();
                    if (lib.books.isEmpty()) break;
                    System.out.print("Enter book number to return: ");
                    int num = sc.nextInt();
                    lib.returnBook(num - 1);
                }
                case 5 -> {
                    System.out.println("Exiting Library System. Goodbye!");
                    System.exit(0); 
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
```
