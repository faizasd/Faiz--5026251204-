package lw02.Unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> allOrders = new LinkedList<>();
        
        
            Scanner sc = new Scanner(Main.class.getResourceAsStream("orders.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] orderDetails = line.split(" ");
                    allOrders.add(orderDetails);
                }
            }
            sc.close();

        LinkedList<String[]> foodStocks =new LinkedList<>();
        foodStocks.add(new String[]{"Bakso","2"});
        foodStocks.add(new String[]{"Sate", "1" });
        foodStocks.add(new String[]{"Soto","2"});

        LinkedList<String[]>drinkStocks =new LinkedList<>();
        drinkStocks.add(new String[]{"EsTeh", "4"});
        drinkStocks.add(new String[]{"EsJeruk", "2"});

        Queue<String[]>orderQueue = new LinkedList<>();
        for (String[] order : allOrders) {
            orderQueue.add(order);
        }

        LinkedList<String[]> successOrders = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();

        while (!orderQueue.isEmpty()) {
            String[] currentOrder =orderQueue.poll();
            String foodOrdered= currentOrder[1];
            String drinkOrdered= currentOrder[2];

            boolean canFulfill = true;

            if (!foodOrdered.equals("-")) {
                boolean foodAvailable = false;
                for (String[] f : foodStocks) {
                    if (f[0].equals(foodOrdered) && Integer.parseInt(f[1]) > 0) {
                        foodAvailable = true;
                        break;
                    }
                }
                if (!foodAvailable) canFulfill = false;
            }    
            if (canFulfill && !drinkOrdered.equals("-")) {
                boolean drinkAvailable = false;
                for (String[] d : drinkStocks) {
                    if (d[0].equals(drinkOrdered) && Integer.parseInt(d[1]) > 0) {
                        drinkAvailable = true;
                        break;
                    }
                }
                if (!drinkAvailable) canFulfill = false;
            }

            if (canFulfill) {
                if (!foodOrdered.equals("-")) {
                    for (String[] f : foodStocks) {
                        if (f[0].equals(foodOrdered)) {
                            f[1] = String.valueOf(Integer.parseInt(f[1]) - 1);
                            break;
                        }
                    }
                }
                if (!drinkOrdered.equals("-")) {
                    for (String[] d : drinkStocks) {
                        if (d[0].equals(drinkOrdered)) {
                            d[1] = String.valueOf(Integer.parseInt(d[1]) - 1);
                            break;
                        }
                    }
                }
                successOrders.add(currentOrder);
            } else {
                failedOrders.push(currentOrder);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println("\n=== Remaining Food Stock ===");
        for (String[] f : foodStocks) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println("\n=== Remaining Drink Stock ===");
        for (String[] d : drinkStocks) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println("\n=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }

}