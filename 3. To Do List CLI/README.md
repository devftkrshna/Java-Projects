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

`FileOutputStream fos = new FileOutputStream("data.txt"); ObjectOutputStream oos = new ObjectOutputStream(fos); oos.writeObject(tasks); oos.close();`

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
    
    `ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tasks.dat")); oos.writeObject(tasks); oos.close();`
    
- **Deserialization (Loading tasks)**
    
     `ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tasks.dat")); tasks = (ArrayList<Task>) ois.readObject(); ois.close();`
    

**Why Used Here?**

- We need to **store task data permanently** and retrieve it when restarting the application.

---

### **3. `static` Keyword**

**Definition:**  
The `static` keyword in Java makes a method or variable belong to the class rather than an instance.

**Example in Our Code:**

`private static ArrayList<Task> tasks = new ArrayList<>();`

**Why Used Here?**

- `tasks` is `static` because it needs to be **shared across all methods** without creating an instance of `ToDoList`.
- The methods like `addTask()`, `viewTasks()`, and `saveTasks()` are `static` so they can **modify the shared task list**.

---

### **4. `ArrayList` in Java**

**Definition:**  
`ArrayList` is a resizable array implementation of `List`. Unlike arrays, it dynamically resizes as elements are added or removed.

**Example:**

java

CopyEdit

`ArrayList<Task> tasks = new ArrayList<>(); tasks.add(new Task("Complete Homework")); tasks.remove(0);`

**Why Used Here?**

- Since task management requires **adding, deleting, and updating** tasks dynamically, `ArrayList` is an ideal choice.

---

### **5. Try-Catch Exception Handling**

**Definition:**  
Try-catch blocks handle runtime exceptions, preventing the program from crashing.

**Example in Our Code:**

java

CopyEdit

`try {     choice = scanner.nextInt(); } catch (Exception e) {     System.out.println("Invalid input! Please enter a number.");     scanner.next(); // Clears invalid input }`

**Why Used Here?**

- To **handle invalid user input** (e.g., entering a string instead of a number).
- Prevents **program crashes** due to `InputMismatchException`.

---

### **6. `switch` Statement with Lambda Expressions**

**Definition:**  
The `switch` statement selects one of multiple code blocks to execute. In Java 14+, the **lambda (`->`) switch case** was introduced.

**Example in Our Code:**

java

CopyEdit

`switch (choice) {     case 1 -> addTask(scanner);     case 2 -> viewTasks();     case 3 -> markTaskCompleted(scanner);     case 4 -> deleteTask(scanner);     case 5 -> {         saveTasks();         System.exit(0);     }     default -> System.out.println("Invalid choice!"); }`

**Why Used Here?**

- **Lambda syntax (`->`)** makes the switch statement cleaner and **reduces boilerplate code**.

---

### **7. Overriding `toString()`**

**Definition:**  
Method overriding allows a subclass to provide a specific implementation of a method already defined in the superclass.

**Example in Our Code:**

java

CopyEdit

`@Override public String toString() {     return (isCompleted ? "[✔]" : "[ ]") + " " + description; }`

**Why Used Here?**

- **Customizes how tasks are displayed** when printed (`System.out.println(task)`).

---

### **8. Scanner Class for User Input**

**Definition:**  
The `Scanner` class reads user input from various sources (keyboard, file, etc.).

**Example in Our Code:**

java

CopyEdit

`Scanner scanner = new Scanner(System.in); System.out.print("Enter task description: "); String description = scanner.nextLine();`

**Why Used Here?**

- To take user input dynamically.

---

### **9. `final` Keyword**

**Definition:**  
The `final` keyword is used to make a variable, method, or class **unchangeable**.

**Example in Our Code:**

java

CopyEdit

`private static final String FILE_NAME = "tasks.dat";`

**Why Used Here?**

- `FILE_NAME` is `final` to **prevent accidental modification**.

---

## **Line-by-Line Explanation of Code**

Here's a **detailed breakdown** of important sections:

### **Task Class**

`class Task implements Serializable {`

 -`implements Serializable` → Allows task objects to be **saved to a file**.
```
private static final long serialVersionUID = 1L;
```

- Ensures **consistent object versioning** across different runs.
- 'serialVersionUID' can be annotated with '@Serial' annotation

```
@Serial  
private static final long serialVersionUID = 123L;
```

java

CopyEdit

`public Task(String description) {     this.description = description;     this.isCompleted = false; }`

- Constructor initializes the task with a description and marks it as **not completed**.

java

CopyEdit

`@Override public String toString() {     return (isCompleted ? "[✔]" : "[ ]") + " " + description; }`

- Overridden `toString()` method **formats how tasks are displayed**.

---

### **Main Application**

java

CopyEdit

`private static ArrayList<Task> tasks = new ArrayList<>();`

- Stores tasks in a **resizable array**.

java

CopyEdit

`public static void main(String[] args) {     loadTasks(); // Loads saved tasks from file     Scanner scanner = new Scanner(System.in);`

- Initializes a `Scanner` object for user input.

java

CopyEdit

`switch (choice) {     case 1 -> addTask(scanner);     case 2 -> viewTasks();     case 3 -> markTaskCompleted(scanner);     case 4 -> deleteTask(scanner);     case 5 -> {         saveTasks();         System.exit(0);     }     default -> System.out.println("Invalid choice!"); }`

- Uses **lambda switch case** for user choices.

---

### **File Handling (Saving & Loading Tasks)**

java

CopyEdit

`private static void saveTasks() {     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {         oos.writeObject(tasks);     } catch (IOException e) {         System.out.println("Error saving tasks.");     } }`

- **Saves tasks to a file** using `ObjectOutputStream`.

java

CopyEdit

`private static void loadTasks() {     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {         tasks = (ArrayList<Task>) ois.readObject();     } catch (IOException | ClassNotFoundException e) {         tasks = new ArrayList<>();     } }`

- **Loads tasks from a file**. If the file doesn’t exist, initializes an empty task list.

---

## **Conclusion**

This **CLI-based To-Do List** covers: ✅ **File Handling (Serialization/Deserialization)**  
✅ **Dynamic Data Storage with `ArrayList`**  
✅ **Exception Handling**  
✅ **Lambda Switch Case**  
✅ **Static & Final Keywords Usage**  
✅ **Custom `toString()` Overriding**

This project is a **great step forward** in Java, giving you **hands-on experience with file handling, OOP, and user input handling**. 🚀