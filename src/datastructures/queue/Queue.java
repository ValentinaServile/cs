package datastructures.queue;

import java.util.LinkedList;

public class Queue {

    private final LinkedList<String> backingLinkedList = new LinkedList<>();

    public void enqueue(String element) {
        backingLinkedList.addLast(element);
    }

    public String dequeue() {
        return backingLinkedList.pop();
    }

    public String peek() {
        return backingLinkedList.getFirst();
    }
}
