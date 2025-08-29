package java_collection.queue_interface;

public class QueueDequeDemo {

    public static void main(String[] args) {

        // 1. Queue sử dụng LinkedList (FIFO)
        System.out.println("=== Queue (LinkedList) Demo ===");
        Queue<String> queue = new LinkedList<>();
        queue.add("A"); // add phần tử
        queue.add("B");
        queue.offer("C"); // offer phần tử
        System.out.println("Queue ban đầu: " + queue);

        System.out.println("Phần tử đầu tiên (peek): " + queue.peek());
        System.out.println("Loại bỏ phần tử đầu tiên (poll): " + queue.poll());
        System.out.println("Queue sau khi poll: " + queue);

        queue.remove(); // remove phần tử đầu tiên
        System.out.println("Queue sau khi remove: " + queue);

        // 2. PriorityQueue
        System.out.println("\n=== PriorityQueue Demo ===");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(50);
        priorityQueue.add(10);
        priorityQueue.add(30);
        priorityQueue.add(20);
        System.out.println("PriorityQueue (tự sắp xếp theo priority): " + priorityQueue);

        System.out.println("Phần tử đầu tiên (peek): " + priorityQueue.peek());
        System.out.println("Loại bỏ phần tử đầu tiên (poll): " + priorityQueue.poll());
        System.out.println("PriorityQueue sau poll: " + priorityQueue);

        // 3. Deque sử dụng ArrayDeque (FIFO & LIFO)
        System.out.println("\n=== Deque (ArrayDeque) Demo ===");
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("A"); // thêm đầu
        deque.addLast("B"); // thêm cuối
        deque.offerFirst("C"); // offer đầu
        deque.offerLast("D"); // offer cuối
        System.out.println("Deque ban đầu: " + deque);

        System.out.println("Lấy đầu (peekFirst): " + deque.peekFirst());
        System.out.println("Lấy cuối (peekLast): " + deque.peekLast());

        System.out.println("Loại bỏ đầu (pollFirst): " + deque.pollFirst());
        System.out.println("Loại bỏ cuối (pollLast): " + deque.pollLast());
        System.out.println("Deque sau khi poll: " + deque);

        // 4. Deque sử dụng LinkedList (FIFO & LIFO)
        System.out.println("\n=== Deque (LinkedList) Demo ===");
        Deque<Integer> dequeLinkedList = new LinkedList<>();
        dequeLinkedList.add(100);
        dequeLinkedList.addFirst(50);
        dequeLinkedList.addLast(150);
        System.out.println("Deque LinkedList: " + dequeLinkedList);

        System.out.println("Loại bỏ đầu: " + dequeLinkedList.removeFirst());
        System.out.println("Loại bỏ cuối: " + dequeLinkedList.removeLast());
        System.out.println("Deque LinkedList sau khi remove: " + dequeLinkedList);

        // 5. Duyệt Queue/Deque với for-each
        System.out.println("\n=== Duyệt Queue/Deque với for-each ===");
        for (String item : deque) {
            System.out.print(item + " ");
        }
        System.out.println();

        for (Integer item : dequeLinkedList) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}