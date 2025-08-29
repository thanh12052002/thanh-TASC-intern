# Map Interface trong Java

## 1. Đặc điểm của Map Interface

1. **Thuộc Collection Framework nhưng không kế thừa Collection**  
   - `Map` là một interface **riêng biệt** trong Java Collection Framework, dùng để lưu trữ **cặp key-value**.  

2. **Nguyên tắc hoạt động:**  
   - **Key phải là duy nhất (unique)**, **value có thể trùng lặp**.  
   - Không dựa trên index, truy xuất bằng **key**.  

3. **Các phương thức chính:**  
   - `put(K key, V value)` → thêm hoặc cập nhật key-value  
   - `get(Object key)` → lấy value theo key  
   - `remove(Object key)` → xóa key-value  
   - `containsKey(Object key)` / `containsValue(Object value)`  
   - `keySet()`, `values()`, `entrySet()` → duyệt Map  

---

## 2. Các class triển khai từ Map Interface

| Class                  | Đặc điểm chính                                  | Khi nào sử dụng |
|-----------------------|------------------------------------------------|----------------|
| **HashMap**           | Lưu trữ dựa trên **hash table**, key duy nhất, value có thể null, không duy trì thứ tự | Khi cần truy xuất key nhanh, không quan tâm thứ tự |
| **LinkedHashMap**     | Giống HashMap nhưng **duy trì thứ tự chèn** key-value | Khi cần truy xuất nhanh và **giữ thứ tự chèn** |
| **TreeMap**           | Lưu trữ theo **cây đỏ-đen**, key được sắp xếp tự động tăng dần | Khi cần **key tự sắp xếp** và tìm kiếm theo khoảng |
| **Hashtable**         | Legacy class, synchronized, key/value không null, chậm hơn HashMap | Khi cần Map **đa luồng**, đồng bộ (hiếm dùng) |
| **ConcurrentHashMap** | Thread-safe, cho phép nhiều thread truy cập đồng thời, key/value không null | Khi cần Map **đa luồng** hiệu năng cao |

---

## 3. So sánh và phân biệt sử dụng

| Tiêu chí                  | HashMap                  | LinkedHashMap             | TreeMap                     | Hashtable                  | ConcurrentHashMap        |
|---------------------------|-------------------------|---------------------------|-----------------------------|----------------------------|-------------------------|
| Thứ tự lưu trữ            | Không duy trì            | Duy trì thứ tự chèn       | Tự sắp xếp theo key         | Không duy trì              | Không duy trì            |
| Null key/value            | Key: 1 null, Value: nhiều | Key: 1 null, Value: nhiều | Key: không null, Value: nhiều | Key/Value: không null     | Key/Value: không null   |
| Đồng bộ hóa               | Không                   | Không                      | Không                        | Có (synchronized)          | Thread-safe (high perf) |
| Truy xuất phần tử          | Nhanh O(1)              | Nhanh O(1)                | Chậm hơn O(log n)            | Chậm hơn HashMap           | Nhanh, hỗ trợ đa luồng  |
| Trường hợp sử dụng         | Map general purpose      | Map cần giữ thứ tự chèn    | Map cần sắp xếp theo key    | Hiếm dùng, legacy          | Map đa luồng hiệu năng cao |
