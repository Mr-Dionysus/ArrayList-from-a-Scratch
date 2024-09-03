package org.example;

/**
 * Used as a test class to compare it's objects in the {@link MyArrayList}. Have a height with
 * which you can compare <b>PersonTest</b> objects.
 */
public class PersonTest {
    private double height = 186;

    public double getHeight() {
        return height;
    }

    public PersonTest(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "PersonTest{" + "height=" + height + '}';
    }
}
