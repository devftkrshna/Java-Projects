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
