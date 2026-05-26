import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATMOperations {

    private UserAccount user;
    private UserAccount[] users;
    private Transaction transaction = new Transaction();

    Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    public ATMOperations(UserAccount user, UserAccount[] users) {
        this.user = user;
        this.users = users;
    }

    public void showHistory() {
        transaction.showTransactionHistory();
    }

    public void withdraw() {

        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        if (amount <= user.getBalance()) {

            user.withdraw(amount);
            updateDatabase();

            transaction.addTransaction("Withdrawn: " + amount + " on " + LocalDateTime.now().format(formatter));

            System.out.println("Withdrawal Successful!");
            System.out.println("Remaining Balance: " + user.getBalance());

        } else {

            System.out.println("Insufficient Balance!");
        }
    }

    public void deposit() {

        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();

        user.deposit(amount);
        updateDatabase();

        transaction.addTransaction("Deposited: " + amount + " on " + LocalDateTime.now().format(formatter));

        System.out.println("Deposit Successful!");
        System.out.println("Updated Balance: " + user.getBalance());
    }

    public void transfer() {

        sc.nextLine();

        System.out.print("Enter Receiver User ID: ");
        String receiverId = sc.nextLine();

        // Check same user
        if (receiverId.equals(user.getUserId())) {

            System.out.println("You cannot transfer money to your own account!");
            return;
        }

        UserAccount receiverAccount = null;

        // Check valid user
        for (UserAccount acc : users) {

            if (acc.getUserId().equals(receiverId)) {
                receiverAccount = acc;
                break;
            }
        }

        // Invalid user
        if (receiverAccount == null) {

            System.out.println("Receiver User ID not found!");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        if (amount <= user.getBalance()) {

            user.withdraw(amount);
            updateDatabase();
            receiverAccount.deposit(amount);
            updateDatabase();

            transaction.addTransaction("Transferred " + amount + " to " + receiverId + " on " + LocalDateTime.now().format(formatter));

            System.out.println("Transfer Successful!");
            System.out.println("Remaining Balance: " + user.getBalance());

        } else {

            System.out.println("Insufficient Balance!");
        }
    }
    private void updateDatabase() {

    try {

        BufferedWriter bw =
                new BufferedWriter(new FileWriter("users.txt"));

        for (UserAccount acc : users) {

            bw.write(acc.getUserId() + ","
                    + acc.getPin() + ","
                    + acc.getBalance());

            bw.newLine();
        }

        bw.close();

    } catch (Exception e) {

        System.out.println("Error updating database.");
    }
}
}