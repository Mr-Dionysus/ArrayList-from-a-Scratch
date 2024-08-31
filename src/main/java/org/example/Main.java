package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> strings = new MyArrayList<String>(Arrays.asList("Hello", "Test", "Desert", "Happy"));
        MyArrayList<String> strings2 = new MyArrayList<String>();

        //        strings.addWithOffset(4, "Offset");
        //        System.out.println(strings);
        System.out.println(strings2.length());
        strings2.addWithOffset(0, "hello");
        System.out.println(strings2);

        ArrayList<String> arrayList = new ArrayList<>();
    }
}