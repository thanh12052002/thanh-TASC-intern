# Data Structure and DBMS
1. Định nghĩa
# 1.1 Data Structure: 
- Data Structure: Là cách tổ chức dữ liệu trong bộ nhớ RAM của một chương trình.
- Mục đích: Tối ưu hóa việc lưu trữ, truy cập và xử lý dữ liệu trong một ứng dụng giúp chương trình chạy nhanh và hiệu quả hơn.
- Phạm vi: Chỉ tồn tại và hoạt động trong ngữ cảnh của một chương trình đang chạy. Dữ liệu sẽ biến mất khi chương trình kết thúc. --> do đó cần lưu vào file thông qua mysql (dbms).
- Cơ chế lưu trữ: Thường lưu trữ trên RAM, sử dụng các cấu trúc như mảng, danh sách liên kết, cây đồ thị, bảng băm.
- Ngôn ngữ truy cập: Dữ liệu được truy cập trực tiếp bằng các lệnh lập trình của ngôn ngữ đó (C,C++,Java,...)
- Quản lý đồng thời: Thường không có cơ chế tích hợp để quản lý nhiều người dùng truy cập cùng lúc. Cần phải tự lập trình (như Lock trong đa luồng).
- Bảo toàn dữ liệu (ACID): Không có cơ chế tích hợp để đảm bảo tính ACID (Atomicity, Consistency, Isolation, Durability)
- Ví dụ: Array, LinkedList, Tree, Graph, Hash table.

# 1.2 DBMS (Hệ quản trị cơ sở dữ liệu)
- Định nghĩa: Phần mềm được thiết kế để quản lý và truy cập cơ sở dữ liệu trên bộ nhớ phụ (ổ đĩa).
- Mục đích: Cung cấp cơ chế quản lý dữ liệu lớn một cách bền vững, an toàn và đồng thời cho nhiều người dùng.
- Phạm vi: Dữ liệu được lưu trữ trên ổ đĩa và tồn tại vĩnh viễn ngay cả khi chương trình hoặc máy chủ tắt.
- Cơ chế lưu trữ: Lưu trên ổ đĩa cứng (HHD/SSD) theo các dạng file. Sử dụng các kỹ thuật để quản lý và truy cập như buffer pool, index (B-Tree), quản lý trang page.
- Ngôn ngữ lập trình: Dữ liệu được truy cập bằng ngôn ngữ SQL.
- Có các cơ chế phức tạp như locking và MVCC để đảm bảo nhiều người dùng có thể đọc và ghi dữ liệu mà không gây ra xung đột.
- Bảo toàn dữ liệu (ACID): Bảo toàn dữ liệu ngay cả khi lỗi hệ thốn, mất điện.
- Ví dụ: MySQL, PostgreSQL,..

# Buffer pool:
- Buffer pool bản chất là một cấu trúc dữ liệu. Nó là một vùng bộ nhớ cache được DBMS quản lý trong RAM. Mục đích để lưu trữ tạm thời các page, chỉ mục được truy cập gần đây từ ổ đĩa, giúp giảm thiểu số lần phải xuống disk lấy lên.

- Trong Buffer pool, DBMS sử dụng các cấu trúc dữ liệu khác nhau để quản lý hiệu quả các page:

1. Bảng băm (Hash Table): Dùng để tra cứu nhanh các trang dữ liệu đã có trong buffer pool. Khi một truy vấn cần một trang dữ liệu, DBMS sẽ dùng ID của trang đó (Ví dụ: Số trang và ID file) để tra cứu trong bảng băm. Nếu tìm thấy, DBMS sẽ truy cập trực tiếp trang đó trong RAM mà không cần đọc từ ổ đĩa.

2. Danh sách liên kết (Linked List) hoặc (Tree): Dùng để quản lý các trang trong buffer pool theo một quy tắc nhất định, chẳng hạn như:
- Các trang được sử dụng gần đây nhất sẽ được giữ lại, và những trang ít được sử dụng nhất sẽ bị loại bỏ khi cần không gian mới.

# Mối quan hệ tổng thể:
- DBMS: là một ứng dụng phần mềm
- Buffer pool: là một thành phần quan trọng trong DBMS
- Buffer pool: Được xây dựng và quản lý bằng cách sử dụng nhiều cấu trúc dữ liệu khác nhau (như bảng băm và linkedlist) để hoạt động hiệu quả.
- Cấu trúc dữ liệu này chạy trên RAM và được xử lý bởi CPU.