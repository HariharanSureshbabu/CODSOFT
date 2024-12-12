import java.util.Scanner;

class BankAccount {
    private String username;
    private String phoneNumber;
    private double balance;

    public BankAccount(String username, String phoneNumber, double initialBalance) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.balance = initialBalance;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited \u20b9" + amount);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew \u20b9" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance for this withdrawal.");
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        }
        return false;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Choose an operation: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAccount Holder: " + account.getUsername());
                    System.out.println("Phone Number: " + account.getPhoneNumber());
                    System.out.println("Your current balance is \u20b9" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the amount to deposit: \u20b9");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to withdraw: \u20b9");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4: 
                    System.out.println("Thank you for using the ATM. Come back later!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

public class ATMmachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your Name: ");
        String username = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter initial balance for the account: \u20b9");
        double initialBalance = scanner.nextDouble();

        BankAccount account = new BankAccount(username, phoneNumber, initialBalance);
        ATM atm = new ATM(account);

        atm.start();
        scanner.close();
    }
}
