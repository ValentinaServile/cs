package datastructures.tree.binarytree.binarysearchtree.avltree;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AvlTreeTest {

    @Nested
    class Insert {

        @Test
        void insertsNewElementsRespectingAvlTreeInvariant() {
            AvlTree tree = new AvlTree();

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
            AvlTree tree = new AvlTree();

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
            AvlTree tree = new AvlTree();

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
            AvlTree tree = new AvlTree();
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
            AvlTree tree = new AvlTree();
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
            AvlTree tree = new AvlTree();
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
            AvlTree tree = new AvlTree();
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
            AvlTree tree = new AvlTree();
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
            AvlTree tree = new AvlTree();

            tree.insert(9);

            tree.remove(9);

            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseInOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 0);
        }

        @Test
        void removesNothingFromEmptyTree() {
            AvlTree tree = new AvlTree();

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
                AvlTree tree = new AvlTree();

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
                assertEquals(nodesInOrder.get(5), 11);
                assertEquals(nodesInOrder.get(6), 10);
                assertEquals(nodesInOrder.get(7), 18);
            }
        }

        @Nested
        class InOrder {

            @Test
            void allowsToTraverseNodesInOrder() {
                AvlTree tree = new AvlTree();

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
                AvlTree tree = new AvlTree();

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
                assertEquals(nodesInOrder.get(4), 10);
                assertEquals(nodesInOrder.get(5), 18);
                assertEquals(nodesInOrder.get(6), 11);
                assertEquals(nodesInOrder.get(7), 9);
            }
        }

        @Nested
        class LevelOrder {

            @Test
            void allowsToTraverseNodesByLayer() {
                AvlTree tree = new AvlTree();

                tree.insert(9);
                tree.insert(6);
                tree.insert(10);
                tree.insert(7);
                tree.insert(18);
                tree.insert(5);
                tree.insert(8);
                tree.insert(11);


                List<Integer> nodesInOrder = new ArrayList<>();
                tree.traverseLevelOrder(nodesInOrder::add);

                assertEquals(nodesInOrder.size(), 8);

                assertEquals(nodesInOrder.get(0), 9);

                assertEquals(nodesInOrder.get(1), 6);
                assertEquals(nodesInOrder.get(2), 11);

                assertEquals(nodesInOrder.get(3), 5);
                assertEquals(nodesInOrder.get(4), 7);
                assertEquals(nodesInOrder.get(5), 10);
                assertEquals(nodesInOrder.get(6), 18);

                assertEquals(nodesInOrder.get(7), 8);
            }

        }

    }
    
    @Nested
    class Rebalancing {

        @Test
        void doesNotRebalanceWhenLeftAndRightHeightDifferenceIsLessThan1() {
            AvlTree tree = new AvlTree();

            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(6);
            tree.insert(13);
            tree.insert(16);


            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseLevelOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 7);

            assertEquals(nodesInOrder.get(0), 10); //first layer

            assertEquals(nodesInOrder.get(1), 5); //second layer
            assertEquals(nodesInOrder.get(2), 15);

            assertEquals(nodesInOrder.get(3), 3); //third layer
            assertEquals(nodesInOrder.get(4), 6);
            assertEquals(nodesInOrder.get(5), 13);
            assertEquals(nodesInOrder.get(6), 16);
        }

        @Test
        void rebalancesLeftLeftCaseWhenHeightDifferenceBecomesMoreThan1() {
            AvlTree tree = new AvlTree();

            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(6);
            tree.insert(13);
            tree.insert(16);
            tree.insert(2);
            tree.insert(1); //this insert should cause the "3, 2, 1" subtree to rebalance


            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseLevelOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 9);

            assertEquals(nodesInOrder.get(0), 10); //first layer

            assertEquals(nodesInOrder.get(1), 5); //second layer
            assertEquals(nodesInOrder.get(2), 15);

            assertEquals(nodesInOrder.get(3), 2); //third layer
            assertEquals(nodesInOrder.get(4), 6);
            assertEquals(nodesInOrder.get(5), 13);
            assertEquals(nodesInOrder.get(6), 16);

            assertEquals(nodesInOrder.get(7), 1); //fourth layer
            assertEquals(nodesInOrder.get(8), 3);
        }

        @Test
        void rebalancesLeftRightCaseWhenHeightDifferenceBecomesMoreThan1() {
            AvlTree tree = new AvlTree();

            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(6);
            tree.insert(13);
            tree.insert(16);
            tree.insert(7);
            tree.insert(8); //this insert should cause the "6, 7, 8" subtree to rebalance


            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseLevelOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 9);

            assertEquals(nodesInOrder.get(0), 10); //first layer

            assertEquals(nodesInOrder.get(1), 5); //second layer
            assertEquals(nodesInOrder.get(2), 15);

            assertEquals(nodesInOrder.get(3), 3); //third layer
            assertEquals(nodesInOrder.get(4), 7);
            assertEquals(nodesInOrder.get(5), 13);
            assertEquals(nodesInOrder.get(6), 16);

            assertEquals(nodesInOrder.get(7), 6); //fourth layer
            assertEquals(nodesInOrder.get(8), 8);
        }

        @Test
        void rebalancesRightLeftCaseWhenHeightDifferenceBecomesMoreThan1() {
            AvlTree tree = new AvlTree();

            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(6);
            tree.insert(13);
            tree.insert(16);
            tree.insert(12);
            tree.insert(11); //this insert should cause the "13, 12, 11" subtree to rebalance


            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseLevelOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 9);

            assertEquals(nodesInOrder.get(0), 10); //first layer

            assertEquals(nodesInOrder.get(1), 5); //second layer
            assertEquals(nodesInOrder.get(2), 15);

            assertEquals(nodesInOrder.get(3), 3); //third layer
            assertEquals(nodesInOrder.get(4), 6);
            assertEquals(nodesInOrder.get(5), 12);
            assertEquals(nodesInOrder.get(6), 16);

            assertEquals(nodesInOrder.get(7), 11); //fourth layer
            assertEquals(nodesInOrder.get(8), 13);
        }

        @Test
        void rebalancesRightRightCaseWhenHeightDifferenceBecomesMoreThan1() {
            AvlTree tree = new AvlTree();

            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(6);
            tree.insert(13);
            tree.insert(16);
            tree.insert(17);
            tree.insert(18); //this insert should cause the "16, 17, 18" subtree to rebalance


            List<Integer> nodesInOrder = new ArrayList<>();
            tree.traverseLevelOrder(nodesInOrder::add);

            assertEquals(nodesInOrder.size(), 9);

            assertEquals(nodesInOrder.get(0), 10); //first layer

            assertEquals(nodesInOrder.get(1), 5); //second layer
            assertEquals(nodesInOrder.get(2), 15);

            assertEquals(nodesInOrder.get(3), 3); //third layer
            assertEquals(nodesInOrder.get(4), 6);
            assertEquals(nodesInOrder.get(5), 13);
            assertEquals(nodesInOrder.get(6), 17);

            assertEquals(nodesInOrder.get(7), 16); //fourth layer
            assertEquals(nodesInOrder.get(8), 18);
        }
    }
}