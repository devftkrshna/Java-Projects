### Concepts:
Variables, Methods, Switch Case, Loops, Exception Handling

### Features:
Addition, Subtraction, Multiplication, Division


---
## My Code:
```
import java.util.*;  
public class Calculator {  
    public static void main(String[] args){  
        int a;  
        int b;  
        char op;  
        System.out.println("Hello, Welcome to my Calculator!!\n");  
        System.out.println("Enter the numbers and operation you want to perform: ");  
        Scanner myobj= new Scanner(System.in);  
        a=myobj.nextInt();  
        b= myobj.nextInt();  
        op=myobj.next().charAt(0);  
        switch (op){  
            case '+':  
                System.out.println(a+b);  
                break;  
            case '-':  
                System.out.println(a-b);  
                break;  
            case '*':  
                System.out.println(a*b);  
                break;  
            case '/':  
                if(b==0) System.out.println("Undefined");  
                else System.out.println((float)(a/b));  
                break;  
            default:  
                throw new IllegalStateException("Unexpected value: " + op);  
        }  
  
        System.out.println("Thank You");  
  
  
  
    }  
}
```

## Better Implementation
```
import java.util.*;  
public class Calculator {  
    public static void main(String[] args){  
        Scanner obj= new Scanner(System.in);  
        System.out.println("Hello, Welcome to my Calculator");  
        while (true){  
            try{  
                System.out.println("Enter First Number: ");  
                double a=obj.nextDouble();  
  
                System.out.println("Enter Second Number: ");  
                double b= obj.nextDouble();  
  
                System.out.println("Enter operation (+,-,*,/): ");  
                char op=obj.next().charAt(0);  
  
                double result=0;  
                boolean validOperation=true;  
                switch (op){  
                    case '+':  
                        result=a+b;  
                        break;  
                    case '-':  
                        result=a-b;  
                        break;  
                    case '*':  
                        result=a*b;  
                        break;  
                    case '/':  
                        if(b==0){  
                            System.out.println("Undefined");  
                            validOperation=false;  
                        }  
                        else result=a/b;  
                        break;  
                    default:  
                        System.out.println("Error: Invalid operator.");  
                        validOperation = false;  
                }  
                if(validOperation){  
                    System.out.print("Result: "+result);  
                }  
            } catch (Exception e) {  
                System.out.println("Error: Invalid input. Please enter numbers only.");  
                obj.next();  
            }  
            System.out.println("\nDo you want to perform another operation (yes/no): ");  
            String choice= obj.next().toLowerCase();  
            if(choice.equals("no")){  
                break;  
            }  
        }  
        System.out.println("Thank you for using the Calculator!");  
        obj.close();  
    }  
}
```


---
# Java Calculator Notes

## **Concepts & Elements Used**

### **1. Scanner Class (User Input Handling)**

- `Scanner scanner = new Scanner(System.in);`
- Used to take user input.
- Methods used:
    - `nextDouble()` → Reads a double input.
    - `next().charAt(0)` → Reads a character input.
    - `next()` → Reads a string input.


**Example:**

```
Scanner scanner = new Scanner(System.in);
double num = scanner.nextDouble();
```

---

### **2. Loops (While Loop for Continuous Execution)**

- Keeps the calculator running until the user decides to exit.
- `while (true) {}` is an **infinite loop**, exited using `break;`.

**Example:**

```
while (true) {
    // Loop body (code inside runs continuously)
    if (exitCondition) {
        break; // Exits the loop
    }
}
```

---

### **3. Exception Handling (try-catch for Input Validation)**

- Prevents program crashes due to incorrect input.
- `try` block contains code that may cause an error.
- `catch` block handles errors gracefully.

**Example:**

```
try {
    double number = scanner.nextDouble();
} catch (Exception e) {
    System.out.println("Invalid input, please enter a number.");
    scanner.next(); // Clears invalid input
}
```

---

### **4. Switch Case (Handling Operations)**

- Used for selecting an operation based on user input.
- `break;` ensures only one case executes.

**Example:**

```
switch (operator) {
    case '+':
        result = a + b;
        break;
    case '-':
        result = a - b;
        break;
    default:
        System.out.println("Invalid operator");
}
```

---

### **5. Conditional Statements (if-else for Error Handling)**

- Used to check conditions (like division by zero).

**Example:**

```
if (b == 0) {
    System.out.println("Error: Division by zero is undefined.");
} else {
    result = a / b;
}
```

---

### **6. String Methods (Checking User Choice)**

- `.toLowerCase()` → Converts input to lowercase.
- `.equals("yes")` → Checks if input matches "yes".

**Example:**

```
if (!choice.equals("yes")) {
    break; // Exits the loop
}
```

---

### **7. Closing Scanner (Avoiding Memory Leaks)**

- `scanner.close();` should be called at the end of the program.

**Example:**

```
scanner.close();
```

---

### **Summary of Concepts Used**

|Concept|Purpose|
|---|---|
|Scanner Class|Take user input|
|While Loop|Repeat calculations|
|Try-Catch|Handle invalid input|
|Switch Case|Perform arithmetic operations|
|If-Else|Handle special conditions (division by zero)|
|String Methods|Process user choice for continuation|
|Scanner Close|Prevent memory leaks|

---

### **Next Steps**

- Convert this CLI-based calculator to a **GUI-based** calculator using Java Swing or JavaFX.
    
- Add more operations like modulus (`%`), exponentiation (`x^y`), and square root (`√x`).