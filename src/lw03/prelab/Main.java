package lw03.prelab;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("===== Problem 1 =====");
        List<String> playlist = new LinkedList<>();
        
        Scanner scanner1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        while (scanner1.hasNext()) {
            String command = scanner1.next();
            
            if (command.equals("INSERT")) {
                int index = scanner1.nextInt();
                String song = scanner1.nextLine().trim();
                playlist.add(index, song);
            } else if (command.equals("ADD")) {
                String song = scanner1.nextLine().trim();
                playlist.add(song);
            } else if (command.equals("REMOVE")) {
                String song = scanner1.nextLine().trim();
                playlist.remove(song);
            }
        }
        scanner1.close();
        
        System.out.println("Total songs: " + playlist.size());
        int songNumber = 1;
        for (String song : playlist) {
            System.out.println(songNumber + ": " + song);
            songNumber++;
        }

        System.out.println("\n===== Problem 2 =====");
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;
        
        Scanner scanner2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        while (scanner2.hasNextLine()) {
            String name = scanner2.nextLine().trim();
            if (name.isEmpty()) continue;
            
            if (participants.contains(name)) {
                duplicateCount++;
            } else {
                participants.add(name);
            }
        }
        scanner2.close();
        
        System.out.println("Unique participants: " + participants.size());
        int participantNumber = 1;
        for (String participant : participants) {
            System.out.println(participantNumber + ". " + participant);
            participantNumber++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);

        System.out.println("\n===== Problem 3 =====");
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;
        
        Scanner scanner3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        while (scanner3.hasNext()) {
            String type = scanner3.next();
            String product = scanner3.next();
            int quantity = scanner3.nextInt();

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    int current = inventory.get(product);
                    inventory.put(product, current + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product)) {
                    int current = inventory.get(product);
                    if (current >= quantity) {
                        inventory.put(product, current - quantity);
                    } else {
                        failedSales++;
                    }
                } else {
                    failedSales++;
                }
            }
        }
        scanner3.close();
        
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
        System.out.println("Failed sales: " + failedSales);
    }
}