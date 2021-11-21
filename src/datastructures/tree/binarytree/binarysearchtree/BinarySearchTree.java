package datastructures.tree.binarytree.binarysearchtree;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class BinarySearchTree {

    private Node root;

    public void insert(Integer element) {
        if (root == null) {
            root = new Node(element);
        } else {
            root.insert(new Node(element));
        }
    }

    public Integer search(Integer element) {
        Node result = root.search(element);

        if (result == null) {
            throw new NoSuchElementException("not found");
        }

        return result.value;
    }

    public void remove(Integer element) {
        if (root == null) {
            return;
        }

        if (root.getValue().equals(element) && root.isLeaf()) {
            root = null;
            return;
        }

        root.remove(element, null);
    }

    public void traversePreOrder(Consumer<Integer> callback) {
        if (root == null) {
            return;
        }

        root.traversePreOrder(callback);
    }

    public void traverseInOrder(Consumer<Integer> callback) {
        if (root == null) {
            return;
        }

        root.traverseInOrder(callback);
    }


    public void traversePostOrder(Consumer<Integer> callback) {
        if (root == null) {
            return;
        }

        root.traversePostOrder(callback);
    }

    private static class Node {

        private Node leftChild;
        private Node rightChild;
        private Integer value;

        private Node(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return leftChild;
        }

        public Node search(Integer value) {
            if (Objects.equals(this.value, value)) {
                return this;
            } else if (value > this.value && rightChild != null) {
                return rightChild.search(value);
            } else if (value < this.value && leftChild != null) {
                return leftChild.search(value);
            }

            return null;
        }

        public void remove(Integer value, Node parent) {

            if (value.equals(this.value)) {
                if (isLeaf() && parent != null) {
                    parent.removeChild(this);
                } else if (hasOnlyOneChild() && parent != null) {
                    parent.replaceChild(this, firstNonNullChild());
                } else {
                    Node replacement = leftChild.max();
                    remove(replacement.getValue(), null);
                    this.value = replacement.getValue();
                }
            } else if (value < this.value && leftChild != null) {
                leftChild.remove(value, this);
            } else if (value > this.value && rightChild != null) {
                rightChild.remove(value, this);
            }

        }

        public void insert(Node other) {
            if (value > other.getValue()) {

                if (leftChild == null) {
                    leftChild = other;
                }
                else {
                    leftChild.insert(other);
                }

            } else if (value < other.getValue()) {

                if (rightChild == null) {
                    rightChild = other;
                }
                else {
                    rightChild.insert(other);
                }
            }
        }

        public void traverseInOrder(Consumer<Integer> callback) {
            if (leftChild != null) {
                leftChild.traverseInOrder(callback);
            }
            callback.accept(value);
            if (rightChild != null) {
                rightChild.traverseInOrder(callback);
            }
        }

        public void traversePreOrder(Consumer<Integer> callback) {
            callback.accept(value);
            if (leftChild != null) {
                leftChild.traversePreOrder(callback);
            }
            if (rightChild != null) {
                rightChild.traversePreOrder(callback);
            }
        }

        public void traversePostOrder(Consumer<Integer> callback) {
            if (leftChild != null) {
                leftChild.traversePostOrder(callback);
            }
            if (rightChild != null) {
                rightChild.traversePostOrder(callback);
            }
            callback.accept(value);
        }

        public boolean isLeaf() {
            return leftChild == null && rightChild == null;
        }

        public boolean hasOnlyOneChild() {
            return leftChild == null ^ rightChild == null;
        }

        public Node firstNonNullChild() {
            return leftChild != null
                    ? leftChild
                    : rightChild != null ? rightChild : null;
        }

        public Node max() {
            if (rightChild == null) {
                return this;
            }
            else {
                return rightChild.max();
            }
        }

        public void removeChild(Node childToRemove) {
            if (childToRemove == leftChild ) {
                leftChild = null;
            } else if (childToRemove == rightChild) {
                rightChild = null;
            }
        }

        public void replaceChild(Node childToRemove, Node replacementChild) {
            if (childToRemove == leftChild ) {
                leftChild = replacementChild;
            } else if (childToRemove == rightChild) {
                rightChild = replacementChild;
            }
        }
    }
}
