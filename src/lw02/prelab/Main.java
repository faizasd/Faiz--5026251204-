package lw02.prelab;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        InputStream is = Main.class.getResourceAsStream("/transactions.txt");
        if (is == null) {
            is = Main.class.getResourceAsStream("transactions.txt");
        }

        if (is == null) {
            System.out.println("File transactions.txt tidak ditemukan di classpath.");
            return;
        }

        Scanner scanner = new Scanner(is);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            transactions.add(parts);

            String name = parts[0];
            boolean exists = false;
            for (String[] cust : customers) {
                if (cust[0].equals(name)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                customers.add(new String[]{name, "0"});
            }
        }
        scanner.close();

        Queue<String[]> transactionQueue = new LinkedList<>();
        for (String[] trans : transactions) {
            transactionQueue.add(trans);
        }

        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] trans = transactionQueue.poll();
            String name = trans[0];
            String type = trans[1];
            int amount = Integer.parseInt(trans[2]);

            String[] targetCustomer = null;
            for (String[] cust : customers) {
                if (cust[0].equals(name)) {
                    targetCustomer = cust;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentBalance = Integer.parseInt(targetCustomer[1]);

                if (type.equals("DEPOSIT")) {
                    currentBalance += amount;
                    targetCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equals("WITHDRAW")) {
                    if (amount > currentBalance) {
                        failedTransactions.push(trans);
                    } else {
                        currentBalance -= amount;
                        targetCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customers) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}