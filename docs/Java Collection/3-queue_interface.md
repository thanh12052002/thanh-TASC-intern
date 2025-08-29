# Queue và Deque Interface trong Java

## 1. Đặc điểm của Queue Interface

1. **Thuộc Collection Framework:**  
   - `Queue` là **interface trong Java Collection Framework**, kế thừa từ `Collection`.  

2. **Nguyên tắc hoạt động:**  
   - Thực hiện **FIFO (First In First Out)**, phần tử vào trước sẽ ra trước.  
   - Thường dùng trong các tình huống như **hàng đợi, task scheduling**.  

3. **Các phương thức chính:**  
   - `add(E e)`, `offer(E e)` → thêm phần tử  
   - `remove()`, `poll()` → lấy và loại bỏ phần tử đầu tiên  
   - `element()`, `peek()` → lấy phần tử đầu tiên nhưng không loại bỏ  

4. **Không cho phép thao tác theo index:**  
   - Khác với `List`, `Queue` chỉ thao tác **phần tử đầu/cuối**.  

---

## 2. Đặc điểm của Deque Interface

1. **Thuộc Collection Framework:**  
   - `Deque` là **Double-Ended Queue**, kế thừa từ `Queue`.  

2. **Nguyên tắc hoạt động:**  
   - Hỗ trợ thêm/xóa phần tử ở **cả hai đầu**: đầu và cuối.  
   - Có thể sử dụng như **Queue (FIFO)** hoặc **Stack (LIFO)**.  

3. **Các phương thức chính:**  
   - Thêm phần tử: `addFirst()`, `addLast()`, `offerFirst()`, `offerLast()`  
   - Lấy/xóa phần tử: `removeFirst()`, `removeLast()`, `pollFirst()`, `pollLast()`  
   - Lấy phần tử mà không xóa: `getFirst()`, `getLast()`, `peekFirst()`, `peekLast()`  

---

## 3. Các class triển khai từ Queue và Deque

| Interface | Class                 | Đặc điểm chính                                   | Khi nào sử dụng |
|-----------|---------------------  |------------------------------------------------- |----------------|
| Queue     | **LinkedList**        | Dựa trên **danh sách liên kết kép**, FIFO, có thể dùng cả Queue và Deque | Khi cần hàng đợi linh hoạt, thêm/xóa ở đầu/cuối |
| Queue     | **PriorityQueue**     | FIFO theo **độ ưu tiên (priority)**, không duy trì thứ tự chèn | Khi cần xử lý task theo độ ưu tiên |
| Deque     | **ArrayDeque**        | Dựa trên **mảng động**, thao tác cả hai đầu nhanh, không đồng bộ | Khi cần queue/stack hiệu năng cao, single-thread |
| Deque     | **LinkedList**        | Danh sách liên kết kép, hỗ trợ FIFO/LIFO       | Khi cần thêm/xóa linh hoạt ở cả hai đầu, duyệt tuần tự |

---

## 4. So sánh và phân biệt sử dụng

| Tiêu chí                  | LinkedList (Queue/Deque)      | PriorityQueue               | ArrayDeque                  |
|---------------------------|------------------------------ |---------------------------- |-----------------------------|
| Cấu trúc lưu trữ          | Danh sách liên kết kép        | Heap (mảng)                 | Mảng động                   |
| Thao tác đầu/cuối         | Nhanh O(1)                    | Chậm O(log n)               | Nhanh O(1)                  |
| Đồng bộ hóa               | Không                         | Không                       | Không                       |
| Truy xuất ngẫu nhiên      | Chậm O(n)                     | Không hỗ trợ trực tiếp      | Chậm O(n)                   |
| FIFO/LIFO                 | FIFO (Queue) hoặc LIFO (Deque)| Theo priority               | FIFO hoặc LIFO              |
| Trường hợp sử dụng        | Hàng đợi, thao tác hai đầu    | Task scheduling theo priority | Khi cần queue/stack hiệu năng cao |
