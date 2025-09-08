# Factory Method Pattern
- Mục đích: Cung cấp một giao diện chung để tạo đối tượng, nhưng để các class con quyết định class nào cần được tạo.
--> Giúp tách biệt logic tạo Object ra khỏi phần sử dụng Object.

- Cách triển khai:
B1: Tạo interface, abstract class định nghĩa sản phẩm
B2: Tạo các class con implements interface.
B3: Tạo một Factory (Method/ class) có nhiệm vụ tạo ra Object phù hợp