import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class PersonalFinanceManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Declare variables
        String name;
        double balance = 0;
        boolean running = true;

        // Data storage
        ArrayList<String> transactionHistory = new ArrayList<>();
        HashMap<String, ArrayList<Double>> categorizedTransactions = new HashMap<>();

        // Welcome to user input
        System.out.println("Hello, welcome to Personal Finance Management System");
        System.out.print("Enter your name: ");
        name = sc.nextLine();
        System.out.print("Enter your initial income: ");
        balance = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.println("Welcome " + name + "! Your current balance is: " + balance);

        // Menu Logic
        while (running) {
            System.out.println("\nChoose an option: ");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Balance");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your income amount: ");
                    double income = sc.nextDouble();
                    balance += income;
                    transactionHistory.add("Income: " + income);
                    System.out.println("Income added successfully. Current balance: " + balance);
                    break;
                case 2:
                    System.out.print("Enter expense amount: ");
                    double expense = sc.nextDouble();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter expense category: ");
                    String category = sc.nextLine();
                    balance -= expense;
                    transactionHistory.add("Expense (" + category + "): -" + expense);
                    categorizedTransactions.putIfAbsent(category, new ArrayList<>());
                    categorizedTransactions.get(category).add(expense);
                    System.out.println("Expense added. Current balance: " + balance);
                    break;
                case 3:
                    double totalIncome = 0, totalExpenses = 0;
                    for (String transaction : transactionHistory) {
                        if (transaction.startsWith("Income")) {
                            totalIncome += Double.parseDouble(transaction.split(": ")[1]);
                        } else if (transaction.startsWith("Expense")) {
                            totalExpenses += Double.parseDouble(transaction.split("-")[1]);
                        }
                    }
                    System.out.println("Total Income: " + totalIncome);
                    System.out.println("Total Expenses: " + totalExpenses);
                    System.out.println("Current Balance: " + balance);
                    System.out.println(balance > 0 ? "You’re saving well!" : "You’re in debt. Try to cut down on expenses.");
                    break;
                case 4:
                    System.out.println("Transaction History:");
                    for (String transaction : transactionHistory) {
                        System.out.println(transaction);
                    }

                    System.out.println("\nCategorized Transactions:");
                    for (String key : categorizedTransactions.keySet()) {
                        System.out.println(key + ": " + categorizedTransactions.get(key));
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using the Finance system!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
