package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> strings = new MyArrayList<>(Arrays.asList("Aello man", "Test", "Desert", "Happy", "Fest"));
        MyArrayList<Integer> nums = new MyArrayList<>(Arrays.asList(4, 1, 5, 8, 6, 2, 9, 1));
        MyArrayList<Character> chars = new MyArrayList<>(Arrays.asList('P', 'A', 'Z', 'B'));
        MyArrayList<Boolean> bools = new MyArrayList<>(Arrays.asList(true, false, false, true, false));

        nums.sort();
        MyArrayList myArrayList = new MyArrayList();
        System.out.println(nums);


        MyArrayList<String> strings2 = new MyArrayList<String>();
        //        System.out.println(strings2.length());
        //        strings2.addWithOffset(0, "hello");
        //        System.out.println(strings2);

        ArrayList<String> arrayList = new ArrayList<>();

    }
}