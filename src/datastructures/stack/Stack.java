package datastructures.stack;

import java.util.LinkedList;

public class Stack {

    private final LinkedList<String> backingLinkedList = new LinkedList<>();

    //Complexity: O(1)
    public void push(String element) {
        backingLinkedList.addFirst(element);
    }

    //Complexity: O(1)
    public String pop() {
        return backingLinkedList.pop();
    }

    //Complexity: O(1)
    public String peek() {
        return backingLinkedList.getFirst();
    }
}
