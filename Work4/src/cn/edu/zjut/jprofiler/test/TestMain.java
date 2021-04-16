package cn.edu.zjut.jprofiler.test;

import java.util.ArrayList;

public class TestMain {
    public static ArrayList<Object> list = new ArrayList<>();
    public static int counter = 0;

    public static void testHotOne() {
        int previous = 1;
        int current = 1;
        System.out.print(String.format("%s,%s",previous, current));
        for(int i = 0; i < 2000; i++) {
            int temp = current;
            current = previous + current;
            previous = temp;
            System.out.print(String.format("%s,", current));
        }
    }

    public static void testHotTwo() {
        int previous = 1;
        int current = 1;
        System.out.print(String.format("%s,%s",previous, current));
        for(int i = 0; i < 20000; i++) {
            int temp = current;
            current = previous + current;
            previous = temp;
            System.out.print(String.format("%s,", current));
        }
    }
}
