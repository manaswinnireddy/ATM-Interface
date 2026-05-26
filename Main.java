import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<UserAccount> usersList = new ArrayList<>();

        // Reading users from users.txt
        try {

            BufferedReader br =
                    new BufferedReader(new FileReader("users.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String userId = data[0];
                String pin = data[1];
                double balance = Double.parseDouble(data[2]);

                usersList.add(
                        new UserAccount(userId, pin, balance));
            }

            br.close();

        } catch (Exception e) {

            System.out.println("Error reading users file.");
            return;
        }

        UserAccount[] users =
                usersList.toArray(new UserAccount[0]);

        System.out.println("===== ATM INTERFACE =====");

        System.out.print("Enter User ID: ");
        String enteredUserId = sc.nextLine();

        System.out.print("Enter PIN: ");
        String enteredPin = sc.nextLine();

        UserAccount loggedInUser = null;

        // Checking Login
        for (UserAccount user : users) {

            if (user.getUserId().equals(enteredUserId)
                    && user.getPin().equals(enteredPin)) {

                loggedInUser = user;
                break;
            }
        }

        // Login Success
        if (loggedInUser != null) {

            System.out.println("\nLogin Successful!");

            ATMOperations operations =
                    new ATMOperations(loggedInUser, users);

            ATM atm =
                    new ATM(loggedInUser, operations);

            atm.showMenu();

        } else {

            System.out.println("\nInvalid User ID or PIN!");
        }

        sc.close();
    }
}