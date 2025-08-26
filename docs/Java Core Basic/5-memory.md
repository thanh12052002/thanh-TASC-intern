# Thế nào là cấp phát tĩnh và cấp phát động
- Cấp phát tĩnh là khi JVM xác định trước cấp phát bộ nhớ vào thời điểm compile, tức kích thước và vị trí của biến được xác định trước khi chương trình chạy thường được tạo trên stack
- Cấp phát tĩnh là khi cấp phát bộ nhớ tại thời điểm runtime, Kích thước và vị trí chỉ được xác định khi chương trình chạy, bộ nhớ cấp phát trên heap
Tức tĩnh không thay đổi được khi runtime còn động có thể thay đổi được.

# Phân biệt bộ nhớ heap và bộ nhớ stack
- Heap và Stack đều thuộc bộ nhớ Ram của máy tính và được cấp phát vùng nhớ trong runtime.
- Heap được tạo khi cấp phát động qua từ khóa new có thể chứa Object, mảng, dữ liệu động. còn Stack lưu biên cục bộ, tham số, lời gọi hàm.
- Quản lý bộ nhớ: Trong heap có GC (Garbage Collector) tự dộng dọn, còn Stack tự động theo cơ chế LIFO
- Tốc độ heap chậm hơn stack.


