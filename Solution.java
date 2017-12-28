package com.lukasz;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<String> danceMoves;
    private char[] programs;
    private char[] originalPrograms;
    String orderAfterFirstDance;
    String orderAfterLastDance;

    private int loop;

    public Solution(List<String> danceMoves) {
        this.danceMoves = danceMoves;
        this.programs = fillTable();

        dance();
        resetPrograms();

        findLoop();
        resetPrograms();

        lastDance();
    }

    private void lastDance() {
        int times = 1000000000 % loop;

        for(int i = 0; i < times; i++) {
            dance();
        }

        orderAfterLastDance = getOrder();
    }

    private void findLoop() {
        for(int i = 0; i < 1000000000; i++) {
            dance();
            if(i == 0)
                orderAfterFirstDance = getOrder();
            if(Arrays.equals(programs, originalPrograms)) {
                loop = i + 1;
                break;
            }
        }
    }

    private void resetPrograms() {
        programs = Arrays.copyOf(originalPrograms, 16);
    }

    private void dance() {
        for(int i = 0; i < danceMoves.size(); i++) {
            String currentMove = danceMoves.get(i);
            char move = currentMove.charAt(0);

            makeMove(move, currentMove);
        }
    }

    private void makeMove(char move, String currentMove) {
        switch (move) {
            case 's':
                int value = Integer.parseInt(currentMove.substring(1));
                spin(value);
                return;
            case 'x':
                String[] values = currentMove.substring(1).split("/");
                int value1 = Integer.parseInt(values[0]);
                int value2 = Integer.parseInt(values[1]);
                exchange(value1, value2);
                return;
            case 'p':
                char ch1 = currentMove.charAt(1);
                char ch2 = currentMove.charAt(3);
                partner(ch1, ch2);
                return;
        }
    }

    private void exchange(int value1, int value2) {
        char temp = programs[value1];
        programs[value1] = programs[value2];
        programs[value2] = temp;
    }

    private void partner(char ch1, char ch2) {
        int value1 = 0;
        int value2 = 0;

        for(int i = 0; i < 16; i++) {
            if (programs[i] == ch1) {
                value1 = i;
            }
            if (programs[i] == ch2) {
                value2 = i;
            }
        }

        exchange(value1, value2);
    }

    private void spin(int value) {
        char[] newTable = new char[16];

        for(int i = 0; i < 16 - value; i++) {
            newTable[i + value] = programs[i];
        }
        for(int i = 0; i < value; i++) {
            newTable[i] = programs[16 - value + i];
        }

        programs = newTable;
    }

    private char[] fillTable() {
        char[] table = new char[16];
        originalPrograms = new char[16];

        for(int i = 0; i < 16; i++) {
            table[i] = (char) ('a' + i);
            originalPrograms[i] = table[i];
        }
        return table;
    }

    public String getOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 16; i++) {
            stringBuilder.append(programs[i]);
        }

        return stringBuilder.toString();
    }

    public String getOrderAfterFirstDance() {
        return orderAfterFirstDance;
    }

    public String getOrderAfterLastDance() {
        return orderAfterLastDance;
    }
}
