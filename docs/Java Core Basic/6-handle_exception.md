# Phân biệt throw và throws
- Mục đích sử dụng:
    --> throw: Dùng để ném ra một ngoại lệ cụ thể nằm bên trong phương thức
    --> throws: Dùng để khai báo rằng phương thức có thể ném ra ngoại lệ trong phần khai báo phương thức
- Đối tượng đi kèm:
    --> throw: Theo sau là một đối tượng exception được tạo ra (Ví dụ new NullPointException());
    --> throws: Theo sau là tên lớp exception (không có new).
- Số lượng exception:
    --> throw: Chỉ ném ra được 1 ngoại lệ tại 1 thời điểm
    --> throws: Có thể khai báo nhiều ngoại lệ, phân tách bằng dấu phẩy.
- Tác động tới xử lý ngoại lệ:
    --> throw: Kích hoạt luồng xử lý ngoại lệ, có thể làm chương trình dừng nếu không được bắt.
    --> throws: Không gây lỗi, chỉ cảnh báo để đối tượng gọi tới phương thức phải xử lý khi dùng.

# Thế nào là checked và unchecked exception
- Checked Exception: 
    1. Là ngoại lệ được kiểm tra tại thời điểm biên dịch (compile-time).

    2. Trình biên dịch bắt buộc bạn phải xử lý bằng try-catch hoặc khai báo throws.

    3. Thường xảy ra khi làm việc với các tác vụ bên ngoài như: đọc/ghi file, kết nối cơ sở dữ liệu, xử lý mạng.

    4. Các lớp kế thừa từ Exception (trừ RuntimeException) là checked.

- Unchecked Exception:
    1. Là ngoại lệ không bị kiểm tra tại thời điểm biên dịch, chỉ phát sinh khi chương trình đang chạy (runtime).

    2. Không bắt buộc phải xử lý hoặc khai báo.

    3. Thường do lỗi logic hoặc sai sót của lập trình viên như: chia cho 0, truy cập null, vượt quá chỉ số mảng.

    4. Các lớp kế thừa từ RuntimeException là unchecked.

# Khác nhau giữa try-catch và try-with-resources
1. Về mục đích sử dụng: 
- Try-catch:  Dùng để bắt và xử lý ngoại lệ xảy ra trong khối try.
- Try-with-resources: Dùng để vừa xử lý ngoại lệ, vừa tự động đóng tài nguyên (như file, stream, connection).

2. Quản lý tài nguyên:
- Try-catch: Phải đóng tài nguyên thủ công bằng cách gọi .close() trong finally.
- Try-with-resources: Tự động đóng tài nguyên sau khi khối try kết thúc, dù có lỗi hay không.

3. Yêu cầu đối tượng:
- Try-catch: Có thể dùng với bất kỳ đối tượng nào
- Try-with-resources: Chỉ dùng với đối tượng triển khai interface AutoCloseable (ví dụ: FileReader, BufferedReader, Connection...).

4. Độ an toàn và ngắn gọn:
- Try-catch: Dễ quên đóng tài nguyên, dễ gây rò rỉ bộ nhớ. Code dài hơn.
- Try-with-resources: An toàn, gọn gàng, dễ bảo trì, ưu tiên dùng trong xử lý IO.

# Làm thế nào để tạo được 1 custom exception ?

1. Tạo một class mới kế thừa Exception hoặc RuntimeException:

Kế thừa Exception -> tạo checked exception

Kế thừa RuntimeException -> tạo unchecked exception

2. Tạo constructor trong class để truyền thông điệp lỗi (message) nếu cần:

Constructor có thể gọi super(message) để gán thông điệp lỗi từ lớp cha.

3. Sử dụng throw để ném custom exception ở nơi cần thiết:

Dùng throw new YourCustomException(...) trong logic xử lý lỗi.

4. Xử lý custom exception bằng try-catch hoặc khai báo throws:

Có thể dùng try-catch để xử lý tại chỗ, hoặc dùng throws để truyền tiếp ngoại lệ cho nơi gọi



