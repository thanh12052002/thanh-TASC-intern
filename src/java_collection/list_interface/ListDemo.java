package java_collection.list_interface;

import java.util.*;

public class ListDemo {

    // Phương thức hiển thị danh sách
    public static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {

        // 1. ArrayList
        System.out.println("=== ArrayList Demo ===");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Python");
        arrayList.add("C++");
        arrayList.add("Python"); // Phần tử trùng
        printList(arrayList);

        // Truy xuất và thay đổi phần tử theo index
        System.out.println("Phần tử thứ 2: " + arrayList.get(1));
        arrayList.set(2, "C#");
        System.out.println("Sau khi set: " + arrayList);

        // Xóa phần tử
        arrayList.remove("Python"); // Xóa lần xuất hiện đầu tiên
        System.out.println("Sau khi remove: " + arrayList);

        // 2. LinkedList
        System.out.println("\n=== LinkedList Demo ===");
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.addFirst(5); // Thêm đầu
        linkedList.addLast(40); // Thêm cuối
        printList(linkedList);

        linkedList.removeFirst(); // Xóa đầu
        linkedList.removeLast(); // Xóa cuối
        printList(linkedList);

        // 3. Vector
        System.out.println("\n=== Vector Demo ===");
        Vector<String> vector = new Vector<>();
        vector.add("A");
        vector.add("B");
        vector.add("C");
        printList(vector);

        // Truy cập phần tử nhanh
        System.out.println("Element at index 1: " + vector.get(1));

        // 4. Stack
        System.out.println("\n=== Stack Demo ===");
        Stack<String> stack = new Stack<>();
        stack.push("One");
        stack.push("Two");
        stack.push("Three");
        printList(stack);

        System.out.println("Top element (peek): " + stack.peek());
        System.out.println("Pop element: " + stack.pop());
        printList(stack);

        // 5. Duyệt List với Iterator
        System.out.println("\n=== Duyệt ArrayList với Iterator ===");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.print(element + " ");
        }
        System.out.println();

        // 6. Duyệt List với for-each
        System.out.println("\n=== Duyệt LinkedList với for-each ===");
        for (int num : linkedList) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
