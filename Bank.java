import java.util.Scanner;

class BankAccount {
    private int id;
    private int pin;
    private String name;
    private double balance;

    public BankAccount(int id, int pin, String name, double balance) {
        this.id = id;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public boolean validatePin(int pin) {
        return this.pin == pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

public class BankingApp {
    private BankAccount[] accounts;
    private Scanner scanner;

    public BankingApp(BankAccount[] accounts) {
        this.accounts = accounts;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("Welcome to Banking App");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            if (option == 1) {
                login();
            } else if (option == 2) {
                break;
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    private void login() {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        BankAccount account = findAccount(id);
        if (account != null && account.validatePin(pin)) {
            System.out.println("Login successful");
            loggedIn(account);
        } else {
            System.out.println("Invalid credentials");
        }
    }

    private BankAccount findAccount(int id) {
        for (BankAccount account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    private void loggedIn(BankAccount account) {
        while (true) {
            System.out.println("1. Check Balance");
            System.out.println("2. Cash-in");
            System.out.println("3. Money Transfer");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            if (option == 1) {
                checkBalance(account);
            } else if (option == 2) {
                cashIn(account);
            } else if (option == 3) {
                moneyTransfer(account);
            } else if (option == 4) {
                break;
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    private void checkBalance(BankAccount account) {
        System.out.println("Balance: " + account.getBalance());
    }

    private void cashIn(BankAccount account) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful");
    }

    private void moneyTransfer(BankAccount account) {
        System.out.print("Enter recipient's user ID: ");
        int recipientId = scanner.nextInt();
        BankAccount recipientAccount = findAccount(recipientId);
        if (recipientAccount != null) {
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                recipientAccount.deposit(amount);
                System.out.println("Transfer successful");
            } else {
                System.out.println("Insufficient balance");
            }
        } else {
            System.out.println("Recipient's account not found");
        }
    }

    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[] {
            new BankAccount(412435, 7452, "Chris Sandoval", 32000),
            new BankAccount(264863, 1349, "Marc Yim", 1000)
        };
        BankingApp app = new BankingApp(accounts);
        app.run();
    }
}
