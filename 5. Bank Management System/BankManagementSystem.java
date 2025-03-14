import java.util.*;

class Account{
    private final String accNumber;
    protected double balance;
    private final String holderName;

    Account(String holdername, String accNumber, double balance){
        this.accNumber=accNumber;
        this.balance=balance;
        this.holderName=holdername;
    }

    public void deposit(double amount){
        if( amount>0){
            balance+=amount;
            System.out.println("Amount Deposited: "+amount);
        }else {
            System.out.println("Invalid Deposit amount");
        }
    }

    public void withdraw(double amount){
        if(amount<balance && amount>0){
            balance-=amount;
            System.out.println("Amount Withdrawn: "+ amount);
        } else{
            System.out.println("Insufficient Balance");
        }
    }

    public double getBalance(){
        return balance;
    }

    public void displayDetails(){
        System.out.println("Name: "+ holderName);
        System.out.println("Account Number:  "+ accNumber);
        System.out.println("Balance: "+ balance);
    }
}

class SavingAccount extends Account{
    public static final double MIN_BALANCE=500;

    SavingAccount(String holderName, String accNumber, double balance){
        super(holderName,accNumber,balance);
    }

    @Override
    public void withdraw(double amount){
        if(balance-amount>MIN_BALANCE){
            balance-=amount;
            System.out.println("Amounr Withdrawn: "+ amount);
        }
        else{
            System.out.println("Withdrwal Denied, Minimum Balance 500 is required in the Account.");
        }
    }

}

class CurrentAccount extends Account{
    CurrentAccount(String holdername, String accNumber, double balance){
        super(holdername, accNumber, balance);
    }
}

public class BankManagementSystem {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);

        System.out.println("Enter Account Number: ");
        String accnum=input.nextLine();

        System.out.println("Enter Holder Name: ");
        String name=input.nextLine();

        System.out.println("Choose Account Type: 1. Savings 2. Current");
        int choice= input.nextInt();

        Account account=null;
        switch (choice){
            case 1-> account=new SavingAccount(name,accnum,500);
            case 2-> account=new CurrentAccount(name,accnum,0);
            default -> System.out.println("Invalid Choice");
        }

        while(true){
            System.out.println("\n--- Bank Account Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int option = input.nextInt();

            switch(option){
                case 1:
                    System.out.println("Enter amount to deposit.");
                    double amount= input.nextDouble();
                    assert account != null;
                    account.deposit(amount);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = input.nextDouble();
                    assert account != null;
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    assert account != null;
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 4:
                    assert account != null;
                    account.displayDetails();
                    break;
                case 5:
                    System.out.println("Exiting Bank System. Thank you!");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

    }
}
