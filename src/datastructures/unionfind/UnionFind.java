package datastructures.unionfind;

import java.util.*;

public class UnionFind { // Also called Disjoint-set

    private final Map<String, Integer> elementToIndex = new HashMap<>();
    private final Map<Integer, String> indexToElement = new HashMap<>();

    private final ArrayList<Integer> backingArray;
    private final ArrayList<Integer> sizes;
    private int components;

    //Complexity: O(n)
    public UnionFind(List<String> items) {
        backingArray = new ArrayList<>(items.size());
        sizes = new ArrayList<>(items.size());
        components = items.size();

        for (int i = 0; i < items.size(); i++) {
            backingArray.add(i, i);
            sizes.add(1);
            elementToIndex.put(items.get(i), i);
            indexToElement.put(i, items.get(i));
        }
    }

    //Complexity: O(1)
    public void makeSet(String item) {
        elementToIndex.put(item, backingArray.size());
        indexToElement.put(backingArray.size(), item);

        backingArray.add(backingArray.size());
    }

    //Complexity: O(a(n))
    public String find(String item) {
        Integer index = backingArray.get(elementToIndex.get(item));

        int root = findRoot(index);

        compressPathFrom(index, root);

        return indexToElement.get(root);
    }

    //Complexity: O(a(n))
    public void union(String first, String second) {
        int firstRoot = findRoot(elementToIndex.get(first));
        int secondRoot = findRoot(elementToIndex.get(second));

        if (firstRoot == secondRoot) {
            return;
        }

        if (sizes.get(firstRoot) <= sizes.get(secondRoot)) {
            backingArray.set(firstRoot, secondRoot);
            sizes.set(secondRoot, sizes.get(firstRoot) + sizes.get(secondRoot));
            sizes.set(firstRoot, 0);
        } else {
            backingArray.set(secondRoot, firstRoot);
            sizes.set(firstRoot, sizes.get(secondRoot) + sizes.get(firstRoot));
            sizes.set(secondRoot, 0);
        }


        components--;

    }

    //Complexity: O(a(n))
    public boolean areConnected(String first, String second) {
        return findRoot(elementToIndex.get(first)) == findRoot(elementToIndex.get(second));
    }

    //Complexity: O(1)
    public int components() {
        return components;
    }

    //Complexity: O(a(n))
    public int componentSize(String element) {
        return sizes.get(findRoot(elementToIndex.get(element)));
    }

    private void compressPathFrom(int index, int root) {
        while (backingArray.get(index) != root) {
            int nextIndex = backingArray.get(index);
            backingArray.set(index, root);
            index = nextIndex;
        }
    }

    private int findRoot(int index) {
        while (index != backingArray.get(index)) {
            index = backingArray.get(index);
        }
        return index;
    }

}
