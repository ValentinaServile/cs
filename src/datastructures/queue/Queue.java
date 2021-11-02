package datastructures.queue;

import java.util.LinkedList;

public class Queue {

    private final LinkedList<String> backingLinkedList = new LinkedList<>();

    //Complexity: O(1)
    public void enqueue(String element) {
        backingLinkedList.addLast(element);
    }

    //Complexity: O(1)
    public String dequeue() {
        return backingLinkedList.pop();
    }

    //Complexity: O(1)
    public String peek() {
        return backingLinkedList.getFirst();
    }
}
