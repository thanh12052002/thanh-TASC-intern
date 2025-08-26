# 1. Nêu ra các tính chất quan trọng của hướng đối tượng
- Tính đóng gói (encapsulation): Giấu chi tiết bên trong, chỉ cho phép truy cập thông qua các phương thức công khai (get/set/method).
- Tính kế thừa (Inheritance): Lớp con kế thừa từ lớp cha, tái sử dụng code.
- Tính đa hình (Polymorphism): Cùng một phương thức nhưng có thể thực hiện nhiều hành vi khác nhau tùy từng đối tượng.
- Tính trừu tượng (Abstraction): Chỉ hiển thị phần cần thiết, ẩn chi tiết logic bên trong.
# 2. Access modifier trong java có những loại nào ? Nêu đặc điểm của từng loại.
- Access modifier trong java là các từ khóa áp dụng cho variable, method, class, constructor để chỉ định pháp vi truy cập của các phần được áp dụng đó trong ứng dụng.
** Có những loại access modifier sau và đặc điểm từng loại:
- Public: Áp dụng cho class, method, constructor, variable  --> cho phép truy cập ở mọi nơi.
- Protected: Áp dụng cho method, constructor, variable (không dùng cho class top-level) --> Truy cập cùng package hoặc khác package nhưng phải là subclass.
Note: không áp dụng cho class top-level vì nếu class là protected, thì lớp con của nó không thể truy cập để kế thừa vì nó chưa là lớp con (vòng lặp mâu thuẫn).
- Default: Áp dụng cho class, method, constructor, variable --> Truy cập trong cùng package
- Private: Áp dụng cho Method, class, constructor, variable (Không áp dụng cho class top-level) --> Ý nghĩa: Chỉ truy cập trong class chứa nó. 
# 3.1 Phân biệt Abstract và Interface
Abstract và Interface là 2 khái niệm đều để đại diện cho sự trừu tượng (abstraction).
- Sự khác biệt Abstract và Interface.
--> Abstract là từ khóa (keyword) trong Java nó không phải là kiểu dữ liệu như interface. Nó dùng để đánh dấu cho class và method. (abstract class và abstract method). Không đánh dấu cho biến vì bản chất abstract đại diện cho sự trừu tượng chưa hoàn thiện, mà biến dùng để biểu thị giá trị rõ ràng không có logic cài đặt nó chứa thông tin cụ thể sẵn.
--> Interface là kiểu dữ liệu non-primitive đặc biệt (một dạng class đặc biệt) trong Java.
### sự khác nhau giữa abstract class và interface.
--> Kế thừa: (Với Abstract class) 1 lớp con chỉ kế thừa 1 abstract class. (Với Interface) 1 class có thể implement nhiều interface hoặc 1 interface có thể extends nhiều interface khác.

--> Constructor: Abstract class có constructor còn Interface thì không
--> Method: (Abstract class) --> có thể có cả phương thức abstract (bắt buộc lớp con phải triển khai cụ thể), và non-abstract. (Interface) --> Mặc đinh toàn bộ method là abstract trừ khi dùng default (dùng cho interface giúp method có phần thân) hoặc static.

--> Fields: (Abstract class: Có biến instance, trong interface chỉ cho phép public static final).

--> Mục đích sử dụng: (Abstract class dùng khi có mối quan hệ kế thừa rõ ràng, muốn chia sẻ logic chung),  còn ( Interface dùng để định nghĩa hợp đồng mà nhiều class khác nhau có thể thực hiện).
--> Tính linh hoạt:  (Abstract class kém hơn interface vì chỉ kế thừa 1 lớp còn interface hỗ trợ đa kế thừa).
--> Modifier mặc định cho method:  (Trong abstract class là protected hoặc public) còn trong Interface mặc đinh là public abstract.
# 3.2 Nêu trường hợp sử dụng cụ thể. Nếu 2 interface hoặc 1 abstract và 1 interface có 1 function cùng tên, có thể cùng hoặc khác kiểu trả về cùng được kế thừa bởi một class, chuyện gì sẽ xảy ra?
TH1: Hai Interface có cùng tên phương thức - cùng kiểu trả về
--> Không bị lỗi biên dịch vì cả method trong interface là abstract method nên Java gộp lại thành 1 method.
TH2: Hai Interface có cùng tên phương thức - khác kiểu trả về
--> Lỗi compile, vì java không biết chọn method nào, không thể resolve bằng override vì chỉ được 1 kiểu trả về.
TH3: Một abstract class và một interface cùng tên method - cùng kiểu trả về
--> Không lỗi chỉ cần override 1 lần sẽ dùng chung cho cả abstract class và interface.
TH4: Hai interface có default method trùng tên
--> Không lỗi nhưng override lại cần chỉ rõ gọi từ interface A hay B (ví dụ A.super.method());
TH5: Interface có default method, abstract class có abstract method cùng tên
--> Không lỗi nhưng bắt buộc phải override vì abstract class ưu tiên hơn --> default method bị bỏ qua.
TH6: Interface và abstract class cùng tên khác kiểu
--> Sẽ báo lỗi biên dịch trừ khi kiểu trả về có quan hệ kế thừa, lúc này nó chọn override method có kiểu là con.
# 3.3 Thế nào là Overriding và Overloading
- Overriding (Ghi đè) (Đa hình runtime): Khi một lớp con định nghĩa lại phương thức của lớp cha thỏa mãn yêu cầu sau:
    --> Cùng tên method
    --> Cùng số tham số và kiểu và thứ tự tham số.
    --> Cùng hoặc kiểu trả về có quan hệ kế thừa
    --> Phải có quan hệ kế thừa
    --> Không được giảm phạm vi truy cập của method (public --> protected) (lớp con có thể mở rộng phạm vi truy cập hơn).
- Overrloading (Nạp chồng) (Đa hình compile): Khi một lớp có nhiều method cùng tên, nhưng khác số lượng hoặc kiểu tham số. --> Giúp sử dụng một phương thức với nhiều cách truyền tham số khác nhau. phải thỏa mãn:
    --> Cùng tên
    --> Khác kiểu hoặc số lượng và thứ tự tham số
    --> Không phụ thuộc kiểu trả về và pham vi truy cập
    --> Không cần quan hệ kế thừa.

# 3.4 Một function có access modifier là private or static có thể overriding được không ?
- Không thể overriding được vì method là private sẽ không truy cập được ngoài lớp kể cả lớp con kế thừa, còn static là method thuộc về lớp không phải đối tượng nên không kế thừa để overriding được. 
- Với private Nếu viết một method cùng tên trong lớp con --> đó là một method mới hoàn toàn không phải override.
- Với static , Nếu lớp con có static cùng tên --> nó ẩn (hide) method lớp cha, không phải override.
Ví dụ 
class Parent {
    static void greet() {
        System.out.println("Hello from Parent");
    }
}

class Child extends Parent {
    static void greet() {
        System.out.println("Hello from Child");
    }
}
Parent p = new Child();
p.greet() (vì greet là static nên gọi theo kiểu dữ liệu lớp nó thuộc về in ra Hello Parent);

# 3.5 Một phương thức final có thể kế thừa được không ?
- Một phương thức final có thể kế thừa được vì final khi được dùng thì không thể override làm thay đổi method, còn method final vẫn thuộc về instance nên nó kế thừa được.
# 3.6 Phân biệt hai từ khóa This và Super
- Đối tượng trỏ đến: this trỏ đến đối tượng hiện tại (đại diện bởi object của class hiện tại khi được new), super (đối tượng cha của lớp hiện tại extends).
- Gọi constructor: This gọi constructor cùng class còn super gọi constructor lớp cha. (super() luôn phải dc gọi đầu đừng trước class con khi gọi constructor)
- Gọi phương thức: This gọi method trong cùng class, super gọi trong class cha.

