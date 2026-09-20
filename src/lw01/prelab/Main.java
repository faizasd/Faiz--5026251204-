package lw01.prelab;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();

        try (InputStream inputStream = Main.class.getResourceAsStream("jobs.txt");
             Scanner scanner = new Scanner(inputStream)) {
            
            if (inputStream == null) {
                System.out.println("Error: jobs.txt not found.");
                return;
            }

            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equals("MONO")) {
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equals("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: jobs.txt not found.");
            return;
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}