package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> strings = new MyArrayList<>(Arrays.asList("Hello", "Test", "Desert", "Happy"));
        MyArrayList<Integer> nums = new MyArrayList<>(Arrays.asList(4, 1, 3, 8));
        System.out.println(strings);
        System.out.println(nums);
        strings.sort();
        nums.sort();


        MyArrayList<String> strings2 = new MyArrayList<String>();
        //        System.out.println(strings2.length());
        //        strings2.addWithOffset(0, "hello");
        //        System.out.println(strings2);

        ArrayList<String> arrayList = new ArrayList<>();

    }
}