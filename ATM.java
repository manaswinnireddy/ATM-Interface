import java.util.Scanner;

public class ATM {

    private UserAccount user;
    private ATMOperations operations;

    public ATM(UserAccount user, ATMOperations operations) {
        this.user = user;
        this.operations = operations;
    }

    public void showMenu() {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    operations.showHistory();
                    break;

                case 2:
                    operations.withdraw();
                    break;

                case 3:
                    operations.deposit();
                    break;

                case 4:
                    operations.transfer();
                    break;

                case 5:
                    System.out.println("Current Balance: " + user.getBalance());
                    break;

                case 6:
                    System.out.println("Thank you for using ATM!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);
    }
}