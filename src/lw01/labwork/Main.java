package lw01.labwork;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("washes.txt"));
        
        int n = scanner.nextInt();
        WashService[] services = new WashService[n];

        for (int i =0; i<n;i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("CAR")) {
                services[i]=new CarWash(id, days);
            } else {
                services[i] = new MotorcycleWash(id, days);
            }
        }

        scanner.close();

        for (WashService service : services) {
            System.out.println(service.summary());
        }
    }
}