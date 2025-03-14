# 🏦 Bank Management System - Explannation

## 📌 Overview

This Java program simulates a simple **Bank Management System**, allowing users to create either a **Savings Account** or a **Current Account**. The system provides basic operations such as **Deposit, Withdraw, Check Balance, and Display Account Details**.

---

## 📜 **Code Breakdown - Line by Line Explanation**

### **1️⃣ Importing Required Package**

```java
import java.util.*;
```

- `import` → Used to include external Java libraries.
- `java.util.*` → Imports all utility classes, including `Scanner`, which is used for user input.

---

### **2️⃣ Defining the `Account` Class**

```java
class Account {
    private final String accNumber;
    protected double balance;
    private final String holderName;
```

- `class` → Defines a **class** in Java.
- `Account` → Parent class representing a general **Bank Account**.
- `private` → Restricts access to **within the class only**.
- `final` → Prevents modification of variables after initialization.
- `protected` → Allows access within the **same package and subclasses**.

#### **Constructor for `Account` Class**

```java
    Account(String holdername, String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
        this.holderName = holdername;
    }
```

- `Account(...)` → Constructor initializes account details.
- `this` → Refers to the current object's instance variables.

#### **Deposit Method**

```java
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount Deposited: " + amount);
        } else {
            System.out.println("Invalid Deposit amount");
        }
    }
```

- `public` → Method can be accessed anywhere.
- `void` → No return value.
- `if` condition ensures deposit amount is valid.

#### **Withdraw Method**

```java
    public void withdraw(double amount) {
        if (amount < balance && amount > 0) {
            balance -= amount;
            System.out.println("Amount Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient Balance");
        }
    }
```

- Checks if sufficient balance is available before withdrawal.

#### **Method to Get Balance**

```java
    public double getBalance() {
        return balance;
    }
```

- Returns the **current balance**.

#### **Displaying Account Details**

```java
    public void displayDetails() {
        System.out.println("Name: " + holderName);
        System.out.println("Account Number: " + accNumber);
        System.out.println("Balance: " + balance);
    }
```

- Prints account holder's name, number, and balance.

---

### **3️⃣ Defining the `SavingAccount` Class**

```java
class SavingAccount extends Account {
    public static final double MIN_BALANCE = 500;
```

- `extends` → Inheritance concept; `SavingAccount` **inherits** from `Account`.
- `static final` → A constant **shared across all instances**.

#### **Overriding Withdraw Method**

```java
    @Override
    public void withdraw(double amount) {
        if (balance - amount > MIN_BALANCE) {
            balance -= amount;
            System.out.println("Amount Withdrawn: " + amount);
        } else {
            System.out.println("Withdrawal Denied, Minimum Balance 500 is required in the Account.");
        }
    }
```

- `@Override` → Indicates **method overriding**.
- Ensures a minimum balance of **₹500** is maintained after withdrawal.

---

### **4️⃣ Defining the `CurrentAccount` Class**

```java
class CurrentAccount extends Account {
    CurrentAccount(String holdername, String accNumber, double balance) {
        super(holdername, accNumber, balance);
    }
}
```

- `super(...)` → Calls the parent class (`Account`) constructor.
- No special withdrawal conditions unlike `SavingAccount`.

---

### **5️⃣ Main Class: `BankManagementSystem`**

```java
public class BankManagementSystem {
    public static void main(String[] args) {
```

- `public` → Allows the JVM to access this method.
- `static` → Belongs to the class, does not require an object.
- `void` → Does not return any value.
- `main(String[] args)` → Entry point of the Java program.

#### **Taking User Input**

```java
        Scanner input = new Scanner(System.in);
```

- `Scanner` → Used to read user input.

```java
        System.out.println("Enter Account Number: ");
        String accnum = input.nextLine();
```

- Reads **account number** as a string.

```java
        System.out.println("Enter Holder Name: ");
        String name = input.nextLine();
```

- Reads **account holder's name**.

```java
        System.out.println("Choose Account Type: 1. Savings 2. Current");
        int choice = input.nextInt();
```

- Asks user to choose between **Savings or Current** account.

---

### **6️⃣ Creating an Account Object Using `switch`**

```java
        Account account = null;
        switch (choice) {
            case 1 -> account = new SavingAccount(name, accnum, 500);
            case 2 -> account = new CurrentAccount(name, accnum, 0);
            default -> System.out.println("Invalid Choice");
        }
```

- `switch` → A control flow statement.
- `case 1` → Creates **Savings Account** with `₹500` minimum balance.
- `case 2` → Creates **Current Account** with `₹0` balance.
- `->` → **Lambda-style syntax** (Java 12+).

---

### **7️⃣ Bank Menu (Looping for Transactions)**

```java
        while (true) {
            System.out.println("\n--- Bank Account Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int option = input.nextInt();
```

- **Infinite loop (`while(true)`)** → Runs **until user exits**.
- Displays a **menu for banking operations**.

---

### **8️⃣ Handling User Choices**

#### **Deposit**

```java
                case 1:
                    System.out.println("Enter amount to deposit.");
                    double amount = input.nextDouble();
                    assert account != null;
                    account.deposit(amount);
                    break;
```

- Takes deposit amount and updates balance.

#### **Withdraw**

```java
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = input.nextDouble();
                    assert account != null;
                    account.withdraw(withdrawAmount);
                    break;
```

- Withdraws money (if balance is sufficient).

#### **Check Balance**

```java
                case 3:
                    assert account != null;
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
```

- Displays **current balance**.

#### **Show Account Details**

```java
                case 4:
                    assert account != null;
                    account.displayDetails();
                    break;
```

- Shows **account number, name, and balance**.

#### **Exit Program**

```java
                case 5:
                    System.out.println("Exiting Bank System. Thank you!");
                    input.close();
                    System.exit(0);
                    break;
```

- `System.exit(0);` → Stops program execution.

---

## 🎯 **Key Java Concepts Used**

1. **OOP Principles**:
    - **Encapsulation** (private variables).
    - **Inheritance** (`extends`).
    - **Polymorphism** (`@Override`).
2. **Access Modifiers**: `private`, `protected`, `public`.
3. **Static & Final**: `static final` for constants.
4. **Exception Handling**: Basic checks for negative deposits/withdrawals.
5. **Loops & Conditionals**: `while`, `if-else`, `switch-case`.
6. **Scanner Class**: To handle user input.

---

## 🔥 **Conclusion**

This **Bank Management System** efficiently demonstrates **OOP principles** and core **Java functionalities**. You can expand this by adding **interest calculation, transaction history, or GUI**.

## **🖊️ Author**
This project is created by **[Vaibhav Sharma](https://www.linkedin.com/in/vaibhavsharma445)**