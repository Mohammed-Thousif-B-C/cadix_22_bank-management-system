import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BankAccount {

    static String accountNumber;
    static String accountHolderName;
    static double balance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + " into account " + accountNumber);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + " from account " + accountNumber);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void displayBalance() {
        System.out.println("Account " + accountNumber + " balance: " + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName);
        interestRate = 0.05;
    }

    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Interest of " + interest + " applied to Savings account " + accountNumber);
    }
}

class CurrentAccount extends BankAccount {

    private double interestRate;

    public CurrentAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName);
        interestRate = 0.02;
    }

    public void applyInterest() {
        double interest = balance * interestRate;
        balance -= interest;
        System.out.println("Interest of " + interest + " applied to Current account " + accountNumber);
    }
}

public class Project1 {
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("*********Banking Transaction*********");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Display Balance");
            System.out.println("6. Apply Interest");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            if (choice == 1 || choice == 2) {
                String accountNumber, accountHolderName;
                System.out.print("Enter account number: ");
                accountNumber = scanner.next();
                System.out.print("Enter account holder name: ");
                accountHolderName = scanner.next();

                boolean accountExists = false;

                for (BankAccount account : accounts) {
                    if (accountNumber.equals(account.getAccountNumber())) {
                        accountExists = true;
                        break;
                    }
                }
                if (!accountExists) {
                    if (choice == 1) {
                        accounts.add(new SavingsAccount(accountNumber, accountHolderName));
                        System.out.println("Savings Account created successfully.");
                    } else if (choice == 2) {
                        accounts.add(new CurrentAccount(accountNumber, accountHolderName));
                        System.out.println("Current Account created successfully.");
                    }
                } else {
                    System.out.println("Account with the same number already exists.");
                }
            } else if (choice == 3) {
                String accountNumber;
                double amount;
                System.out.print("Enter account number: ");
                accountNumber = scanner.next();
                boolean accountFound = false;
                for (BankAccount account : accounts) {
                    if (accountNumber.equals(account.getAccountNumber())) {
                        System.out.print("Enter deposit amount: ");
                        amount = scanner.nextDouble();
                        account.deposit(amount);
                        accountFound = true;
                        break;
                    }
                }
                if (!accountFound) {
                    System.out.println("Account does not exist. Create the account first.");
                }
            } else if (choice == 4) {
                String accountNumber;
                double amount;
                System.out.print("Enter account number: ");
                accountNumber = scanner.next();
                boolean accountFound = false;
                for (BankAccount account : accounts) {
                    if (accountNumber.equals(account.getAccountNumber())) {
                        System.out.print("Enter withdrawal amount: ");
                        amount = scanner.nextDouble();
                        account.withdraw(amount);
                        accountFound = true;
                        break;
                    }
                }
                if (!accountFound) {
                    System.out.println("Account does not exist. Create the account first.");
                }
            } else if (choice == 5) {
                String accountNumber;
                System.out.print("Enter account number: ");
                accountNumber = scanner.next();
                boolean accountFound = false;
                for (BankAccount account : accounts) {
                    if (accountNumber.equals(account.getAccountNumber())) {
                        account.displayBalance();
                        accountFound = true;
                        break;
                    }
                }
                if (!accountFound) {
                    System.out.println("Account does not exist. Create the account first.");
                }
            } else if (choice == 6) {
                String accountNumber;
                System.out.print("Enter account number: ");
                accountNumber = scanner.next();
                boolean accountFound = false;
                for (BankAccount account : accounts) {
                    if (accountNumber.equals(account.getAccountNumber())) {
                        if (account instanceof SavingsAccount) {
                            ((SavingsAccount) account).applyInterest();
                        } else if (account instanceof CurrentAccount) {
                            ((CurrentAccount) account).applyInterest();
                        }
                        accountFound = true;
                        break;
                    }
                }
                if (!accountFound) {
                    System.out.println("Account does not exist. Create the account first.");
                }
            } else if (choice == 7) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();

        for (BankAccount account : accounts) {
            account = null;
        }
    }
}