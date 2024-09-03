package org.example;

/**
 * Used as a test class to compare it's objects in the {@link MyArrayList}. Have a height with
 * which you can compare <b>Person</b> objects.
 */
public class Person {
    private double height = 186;

    public double getHeight() {
        return height;
    }

    public Person(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" + "height=" + height + '}';
    }
}
