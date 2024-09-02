package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MyArrayListTest {
    MyArrayList<String> strings = new MyArrayList<>(Arrays.asList("Bounty", "Test", "Desert", "Happy", "Fest"));
    MyArrayList<Integer> integers = new MyArrayList<>(Arrays.asList(4, 1, 5, 8, 6, 2, 9, 1));
    MyArrayList<Character> chars = new MyArrayList<>(Arrays.asList('P', 'A', 'G', 'B'));
    MyArrayList<Boolean> booleans = new MyArrayList<>(Arrays.asList(true, false, false, true, false));
    MyArrayList<Double> doubles = new MyArrayList<>(Arrays.asList(1.4, 3.6, 2.7, 10.5, 2.3));
    MyArrayList<Float> floats = new MyArrayList<>(Arrays.asList(1.45f, 7.12f, 3.13f));
    MyArrayList<Byte> bytes = new MyArrayList<>(Arrays.asList(new Byte[]{100, -34, 88}));
    MyArrayList<Short> shorts = new MyArrayList<>(Arrays.asList(new Short[]{1084, 356, 487}));
    MyArrayList<Long> longs = new MyArrayList<>(Arrays.asList(10572L, 3685L, 4879L));

    Person person1 = new Person(160);
    Person person2 = new Person(190);
    Person person3 = new Person(178);
    MyArrayList<Person> persons = new MyArrayList<>(Arrays.asList(person1, person2, person3));
    Comparator<Person> comparatorPerson = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return Double.compare(o1.getHeight(), o2.getHeight());
        }
    };

    @Test
    void length() {
        int actual = strings.length();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        integers.add(88);
        MyArrayList<Integer> actualArray = integers;
        MyArrayList<Integer> expectedArray = new MyArrayList<>(Arrays.asList(4, 1, 5, 8, 6, 2, 9, 1, 88));
        boolean areArraysEqual = true;

        for (int i = 0; i < actualArray.length(); i++) {
            int actualElement = actualArray.get(i);
            int expectedElement = expectedArray.get(i);

            if (actualElement != expectedElement) {
                areArraysEqual = false;
                break;
            }
        }

        assertTrue(areArraysEqual);
    }

    @Test
    void add1000() {
        for (int i = 0; i < 1000; i++) {
            integers.add(10);
        }
        int actual = integers.length();
        int expected = 1008;

        assertEquals(expected, actual);
    }

    @Test
    void addWithIndex() {
        integers.add(3, 88);
        MyArrayList<Integer> actualArray = integers;
        MyArrayList<Integer> expectedArray = new MyArrayList<>(Arrays.asList(4, 1, 5, 88, 6, 2, 9, 1));
        boolean areArraysEqual = true;

        for (int i = 0; i < actualArray.length(); i++) {
            int actualElement = actualArray.get(i);
            int expectedElement = expectedArray.get(i);

            if (actualElement != expectedElement) {
                areArraysEqual = false;
                break;
            }
        }

        assertTrue(areArraysEqual);
    }

    @Test
    void addWithOffset() {
        integers.addWithOffset(3, 88);
        MyArrayList<Integer> actualArray = integers;
        MyArrayList<Integer> expectedArray = new MyArrayList<>(Arrays.asList(4, 1, 5, 88, 8, 6, 2, 9, 1));
        boolean areArraysEqual = true;

        for (int i = 0; i < actualArray.length(); i++) {
            int actualElement = actualArray.get(i);
            int expectedElement = expectedArray.get(i);

            if (actualElement != expectedElement) {
                areArraysEqual = false;
                break;
            }
        }

        assertTrue(areArraysEqual);
    }

    @Test
    void delete() {
        integers.delete(5);
        MyArrayList<Integer> actualArray = integers;
        MyArrayList<Integer> expectedArray = new MyArrayList<>(Arrays.asList(4, 1, 5, 8, 6, 9, 1));
        boolean areArraysEqual = true;

        for (int i = 0; i < actualArray.length(); i++) {
            int actualElement = actualArray.get(i);
            int expectedElement = expectedArray.get(i);

            if (actualElement != expectedElement) {
                areArraysEqual = false;
                break;
            }
        }

        assertTrue(areArraysEqual);
    }

    @Test
    void delete500() {
        for (int i = 0; i < 1000; i++) {
            integers.add(10);
        }

        for (int i = 0; i < 500; i++) {
            integers.delete(integers.length() - 1);
        }

        int actual = integers.length();
        int expected = 508;

        assertEquals(expected, actual);
    }

    @Test
    void get() {
        char actual = chars.get(2);
        char expected = 'G';
        assertEquals(expected, actual);
    }

    @Test
    void clear() {
        booleans.clear();
        int actual = booleans.length();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        String actual = longs.toString();
        String expected = "[10572, 3685, 4879]";
        assertEquals(expected, actual);
    }

    @Test
    void quickSort() {
        doubles.quickSort();
        MyArrayList<Double> actualArray = doubles;
        MyArrayList<Double> expectedArray = new MyArrayList<>(Arrays.asList(1.4, 2.3, 2.7, 3.6, 10.5));
        boolean areArraysEqual = true;

        for (int i = 0; i < actualArray.length(); i++) {
            double actualElement = actualArray.get(i);
            double expectedElement = expectedArray.get(i);

            if (actualElement != expectedElement) {
                areArraysEqual = false;
                break;
            }
        }

        assertTrue(areArraysEqual);
    }

    @Test
    void QuickSortWithComparator() {
        persons.quickSort(comparatorPerson);
        MyArrayList<Person> actualArray = persons;
        MyArrayList<Person> expectedArray = new MyArrayList<>(Arrays.asList(person1, person3, person2));
        boolean areArraysEqual = true;

        for (int i = 0; i < persons.length(); i++) {
            Person actualElement = actualArray.get(i);
            Person expectedElement = expectedArray.get(i);

            if (comparatorPerson.compare(actualElement, expectedElement) != 0) {
                areArraysEqual = false;
                break;
            }
        }

        assertTrue(areArraysEqual);
    }
}