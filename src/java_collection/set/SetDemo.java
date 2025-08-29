package java_collection.set;

import java.util.*;

public class SetDemo {

    public static void main(String[] args) {

        // 1. HashSet
        System.out.println("=== HashSet Demo ===");
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Cherry");
        hashSet.add("Apple"); // phần tử trùng, sẽ không thêm
        System.out.println("HashSet: " + hashSet);

        System.out.println("Contains 'Banana'? " + hashSet.contains("Banana"));
        hashSet.remove("Banana");
        System.out.println("HashSet sau khi remove 'Banana': " + hashSet);
        System.out.println("Size: " + hashSet.size());

        // 2. LinkedHashSet
        System.out.println("\n=== LinkedHashSet Demo ===");
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(10);
        linkedHashSet.add(5);
        linkedHashSet.add(20);
        linkedHashSet.add(10); // trùng, không thêm
        System.out.println("LinkedHashSet (duy trì thứ tự chèn): " + linkedHashSet);

        linkedHashSet.remove(5);
        System.out.println("LinkedHashSet sau khi remove 5: " + linkedHashSet);

        // 3. TreeSet
        System.out.println("\n=== TreeSet Demo ===");
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Banana");
        treeSet.add("Apple");
        treeSet.add("Cherry");
        treeSet.add("Apple"); // trùng, không thêm
        System.out.println("TreeSet (tự sắp xếp tăng dần): " + treeSet);

        treeSet.remove("Banana");
        System.out.println("TreeSet sau khi remove 'Banana': " + treeSet);

        // 4. Duyệt Set với for-each
        System.out.println("\n=== Duyệt HashSet với for-each ===");
        for (String fruit : hashSet) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        System.out.println("\n=== Duyệt LinkedHashSet với Iterator ===");
        Iterator<Integer> iterator = linkedHashSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}
