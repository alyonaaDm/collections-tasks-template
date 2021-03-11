package tasks.first;

import java.util.ArrayDeque;
import java.util.Scanner;

public class FirstTaskSolution implements FirstTask {
    public FirstTaskSolution() {
    }

    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int indexStart) {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        answer += indexStart + ", ";
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        int size = adjacencyMatrix[0].length;
        int[] arrOfUsedV = new int[size];
        int[] parentV = new int[size];
        queue.addFirst(indexStart);
        arrOfUsedV[indexStart - 1] = 1;
        parentV[indexStart - 1] = -1;
        while (!queue.isEmpty()) {
            int v = queue.getFirst();
            queue.removeFirst();
            for (int j = 0; j < size; j++) {
                if (adjacencyMatrix[v - 1][j] && arrOfUsedV[j] == 0) {
                    queue.addLast(j + 1);
                    arrOfUsedV[j] = 1;
                    parentV[j] = v;
                    answer += j + 1 + ", ";
                }
            }
        }
        for (int i=0; i< answer.length() - 2;i++){
            System.out.print(answer.charAt(i));
        }
        System.out.println();
        answer ="";
        System.out.println("Please enter the number of final peak");
        int endV = scanner.nextInt();
        if (arrOfUsedV[endV - 1] == 0) {
            return "No way";
        }
        queue.addFirst(endV);
        int k = endV - 1;
        while (parentV[k] != -1) {
            queue.addFirst(parentV[k]);
            k = parentV[k] - 1;
        }
        while (!queue.isEmpty()) {
            if (queue.size() == 1) {
                answer += (queue.getFirst());
            } else {
                answer += (queue.getFirst() + ", ");
            }
            queue.removeFirst();
        }
        return answer;
    }

    @Override
    public boolean checkBrackets(String s){
        char[] arrString = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arrString[i] = s.charAt(i);
        }
        ArrayDeque<Character> entrance = new ArrayDeque<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (arrString[i] == '(' || arrString[i] == '[' || arrString[i] == '{') {
                entrance.addLast(arrString[i]);
            }
            if (arrString[i] == ')' || arrString[i] == ']' || arrString[i] == '}') {
                if(entrance.isEmpty()){
                    return false;
                }
                if(isNotValid(arrString[i],entrance.getLast())){
                    return false;
                }
                entrance.removeLast();
            }
        }
        return true;
    }
    public boolean isNotValid(char open, char last){
        if(open == ')' && last!= '(') {
            return true;
        }
        if(open == ']' && last!= '[') {
            return true;
        }
        if(open == '}' && last != '{') {
            return true;
        }
        return false;
    }

    @Override
    public Long reverseCalculator(String s) throws IllegalArgumentException {
        ArrayDeque<Long> arr = new ArrayDeque<>();
        int rank = 0;
        long c = 0;
        boolean check = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0') {
                rank++;
                if (rank > 1) {
                    c *= 10;
                }
                check = true;
                c += (s.charAt(i) - 48);
            } else if (check) {
                arr.addLast(c);
                c = 0;
                rank = 0;
                check = false;
            }
            operations(arr, s.charAt(i));
        }
        return arr.getLast();
    }
    public void operations(ArrayDeque<Long> arr, char c) {
        if (c < '0' && c != ' ') {
            long number2 = arr.getLast();
            arr.removeLast();
            long number1 = arr.getLast();
            arr.removeLast();
            if (c == '+') {
                arr.addLast(number1 + number2);
            }
            if (c == '-') {
                arr.addLast(number1 - number2);
            }
            if (c == '*') {
                arr.addLast(number1 * number2);
            }
            if (c == '/') {
                arr.addLast(number1 / number2);
            }
        }
    }}
