# Phân biệt Clustered Index và Non-Clustered Index
1. Clustered Index
- Là gì: Một clustered index xác định thứ tự vật lý (physical order) mà dữ liệu được lưu trữ trên đĩa. Nó giống như mục lục của một cuốn sách, nơi các trang được sắp xếp theo đúng thứ tự của mục lục.

- Giới hạn: Mỗi bảng chỉ có thể có một clustered index duy nhất.

- Cách hoạt động: Khi bạn tạo một clustered index trên một cột, các hàng dữ liệu thực tế của bảng sẽ được sắp xếp theo thứ tự của cột đó.

2. Non-Clustered Index
- Là gì: Một non-clustered index không sắp xếp dữ liệu vật lý của bảng. Nó giống như một chỉ mục (index) riêng biệt ở cuối cuốn sách, nơi nó chứa các giá trị của cột được đánh index và con trỏ (pointer) trỏ đến vị trí của dữ liệu thực tế trong bảng.

- Giới hạn: Một bảng có thể có nhiều non-clustered index.

- Cách hoạt động: Dữ liệu thực tế vẫn được lưu trữ một cách ngẫu nhiên, và non-clustered index chỉ tạo ra một cấu trúc riêng biệt để truy cập dữ liệu nhanh hơn.

## So sánh về Performance (Hiệu suất)
1. Phân biệt Clustered Index và Non-Clustered Index
- Clustered Index
Tốc độ truy vấn: Nhanh hơn khi lấy dữ liệu theo khoảng (range query) vì dữ liệu được sắp xếp gần nhau.

Tốc độ ghi/cập nhật: Chậm hơn vì việc chèn hoặc cập nhật dữ liệu có thể yêu cầu di chuyển dữ liệu vật lý để duy trì thứ tự.

Non-Clustered Index
Tốc độ truy vấn: Chậm hơn một chút so với clustered index vì phải thực hiện thêm một bước "tìm kiếm" để đi từ index đến dữ liệu thực tế.

Tốc độ ghi/cập nhật: Nhanh hơn vì chỉ cần cập nhật index, không cần di chuyển dữ liệu thực tế của bảng.

Giải thích nguyên nhân
- Clustered Index: Tốc độ truy vấn nhanh vì dữ liệu đã được sắp xếp sẵn trên đĩa, cho phép cơ sở dữ liệu đọc một cách tuần tự mà không cần nhảy qua lại. Tuy nhiên, việc ghi/cập nhật chậm hơn vì phải duy trì thứ tự vật lý đó.

- Non-Clustered Index: Tốc độ truy vấn có thêm một bước phụ là đi theo con trỏ, nên chậm hơn. Nhưng tốc độ ghi/cập nhật nhanh hơn vì không làm ảnh hưởng đến thứ tự vật lý của dữ liệu.

### Cơ sở để đánh Index
Việc đánh index nên dựa trên các yếu tố sau:

Cột được sử dụng trong mệnh đề WHERE: Đây là trường hợp phổ biến nhất.

Cột được sử dụng trong mệnh đề JOIN: Index giúp tăng tốc độ kết hợp các bảng.

Cột được sử dụng trong mệnh đề ORDER BY: Index giúp sắp xếp dữ liệu nhanh hơn.

Cột có tính duy nhất cao: Các cột như ID, email hoặc username là những ứng cử viên tốt.

Kết hợp nhiều cột: Nếu bạn thường xuyên truy vấn dựa trên nhiều cột (ví dụ: WHERE ho = 'Nguyen' AND ten = 'Van'), bạn nên tạo một index tổng hợp (composite index) trên cả hai cột đó.

Cách biết một câu Query đã sử dụng Index hay chưa
Bạn có thể sử dụng các công cụ và câu lệnh EXPLAIN (hoặc tương tự) để kiểm tra kế hoạch thực thi của một truy vấn.

MySQL: Dùng câu lệnh EXPLAIN SELECT ...

PostgreSQL: Dùng EXPLAIN ANALYZE SELECT ...

SQL Server: Dùng EXECUTE (QUERY) WITH PLAN hoặc các công cụ trực quan.

Kết quả của lệnh này sẽ cho bạn biết:

Bảng nào đã được quét (table scan).

Loại index nào đã được sử dụng (index scan, index seek).

Thứ tự các bảng được kết hợp.