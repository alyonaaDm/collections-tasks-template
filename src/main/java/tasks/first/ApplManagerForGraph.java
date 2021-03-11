package tasks.first;

import java.util.Scanner;

public class ApplManagerForGraph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FirstTaskSolution firstTaskSolution = new FirstTaskSolution();
        System.out.println("Please enter the number of peaks");
        int n = scanner.nextByte();
        scanner.nextLine();
        boolean[][] adjacencyMatrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            int l = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '0') {
                    adjacencyMatrix[i][l] = false;
                    l++;
                }
                if (s.charAt(j) == '1') {
                    adjacencyMatrix[i][l] = true;
                    l++;
                }
            }
        }
        System.out.println("Please enter the number of starting peak");
        int startV = scanner.nextInt();
        System.out.println(firstTaskSolution.breadthFirst(adjacencyMatrix, startV));
    }
}
