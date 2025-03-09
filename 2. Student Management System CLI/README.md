## Concepts: 
Classes, Objects, Constructors
## Features:
Add, Remove, Display Student Details


---
## Code
```
import java.util.*;  
  
class Student{  
    private int uid;  
    private String name;  
    private int age;  
    private String course;  
  
    Student(int id, String Name, int Age, String Course){  
        this.uid=id;  
        this.name=Name;  
        this.age=Age;  
        this.course=Course;  
    }  
  
    public int getUid(){ return uid; }  
    public String getName() { return name; }  
    public int getAge() { return age; }  
    public String getCourse() { return course; }  
  
    public void setUid(int uid) { this.uid = uid; }  
    public void setName(String name) { this.name = name; }  
    public void setAge(int age) { this.age = age; }  
    public void setCourse(String course) { this.course = course; }  
  
    public void displayStudent(){  
        System.out.printf("%-7d %-20s %-7d %-20s \n", uid, name, age,course);  
    }  
};  
  
public class StudentManagementSystem {  
    private static final ArrayList<Student> s = new ArrayList<>();  
    private static final Scanner obj = new Scanner(System.in);  
  
    public static void main(String[] args) {  
        while (true) {  
            System.out.println("\n=========Student Management System======\n");  
            System.out.println("1. Add Student");  
            System.out.println("2. View All Students");  
            System.out.println("3. Search Student by ID");  
            System.out.println("4. Update Student Details");  
            System.out.println("5. Delete Student");  
            System.out.println("6. Exit");  
            System.out.print("Enter your choice: ");  
  
            int choice;  
            try {  
                choice = obj.nextInt();  
            } catch (Exception e) {  
                System.out.println("Invalid input! Please enter a number between 1-6.");  
                obj.next(); // Clear invalid input  
                continue;  
            }  
  
            switch (choice) {  
                case 1 -> addStudent();  
                case 2 -> viewStudents();  
                case 3 -> searchStudent();  
                case 4 -> updateStudent();  
                case 5 -> deleteStudent();  
                case 6 -> {  
                    System.out.println("Exiting System...Have a good day!!");  
                    obj.close();  
                    System.exit(0);  
                }  
                default -> System.out.println("Invalid Choice! Please enter a number between 1-6!!");  
            }  
        }  
    }  
  
    private static void addStudent(){  
        try {  
            System.out.println("Enter student id: ");  
            int id = obj.nextInt();  
            obj.nextLine();  
  
            System.out.println("Enter Student Name: ");  
            String name = obj.nextLine();  
  
            System.out.println("Enter Student Age: ");  
            int age = obj.nextInt();  
            obj.nextLine();  
  
            System.out.println("Enter Course Enrolled: ");  
            String course = obj.nextLine();  
  
            s.add(new Student(id, name, age, course));  
            System.out.println("Students Added Successfully!! ");  
        }catch (Exception e){  
            System.out.println("Invalid Input");  
            obj.next();  
        }  
    }  
  
    public static void viewStudents(){  
        if(s.isEmpty()){  
            System.out.println("No Students Found!! Database Empty...");  
            return;  
        }  
  
        System.out.printf("\n%-7s %-20s %-7s %-20s\n", "ID", "Name", "Age", "Course");  
        System.out.println("--------------------------------------------------------");  
  
        for(Student student:s){  
            student.displayStudent();  
        }  
    }  
  
    public static void searchStudent(){  
        System.out.println("Enter UID to search: ");  
        int id=obj.nextInt();  
  
        for (Student student : s) {  
            if (student.getUid() == id) {  
                System.out.printf("\n%-7s %-20s %-7s %-15s\n", "ID", "Name", "Age", "Course");  
                System.out.println("--------------------------------------------------");  
                student.displayStudent();  
                return;  
            }  
        }  
        System.out.println("Student Not Found!!");  
    }  
  
    public static void updateStudent(){  
        System.out.println("Enter UID to Update: ");  
        int id=obj.nextInt();  
        for(Student student:s){  
            if(student.getUid()==id){  
                while(true) {  
                    System.out.println("What do you want to Update?");  
                    System.out.println("1. UID");  
                    System.out.println("2. Name");  
                    System.out.println("3. Age");  
                    System.out.println("4. Course");  
                    System.out.println("5. Exit");  
  
                    int choice;  
                    try {  
                        choice = obj.nextInt();  
                    } catch (Exception e) {  
                        System.out.println("Choose Correct Option!! ");  
                        obj.next(); // Clear invalid input  
                        continue;  
                    }  
  
                    switch (choice){  
                        case 1->{  
                            System.out.println("Enter new UID: ");  
                            int newid=obj.nextInt();  
                            student.setUid(newid);  
                            System.out.println("UID Updated Successfully!!");  
                        }  
                        case 2->{  
                            System.out.println("Enter new Name: ");  
                            obj.nextLine();  
                            String newname=obj.nextLine();  
                            student.setName(newname);  
                            System.out.println("Name Updated Successfully!!");  
                        }  
  
                        case 3->{  
                            System.out.println("Enter new Age: ");  
                            int newage=obj.nextInt();  
                            student.setUid(newage);  
                            System.out.println("Age Updated Successfully!!");  
                        }  
  
                        case 4-> {  
                            System.out.println("Enter new Course: ");  
                            obj.nextLine();  
                            String newcourse = obj.nextLine();  
                            student.setCourse(newcourse);  
                            System.out.println("Course Updated Successfully!!");  
                        }  
  
                        case 5 -> {  
                            System.out.println("Exiting Update Menu...");  
                            return;  
                        }  
  
                        default -> throw new IllegalStateException("Unexpected value: " + choice);  
                    }  
                }  
            }  
        }  
        System.out.println("Not Found!! ");  
    }  
  
    public static void deleteStudent(){  
        System.out.print("Enter Student ID to delete: ");  
        int id = obj.nextInt();  
  
        for (Student student : s) {  
            if (student.getUid() == id) {  
                s.remove(student);  
                System.out.println("✅ Student deleted successfully!");  
                return;  
            }  
        }  
        System.out.println("⚠ Student not found!");  
    }  
}
```


---
# **Student Management System - Detailed Notes**

## **1️⃣ Introduction**

The **Student Management System** is a CLI-based Java application that allows users to **Add, View, Search, Update, and Delete student records**. This document explains all the concepts used in the implementation.

---

## **2️⃣ Java Concepts Used**

### **1. ArrayList**

- `ArrayList<Student> students = new ArrayList<>();`
    
- Used to store **multiple Student objects** dynamically.
    
- Unlike arrays, `ArrayList` **does not have a fixed size** and allows easy modification (add, remove, search).
    
- **Key Methods Used:**
    
    - `add(element)`: Adds an element to the list.
        
    - `remove(object)`: Removes the first occurrence of the object.
        
    - `get(index)`: Retrieves an element at a specific index.
        
    - `size()`: Returns the number of elements in the list.
        

### **2. printf Formatting**

- `System.out.printf("%-5d %-20s %-5d %-15s\n", id, name, age, course);`
    
- **Formatted output** using placeholders:
    
    - `%d` → Integer
        
    - `%s` → String
        
    - `%-5d` → Left-align integer with **width 5**
        
    - `%-20s` → Left-align string with **width 20**
        

### **3. Final Keyword**

- `private static final Scanner scanner = new Scanner(System.in);`
    
- `final` makes a **variable constant** (its reference cannot change after initialization).
    
- Used for `Scanner` to **prevent reassignment** and maintain a single instance.
    

### **4. Static Keyword**

- `static` allows methods/variables to belong **to the class rather than an instance**.
    
- **Why static?**
    
    - `students` list is shared across all methods.
        
    - `scanner` is used globally, avoiding multiple instances.
        
    - `main()` is `static` since the program starts execution from it without an object.
        

### **5. Exception Handling (try-catch block)**

- Used to **handle invalid inputs** (e.g., non-numeric input where an integer is expected).
    
- Example:
    
    ```
    try {
        choice = scanner.nextInt();
    } catch (Exception e) {
        System.out.println("Invalid input! Please enter a number between 1-6.");
        scanner.next(); // Clear invalid input
        continue;
    }
    ```
    
- Prevents the program from crashing due to incorrect input.
    

---

## **3️⃣ Line-by-Line Code Explanation**

### **Student Class**

```
class Student {
    private int id;
    private String name;
    private int age;
    private String course;
```

- Defines a **Student object** with attributes (`id`, `name`, `age`, `course`).
    
- All fields are **private** (encapsulation - data hiding).
    

```
    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }
```

- **Constructor** initializes student attributes when an object is created.
    

```
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
```

- **Getter methods** to access private variables.
    

```
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }
```

- **Setter methods** allow modification of attributes.
    

```
    public void display() {
        System.out.printf("%-5d %-20s %-5d %-15s\n", id, name, age, course);
    }
```

- Displays student details in **formatted output** using `printf`.
    

---

### **Main Class (StudentManagementSystem)**

```
public class StudentManagementSystem {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
```

- `students` stores **all student records**.
    
- `scanner` is **final** (constant reference) to avoid multiple scanner objects.
    

```
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student Details");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
```

- **Menu-driven loop** runs until the user exits.
    

```
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number between 1-6.");
                scanner.next(); // Clear invalid input
                continue;
            }
```

- **Prevents crashes** from incorrect input (non-integer values).
    

```
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    System.out.println("Exiting the system... Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please enter a number between 1-6.");
            }
        }
    }
```

- Uses **enhanced switch statement (Java 14+)**.
    
- Calls respective **methods** based on user input.
    

```
private static void addStudent() {
    System.out.print("Enter Student ID: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consume newline
```

- `scanner.nextLine();` **clears newline** after integer input to avoid skipping.
    

```
    System.out.print("Enter Student Name: ");
    String name = scanner.nextLine();
```

- Reads **full string input** (including spaces).
    

```
    students.add(new Student(id, name, age, course));
    System.out.println("✅ Student added successfully!");
}
```

- Adds new student **to ArrayList**.
    

```
private static void viewStudents() {
    if (students.isEmpty()) {
        System.out.println("No students found.");
        return;
    }
    System.out.printf("\n%-5s %-20s %-5s %-15s\n", "ID", "Name", "Age", "Course");
```

- Checks if list is **empty** before displaying.
    
- Prints formatted **table headers**.
    

---

## **4️⃣ Summary**

- **ArrayList** for dynamic storage.
    
- **Static methods & variables** for shared access.
    
- **Formatted output** using `printf`.
    
- **Exception handling** for robustness.
    
- **Menu-driven program** using `while(true) {}`.
    

🚀 This project builds a **strong foundation** for Java OOP, data structures, and exception handling. 