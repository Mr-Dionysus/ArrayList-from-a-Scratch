package org.example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 *
 */
public class MyArrayList<E> implements Serializable, Comparator<E> {
    transient E[] arr;
    private final transient Object[] DEFAULT_CAPACITY_EMPTY_ARR = {};
    final int DEFAULT_CAPACITY_INCREASE = 1;

    public MyArrayList() {
        this.arr = (E[]) DEFAULT_CAPACITY_EMPTY_ARR;
    }

    public MyArrayList(Collection<? extends E> arr) {
        Object[] newArr = arr.toArray();
        this.arr = (E[]) newArr;
    }

    public int length() {
        int arrLength = 0;

        for (int i = 0; i < this.arr.length; i++) {
            arrLength++;
        }

        return arrLength;
    }

    public void add(E element) {
        final int ARR_OLD_LENGTH = this.arr.length;
        final int ARR_NEW_LENGTH = DEFAULT_CAPACITY_INCREASE + ARR_OLD_LENGTH;

        E[] arrCopy = Arrays.copyOf(this.arr, ARR_NEW_LENGTH);
        arrCopy[ARR_NEW_LENGTH - 1] = element;
        this.arr = arrCopy;
    }

    public void add(int i, E element) {
        final int ARR_OLD_LENGTH = this.arr.length;
        final int ARR_NEW_LENGTH = DEFAULT_CAPACITY_INCREASE + ARR_OLD_LENGTH;

        if ((i == ARR_OLD_LENGTH - 1 || i < ARR_OLD_LENGTH - 1) && i > 0) {
            E[] arrCopy = Arrays.copyOf(this.arr, ARR_OLD_LENGTH);
            arrCopy[i] = element;
            this.arr = arrCopy;
        } else if (i == ARR_OLD_LENGTH) {
            E[] arrCopy = Arrays.copyOf(this.arr, ARR_NEW_LENGTH);
            arrCopy[i] = element;
            this.arr = arrCopy;
        } else if (i < 0) {
            System.out.println("You can't use negative numbers as an index.");
        } else if (i > ARR_OLD_LENGTH) {
            System.out.println("You can't add an element if the index is bigger than old length + 1.");
        }
    }

    public void addWithOffset(int i, E element) {
        final int ARR_OLD_LENGTH = this.arr.length;

        if ((i == ARR_OLD_LENGTH - 1 || i < ARR_OLD_LENGTH - 1) && i > 0) {
            E[] arrLeftPart = Arrays.copyOfRange(this.arr, 0, i);
            E[] arrRightPart = Arrays.copyOfRange(this.arr, i, ARR_OLD_LENGTH);
            E[] arrNewElement = (E[]) new Object[1];
            arrNewElement[0] = element;
            E[] result = Arrays.copyOf(arrLeftPart, arrLeftPart.length + arrRightPart.length + arrNewElement.length);
            System.arraycopy(arrNewElement, 0, result, i, arrNewElement.length);
            System.arraycopy(arrRightPart, 0, result, i + 1, arrRightPart.length);
        } else if (i == ARR_OLD_LENGTH) {
            this.add(element);
        } else if (i < 0) {
            System.out.println("You can't use negative numbers as an index.");
        } else if (i > ARR_OLD_LENGTH) {
            System.out.println("You can't add the element if the index is bigger than old length + 1.");
        } else {
            System.out.println("You did something wrong.");
        }
    }

    public void delete(int i) {
        final int ARR_OLD_LENGTH = this.arr.length;

        if ((i == ARR_OLD_LENGTH - 1 || i < ARR_OLD_LENGTH - 1) && i >= 0) {
            E[] arrLeftPart = Arrays.copyOfRange(this.arr, 0, i);
            E[] arrRightPart = Arrays.copyOfRange(this.arr, i + 1, ARR_OLD_LENGTH);
            E[] result = Arrays.copyOf(arrLeftPart, arrLeftPart.length + arrRightPart.length);

            System.arraycopy(arrRightPart, 0, result, i, arrRightPart.length);
            this.arr = result;
        } else if (i < 0) {
            System.out.println("You can't use negative numbers as an index.");
        } else if (i >= ARR_OLD_LENGTH) {
            System.out.println("You can't delete the element because that arr doesn't have that index.");
        } else {
            System.out.println("You did something wrong.");
        }
    }

    public E get(int i) {
        return arr[i];
    }

    public void clear() {
        this.arr = (E[]) DEFAULT_CAPACITY_EMPTY_ARR;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.arr);
    }

    public void sort() {
        int middleIndex = this.length() / 2;
        E pivot = this.get(middleIndex);
        this.delete(middleIndex);
        MyArrayList<E> arrLeft = new MyArrayList<>();
        MyArrayList<E> arrRight = new MyArrayList<>();

        for (int i = 0; i < this.length(); i++) {
            E element = this.get(i);
            if (compare(element, pivot) == -1) {
                arrLeft.add(element);
            }
        }

        for (int i = 0; i < this.length(); i++) {
            E element = this.get(i);
            if (compare(element, pivot) == 1) {
                arrRight.add(element);
            }
        }
    }

    @Override
    public int compare(E o1, E o2) {
        boolean isString = o1 instanceof String;
        boolean isInt = o1 instanceof Integer;
        boolean isDouble = o1 instanceof Double;
        boolean isFloat = o1 instanceof Float;
        boolean isByte = o1 instanceof Byte;
        boolean isChar = o1 instanceof Character;
        boolean isShort = o1 instanceof Short;
        boolean isLong = o1 instanceof Long;
        boolean isBoolean = o1 instanceof Boolean;

        if (isString) {
            return compareString(o1, o2);
        } else if (isInt) {
            return compareInt(o1, o2);
        } else if (isDouble) {
            return compareDouble(o1, o2);
        } else if (isFloat) {
            return compareFloat(o1, o2);
        } else if (isByte) {
            return compareByte(o1, o2);
        } else if (isChar) {
            return compareChar(o1, o2);
        } else if (isShort) {
            return compareShort(o1, o2);
        } else if (isLong) {
            return compareLong(o1, o2);
        } else if (isBoolean) {
            return compareBoolean(o1, o2);
        } else {
            return -2;
        }
    }

    public int compareString(E o1, E o2) {
        String str1 = (String) o1;
        String str2 = (String) o2;
        return str1.compareTo(str2);
    }

    public int compareInt(E o1, E o2) {
        int int1 = (int) o1;
        int int2 = (int) o2;
        return Integer.compare(int1, int2);
    }

    public int compareDouble(E o1, E o2) {
        double double1 = (double) o1;
        double double2 = (double) o2;
        return Double.compare(double1, double2);
    }

    public int compareFloat(E o1, E o2) {
        float float1 = (float) o1;
        float float2 = (float) o2;
        return Float.compare(float1, float2);
    }

    public int compareByte(E o1, E o2) {
        byte byte1 = (byte) o1;
        byte byte2 = (byte) o2;
        return Byte.compare(byte1, byte2);
    }

    public int compareChar(E o1, E o2) {
        char char1 = (char) o1;
        char char2 = (char) o2;
        return Character.compare(char1, char2);
    }

    public int compareShort(E o1, E o2) {
        short short1 = (short) o1;
        short short2 = (short) o2;
        return Short.compare(short1, short2);
    }

    public int compareLong(E o1, E o2) {
        long long1 = (long) o1;
        long long2 = (long) o2;
        return Long.compare(long1, long2);
    }

    public int compareBoolean(E o1, E o2) {
        boolean boolean1 = (boolean) o1;
        boolean boolean2 = (boolean) o2;
        return Boolean.compare(boolean1, boolean2);
    }
}
