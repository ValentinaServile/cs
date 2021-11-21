package datastructures.tree.binarytree.binarysearchtree;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Nested
    class Insert {

        @Test
        void insertsNewElementsRespectingBinarySearchTreeInvariant() {
            BinarySearchTree tree = new BinarySearchTree();

            tree.insert(9);
            tree.insert(6);
            tree.insert(10);
            tree.insert(7);
            tree.insert(18);
            tree.insert(5);
            tree.insert(8);
            tree.insert(11);

            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseInOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 8);

            assertEquals(nodesInOrder.get(0), 5);
            assertEquals(nodesInOrder.get(1), 6);
            assertEquals(nodesInOrder.get(2), 7);
            assertEquals(nodesInOrder.get(3), 8);
            assertEquals(nodesInOrder.get(4), 9);
            assertEquals(nodesInOrder.get(5), 10);
            assertEquals(nodesInOrder.get(6), 11);
            assertEquals(nodesInOrder.get(7), 18);

        }
    }

    @Nested
    class Search {

        @Test
        void throwsExceptionWhenElementDoesNotExist() {
            BinarySearchTree tree = new BinarySearchTree();

            tree.insert(9);
            tree.insert(6);
            tree.insert(10);
            tree.insert(7);
            tree.insert(18);
            tree.insert(5);
            tree.insert(8);
            tree.insert(11);

            assertThrows(NoSuchElementException.class, () -> tree.search(99));
        }

        @Test
        void isAbleToFindElementWhenItExists() {
            BinarySearchTree tree = new BinarySearchTree();

            tree.insert(9);
            tree.insert(6);
            tree.insert(10);
            tree.insert(7);
            tree.insert(18);
            tree.insert(5);
            tree.insert(8);
            tree.insert(11);

            assertEquals(tree.search(18), 18);
        }
    }

    @Nested
    class Remove {

        @Test
        void removesALeafNode() {
            BinarySearchTree tree = new BinarySearchTree();
            tree.insert(9);
            tree.insert(6);
            tree.insert(10);
            tree.insert(7);
            tree.insert(18);
            tree.insert(5);
            tree.insert(8);
            tree.insert(11);

            tree.remove(11);

            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseInOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 7);

            assertEquals(nodesInOrder.get(0), 5);
            assertEquals(nodesInOrder.get(1), 6);
            assertEquals(nodesInOrder.get(2), 7);
            assertEquals(nodesInOrder.get(3), 8);
            assertEquals(nodesInOrder.get(4), 9);
            assertEquals(nodesInOrder.get(5), 10);
            assertEquals(nodesInOrder.get(6), 18);
        }

        @Test
        void removesANodeThatHasOnlyOneChildSubtree() {
            BinarySearchTree tree = new BinarySearchTree();
            tree.insert(9);
            tree.insert(6);
            tree.insert(10);
            tree.insert(7);
            tree.insert(18);
            tree.insert(5);
            tree.insert(8);
            tree.insert(11);

            tree.remove(10);

            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseInOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 7);

            assertEquals(nodesInOrder.get(0), 5);
            assertEquals(nodesInOrder.get(1), 6);
            assertEquals(nodesInOrder.get(2), 7);
            assertEquals(nodesInOrder.get(3), 8);
            assertEquals(nodesInOrder.get(4), 9);
            assertEquals(nodesInOrder.get(5), 11);
            assertEquals(nodesInOrder.get(6), 18);
        }

        @Test
        void removesANodeThatHasTwoChildrenSubtrees() {
            BinarySearchTree tree = new BinarySearchTree();
            tree.insert(9);
            tree.insert(6);
            tree.insert(10);
            tree.insert(7);
            tree.insert(18);
            tree.insert(5);
            tree.insert(8);
            tree.insert(11);

            tree.remove(6);

            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseInOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 7);

            assertEquals(nodesInOrder.get(0), 5);
            assertEquals(nodesInOrder.get(1), 7);
            assertEquals(nodesInOrder.get(2), 8);
            assertEquals(nodesInOrder.get(3), 9);
            assertEquals(nodesInOrder.get(4), 10);
            assertEquals(nodesInOrder.get(5), 11);
            assertEquals(nodesInOrder.get(6), 18);
        }

        @Test
        void removesRootNodeThatHasTwoChildrenSubtrees() {
            BinarySearchTree tree = new BinarySearchTree();
            tree.insert(9);
            tree.insert(6);
            tree.insert(10);
            tree.insert(7);
            tree.insert(18);
            tree.insert(5);
            tree.insert(8);
            tree.insert(11);

            tree.remove(9);

            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseInOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 7);

            assertEquals(nodesInOrder.get(0), 5);
            assertEquals(nodesInOrder.get(1), 6);
            assertEquals(nodesInOrder.get(2), 7);
            assertEquals(nodesInOrder.get(3), 8);
            assertEquals(nodesInOrder.get(4), 10);
            assertEquals(nodesInOrder.get(5), 11);
            assertEquals(nodesInOrder.get(6), 18);
        }

        @Test
        void removesRootNodeThatHasOneChildSubtree() {
            BinarySearchTree tree = new BinarySearchTree();
            tree.insert(9);
            tree.insert(6);

            tree.remove(9);

            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseInOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 1);

            assertEquals(nodesInOrder.get(0), 6);
        }

        @Test
        void removesRootNodeThatHasNoChildren() {
            BinarySearchTree tree = new BinarySearchTree();

            tree.insert(9);

            tree.remove(9);

            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseInOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 0);
        }

        @Test
        void removesNothingFromEmptyTree() {
            BinarySearchTree tree = new BinarySearchTree();

            tree.remove(9);

            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseInOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 0);

        }
    }

    @Nested
    class Traverse {

        @Nested
        class PreOrder {

            @Test
            void allowsToTraverseNodesInPreOrder() {
                BinarySearchTree tree = new BinarySearchTree();

                tree.insert(9);
                tree.insert(6);
                tree.insert(10);
                tree.insert(7);
                tree.insert(18);
                tree.insert(5);
                tree.insert(8);
                tree.insert(11);

                List<Integer> nodesInOrder = new ArrayList<>();
                tree.traversePreOrder(nodesInOrder::add);

                assertEquals(nodesInOrder.size(), 8);

                assertEquals(nodesInOrder.get(0), 9);
                assertEquals(nodesInOrder.get(1), 6);
                assertEquals(nodesInOrder.get(2), 5);
                assertEquals(nodesInOrder.get(3), 7);
                assertEquals(nodesInOrder.get(4), 8);
                assertEquals(nodesInOrder.get(5), 10);
                assertEquals(nodesInOrder.get(6), 18);
                assertEquals(nodesInOrder.get(7), 11);
            }
        }

        @Nested
        class InOrder {

            @Test
            void allowsToTraverseNodesInOrder() {
                BinarySearchTree tree = new BinarySearchTree();

                tree.insert(9);
                tree.insert(6);
                tree.insert(10);
                tree.insert(7);
                tree.insert(18);
                tree.insert(5);
                tree.insert(8);
                tree.insert(11);

                List<Integer> nodesInOrder = new ArrayList<>();
                tree.traverseInOrder(nodesInOrder::add);

                assertEquals(nodesInOrder.size(), 8);

                assertEquals(nodesInOrder.get(0), 5);
                assertEquals(nodesInOrder.get(1), 6);
                assertEquals(nodesInOrder.get(2), 7);
                assertEquals(nodesInOrder.get(3), 8);
                assertEquals(nodesInOrder.get(4), 9);
                assertEquals(nodesInOrder.get(5), 10);
                assertEquals(nodesInOrder.get(6), 11);
                assertEquals(nodesInOrder.get(7), 18);
            }
        }

        @Nested
        class PostOrder {

            @Test
            void allowsToTraverseNodesInPostOrder() {
                BinarySearchTree tree = new BinarySearchTree();

                tree.insert(9);
                tree.insert(6);
                tree.insert(10);
                tree.insert(7);
                tree.insert(18);
                tree.insert(5);
                tree.insert(8);
                tree.insert(11);

                List<Integer> nodesInOrder = new ArrayList<>();
                tree.traversePostOrder(nodesInOrder::add);

                assertEquals(nodesInOrder.size(), 8);

                assertEquals(nodesInOrder.get(0), 5);
                assertEquals(nodesInOrder.get(1), 8);
                assertEquals(nodesInOrder.get(2), 7);
                assertEquals(nodesInOrder.get(3), 6);
                assertEquals(nodesInOrder.get(4), 11);
                assertEquals(nodesInOrder.get(5), 18);
                assertEquals(nodesInOrder.get(6), 10);
                assertEquals(nodesInOrder.get(7), 9);
            }
        }

    }

}