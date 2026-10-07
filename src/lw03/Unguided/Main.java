package lw03.Unguided;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        
        Set<String> regStudents = new LinkedHashSet<>();
        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (sc1.hasNextLine()) {
            String id = sc1.nextLine().trim();
            if (!id.isEmpty()) {
                regStudents.add(id);
            }
        }
        sc1.close();

        List<String> checkInRes = new LinkedList<>();
        Set<String> checkedInStudents = new LinkedHashSet<>();
        int rejectedAttempts = 0;

        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (sc2.hasNextLine()) {
            String id = sc2.nextLine().trim();
            if (id.isEmpty()) continue;

            if (regStudents.contains(id)) {
                if (checkedInStudents.contains(id)) {
                    checkInRes.add(id + ": Rejected (already checked in)");
                    rejectedAttempts++;
                } else {
                    checkedInStudents.add(id);
                    checkInRes.add(id + ": Checked in");
                }
            } else {
                checkInRes.add(id + ": Rejected (not registered)");
                rejectedAttempts++;
            }
        }
        sc2.close();

        System.out.println("===== Event Check-In Results =====");
        for (String result : checkInRes) {
            System.out.println(result);
        }

        System.out.println("\n===== Final Event Summary =====");
        System.out.println("Registered students: " + regStudents.size());
        System.out.println("Successful check-ins: " + checkedInStudents.size());
        System.out.println("Absent students: " + (regStudents.size() - checkedInStudents.size()));
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}