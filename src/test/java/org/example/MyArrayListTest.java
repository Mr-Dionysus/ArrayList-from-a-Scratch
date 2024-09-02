package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MyArrayListTest {

    MyArrayList<String> strings = new MyArrayList<>(Arrays.asList("Bounty", "Test", "Desert", "Happy", "Fest"));
    MyArrayList<Integer> integers = new MyArrayList<>(Arrays.asList(4, 1, 5, 8, 6, 2, 9, 1));
    MyArrayList<Character> chars = new MyArrayList<>(Arrays.asList('P', 'A', 'Z', 'B'));
    MyArrayList<Boolean> booleans = new MyArrayList<>(Arrays.asList(true, false, false, true, false));
    MyArrayList<Double> doubles = new MyArrayList<>(Arrays.asList(1.4, 3.6, 2.7));
    MyArrayList<Float> floats = new MyArrayList<>(Arrays.asList(1.45f, 7.12f, 3.13f));
    MyArrayList<Byte> bytes = new MyArrayList<>(Arrays.asList(new Byte[]{100, -34, 88}));

    @Test
    void length() {
        bytes.quickSort();
        System.out.println(bytes);
    }

    @Test
    void add() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void addWithOffset() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void clear() {
    }

    @Test
    void testToString() {
    }

    @Test
    void quickSort() {
    }

    @Test
    void testQuickSort() {
    }

    @Test
    void compare() {
    }
}