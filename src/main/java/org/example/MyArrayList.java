package org.example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * <i>MyArrayList</i> is my attempt to recreate <i>ArrayList</i> from scratch.
 * <p><b>Possibilities:</b></p>
 * <p>- Can hold any types via use of generics;</p>
 * <p>- Can be created with default capacity and with passing array;</p>
 * <p>- Can find it's length;</p>
 * <p>- Can add a new element with corresponding type to the end of the array or any other place of array;</p>
 * <p>- Can replace an element of array with passed element with index;</p>
 * <p>- Can delete any element with index;</p>
 * <p>- Can get any element with index;</p>
 * <p>- Can clear the array from elements;</p>
 * <p>- Can print all content of the array;</p>
 * <p>- Can sort the array with QuickSort algorithm that uses <i>Comparator</i> {@link java.util.Comparator};</p>
 * <p>- Can be <i>Serializable</i> {@link java.io.Serializable}.</p>
 * <p>---------------------------------------------------</p>
 *
 * @author <i>Denis Reznikov</i>
 */
public class MyArrayList<E> implements Serializable {
    private final transient Object[] DEFAULT_EMPTY_ARR = {};

    /**
     * The list where the <b>MyArrayList</b> will store all elements.
     */
    transient E[] arrMyArrayList;

    /**
     * The default increase of capacity of the <b>arrMyArrayList</b>.
     */
    static final int DEFAULT_CAPACITY_INCREASE = 1;

    /**
     * The default constructor which will create the empty <b>arrMyArrayList</b>.
     */
    public MyArrayList() {
        this.arrMyArrayList = (E[]) DEFAULT_EMPTY_ARR;
    }

    /**
     * <p>The constructor which creates the <b>MyArrayList</b> from a collection like <i>Arrays.asList(...)</i></p>
     *
     * @param arr is a collection which elements you want to use via the <b>MyArrayList</b>.
     */
    public MyArrayList(Collection<? extends E> arr) {
        Object[] newArr = arr.toArray();
        this.arrMyArrayList = (E[]) newArr;
    }

    /**
     * Calculates the length of the <b>arrMyArrayList</b>, i.e., of the <b>MyArrayList</b> via for loop
     *
     * @return length of the <b>arrMyArrayList</b>
     */
    public int length() {
        int arrLength = 0;

        for (int i = 0; i < this.arrMyArrayList.length; i++) {
            arrLength++;
        }

        return arrLength;
    }

    /**
     * Adds an element to the end of the <b>arrMyArrayList</b>. It calculates a length of the <b>arrMyArrayList</b> than calculates new length and assigns a new array with elements from a previous arr plus a new element to the <b>arrMyArrayList</b>.
     *
     * @param element the element which you want to add to the <b>arrMyArrayList</b>
     */
    public void add(E element) {
        final int ARR_OLD_LENGTH = this.arrMyArrayList.length;
        final int ARR_NEW_LENGTH = DEFAULT_CAPACITY_INCREASE + ARR_OLD_LENGTH;

        E[] arrCopy = Arrays.copyOf(this.arrMyArrayList, ARR_NEW_LENGTH);
        arrCopy[ARR_NEW_LENGTH - 1] = element;
        this.arrMyArrayList = arrCopy;
    }

    /**
     * Replaces an element in the <b>arrMyArrayList</b> at the index <b>i</b> with the <b>element</b>. It calculates a previous length of the <b>arrMyArrayList</b> then calculates a new length, and then it checks if it can replace a previous element with the new <b>element</b>.
     *
     * @param i       index of the previous element which you want to replace with the new <b>element</b>.
     * @param element the element which you want to have in place of a previous element at the index <b>i</b>
     */
    public void add(int i, E element) {
        final int ARR_OLD_LENGTH = this.arrMyArrayList.length;
        final int ARR_NEW_LENGTH = DEFAULT_CAPACITY_INCREASE + ARR_OLD_LENGTH;

        if ((i == ARR_OLD_LENGTH - 1 || i < ARR_OLD_LENGTH - 1) && i >= 0) {
            E[] arrCopy = Arrays.copyOf(this.arrMyArrayList, ARR_OLD_LENGTH);
            arrCopy[i] = element;
            this.arrMyArrayList = arrCopy;
        } else if (i == ARR_OLD_LENGTH) {
            E[] arrCopy = Arrays.copyOf(this.arrMyArrayList, ARR_NEW_LENGTH);
            arrCopy[i] = element;
            this.arrMyArrayList = arrCopy;
        } else if (i < 0) {
            throw new RuntimeException("You can't use negative numbers as an index.");
        } else if (i > ARR_OLD_LENGTH) {
            throw new RuntimeException("You can't add an element if the index is bigger than old " + "length + 1.");
        }
    }

    /**
     * Adds the <b>element</b> to the given index <b>i</b> with an offset of other part of <b>arrMyArrayList</b> if necessary.
     * It calculates a length of the <b>arrMyArrayList</b> then checks the given index <b>i</b> if you can or can't add the <b>element</b> here.
     * If everything's okay then it creates three arrays: an array before the index <b>i</b> inclusively, an array after the index <b>i</b>, and an array which contains only the new <b>element</b>. Then it connects them together and replaces <b>arrMyArrayList</b> with the new array.
     *
     * @param i       index at which you want to place the <b>element</b>.
     * @param element an element you want to place at the index <b>i</b>.
     */
    public void addWithOffset(int i, E element) {
        final int ARR_OLD_LENGTH = this.arrMyArrayList.length;

        if ((i == ARR_OLD_LENGTH - 1 || i < ARR_OLD_LENGTH - 1) && i >= 0) {
            E[] arrLeftPart = Arrays.copyOfRange(this.arrMyArrayList, 0, i);
            E[] arrRightPart = Arrays.copyOfRange(this.arrMyArrayList, i, ARR_OLD_LENGTH);
            E[] arrNewElement = (E[]) new Object[1];
            arrNewElement[0] = element;
            E[] result = Arrays.copyOf(arrLeftPart, arrLeftPart.length + arrRightPart.length + arrNewElement.length);
            System.arraycopy(arrNewElement, 0, result, i, arrNewElement.length);
            System.arraycopy(arrRightPart, 0, result, i + 1, arrRightPart.length);
            this.arrMyArrayList = result;
        } else if (i == ARR_OLD_LENGTH) {
            this.add(element);
        } else if (i < 0) {
            throw new RuntimeException("You can't use negative numbers as an index.");
        } else if (i > ARR_OLD_LENGTH) {
            throw new RuntimeException("You can't add the element if the index is bigger than an " + "old length + 1.");
        } else {
            throw new RuntimeException("Something's wrong with addWithOffset method of " + "MyArrayList class");
        }
    }

    /**
     * Deletes an element at the index <b>i</b>. It calculates a length of the
     * <b>arrMyArrayList</b> then it creates two arrays: an array with elements before <b>i</b>
     * element and an array with elements after <b>i</b> element. After that it connects that
     * arrays and reassign <b>arrMyArrayList</b> with it.
     *
     * @param i an index of an element which you want to delete.
     */
    public void delete(int i) {
        final int ARR_OLD_LENGTH = this.arrMyArrayList.length;

        if ((i == ARR_OLD_LENGTH - 1 || i < ARR_OLD_LENGTH - 1) && i >= 0) {
            E[] arrLeftPart = Arrays.copyOfRange(this.arrMyArrayList, 0, i);
            E[] arrRightPart = Arrays.copyOfRange(this.arrMyArrayList, i + 1, ARR_OLD_LENGTH);
            E[] result = Arrays.copyOf(arrLeftPart, arrLeftPart.length + arrRightPart.length);

            System.arraycopy(arrRightPart, 0, result, i, arrRightPart.length);
            this.arrMyArrayList = result;
        } else if (i < 0) {
            throw new RuntimeException("You can't use negative numbers as an index.");
        } else if (i >= ARR_OLD_LENGTH) {
            throw new RuntimeException("You can't delete the element because that arr doesn't " + "have that index.");
        } else {
            throw new RuntimeException("Something's wrong with delete method of MyArrayList class");
        }
    }

    /**
     * Gets an element at the index <b>i</b> out of the <b>arrMyArrayList</b>.
     *
     * @param i an index of the element which you want to get
     * @return an element which you want to get
     */
    public E get(int i) {
        return this.arrMyArrayList[i];
    }

    /**
     * Clears the <b>arrMyArrayList</b> from any elements by reassigning it to the
     * <b>DEFAULT_EMPTY_ARR</b>.
     */
    public void clear() {
        this.arrMyArrayList = (E[]) DEFAULT_EMPTY_ARR;
    }

    /**
     * Changes behavior so now it creates a string of all elements of the <b>arrMyArrayList</b>
     * via Arrays.toString() method.
     *
     * @return string which contains all elements of the <b>arrMyArrayList</b>
     */
    @Override
    public String toString() {
        return Arrays.toString(this.arrMyArrayList);
    }

    /**
     * QuickMergeSort realisation from scratch. It creates the <b>MyArrayList</b> with all elements
     * and passes it with the array <b>arrResult</b> which at the end will be a sorted version of
     * the used array. The method <b>quickMergeSortRecursion</b> uses the <b>index</b> as a first
     * index of the element <b>pivot</b> with which all other elements will compare. <p>It
     * deletes the element from the <b>arrMyArrayList</b> and starts checking all elements of the
     * <b>arrMyArrayList</b> if they're less than or equals the <b>pivot</b>. If that's true than
     * all elements will be added to the <b>arrLeft</b> which will be used inside the
     * <b>quickMergeSortRecursion</b> method. <p>That method will do practically the same process as
     * the
     * <b>quickMergeSort</b> but with a moment - at the end when it have an array with two
     * elements it
     * will compare them and add them to the array <b>arrResul</b> with ASC order. If an array
     * have only one element than it will add it. If zero elements than it will stop.</p>
     * <p>At the end <b>quickMergeSortRecursion</b>will add all elements of the array <b>arrLeft</b>
     * in ASC order to the array <b>arrResult</b> and than it adds the <b>pivot</b> itself to
     * that array because all other element will be bigger than it so we can safely add it
     * .</p><p>Then the process repeats but with the array <b>arrRight</b> which will have all
     * the elements bigger than the <b>pivot</b> and it'll go to the <b>quickMergeSortRecursion</b>
     * method which will add to the array <b>arrResult</b> all elements bigger than the
     * <b>pivot</b>.</p> At the end the array <b>arrMyArrayList</b> will be cleared from all it's
     * elements and all the elements of the sorted array <b>arrResult</b> will be added to the
     * empty <b>arrMyArrayList</b> via for-loop.
     */
    public void quickMergeSort() {
        MyArrayList<E> arrStart = new MyArrayList<>(Arrays.asList(this.arrMyArrayList));
        MyArrayList<E> arrResult = new MyArrayList<>();
        quickMergeSortRecursion(arrStart, arrResult);
        this.clear();

        for (int i = 0; i < arrResult.length(); i++) {
            this.add(arrResult.get(i));
        }
    }

    private void quickMergeSortRecursion(MyArrayList<E> arr, MyArrayList<E> arrResult) {
        if (arr.length() == 2) {
            E element1 = arr.get(0);
            E element2 = arr.get(1);

            switch (compare(element1, element2)) {
                case 1, 0:
                    arrResult.add(element2);
                    arrResult.add(element1);
                    break;
                case -1:
                    arrResult.add(element1);
                    arrResult.add(element2);
                    break;
                default:
                    throw new RuntimeException("Something's wrong with the quickMergeSortRecursion method of MyArrayList class");
            }

            return;
        } else if (arr.length() == 1) {
            arrResult.add(arr.get(0));
            return;
        } else if (arr.length() == 0) {
            return;
        }

        int index = 0;
        E pivot = arr.get(index);
        arr.delete(index);
        MyArrayList<E> arrLeft = new MyArrayList<>();
        MyArrayList<E> arrRight = new MyArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            E element = arr.get(i);
            if (compare(element, pivot) == -1 || compare(element, pivot) == 0) {
                arrLeft.add(element);
            }
        }

        quickMergeSortRecursion(arrLeft, arrResult);
        arrResult.add(pivot);

        for (int i = 0; i < arr.length(); i++) {
            E element = arr.get(i);
            if (compare(element, pivot) == 1) {
                arrRight.add(element);
            }
        }

        quickMergeSortRecursion(arrRight, arrResult);
    }

    /**
     * QuickMergeSort realization for objects with the <b>comparator</b>. It creates the
     * <b>MyArrayList</b> with all elements and passes it with the array <b>arrResult</b> which
     * at the end will be a sorted version of the used array. The method
     * <b>quickMergeSortRecursion</b> uses the <b>index</b> as a first index of the element
     * <b>pivot</b> with which all other elements will compare. <p>It
     * deletes the element from the <b>arrMyArrayList</b> and starts checking all elements of the
     * <b>arrMyArrayList</b> if they're less than or equals the <b>pivot</b>. If that's true than
     * all elements will be added to the <b>arrLeft</b> which will be used inside the
     * <b>quickMergeSortRecursion</b> method. <p>That method will do practically the same process as
     * the
     * <b>quickMergeSort</b> but with a moment - at the end when it have an array with two
     * elements it
     * will compare them and add them to the array <b>arrResul</b> with ASC order. If an array
     * have only one element than it will add it. If zero elements than it will stop.</p>
     * <p>At the end <b>quickMergeSortRecursion</b>will add all elements of the array <b>arrLeft</b>
     * in ASC order to the array <b>arrResult</b> and than it adds the <b>pivot</b> itself to
     * that array because all other element will be bigger than it so we can safely add it
     * .</p><p>Then the process repeats but with the array <b>arrRight</b> which will have all
     * the elements bigger than the <b>pivot</b> and it'll go to the <b>quickMergeSortRecursion</b>
     * method which will add to the array <b>arrResult</b> all elements bigger than the
     * <b>pivot</b>.</p> At the end the array <b>arrMyArrayList</b> will be cleared from all it's
     * elements and all the elements of the sorted array <b>arrResult</b> will be added to the
     * empty <b>arrMyArrayList</b> via for-loop.
     *
     * @param comparator used to compare object with it's compare method.
     */
    public void quickMergeSort(Comparator<E> comparator) {
        MyArrayList<E> arrStart = new MyArrayList<>(Arrays.asList(this.arrMyArrayList));
        MyArrayList<E> arrResult = new MyArrayList<>();
        quickMergeSortRecursion(arrStart, arrResult, comparator);
        this.clear();

        for (int i = 0; i < arrResult.length(); i++) {
            this.add(arrResult.get(i));
        }
    }

    private void quickMergeSortRecursion(MyArrayList<E> arr, MyArrayList<E> arrResult, Comparator<E> comparator) {

        if (arr.length() == 2) {
            E element1 = arr.get(0);
            E element2 = arr.get(1);

            switch (compareObject(element1, element2, comparator)) {
                case 1, 0:
                    arrResult.add(element2);
                    arrResult.add(element1);
                    break;
                case -1:
                    arrResult.add(element1);
                    arrResult.add(element2);
                    break;
                default:
                    throw new RuntimeException("Something's wrong with the " + "quickMergeSortRecursion with a Comparator method of MyArrayList " + "class");
            }

            return;
        } else if (arr.length() == 1) {
            arrResult.add(arr.get(0));
            return;
        } else if (arr.length() == 0) {
            return;
        }

        int index = 0;
        E pivot = arr.get(index);
        arr.delete(index);
        MyArrayList<E> arrLeft = new MyArrayList<>();
        MyArrayList<E> arrRight = new MyArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            E element = arr.get(i);
            if (compareObject(element, pivot, comparator) == -1 || compareObject(element, pivot, comparator) == 0) {
                arrLeft.add(element);
            }
        }

        quickMergeSortRecursion(arrLeft, arrResult, comparator);
        arrResult.add(pivot);

        for (int i = 0; i < arr.length(); i++) {
            E element = arr.get(i);
            if (compareObject(element, pivot, comparator) == 1) {
                arrRight.add(element);
            }
        }

        quickMergeSortRecursion(arrRight, arrResult, comparator);
    }

    /**
     * My QuickSort realization. Method is used as a start for the quickSort algorithm for user's
     * convenience. Algorithm itself uses array as <b>arrMyArrayList</b>, indexes of the
     * beginning and of the end of the array. Firstly it sorts the left part of the array by
     * choosing the <b>pivot</b> with the <b>indexEnd</b>. Next it will compare each element of
     * the <b>arr</b> between the <b>indexBegin</b> and the <b>indexEnd</b> and if an element
     * will be smaller than the <b>pivot</b> than it will go to the left of the <b>pivot</b>.
     * When the left side will be gone it will start to sort right side.
     */
    public void quickSort() {
        quickSortAlgorithm(this.arrMyArrayList, 0, this.length() - 1);
    }

    private void quickSortAlgorithm(E[] arr, int indexBegin, int indexEnd) {
        if (indexBegin < indexEnd) {
            int partitionIndex = partition(arr, indexBegin, indexEnd);

            quickSortAlgorithm(arr, indexBegin, partitionIndex - 1);
            quickSortAlgorithm(arr, partitionIndex + 1, indexEnd);
        }
    }

    private int partition(E[] arr, int indexBegin, int indexEnd) {
        E pivot = arr[indexEnd];
        int i = (indexBegin - 1);

        for (int j = indexBegin; j < indexEnd; j++) {
            if (compare(arr[j], pivot) == -1) {
                i++;

                E temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        E temp = arr[i + 1];
        arr[i + 1] = arr[indexEnd];
        arr[indexEnd] = temp;

        return i + 1;
    }

    private int compare(E o1, E o2) {

        if (!(o1 instanceof Comparable<?>)) {
            throw new RuntimeException("These types aren't comparable");
        }

        return ((Comparable<E>) o1).compareTo(o2);
    }

    private int compareObject(E o1, E o2, Comparator<E> c) {
        if (c == null) {
            return 0;
        }

        return c.compare(o1, o2);
    }
}
