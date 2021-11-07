package datastructures.unionfind;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnionFindTest {

    @Nested
    class UnionAndFind {

        @Test
        void findsParentGroupRepresentativeIfNodeIsRootNode() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange", "pineapple", "strawberry", "peach"));

            assertEquals(unionFind.find("orange"), "orange");
            assertEquals(unionFind.find("apple"), "apple");
            assertEquals(unionFind.find("strawberry"), "strawberry");
        }


        @Test
        void findsParentGroupRepresentativeAfterMergingTwoElements() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange", "pineapple", "strawberry", "peach"));

            unionFind.union("orange", "pineapple");

            assertEquals(unionFind.find("orange"), "pineapple");
            assertEquals(unionFind.find("pineapple"), "pineapple");
        }


        @Test
        void findsParentGroupRepresentativeAfterMergingTwoGroups() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange", "pineapple", "strawberry", "peach"));

            unionFind.union("orange", "pineapple");
            unionFind.union("strawberry", "peach");

            unionFind.union("orange", "strawberry");

            assertEquals(unionFind.find("orange"), "peach");
            assertEquals(unionFind.find("pineapple"), "peach");
            assertEquals(unionFind.find("strawberry"), "peach");
            assertEquals(unionFind.find("peach"), "peach");
        }

        @Test
        void mergesSmallerGroupIntoTheBiggerGroup() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange", "pineapple", "strawberry", "peach"));

            unionFind.union("orange", "pineapple");
            unionFind.union("orange", "pear");

            unionFind.union("strawberry", "peach");

            unionFind.union("orange", "strawberry");

            assertEquals(unionFind.find("orange"), "pineapple");
            assertEquals(unionFind.find("pineapple"), "pineapple");
            assertEquals(unionFind.find("strawberry"), "pineapple");
            assertEquals(unionFind.find("peach"), "pineapple");
        }

    }

    @Nested
    class MakeSet {

        @Test
        void addsAnElementAddsItInItsOwnGroup() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange", "pineapple", "strawberry", "peach"));

            unionFind.union("orange", "pineapple");
            unionFind.union("strawberry", "peach");
            unionFind.union("orange", "strawberry");

            unionFind.makeSet("banana");

            assertEquals(unionFind.find("banana"), "banana");
        }
    }

    @Nested
    class AreConnected {

        @Test
        void twoRootNodesAreNotConnected() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange", "pineapple", "strawberry", "peach"));

            assertFalse(unionFind.areConnected("orange", "apple"));
        }

        @Test
        void twoNodesAreConnectedAfterMerging() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange", "pineapple", "strawberry", "peach"));

            unionFind.union("orange", "pineapple");

            assertTrue(unionFind.areConnected("orange", "pineapple"));
        }

        @Test
        void twoNodesAreConnectedAfterTheirGroupsHaveMerged() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange", "pineapple", "strawberry", "peach"));

            unionFind.union("orange", "pineapple");
            unionFind.union("strawberry", "peach");

            unionFind.union("orange", "strawberry");


            assertTrue(unionFind.areConnected("pineapple", "peach"));
        }
    }

    @Nested
    class Components {

        @Test
        void numberOfComponentsStartsAsSameNumberAsElements() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange"));

            assertEquals(unionFind.components(), 3);
        }

        @Test
        void numberOfComponentsDecreasesAfterMerging() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange"));

            unionFind.union("apple", "pear");

            assertEquals(unionFind.components(), 2);
        }

        @Test
        void numberOfComponentsIsOneAfterEverythingIsMerged() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange"));

            unionFind.union("apple", "pear");
            unionFind.union("pear", "orange");

            assertEquals(unionFind.components(), 1);
        }
    }

    @Nested
    class ComponentSize {

        @Test
        void allComponentsAreOfSizeOneInitially() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange"));

            assertEquals(unionFind.componentSize("apple"), 1);
            assertEquals(unionFind.componentSize("pear"), 1);
            assertEquals(unionFind.componentSize("orange"), 1);
        }

        @Test
        void sizeIncreasesAfterMerging() {
            UnionFind unionFind = new UnionFind(List.of("apple", "pear", "orange"));

            unionFind.union("apple", "pear");
            unionFind.union("pear", "orange");

            assertEquals(unionFind.componentSize("apple"), 3);
            assertEquals(unionFind.componentSize("pear"), 3);
            assertEquals(unionFind.componentSize("orange"), 3);
        }
    }

}