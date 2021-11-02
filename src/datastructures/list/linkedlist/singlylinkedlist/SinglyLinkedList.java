package datastructures.list.linkedlist.singlylinkedlist;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedList {

    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    //Complexity: O(1)
    public int size() {
        return size;
    }

    //Complexity: O(n)
    public String get(int index) {
        return nodeAt(index).getElement();
    }

    //Complexity: O(n)
    public void add(String element) {
        Node node = new Node(element);
        if (head == null) {
            linkFirst(node);
            return;
        }
        link(node, size);
    }

    //Complexity: O(n)
    public void add(String element, int index) {
        Node toAdd = new Node(element);
        if (index == 0) {
            linkFirst(toAdd);
        } else {
            link(toAdd, index);
        }
    }

    //Complexity: O(n)
    public void remove(String element) {
        if (head == null) {
            return;
        }
        int currentIndex = 0;
        Node previousNode = null;
        Node currentNode = head;
        while (currentIndex < size) {
            boolean found = element.equals(currentNode.getElement());
            if (found && currentIndex == 0) {
                unlinkFirst();
                return;
            } else if (found) {
                unlink(previousNode);
                return;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
            currentIndex++;
        }

    }

    //Complexity: O(n)
    public void remove(int index) {
        if (index == 0) {
            unlinkFirst();
        } else {
            unlink(index);
        }
    }

    private void linkFirst(Node node) {
        if (head == null) {
            head = node;
            size++;
            return;
        }
        node.setNext(head);
        head = node;
        size++;
    }

    private void unlinkFirst() {
        if (size == 0) {
            //do nothing
        } else if (size == 1) {
            head = null;
            size--;
        } else {
            head = head.getNext();
            size--;
        }
    }

    public Integer indexOf(String element) {
        int currentIndex = 0;
        Node currentNode = head;
        while (currentIndex < size) {
            if (currentNode.getElement().equals(element)) {
                return currentIndex;
            }
            currentNode = currentNode.getNext();
            currentIndex++;
        }

        return -1;
    }

    public boolean contains(String element) {
        return indexOf(element) != -1;
    }

    private void link(Node node, int index) {
        Node previous = nodeAt(index - 1);
        Node next =  previous.getNext();
        node.setNext(next);
        previous.setNext(node);
        size++;
    }

    private void unlink(int index) {
        Node previous = nodeAt(index - 1);
        Node next = previous.getNext().getNext();

        previous.setNext(next);
        size--;
    }

    private void unlink(Node previousNode) {
        Node next = previousNode.getNext().getNext();
        previousNode.setNext(next);
        size--;
    }


    private Node nodeAt(int index) {
        if (head == null || index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("invalid index");
        }
        int currentIndex = 0;
        Node currentNode = head;
        while (currentIndex < index) {
            currentNode = currentNode.getNext();
            currentIndex++;
        }

        return currentNode;
    }

    @Override
    public String toString() {
        List<String> elements = new ArrayList<>();
        Node nextElement = head;
        while (nextElement != null) {
            elements.add(nextElement.element);
            nextElement = nextElement.getNext();
        }

        return "SinglyLinkedList{" + String.join( ",", elements) + '}' + size;
    }

    private static class Node {
        private Node next;
        private String element;

        Node(String element) {
            this.element = element;
            this.next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String getElement() {
            return element;
        }
    }
}
