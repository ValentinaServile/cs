package datastructures.list.arraylist;

import java.util.Arrays;

public class ArrayList {

    private String[] backingArray;
    private int nextEmptyIndex;

    public ArrayList() {
         this(2);
    }

    public ArrayList(int initialCapacity) {
        nextEmptyIndex = 0;
        backingArray = new String[initialCapacity];
    }

    public int size() {
        return nextEmptyIndex;
    }

    public String get(int index) {
        return backingArray[index];
    }

    public void add(String element) {
        if (nextEmptyIndex == backingArray.length) {
            doubleBackingArraySize();
        }

        backingArray[nextEmptyIndex] = element;
        nextEmptyIndex += 1;
    }

    public void add(String element, int index) {
        if (nextEmptyIndex == backingArray.length) {
            doubleBackingArraySize();
        }

        makeRoomAt(index);
        backingArray[index] = element;
    }

    public void remove(int index) {
        if (index >= nextEmptyIndex) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < nextEmptyIndex - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        nextEmptyIndex--;
    }

    public void remove(String element) {
        int index = indexOf(element);
        if (index == -1) {
            return;
        }
        remove(index);
    }

    public int indexOf(String element) {
        for (int i = 0; i < backingArray.length; i++) {
            if (element.equals(backingArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains (String element) {
        return indexOf(element) != -1;
    }

    private void makeRoomAt(int index) {
        for (int i = nextEmptyIndex; i > index; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        nextEmptyIndex++;
    }

    private void doubleBackingArraySize() {
        String[] newBackingArray = new String[backingArray.length * 2];

        for (int i = 0; i < backingArray.length; i++ ) {
            newBackingArray[i] = backingArray[i];
        }

        backingArray = newBackingArray;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "backingArray=" + Arrays.toString(backingArray) +
                ", nextEmptyIndex=" + nextEmptyIndex +
                '}';
    }
}
