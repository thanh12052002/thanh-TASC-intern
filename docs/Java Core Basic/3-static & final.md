# static 
# 1. Thế nào là static
- Static là từ khóa được áp dụng cho variable, method, block (khối lệch), nested class (lớp lồng nhau). Khi khai báo với static các phần trên sẽ thuộc về lớp chứ không thuộc về đối đượng. 
# 2. Phương thức, thuộc tính khai báo bằng từ khóa static được sử dụng khi nào ?
**Sử dụng từ khóa static cho các trường hợp sau: 
- Phương thức tiện ích (Utility Methods): Các phương thức này được dùng chung cho nhiều phần trong project như validate dữ liệu, convert data từ người dùng sang Entity,..., Các phương thức này thực hiện một tác vụ mà không cần truy cập vào dữ liệu riêng của một đối tượng.
- Hằng số: static dùng để khai báo một giá trị không đổi và được chia sẻ bởi tất cả các đối tượng của lớp (được dùng kết hợp với final như final static).
- Biến đếm: Khi cần đếm số lượng đối tượng đã được tạo ra từ một lớp, hoặc theo dõi một trạng thái chung của lớp.
- Truy cập nhanh và độc lập: Khi muốn một method có thể được truy cập trực tiếp mà không cần tạo đối tượng, giúp tiết kiệm bộ nhớ và tăng hiệu quả.

# 3. Làm thế nào để truy cập được tới phương thức, thuộc tính static
Có 2 cách truy cập phương thức hoặc thuộc tính static
Cách 1: Sử dụng tên lớp.
Cách 2: Sử dụng tên đối tượng.


# final
# 1. Thế nào là final ?
- Final là từ khóa trong java được dùng để khai báo một thực thể không thể thay đổi. Nó có thể áp dụng cho biến, method, class.
# 2. Khai báo 1 biến final khác gì với static
- Final dùng để chỉ một thực thể là bất biến (không thể thay đổi giá trị sau khi gán khởi tạo), còn static để chỉ định một thực thể thuộc về lớp chứ không phải đối tượng.
- Giá trị biến final được khởi tạo 1 lần và không thể gán lại, biến static có thể thay đổi giá trị
- Bộ nhớ: Không ảnh hưởng trực tiếp đến cách lưu trữ biến. Mỗi đối tượng có một bản sao riêng của biến final. còn biến static được chia sẻ bởi tất cả các đối tượng, chỉ có một bản sao duy nhất trong bộ nhớ.
# 3. Biến final có thể chỉnh sửa không ?
- Biến được khai báo bằng final không thể chỉnh sửa sau khi nó đã được khởi tạo. Một khi được gán giá trị, giá trị đó là vĩnh viễn.
Ví dụ: final int number = 10;  number = 2 // lỗi không thể gán lại giá trị
Chú ý: Với biến tham chiếu thì giá trị được gán cho biến ở đây là địa chỉ của vùng nhớ trỏ tới đối tượng trên heap. Tức là biến đó không thể thay đổi trỏ sang vùng nhớ đối tượng khác. Nhưng bên trong đối tượng đó (các biến, method) ta có thể chỉnh sửa được
Ví dụ: 
- final StringBuilder thanh = new StringBuilder("thanh");
thanh  = new StringBuilder("World); // lỗi, không thể trỏ đến đối tượng mới.
- thanh.append("World) // hợp lệ tức nội dung bên trong đối tượng có thể thay đổi được.




