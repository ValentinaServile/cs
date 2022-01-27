package datastructures.tree.binarytree.binarysearchtree.avltree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Consumer;

public class AvlTree {

    private Node root;

    //Complexity: O(log(n))
    public void insert(Integer element) {
        if (root == null) {
            root = new Node(element);
        } else {
            root.insert(new Node(element));
        }
    }

    //Complexity: O(log(n))
    public Integer search(Integer element) {
        Node result = root.search(element);

        if (result == null) {
            throw new NoSuchElementException("not found");
        }

        return result.value;
    }

    //Complexity: O(log(n))
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

    //Complexity: O(n)
    public void traversePreOrder(Consumer<Integer> callback) {
        if (root == null) {
            return;
        }

        root.traversePreOrder(callback);
    }

    //Complexity: O(n)
    public void traverseInOrder(Consumer<Integer> callback) {
        if (root == null) {
            return;
        }

        root.traverseInOrder(callback);
    }

    //Complexity: O(n)
    public void traversePostOrder(Consumer<Integer> callback) {
        if (root == null) {
            return;
        }

        root.traversePostOrder(callback);
    }

    //Complexity: O(n)
    public void traverseLevelOrder(Consumer<Integer> callback) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node nodeToProcess = queue.remove();
            callback.accept(nodeToProcess.getValue());
            if (nodeToProcess.hasLeftChild()) {
                queue.add(nodeToProcess.getLeftChild());
            }
            if (nodeToProcess.hasRightChild()) {
                queue.add(nodeToProcess.getRightChild());
            }
        }
    }

    private static class Node {

        private Node leftChild;
        private Node rightChild;
        private Integer value;
        private int height;
        private int balanceFactor;

        private Node(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public boolean hasLeftChild() {
            return leftChild != null;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public boolean hasRightChild() {
            return rightChild != null;
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

            updateBalanceInformation();
            if (leftChild != null)
                leftChild.balance(this, true);

            if (rightChild != null)
                rightChild.balance(this, false);
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

            updateBalanceInformation();

            if (leftChild != null)
                leftChild.balance(this, true);

            if (rightChild != null)
                rightChild.balance(this, false);

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

        public void updateBalanceInformation() {
            int rightHeight = - 1;
            if (rightChild != null) {
                rightChild.updateBalanceInformation();
                rightHeight = rightChild.height;
            }
            int leftHeight = - 1;
            if (leftChild != null) {
                leftChild.updateBalanceInformation();
                leftHeight = leftChild.height;
            }

            height = Math.max(leftHeight, rightHeight) + 1;
            balanceFactor = rightHeight - leftHeight;
        }

        public void balance(Node parent, boolean isLeftChild) {
            if (isLeftHeavy()) {
                if (leftChild.balanceFactor <= 0) {
                    leftLeftRebalance(parent, isLeftChild);
                } else {
                    leftRightRebalance(parent, isLeftChild);
                }
            }
            if (isRightHeavy()) {
                if (rightChild.balanceFactor >= 0) {
                    rightRightRebalance(parent, isLeftChild);
                } else {
                    rightLeftRebalance(parent, isLeftChild);
                }
            }

            if (leftChild != null) {
                leftChild.balance(this, true);
            }
            if (rightChild != null) {
                rightChild.balance(this, false);
            }
        }

        private void rightLeftRebalance(Node parent, boolean isLeftChild) {
            rightChild.rightRotate(this, false);
            rightRightRebalance(parent, isLeftChild);
        }

        private void rightRightRebalance(Node parent, boolean isLeftChild) {
            leftRotate(parent, isLeftChild);
        }

        private void leftRightRebalance(Node parent, boolean isLeftChild) {
            leftChild.leftRotate(this, true);
            leftLeftRebalance(parent, isLeftChild);
        }

        private void leftLeftRebalance(Node parent, boolean isLeftChild) {
            rightRotate(parent, isLeftChild);
        }

        public void rightRotate(Node parent, boolean isLeftChild) {
            Node newRoot = leftChild;
            leftChild = newRoot.rightChild;
            newRoot.rightChild = this;

            if (parent != null) {

                if (isLeftChild) {
                    parent.leftChild = newRoot;
                } else {
                    parent.rightChild = newRoot;
                }
            }
        }

        public void leftRotate(Node parent, boolean isLeftChild) {
            Node newRoot = rightChild;
            rightChild = newRoot.leftChild;
            newRoot.leftChild = this;

            if (parent != null) {

                if (isLeftChild) {
                    parent.leftChild = newRoot;
                } else {
                    parent.rightChild = newRoot;
                }

            }

        }

        private boolean isLeftHeavy() {
            return balanceFactor == -2;
        }

        private boolean isRightHeavy() {
            return balanceFactor == 2;
        }
    }
}
