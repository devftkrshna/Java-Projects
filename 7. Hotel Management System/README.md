## Hotel Booking System using MultiThreading

### Concepts Used in This Project

1. **Object-Oriented Programming (OOP)**
    
    - Classes and Objects
        
    - Encapsulation (Private Variables, Getter Methods)
        
    - Polymorphism (Method Overriding)
        
    - Inheritance (Not explicitly used, but could be extended)
        
2. **Multi-threading in Java**
    
    - `Thread` class usage
        
    - `synchronized` methods to prevent race conditions
        
    - `Thread.sleep()` for simulating real-world delays
        
    - `Thread.join()` to ensure threads finish execution before proceeding
        
3. **File Handling in Java**
    
    - `BufferedWriter` and `FileWriter` for writing to a file
        
    - `BufferedReader` and `FileReader` for reading from a file
        
4. **Data Structures in Java**
    
    - `ArrayList` for storing Room and User objects
        
    - `List` Interface usage
        
5. **Exception Handling**
    
    - Handling `IOException` during file operations
        
    - Handling `InterruptedException` in multithreading
        
6. **Java Utility Classes**
    
    - `Scanner` for user input
        
    - `Random` for simulating payment success/failure
        

---

## Code Breakdown

### **Class: Room**

```java
class Room {
    private int roomNumber;
    private String type;
    private int price;
    private boolean isBooked;
```

- Defines a `Room` object with attributes: `roomNumber`, `type`, `price`, and `isBooked`.
    
- The `isBooked` variable tracks room booking status.
    

```java
public synchronized void bookRoom() {
    if (!isBooked) {
        isBooked = true;
        System.out.println(Thread.currentThread().getName() + " successfully booked Room: " + roomNumber);
    } else {
        System.out.println(Thread.currentThread().getName() + " failed, Room " + roomNumber + " is already booked.");
    }
}
```

- **`synchronized`** ensures that only one thread can execute `bookRoom()` at a time, preventing multiple bookings for the same room.
    

```java
public synchronized void cancelBooking() {
    if (isBooked) {
        isBooked = false;
        System.out.println("Booking Cancelled Successfully for Room: " + roomNumber + " And refund processed");
    } else {
        System.out.println("Room was not booked!");
    }
}
```

- Ensures safe cancellation of a booking.
    

---

### **Class: Hotel**

```java
class Hotel {
    private ArrayList<Room> rooms = new ArrayList<>();
```

- Stores `Room` objects in an `ArrayList`.
    

```java
public void displayAvailableRooms() {
    for (Room room : rooms) {
        if (!room.isBooked()) {
            System.out.printf("%-20d %-15s %-7d \n", room.getRoomNumber(), room.getType(), room.getPrice());
        }
    }
}
```

- Iterates through `rooms` list and displays unbooked rooms.
    

---

### **Class: BookingThread (Thread Implementation)**

```java
class BookingThread extends Thread {
    private Room room;

    public BookingThread(Room room) {
        this.room = room;
    }

    @Override
    public void run() {
        room.bookRoom();
    }
}
```

- `BookingThread` extends `Thread` and overrides the `run()` method to book a room.
    

---

### **Class: FileHandler (File Handling Operations)**

```java
class FileHandler {
    private static final String FILE_PATH = "bookings.txt";
```

- Defines a constant file path for storing booking records.
    

```java
public static void saveBooking(String data) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter((FILE_PATH), true))) {
        writer.write(data + "\n");
    }
}
```

- Uses `BufferedWriter` to append booking data to the file.
    

```java
public static void readBookings() {
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
```

- Reads and prints booking records from the file.
    

---

### **Class: User (Authentication System)**

```java
class User {
    private String username;
    private String password;
    private boolean isAdmin;
```

- Represents a `User` with authentication details.
    

```java
public boolean validate(String username, String password) {
    return this.username.equals(username) && this.password.equals(password);
}
```

- Verifies login credentials.
    

---

### **Main Class: HotelBookingSystem (User Interaction & Control Flow)**

```java
public class HotelBookingSystem {
    private static List<User> users = new ArrayList<>();
```

- Stores predefined users (Admin, Guest).
    

```java
System.out.print("Enter Username: ");
String username = input.next();
System.out.println("Enter Password: ");
String password = input.next();
```

- Takes username and password input.
    

```java
if (currentUser == null) {
    System.out.println("Invalid Login");
    return;
}
```

- Ensures valid user authentication.
    

```java
case 2 -> {
    hotel.displayAvailableRooms();
    System.out.println("Enter Room Number to Book: ");
    int roomNumber = input.nextInt();
    Room room = hotel.getRoomByNumber(roomNumber);
    if (room != null && !room.isBooked()) {
        BookingThread thread = new BookingThread(room);
        thread.start();
        FileHandler.saveBooking("Room " + roomNumber + " booked.");
        System.out.println("Processing payment...");
        if (new Random().nextBoolean()) {
            System.out.println("Payment Successful!");
        } else {
            System.out.println("Payment Failed! Try Again.");
        }
    }
}
```

- Books a room using multithreading and simulates payment processing.
    

---

### **Test Class: TestConcurrentBooking (Testing Multi-threading)**

```java
public class TestConcurrentBooking {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        int roomNumberToBook1 = 101;
        Room room1 = hotel.getRoomByNumber(roomNumberToBook1);
        Thread user1 = new BookingThread(room1);
        Thread user2 = new BookingThread(room1);
        user1.start();
        user2.start();
        user1.join();
        user2.join();
        hotel.displayAvailableRooms();
    }
}
```

- **Creates two threads** trying to book the **same room**, demonstrating synchronized booking.
    
- **Uses `Thread.join()`** to ensure both threads finish execution before displaying available rooms.
    

---

### **Conclusion**

- This project effectively demonstrates **multi-threading** with **synchronized** methods to handle concurrent bookings.
    
- Implements **file handling** to persist booking data.
    
- Uses **OOP principles** to maintain modularity and scalability.
    
- Can be **extended** with more functionalities like **database integration**, **payment gateway**, and **graphical UI**.