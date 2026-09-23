package lw01.prelab;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();

             Scanner scanner = new Scanner(Main.class.getResourceAsStream("jobs.txt")); {
            
            
            

            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equals("MONO")) {
                    jobs = new MonoPrint(id, pages));
                } else {
                    jobs = new ColourPrint(id, pages);
                }
            }

            jobs.add(job);
        } 

        scanner.close();
        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}