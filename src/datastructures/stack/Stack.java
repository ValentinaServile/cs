package datastructures.stack;

import java.util.LinkedList;

public class Stack {

    private LinkedList<String> backingLinkedList = new LinkedList<>();

    public void push(String element) {
        backingLinkedList.addFirst(element);
    }

    public String pop() {
        return backingLinkedList.pop();
    }

    public String peek() {
        return backingLinkedList.getFirst();
    }
}
