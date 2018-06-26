package j.test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            for (int j = 0; j < 1000; j++) {
                int a = 300;
            }
        }

        System.out.println(System.currentTimeMillis() - time);
    }
}