import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

class Room{
    private int roomNumber;
    private String type;
    private int price;
    private boolean isBooked;

    public Room(int roomNumber, String type,int price){
        this.roomNumber=roomNumber;
        this.type=type;
        this.price=price;
        this.isBooked=false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public synchronized void bookRoom(){
        //Use this when you want to test the multi threading.
/*        try {
            System.out.println(Thread.currentThread().getName() + " trying to book Room: " + roomNumber);
            Thread.sleep(10000); // Simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        if(!isBooked){
            isBooked = true;
            System.out.println(Thread.currentThread().getName() + " successfully booked Room: " + roomNumber);
        } else {
            System.out.println(Thread.currentThread().getName() + " failed, Room " + roomNumber + " is already booked.");
        }
    }

    public synchronized void cancelBooking() {
        if(isBooked){
            isBooked=false;
            System.out.println("Booking Cancelled Successfully for Room: "+ roomNumber+ "And refund processed");
        }else{
            System.out.println("Room was not booked! ");
        }
    }
}

class Hotel{
    private ArrayList<Room> rooms=new ArrayList<>();

    public Hotel(){
        rooms.add(new Room(101, "Single", 1000));
        rooms.add(new Room(102, "Single",1000));
        rooms.add(new Room(103, "Single",1000));
        rooms.add(new Room(201, "Double",2000));
        rooms.add(new Room(202, "Double",2000));
        rooms.add(new Room(203, "Double",2000));
        rooms.add(new Room(204, "Double",2000));
        rooms.add(new Room(301, "Suite",6000));
        rooms.add(new Room(302, "King Suite",8000));
    }

    public void displayAvailableRooms(){
        if(rooms.isEmpty()){
            System.out.println("Sorry no rooms available in the hotel!! ");
        }else{
            System.out.printf("%-20s %-15s %-7s \n", "Room Number","Type", "Price");
            for(Room room:rooms){
                if(!room.isBooked()){
                    System.out.printf("%-20d %-15s %-7d \n", room.getRoomNumber(), room.getType(), room.getPrice());
                }
            }
        }
    }

    public Room getRoomByNumber(int roomNumber){
        for(Room room:rooms){
            if(room.getRoomNumber()==roomNumber){
                return room;
            }
        }
        return null;
    }
}

class BookingThread extends Thread{
    private Room room;

    public BookingThread(Room room){
        this.room=room;
    }

    @Override
    public void run(){
        room.bookRoom();
    }
}

class FileHandler{
    private static final String FILE_PATH="bookings.txt";

    public static void saveBooking(String data) throws IOException {
        try(BufferedWriter writer =new BufferedWriter(new FileWriter((FILE_PATH), true))){
            writer.write(data+"\n");
        }
    }
    public static void readBookings() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class User{
    private String username;
    private String password;
    private boolean isAdmin;

    public User(String username, String password, boolean isAdmin){
        this.username=username;
        this.password=password;
        this.isAdmin=isAdmin;
    }

    public boolean validate(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}



public class HotelBookingSystem {
    private static List<User> users =new ArrayList<>();
    static {
        users.add(new User("Admin","admin123", true));
        users.add(new User("Guest", "guest123",false));
    }
    static Scanner input= new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        Hotel hotel=new Hotel();
        System.out.print("Enter Username: ");
        String username =input.next();
        System.out.println("Enter Password: ");
        String password =input.next();

        User currentUser=null;
        for(User user:users){
            if(user.validate(username,password)){
                currentUser=user;
                break;
            }
        }

        if(currentUser==null){
            System.out.println("Invalid Login");
            return;
        }

        System.out.println("Welcome "+ username);
        boolean isAdmin= currentUser.isAdmin();

        while (true){
            System.out.println(" 1. View Rooms\n 2. Book Room\n 3. Cancel Booking\n 4. View Bookings");
            if (isAdmin) {
                System.out.println(" 5. Add Room\n 6. Remove Room");
            }
            System.out.println(" 7. Exit");
            int choice=input.nextInt();

            switch (choice){
                case 1->hotel.displayAvailableRooms();
                case 2->{
                    hotel.displayAvailableRooms();

                    System.out.println("Enter Room Number to Book: ");
                    int roomNumber=input.nextInt();

                    Room room =hotel.getRoomByNumber(roomNumber);

                    if(room !=null && !room.isBooked()){
                        BookingThread thread =new BookingThread(room);
                        thread.start();
                        FileHandler.saveBooking("Room " + roomNumber + " booked.");
                        System.out.println("Processing payment...");
                        if (new Random().nextBoolean()) {
                            System.out.println("Payment Successful!");
                        } else {
                            System.out.println("Payment Failed! Try Again.");
                        }
                    } else {
                        System.out.println("Room not available.");
                    }
                    }
                case 3->{
                    System.out.println("Enter Room Number to Cancel booking ");
                    int roomNumber = input.nextInt();
                    Room room =hotel.getRoomByNumber(roomNumber);
                    if (room != null && room.isBooked()) {
                        room.cancelBooking();
                        FileHandler.saveBooking("Room " + roomNumber + " booking cancelled.");
                    } else {
                        System.out.println("No active booking found for this room.");
                    }
                }
                case 4-> FileHandler.readBookings();
                case 5-> {
                    if (isAdmin) {
                        System.out.print("Enter new room number: ");
                        int roomNumber = input.nextInt();

                        System.out.print("Enter room type: ");
                        String type = input.next();

                        System.out.print("Enter price: ");
                        double price = input.nextInt();

                        hotel.getRoomByNumber(roomNumber);
                        System.out.println("Room added successfully.");

                    } else {
                        System.out.println("Unauthorized action.");
                    }
                }
                case 6 -> {
                    if (isAdmin) {
                        System.out.print("Enter room number to remove: ");
                        int roomNumber = input.nextInt();

                        Room room = hotel.getRoomByNumber(roomNumber);

                        if (room != null) {
                            System.out.println("Room " + roomNumber + " removed.");
                        }

                    } else {
                        System.out.println("Unauthorized action.");
                    }

                }
                case 7 -> System.exit(0);
                default -> System.out.println("Invalid Choice");
            }
        }

    }
}
