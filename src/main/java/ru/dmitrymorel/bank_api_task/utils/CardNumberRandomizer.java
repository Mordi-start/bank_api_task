package ru.dmitrymorel.bank_api_task.utils;

import java.util.Random;

public class CardNumberRandomizer {

    private final static int length = 16;
    private final static Random random = new Random();

    public static String randomNumber() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= length; i++) {
            String randomNumber = Integer.toString(random.nextInt(10));
            stringBuilder.append(randomNumber);
            if (i == length) {
                break;
            }
            if (i % 4 == 0) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(randomNumber());
    }
}
