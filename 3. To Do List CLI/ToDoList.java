import java.io.*;
import java.util.*;

class Task implements Serializable{
    @Serial
    private static final long serialVersionUID = 123L;
    private String Description;
    private boolean isCompleted;

    Task(String description){
        this.Description=description;
        this.isCompleted=false;
    }

    public String getDescription(){
        return Description;
    }

    public boolean isCompleted(){
        return isCompleted;
    }

    public void markCompleted(){
        this.isCompleted=true;
    }

    public String toString(){
        return (isCompleted ? "[Done]": "[]") + " " + Description;
    }
}

public class ToDoList {
    private static final  String file_name="tasks.dat";
    private static ArrayList<Task> tasks= new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        loadTasks();
        while(true){
            System.out.println("Welcome to ToDoList- Managing Tasks Made Simple!!");
            System.out.println("Choose any one option to continue...");
            System.out.println("1. View Tasks.");
            System.out.println("2. Add New Task.");
            System.out.println("3. Delete Existing Task.");
            System.out.println("4. Mark Task Completed.");
            System.out.println("5. Exit.");
            System.out.println("Enter your Choice: ");

            int choice;
            try{
                choice=input.nextInt();
                input.nextLine();
            } catch(Exception e){
                System.out.println("Invalid Choice");
                input.next();
                continue;
            }

            switch (choice){
                case 1-> viewTasks();
                case 2-> addTask();
                case 3-> deleteTask();
                case 4-> markTaskCompleted();
                case 5->{
                    saveTasks();
                    System.out.println("Exiting...Bye!!!");
                    input.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice. Try Again!!!");
            }
        }

    }

    private static void addTask(){
        System.out.println("Enter Task Description: ");
        String des= input.nextLine();
        tasks.add(new Task(des));
        System.out.println("Task Added Successfully!!!");
    }

    private static void viewTasks(){
        if(tasks.isEmpty()){
            System.out.println("There is no Tasks..");
            return;
        }
        System.out.println("----------Task List------------");
        for(int i=0; i< tasks.size();i++){
            System.out.println((i+1)+". "+ tasks.get(i));
        }
    }

    private static void markTaskCompleted(){
        viewTasks();
        if(tasks.isEmpty()) return;
        System.out.println("Enter task number to mark completed: ");
        int t=input.nextInt();
        if(t>0&&t<=tasks.size()){
            tasks.get(t-1).markCompleted();
            System.out.println("Task marked Completed!!");
        }
        System.out.println("Choose the Task again carefully!!");
    }

    private static void deleteTask(){
        viewTasks();
        if(tasks.isEmpty()) return;
        System.out.println("Enter task number to delete: ");
        int t= input.nextInt();
        if(t>0 && t<=tasks.size()){
            tasks.remove(t-1);
            System.out.println("Task deleted succesfully!!!");
        }else System.out.println("Invalid Number");

    }

    public static void saveTasks(){
        try(ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(file_name))){
            oos.writeObject(tasks);
        } catch (IOException e){
            System.out.println("Save Failed!!");
        }
    }

    public static void loadTasks(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file_name))){
            tasks=(ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error Loading tasks");
        }
    }
}
