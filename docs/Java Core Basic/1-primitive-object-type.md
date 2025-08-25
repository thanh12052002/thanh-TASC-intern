# Kiểu dữ liệu trong java được dùng với biến để chỉ định kích thước bộ nhớ cấp phát và các giá trị khác nhau mà biến có thể lưu trữ.

# 1. Phân biệt kiểu dữ liệu nguyên thủy và kiểu dữ liệu object.
- Kiểu dữ liệu nguyên thủy là kiểu dữ liệu dùng đối với biến lưu trữ giá trị trực tiếp trong vùng nhớ. Khi đó kích thước, loại dữ liệu và vị trí lưu trữ trong bộ nhớ được chỉ định rõ tại compile-time.
- Kiếu dữ liệu đối tượng là kiểu dữ liệu dùng đối với biến lưu trữ địa chỉ tham chiếu bên trong vùng nhớ. Tức giá trị lưu trữ của nó là địa chỉ của vùng nhớ trỏ tới đối tượng nằm trong heap.

# 2. Có thể chuyển đổi giữa hai kiểu dữ liệu này không ?
- Có, ta có thể chuyển đổi giữa hai kiểu dữ liệu thông qua Boxing (auto-boxing) và unBoxing
- Boxing: chuyển từ primitive -> object, đây là quá trình java bọc giá trị của biến primitive vào trong một Object Wrapper Class. (int -> Integer, float -> Float,..).
- Unboxing: chuyển từ Object Wrapper -> primitive tương ứng.

# 3. Có thể so sánh hai kiểu dữ liệu này với nhau hay không ?
- Có thể so sánh hai kiểu dữ liệu với nhau nhưng chúng không so sánh trực tiếp như việc so sánh cùng kiểu dữ liệu (Primitive và Primitive, Object Wrapper và Object Wrapper), Khi so sánh java sẽ thực hiện ngầm định việc unboxing cho biến có kiểu dữ liệu Object Wrapper về kiểu dữ liệu Primitive tương ứng, rồi so sánh chúng với nhau.
- Nếu unboxing không phải 1 loại Wrapper class thì nó không thể unboxing sang primitve và sẽ báo lỗi tại compile-time.

# 4. Giá trị khi khởi tạo biến với hai loại kiểu dữ liệu này là gì ?
- TH1: Khởi tạo biến bằng cách gán trị rõ ràng:
--> Đối với biến kiểu primitive: Mỗi kiểu dữ liệu khi khai báo sẽ được cấp phát vùng nhớ với kích thước tương ứng, do đó việc gán giá trị khởi tạo cho biến cần phải thỏa mãn giá trị nằm trong khả năng mà vùng nhớ được cấp phép có thể chứa. Ví dụ: Đối với kiểu dữ liệu int thì bộ nhớ sẽ cấp phát vùng nhớ có kích thước 32 bit để lưu trữ, tương ứng với khoảng giá trị có thể lưu là: -2*10^9 -> 2*10^9. Nếu gán giá trị sai sẽ gây ra lỗi vào compile-time.
--> Đối với biến kiểu object: 
+, Nếu chưa muốn khởi tạo ta có thể gán cho nó giá trị là null.
+, Gán bằng từ khóa new: cần khởi tạo object theo đúng cấu trúc constructor của class (Nếu không khai báo thì sẽ là constructor mặc định không tham số).
+, Gán từ một object khác: Biến có thể nhận giá trị từ một object đã khởi tạo trước đó hoặc từ method trả về (chú ý: kiểu dữ liệu Object khi gán phải giống nhau, hoặc là kiểu con của kiểu của biến, nếu không sẽ gây ra lỗi trong compile-time).

- TH2: Khởi tạo biến với giá trị mặc định:
--> Đối với biến local (khai báo bên trong method, constructor, khối block): thì java không tự gán giá trị mặc định, bắt buộc ta cần phải gán giá trị tường minh theo TH1.
--> Đối với biến instance hoặc static java sẽ gán các giá trị mặc định như sau: Đối với kiểu dữ liệu primitive ([int,long,byte,short --> 0]; [float,double] --> 0.0, char --> '\u0000' tức kí tự null, boolean --> false). Đối với kiểu dữ liệu object giá trị khởi tạo mặc định sẽ là null.

- Một số lỗi khi khởi tạo giá trị:
    +, Gán kiểu không tương thích: String s = new Scanner(System.in);
    +, Gán primitive cho object: String s = 5 (không tự động convert như wrapper);
    +, Dùng object chưa khởi tạo hoặc khởi tạo sai constructor cũng gây ra lỗi.



