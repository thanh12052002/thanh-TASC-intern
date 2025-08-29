# Set Interface trong Java

## 1. Đặc điểm của Set Interface

1. **Thuộc Collection Framework:**  
   - `Set` là một **interface trong Java Collection Framework**, kế thừa từ `Collection`.  

2. **Nguyên tắc hoạt động:**  
   - **Không cho phép phần tử trùng lặp (Unique Elements)**.  
   - Không duy trì thứ tự các phần tử (trừ một số class đặc biệt).  

3. **Các phương thức chính:**  
   - `add(E e)` → thêm phần tử  
   - `remove(Object o)` → xóa phần tử  
   - `contains(Object o)` → kiểm tra phần tử tồn tại  
   - `size()`, `isEmpty()`, `clear()`  

4. **Không index-based:**  
   - Khác với `List`, không thể truy xuất phần tử bằng chỉ số.  
   - Phải duyệt qua **Iterator** hoặc **for-each loop**.  

---

## 2. Các class triển khai từ Set Interface

| Class               | Đặc điểm chính                                    | Khi nào sử dụng |
|--------------------|--------------------------------------------------|----------------|
| **HashSet**        | Lưu trữ dựa trên **hash table**, không duy trì thứ tự, phần tử duy nhất | Khi cần lưu trữ phần tử **unique**, truy cập nhanh, không quan tâm thứ tự |
| **LinkedHashSet**  | Giống HashSet nhưng **duy trì thứ tự chèn**, sử dụng danh sách liên kết bên trong | Khi cần lưu phần tử **unique** và duy trì **thứ tự chèn** |
| **TreeSet**        | Lưu trữ theo **cây đỏ-đen (Red-Black Tree)**, tự sắp xếp tăng dần, unique | Khi cần **sắp xếp tự động** và loại bỏ phần tử trùng |

---

## 3. So sánh và phân biệt sử dụng

| Tiêu chí                  | HashSet                      | LinkedHashSet                 | TreeSet                      |
|---------------------------|-----------------------------|-------------------------------|-------------------------------|
| Thứ tự lưu trữ            | Không duy trì               | Duy trì thứ tự chèn            | Tự sắp xếp theo tăng dần     |
| Truy xuất phần tử         | Nhanh O(1)                  | Nhanh O(1)                     | Chậm hơn O(log n)            |
| Cho phép null             | Có (1 phần tử)              | Có (1 phần tử)                 | Không (gây NullPointerException) |
| Sử dụng                   | Khi cần **unique** và **hiệu năng cao** | Khi cần **unique + thứ tự chèn** | Khi cần **unique + sắp xếp tự động** |
