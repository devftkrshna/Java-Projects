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
