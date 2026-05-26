import java.util.ArrayList;

public class Transaction {

    private ArrayList<String> history = new ArrayList<>();

    public void addTransaction(String record) {
        history.add(record);
    }

    public void showTransactionHistory() {

        if (history.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("\n===== Transaction History =====");

            for (String transaction : history) {
                System.out.println(transaction);
            }
        }
    }
}