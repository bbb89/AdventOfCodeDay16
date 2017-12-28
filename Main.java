package com.lukasz;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<String> getInput() {
        System.out.println("Enter dance moves:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] danceMoves = input.split(",");

        return Arrays.asList(danceMoves);
    }

    public static void main(String[] args) {
        List<String> danceMoves = getInput();
        Solution solution = new Solution(danceMoves);

        System.out.println("Order of programs after first dance is " + solution.getOrderAfterFirstDance());
        System.out.println("Order of programs after last dance is " + solution.getOrderAfterLastDance());
    }
}
