package datastructures.list.arraylist;

public class Main {

    public static void main (String[] args) {
        ArrayList arrayList = new ArrayList();

        System.out.println("==========Addition==========");
        arrayList.add("bla");
        arrayList.add("bla");
        arrayList.add("bla");
        arrayList.add("bla");
        arrayList.add("bla");
        arrayList.add("bla");
        arrayList.add("bla");
        System.out.println(arrayList);

        System.out.println("==========Addition at index==========");
        arrayList.add("miao", 5);
        System.out.println(arrayList);
        arrayList.add("miao", 6);
        System.out.println(arrayList);

        System.out.println("==========Size==========");
        System.out.println(arrayList.size());

        System.out.println("==========Retrieval==========");
        System.out.println(arrayList.get(4));
        System.out.println(arrayList.get(5));

        System.out.println("==========Removal by index==========");
        arrayList.remove(4);
        arrayList.remove(5);
        System.out.println(arrayList);

        System.out.println("==========Removal by element==========");
        arrayList.add("REMOVEME");
        arrayList.remove("REMOVEME");
        System.out.println(arrayList);

        System.out.println("==========Index of==========");
        System.out.println(arrayList.indexOf("bla"));
        System.out.println(arrayList.indexOf("miao"));
        System.out.println(arrayList.indexOf("nonexistent"));

        System.out.println("==========Contains==========");
        System.out.println(arrayList.contains("bla"));
        System.out.println(arrayList.contains("miao"));
        System.out.println(arrayList.contains("nonexistent"));
    }
}
