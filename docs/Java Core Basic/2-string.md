# String trong java là một đối tượng đại diện cho một chuỗi các kí tự liên tiếp.

# 1. Tìm hiểu về các đặc điểm và tính chất của String trong Java.
- Tính bất biến (Immutable): Một khi chuỗi được tạo ra, nội dung của nó không thể thay đổi.
Ví dụ: String s = "Hello" sau đó s.concat("World) thì khi in s vẫn là Hello. Vì khi thay đối String, JVM sẽ tạo ra 1 đối tượng chuỗi mới và chuỗi ban đầu không hề thay đổi. Nếu bạn cố gán s cho một đối tượng string khác thì thực chất chỉ là thay đổi tham chiếu trỏ tới đối tượng khác, "Hello" vẫn tồn tại ở pool.
- String pool: Chuỗi literal trong java được lưu ở bộ nhớ đặc biệt đó là String pool, khi khởi tạo String theo literal thì JVM sẽ kiểm tra trong pool nếu đã có nó trả về tham chiếu để tối ưu hiệu suất, tránh tạo nhiều string có cùng nội dung. 
- Hỗ trợ đẩy đủ các phương thức như: .length(), .charAt(i), .substring(),...
- String là final do đó không thể có lớp extends class String đảm bảo tính bất biến.
- String implements Serializable, Comparable, CharSequence. 

# 2. Có bao nhiêu cách để tạo 1 biến String.
- Có 2 cách để tạo đối tượng String đó là: String literal và dùng keyword new.
# Cách 1: String literal --> khai báo biến String và gán trị literal cho nó bên trong dấu ngoặc kép, ví dụ: String s1 = "Welcome".
--> Với cách khai báo JVM sẽ hoạt động như sau, đầu tiên JVM sẽ kiểm tra trong String constant pool (vùng nhớ nằm trong heap) để kiểm tra xem dữ liệu chuỗi literal "Welcome" đã tồn tại trong đó chưa. Nếu chưa nó sẽ tạo "Welcome" và gán địa chỉ cho biến s1. Lúc này biến s1 tham chiếu tới vùng nhớ chứa chuỗi "Welcome" trong String constant pool. 
--> Do đó nếu sau đó ta tạo 1 biến s2 = "Welcome" thì JVM sẽ kiểm tra và thấy welcome đã có trong string constant pool, lúc này nó gán ngay địa chỉ cho biến s2. Khi đó s1, s2 sẽ cùng trỏ tới 1 vùng nhớ chứa literal Welcome.

# Cách 2: Dùng new Keyword để khởi tạo đối tượng lớp String.
--> String s1 = new String("Welcome");
Khi khởi tạo như sau, Nếu khởi tạo constructor và dùng literal (String("Welcome")) như trên thì JVM dựa vào literal nó sẽ kiểm tra pool nếu chưa có nó tạo giá trị literal ("Welcome") trong pool. Sau đó nó tạo 1 object bên ngoài pool , bên trong heap. Khi này object tạo trong heap này sẽ tham chiếu đến địa chỉ vùng nhớ literal được tạo trong pool. Và sau cùng biến s1 sẽ lưu địa chỉ tham chiếu đến đối tượng object trong heap. 
Hay để rõ ràng ta tạo String s2 = "Welcome".  
--> Lúc này ta thấy khi so sánh s1 == s2 nó sẽ trả về false,  vì s1 đang trỏ tới đối tượng tạo trong heap, còn s2 đang trỏ tới vùng nhớ literal tạo ở pool.
Tuy nhiên theo giải thích ban đầu nếu khởi tạo String kết hợp literal thì biến s1 --> object trên heap  --> literal tạo trong pool. Khi đó giả sử java có con trỏ như C, thì khi giải tham chiếu **s1 nó sẽ trỏ đến cùng đối tượng trong pool với s2.
# 3. Tìm hiểu về String pool.
- String pool là một vùng nhớ đặc biệt trong JVM được dùng để lưu trữ các chuỗi literal. Ta có thể dùng .intern() để đưa chuỗi về pool
- Ví dụ: String s4 = new String("Hello").intern();
String s5 = "Hello";
System.out.println(s4 == s5); // trả về true.
--> method intern() trả về chuỗi tương ứng trong pool, nếu chưa có nó sẽ tạo trong pool.
# 4. Làm sao để so sánh hai chuỗi trong java.
- Java hỗ trợ các cách so sánh sau:
- So sánh bằng == (so sánh địa chỉ);
- So sánh bằng .equals() (so sánh nội dung);
- So sánh không phân biệt hoa thường .equalsIgnoreCase();
- So sánh theo thứ tự từ điển: .compareTo();

Bổ sung: Tính bất biến của String trong java:
- Ưu điểm: Giúp an toàn trong môi trường đa luồng mà không cần đồng bộ hóa. Tức cùng một giá trị String có thể chỉ lưu trữ một lần trong pool, và dùng chung cho nhiều lần mà không cần tạo mới gây lãng phí bộ nhớ.
- Nhược điểm: Ưu điểm trên cũng gây ra nhược điểm ví dụ khi in thông tin đối tượng ta cộng các thông tin này vào, nhưng String bất biến nên mỗi lần cộng nó lại tạo 1 đối tượng String với giá trị mới trong khi ta chỉ cần một kết quả duy nhất. Do đó, ra đời StringBuilder và StringBuffer. (StringBuffer an toàn trong môi trường đa luồng khi thay đổi các luồng đồng bộ theo).
- Nhầm lần: StringBuilder và StringBuffer không làm thay đổi tính bất biến của String tức nó không thay đổi String ban đầu.
- String bất biến (immutable) vì nội dung của nó được lưu trong một mảng char[] được khai báo final và không có phương thức nào thay đối trực tiếp nội dung này.
- StringBuilder/StringBuffer mutable vì nội dung được lưu trong mảng char[] có thể thay đổi được, các phương thức append(), insert() sẽ trực tiếp sửa đổi nó.

Tức khác biệt do trong class String việc lưu trữ mảng kí tự được đặt là final, nên ko thể gán lại, còn lớp StringBuilder bên trong nó không đặt final.  Và khi truyền String vào StringBuilder khi khởi tạo bên trong class StringBuilder nó tạo copy nội dung String vào mảng chuỗi mới, sau khi thao tác xong nó có hàm toString() bên trong nó new ra một String rồi gán mảng char[] thao tác sau cùng cho nó khi đó kết quả trả ra là một new String() nên bất biến.

- Tại sao cần .toString(): do khi thay đổi ta muốn kết quả là 1 String bất biến tức 
- Ví dụ: StringBuilder sb = new StringBuilder("abc");
lúc này sb vẫn là StringBuilder có thể dùng hàm để thay đổi được // someMethod(sb) // phương thức này có thể chỉnh sửa sb. --> Nội dung không ổn định.
Tuy nhiên nếu ta gọi: String result = sb.toString();
result lúc này sẽ là bấy biến, không ai có thể sửa. 


- StringBuilder hay sử dụng trong truy vấn SQL. 