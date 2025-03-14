import java.util.*;

public class NumberGuessingGame {
    public static void main(String[] args){
        Scanner input= new Scanner(System.in);
        Random random=new Random();
        boolean playAgain;

        System.out.println("Welcome to Number Guessing Game!!");

        do {
            int myNumber = random.nextInt(100)+1;
            int attempts=0;
            int userGuess=0;

            System.out.println("I have chosen  a number between 1-100, try to guess it!! ");

            while (userGuess!=myNumber){
                System.out.println("Enter you guess: ");
                try{
                    userGuess=input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid Input!");
                    input.next();
                    continue;
                }

                if(userGuess>myNumber){
                    System.out.println("Too high, Try Again");
                    attempts++;
                } else if (userGuess<myNumber) {
                    System.out.println("Too low, Try Again");
                    attempts++;
                } else{
                    System.out.println("You guessed it correct in "+ attempts+ " attempts.");
                }
            }
            System.out.println(("Do you want to play again? "));
            input.nextLine();
            String yn=input.nextLine().toLowerCase();
            if(yn.equals("yes")) playAgain=true;
            else playAgain=false;
        }while (playAgain);

        System.out.println("Thank you for Playing Game!!");
        input.close();
    }
}
