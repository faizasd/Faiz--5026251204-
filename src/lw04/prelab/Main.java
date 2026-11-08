package lw04.prelab;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner fileScanner = new Scanner(Main.class.getResourceAsStream("branches.txt"));
        
        int n = fileScanner.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = fileScanner.nextInt();
        }
        
        int m = fileScanner.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = fileScanner.nextInt();
        }
        
        fileScanner.close();
        
        int passes = 3;
        for (int i = 1; i <= passes && i < n; i++) {
            int key = branches[i];
            int j = i - 1;
            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }
            branches[j + 1] = key;
        }
        
        for (int i = 0; i < n; i++) {
            System.out.print(branches[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();
        
        for (int i = 0; i < m; i++) {
            int target = targets[i];
            int foundIndex = -1;
            
            for (int k = 0; k < n; k++) {
                if (branches[k] == target) {
                    foundIndex = k;
                    break;
                }
            }
            
            if (foundIndex != -1) {
                System.out.println(target + ": FOUND at index " + foundIndex);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }
}