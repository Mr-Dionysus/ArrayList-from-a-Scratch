package org.example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 */
public class MyArrayList<E> implements Serializable {
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

    @Override
    public String toString() {
        return Arrays.toString(this.arr);
    }
}
