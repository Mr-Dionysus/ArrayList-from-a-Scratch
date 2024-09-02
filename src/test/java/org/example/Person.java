package org.example;

/**
 * Used as a test class in the <b>QuickSort</b> method {@link org.example.MyArrayList#quickSort(java.util.Comparator)}. Have height with which you
 * can compare <b>Person</b> objects.
 */
public class Person {
    private double height = 186;

    public double getHeight() {
        return height;
    }

    public Person(double height) {
        this.height = height;
    }
}
