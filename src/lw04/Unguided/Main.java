package lw04.Unguided;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] t = new int[m];
        for (int i = 0; i < m; i++) {
            t[i] = sc.nextInt();
        }

        int[] copy = new int[n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] < a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
            if (i == 1) {
                for (int k = 0; k < n; k++) copy[k] = a[k];
            }
        }

        System.out.println("==== After 2 Passes ====");
        for (int i = 0; i < n; i++) {
            System.out.print(copy[i] + (i < n - 1 ? " " : "\n"));
        }
        System.out.println("==== Fully Sorted ====");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + (i < n - 1 ? " " : "\n"));
        }
        System.out.println("==== Search Results ====");

        for (int x = 0; x < m; x++) {
            int low = 0, high = n - 1, steps = 0, found = -1;
            while (low <= high && found == -1) {
                int mid = (low + high) / 2;
                steps++;
                if (a[mid] == t[x]) found = mid;
                else if (a[mid] > t[x]) low = mid + 1;
                else high = mid - 1;
            }
            if (found != -1)
                System.out.println(t[x] + ": FOUND at index " + found + " (steps: " + steps + ")");
            else
                System.out.println(t[x] + ": NOT FOUND (steps: " + steps + ")");
        }
    }
}
