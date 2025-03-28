public class TestConcurrentBooking {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(); // Create hotel instance

        // Pick a room number to test concurrent booking
        int roomNumberToBook1 = 101;
        int roomNumberToBook2 =201;
        int roomNumberToBook3 =301;


        // Get the room object
        Room room1 = hotel.getRoomByNumber(roomNumberToBook1);
        Room room2 = hotel.getRoomByNumber(roomNumberToBook2);
        Room room3 = hotel.getRoomByNumber(roomNumberToBook3);


        // Create multiple threads trying to book the same room
        Thread user1 = new BookingThread(room1);
        Thread user2 = new BookingThread(room2);
        Thread user3 = new BookingThread(room3);

        // Assign thread names
        user1.setName("User 1");
        user2.setName("User 2");
        user3.setName("User 3");

        // Start all threads
        user1.start();
        user2.start();
        user3.start();

        // Wait for all threads to finish
        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display available rooms after booking attempts
        hotel.displayAvailableRooms();
    }
}
