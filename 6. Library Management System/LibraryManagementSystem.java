import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    String title;
    int bookId;
    String authorName;
    boolean isAvailable;

    Book( int bookId, String title, String authorName) {
        this.bookId = bookId;
        this.title = title;
        this.authorName = authorName;
        this.isAvailable=true;
    }

    public String getTitle(){ return title; }
    public int getBookId(){ return bookId; }
    public String getAuthorName(){ return authorName; }

    public void setAvailable(boolean available){
        this.isAvailable=available;
    }

    public void setTitle(String title){ this.title=title; }
    public void setBookId(int bookId){ this.bookId=bookId; }
    public void setAuthorName(String authorName){ this.authorName=authorName;}

    @Override
    public String toString(){ return "Book: "+ title+ " BookID: "+bookId+ "Author: "+ authorName+ "Available:"+(isAvailable?"Yes":"No"); }
}

class Library{
    private ArrayList<Book> books;
    private static final String FILE_NAME= "Library.txt";

    Library(){
        books= new ArrayList<>();
        loadbooks();
    }

    public void addBook(Book book){
        books.add(book);
        System.out.println(book.title+ " by "+book.title+" added successfully!!");
    }

    public void viewBooks(){
        if(books.isEmpty()){
            System.out.println("No books available in database!!");
        } else{
            for (Book book:books){
                System.out.println(book);
            }
        }
    }

    public void issueBook(int bookId){
        for(Book book:books){
            if(book.getBookId()==bookId){
                if(book.isAvailable){
                    book.setAvailable(false);
                    System.out.println("Book is Issued Successfully!!");
                } else{
                    System.out.println("Book is already issued to SomeoneElse!!");
                }
                return;
            }
        }
        System.out.println("Book not found");
    }

    public void returnBook(int bookId){
        for(Book book:books){
            if(book.getBookId()==bookId){
                if(book.isAvailable){
                    book.setAvailable(true);
                    System.out.println("Book is returned Successfully!!");
                } else{
                    System.out.println("Book was not issued!!");
                }
                return;
            }
        }
        System.out.println("Book not found");
    }

    public void removeBook(int bookID) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == bookID) {
                books.remove(i);
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void saveBooks(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(books);
            System.out.println("Data Saved !!");
        }catch(IOException e){
            System.out.println("Error Saving Data "+ e.getMessage());
        }
    }

    public void loadbooks(){
        try(ObjectInputStream ois= new ObjectInputStream(new FileInputStream(FILE_NAME))){
            books= (ArrayList<Book>) ois.readObject();
        }catch (FileNotFoundException e){
            System.out.print("No Data Found for the Library, Starting Fresh");
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error Loading data "+e.getMessage());
        }
    }
}



public class LibraryManagementSystem {
    public static void main(String[] args){
        Library library=new Library();
        Scanner input=new Scanner(System.in);

        while(true){
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Remove Book");
            System.out.println("6. Save & Exit");
            System.out.print("Enter your choice: ");
            int choice= input.nextInt();
            input.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Enter Book ID: ");
                    int bookID = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter Title: ");
                    String title = input.nextLine();
                    System.out.print("Enter Author: ");
                    String author = input.nextLine();
                    Book newBook = new Book(bookID, title, author);
                    library.addBook(newBook);
                    break;
                case 2:
                    library.viewBooks();
                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int issueID = input.nextInt();
                    library.issueBook(issueID);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnID = input.nextInt();
                    library.returnBook(returnID);
                    break;
                case 5:
                    System.out.print("Enter Book ID to remove: ");
                    int removeID = input.nextInt();
                    library.removeBook(removeID);
                    break;
                case 6:
                    library.saveBooks();
                    System.out.println("Exiting system. Goodbye!");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
