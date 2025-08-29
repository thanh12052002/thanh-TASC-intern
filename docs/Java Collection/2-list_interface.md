# List Interface trong Java

## 1. Đặc điểm của List Interface

1. **Thuộc Collection Framework:**  
   - `List` là một **interface trong Java Collection Framework**, kế thừa từ `Collection`.  

2. **Chứa các phần tử có thứ tự (Ordered Collection):**  
   - Các phần tử được lưu theo **trình tự chèn**.  
   - Có thể truy xuất bằng **chỉ số (index-based)** như mảng.  

3. **Cho phép phần tử trùng lặp (Duplicate Elements Allowed):**  
   - Khác với `Set` là **không chứa phần tử trùng**.  

4. **Cho phép thao tác linh hoạt:**  
   - Thêm (`add`), xóa (`remove`), truy xuất (`get`), thay thế (`set`) phần tử theo chỉ số.  

5. **Duyệt phần tử dễ dàng:**  
   - Hỗ trợ **for loop**, **for-each loop**, **Iterator**, **ListIterator**.  

6. **Đa dạng triển khai:**  
   - Nhiều class triển khai List, phù hợp với nhiều nhu cầu khác nhau về **hiệu năng và tính năng**.  

---

## 2. Các class triển khai từ List Interface

| Class       | Đặc điểm chính                                   | Khi nào sử dụng                                               |
|------------ |------------------------------------------------- |---------------------------------------------------------------|
| **ArrayList** | Dựa trên **mảng động**, truy cập theo index nhanh (O(1)), thêm phần tử cuối nhanh | Khi cần **truy cập ngẫu nhiên nhiều lần**, thao tác thêm/xóa ở cuối mảng |
| **LinkedList** | Dữ liệu được lưu dưới dạng **danh sách liên kết kép**, thêm/xóa ở đầu/cuối nhanh, truy cập theo index chậm (O(n)) | Khi cần **thêm/xóa nhiều** phần tử ở đầu, giữa hoặc cuối, duyệt tuần tự |
| **Vector**     | Giống ArrayList nhưng **đồng bộ hóa (synchronized)**, thread-safe | Khi cần **đa luồng** và bảo vệ dữ liệu, nhưng ít dùng vì hiệu năng kém |
| **Stack**      | Lớp kế thừa Vector, hỗ trợ **ngăn xếp LIFO** | Khi cần **thao tác ngăn xếp**, push/pop/peek |

---

## 3. So sánh và phân biệt sử dụng

| Tiêu chí                  | ArrayList                        | LinkedList                      | Vector                         | Stack                      |
|---------------------------|--------------------------------- |-------------------------------- |------------------------------- |----------------------------|
| Cấu trúc lưu trữ          | Mảng động                        | Danh sách liên kết kép          | Mảng động                      | Mảng động kế thừa Vector   |
| Truy xuất theo index      | Nhanh (O(1))                     | Chậm (O(n))                     | Nhanh (O(1))                   | Nhanh (O(1))               |
| Thêm/xóa phần tử đầu/cuối | Chậm nếu đầu/middle              | Nhanh                           | Chậm                           | Nhanh (push/pop)           |
| Đồng bộ hóa               | Không                            | Không                           | Có                             | Có                         |
| Trường hợp sử dụng        | Khi cần truy cập thêm/xóa        | Khi cần thêm/xóa nhiều          | Khi cần đa luồng               | Khi cần ngăn xếp LIFO      |
