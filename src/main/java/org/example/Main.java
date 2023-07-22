package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int FROM_FIRST_NUMBER_FIRST_THREAD = 1;
    private static final int TO_SECOND_NUMBER_FIRST_THREAD = 500;

    private static final int FROM_FIRST_NUMBER_SECOND_THREAD = 501;
    private static final int TO_SECOND_NUMBER_SECOND_THREAD = 1000;

    private static int result1;
    private static int result2;

    public static void main(String[] args) {

        //Task: Speed up summing all numbers from 1 to 1000

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = FROM_FIRST_NUMBER_FIRST_THREAD; i <= TO_SECOND_NUMBER_FIRST_THREAD; i++) {
                    result1 += i;
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = FROM_FIRST_NUMBER_SECOND_THREAD; i <= TO_SECOND_NUMBER_SECOND_THREAD; i++) {
                   result1 += i;
                }
            }
        };

        long start1 = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("1 Result: " + result1);
        }

        long finish1 = System.currentTimeMillis();

        System.out.println("First method time: " + (finish1 - start1));

        long start2 = System.currentTimeMillis();

        for (int i = FROM_FIRST_NUMBER_FIRST_THREAD; i <= TO_SECOND_NUMBER_SECOND_THREAD; i++) {
            result2 += i;
        }

        System.out.println("2 Result: " + result2);

        long finish2 = System.currentTimeMillis();

        System.out.println("Second method time: " + (finish2 - start2));

    }
}