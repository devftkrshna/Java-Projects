## Concepts: 
Collections (ArrayList), File Handling

## Features:
Add, Edit, Delete, Save Tasks

---

### Concepts Used in the To-Do List Project (Detailed Explanation)

---

### **1. File Handling in Java**

**Definition:**  
File handling is used to read, write, and manipulate files in Java. The key classes used for file handling are:

- `FileInputStream` and `FileOutputStream` (for byte-stream files)
- `FileReader` and `FileWriter` (for character-stream files)
- `ObjectInputStream` and `ObjectOutputStream` (for object serialization)

**Example:**

```java
FileOutputStream fos = new FileOutputStream("data.txt");
ObjectOutputStream oos = new ObjectOutputStream(fos);
oos.writeObject(tasks);
oos.close();
```

This writes the `tasks` object to a file.

**Why Used Here?**

- To **store tasks** persistently even after the program exits.
- Uses `ObjectOutputStream` and `ObjectInputStream` to **serialize and deserialize** the `ArrayList<Task>`.

---

### **2. Serialization & Deserialization**

**Definition:**  
Serialization is the process of converting an object into a byte stream so that it can be saved in a file or sent over a network.  
Deserialization is the opposite: converting a byte stream back into an object.

**Example:**

- **Serialization (Saving tasks)**
    
    ```java
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tasks.dat"));
    oos.writeObject(tasks);
    oos.close();
    ```
    
- **Deserialization (Loading tasks)**
    
    ```java
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tasks.dat"));
    tasks = (ArrayList<Task>) ois.readObject();
    ois.close();
    ```
    
**Why Used Here?**

- We need to **store task data permanently** and retrieve it when restarting the application.

---

### **3. `static` Keyword**

**Definition:**  
The `static` keyword in Java makes a method or variable belong to the class rather than an instance.

**Example in Our Code:**

```java
private static ArrayList<Task> tasks = new ArrayList<>();
```

**Why Used Here?**

- `tasks` is `static` because it needs to be **shared across all methods** without creating an instance of `ToDoList`.
- The methods like `addTask()`, `viewTasks()`, and `saveTasks()` are `static` so they can **modify the shared task list**.

---

### **4. `ArrayList` in Java**

**Definition:**  
`ArrayList` is a resizable array implementation of `List`. Unlike arrays, it dynamically resizes as elements are added or removed.

**Example:**

```java
ArrayList<Task> tasks = new ArrayList<>();
tasks.add(new Task("Complete Homework"));
tasks.remove(0);
```

**Why Used Here?**

- Since task management requires **adding, deleting, and updating** tasks dynamically, `ArrayList` is an ideal choice.

---

### **5. Try-Catch Exception Handling**

**Definition:**  
Try-catch blocks handle runtime exceptions, preventing the program from crashing.

**Example in Our Code:**

```java
try {
    choice = scanner.nextInt();
} catch (Exception e) {
    System.out.println("Invalid input! Please enter a number.");
    scanner.next(); // Clears invalid input
}
```

**Why Used Here?**

- To **handle invalid user input** (e.g., entering a string instead of a number).
- Prevents **program crashes** due to `InputMismatchException`.

---

### **6. `switch` Statement with Lambda Expressions**

**Definition:**  
The `switch` statement selects one of multiple code blocks to execute. In Java 14+, the **lambda (`->`) switch case** was introduced.

**Example in Our Code:**

```java
switch (choice) {
    case 1 -> addTask(scanner);
    case 2 -> viewTasks();
    case 3 -> markTaskCompleted(scanner);
    case 4 -> deleteTask(scanner);
    case 5 -> {
        saveTasks();
        System.exit(0);
    }
    default -> System.out.println("Invalid choice!");
}
```

**Why Used Here?**

- **Lambda syntax (`->`)** makes the switch statement cleaner and **reduces boilerplate code**.

---

### **10. SerialVersionUID**

#### **Definition**
`SerialVersionUID` is a **unique identifier** for a `Serializable` class in Java. It ensures that during the **deserialization** process, the **class definition** used to serialize an object **matches** the one being used to deserialize it.

#### **Why is it Needed?**
When a class implements `Serializable`, Java automatically assigns a `SerialVersionUID`. However, if a class is modified after serialization (e.g., new fields added, method signatures changed), the default auto-generated `SerialVersionUID` **changes**. This can lead to `InvalidClassException` during deserialization.

By explicitly defining `SerialVersionUID`, we ensure **backward compatibility**, allowing older serialized objects to be **read properly** even if the class has been modified.

#### **Example:**

```java
import java.io.*;

class Task implements Serializable {
    private static final long serialVersionUID = 1L; // Explicit UID
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[✔]" : "[ ]") + " " + description;
    }
}
```

#### **Best Practices**
- Always **explicitly define** `SerialVersionUID` when implementing `Serializable`.
- Choose a **fixed value** (e.g., `1L`, `1001L`) and **increment it** when making breaking changes.
- Use the `serialver` tool (`serialver Task`) to generate a compatible UID.## **Conclusion**

### CONCLUSION
This **CLI-based To-Do List** covers:  
✅ **File Handling (Serialization/Deserialization)**  
✅ **Dynamic Data Storage with `ArrayList`**  
✅ **Exception Handling**  
✅ **Lambda Switch Case**  
✅ **Static & Final Keywords Usage**  
✅ **Custom `toString()` Overriding**  

This project is a **great step forward** in Java, giving you **hands-on experience with file handling, OOP, and user input handling**. 🚀

## Author
This project is created by [Vaibhav Sharma](https://www.linkedin.com/in/vaibhavsharma445).

