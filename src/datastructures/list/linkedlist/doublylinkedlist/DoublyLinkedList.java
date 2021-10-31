package datastructures.list.linkedlist.doublylinkedlist;


import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public int size() {
        return size;
    }

    public String get(int index) {
        return nodeAt(index).getElement();
    }

    public boolean contains(String element) {
        return indexOf(element) != -1;
    }

    public void add(String element) {
        Node toAdd = new Node(element);
        linkLast(toAdd);
    }

    public void add(String element, int index) {
        Node toAdd = new Node(element);
        if (index == size) {
            linkLast(toAdd);
        } else if (index == 0) {
            linkFirst(toAdd);
        } else {
            Node next = nodeAt(index);
            linkBefore(next, toAdd);
        }
    }

    public void remove(int index) {
        if (index == 0) {
            unlinkFirst();
        } else if (index == size - 1) {
            unlinkLast();
        } else {
            Node toRemove = nodeAt(index);
            unlink(toRemove);
        }
    }

    public void remove(String element) {
        int currentIndex = 0;
        Node currentNode = head;
        while (currentIndex < size) {
            boolean found = element.equals(currentNode.getElement());
            if (found && currentIndex == 0) {
                unlinkFirst();
                return;
            } else if (found && currentIndex == size - 1) {
                unlinkLast();
                return;
            } else if (found) {
                unlink(currentNode);
                return;
            }
            currentIndex++;
            currentNode = currentNode.getNext();
        }
    }

    public int indexOf(String element) {
        int currentIndex = 0;
        Node currentNode = head;
        while (currentIndex < size) {
            if (element.equals(currentNode.getElement())) {
                return currentIndex;
            }
            currentIndex++;
            currentNode = currentNode.getNext();
        }
        return -1;
    }

    private void linkBefore(Node node, Node toLink) {
        Node previous = node.getPrevious();

        toLink.setPrevious(previous);
        toLink.setNext(node);

        previous.setNext(toLink);
        node.setPrevious(toLink);
        size++;
    }

    private void linkFirst(Node toAdd) {
        if (head == null) {
            head = toAdd;
            tail = toAdd;
        } else {
            toAdd.setNext(head);
            head.setPrevious(toAdd);
            head = toAdd;
        }
        size++;
    }

    private void linkLast(Node toAdd) {
        if (head == null) {
            head = toAdd;
            tail = toAdd;
        } else {
            toAdd.setPrevious(tail);
            tail.setNext(toAdd);
            tail = toAdd;
        }
        size++;
    }

    private void unlinkFirst() {
        if (size == 0) {
            return;
        } else if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else {
            head = head.getNext();
            head.setPrevious(null);
            size--;
        }
    }

    private void unlinkLast() {
        if (size == 0) {
            return;
        } else if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
        }
    }

    private void unlink(Node node) {
        Node previous = node.getPrevious();
        Node next = node.getNext();

        previous.setNext(next);
        next.setPrevious(previous);
        size--;
    }

    private Node nodeAt(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("invalid index");
        }

        if (index <= (size / 2) ) {
            int currentIndex = 0;
            Node currentNode = head;
            while (currentIndex != index) {
                currentNode = currentNode.getNext();
                currentIndex++;
            }
            return currentNode;
        } else {
            int currentIndex = size - 1;
            Node currentNode = tail;
            while (currentIndex != index) {
                currentNode = currentNode.getPrevious();
                currentIndex--;
            }
            return currentNode;
        }
    }

    @Override
    public String toString() {
        List<String> elements = new ArrayList<>();
        Node nextElement = head;
        while (nextElement != null) {
            elements.add(nextElement.element);
            nextElement = nextElement.getNext();
        }

        return "DoublyLinkedList{" + String.join( ",", elements) + '}' + size;
    }

    private static class Node {
        private Node next;
        private Node previous;
        private String element;

        public Node(String element) {
            this.element = element;
        }

        public String getElement() {
            return element;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

}
