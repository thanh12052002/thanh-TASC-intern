# Khi nào thì đánh Partition trong một bảng
--> xem xét việc đánh partition (phân vùng) cho một bảng khi gặp các trường hợp sau:

- Bảng có dung lượng rất lớn: Nếu bảng của bạn có hàng triệu hoặc hàng tỷ bản ghi, việc truy vấn, chèn, hay xóa dữ liệu có thể trở nên rất chậm. Phân vùng giúp chia bảng lớn thành các phần nhỏ hơn, dễ quản lý hơn.

- Dữ liệu có tính lịch sử: Nếu bạn thường xuyên truy vấn các bản ghi gần đây nhưng hiếm khi truy cập dữ liệu cũ, bạn có thể phân vùng bảng theo thời gian. Điều này giúp các truy vấn tìm kiếm dữ liệu mới nhanh hơn vì chỉ cần quét một phân vùng nhỏ thay vì toàn bộ bảng.

- Cần xóa một lượng lớn dữ liệu cũ: Thay vì chạy câu lệnh DELETE tốn kém trên hàng triệu bản ghi, bạn có thể chỉ cần xóa toàn bộ một phân vùng, một thao tác nhanh hơn rất nhiều.

- Đọc và ghi dữ liệu đồng thời: Phân vùng giúp giảm tranh chấp (contention) khi nhiều người dùng cùng truy cập vào bảng, vì họ có thể đang làm việc trên các phân vùng khác nhau.

## Lợi ích của Partition đối với truy vấn
Việc đánh partition mang lại những lợi ích đáng kể sau đây cho hiệu suất truy vấn:

Tăng tốc độ truy vấn: Khi bạn truy vấn một bản ghi hoặc một tập hợp bản ghi trong một phân vùng cụ thể, cơ sở dữ liệu chỉ cần quét một phần nhỏ của bảng thay vì toàn bộ bảng. Điều này giúp giảm đáng kể thời gian tìm kiếm.

Hiệu quả khi truy vấn dữ liệu theo khoảng thời gian: Nếu bạn phân vùng theo ngày, tháng hoặc năm, các truy vấn theo khoảng thời gian (ví dụ: "tìm tất cả các đơn hàng trong tháng 10") sẽ cực kỳ nhanh vì cơ sở dữ liệu có thể loại bỏ ngay lập tức các phân vùng không liên quan.

Cải thiện tốc độ bảo trì: Các thao tác bảo trì như xây dựng lại index hoặc sao lưu dữ liệu có thể được thực hiện trên từng phân vùng một, giúp giảm thiểu thời gian ngừng hoạt động của toàn bộ bảng.