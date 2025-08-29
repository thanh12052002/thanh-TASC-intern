# 1. Nêu hiểu biết về kiểu mảng trong Java
1. Khái niệm về mảng trong Java
- Array trong Java là một kiểu dữ liệu đặc biệt thuộc reference type, đồng thời là một đối tượng (object) được tạo từ một class ẩn danh do JVM sinh ra. Array lưu trữ các phần tử cùng kiểu dữ liệu liên tiếp trong bộ nhớ, với số lượng cố định, và có thể chứa primitive hoặc object.
- Array trong Java chứa các giá trị nguyên thủy (primitive) hoặc các đối tượng và java cho phép tạo mảng một chiều hoặc nhiều chiều.
- Array implements các interface Serializable và Cloneable, kế thừa lớp Object
- Array trong Java dựa trên chỉ số, phần tử đầu tiên được lưu ở chỉ số 0, phần tử thứ hai ở chỉ số 1, và cứ tiếp tục như vậy.

2. Các loại mảng trong Java
# Mảng một chiều: Là một dãy tuyến tính các phần từ cùng kiểu dữ liệu
- Cú pháp khai báo (3 cách):
    --> Cách 1: dataType[] arr;
    --> Cách 2: dataType []arr;
    --> Cách 3: dataType arr[];
- Khởi tạo:
    arrayRefVar = new datatype[size];
- Khởi tạo và gán giá trị trong một dòng: ví dụ --> int arr[] = {10,20,30,40};
- Có thể duyệt mảng bằng For, For-each.

# Mảng hai chiều: Là mảng của mảng, thường dùng để lưu dữ liệu dạng hàng-cột (matrix)
- Cú pháp khai báo (4 cách):
    --> Cách 1: dataType[][] arrayRefVar;
    --> Cách 2: dataType [][]arrayRefVar;
    --> Cách 3: dataType arrayRefVar[][];
    --> Cách 4: dataType []arrayRefVar[];
- Khởi tạo: int [][] arr = new int[3][3];
- Duyệt bằng vòng lặp lồng nhau

3. Mảng jagged: Là mảng 2 chiều nhưng mỗi hàng có số cột khác nhau
- Khai báo và khởi tạo: 
    int arr[][] = new int [3][];
    arr[0] = new int[3];
    arr[1] = new int[4];
    arr[2] = new int[2];

4.  Mảng ẩn danh: Là mảng không có biến tham chiếu, thường được tạo trực tiếp khi truyền vào phương thức.
- Cú pháp:  method(new int[]{10,20,30});
- Dùng khi muốn thao tác tạm thời không cần tái sử dụng

5. Các thao tác phổ biến trong mảng
- Truy xuất mảng:  arr[index];
- Lấy độ dài mảng: arr.length;
- Duyệt mảng: for, for-each;
- Truyền mảng vào phương thức: Truyền theo tham chiếu, thay đổi bản trong phương thức sẽ có ảnh hưởng tới mảng
- Trả về mảng từ phương thức
- Sap chép mảng: System.arraycopy(src, srcPos, dest, destPos, length)
- Clone mảng: 
    . arr.clone();
    . 1D array: deep copy
    . 2D array: shallow copy (copy reference)

# 2. Dùng kiểu mảng mang lại ưu , nhược điểm gì?

1. Ưu điểm của mảng (Advantages)

    - Truy cập ngẫu nhiên (Random Access) nhanh:

    - Vì các phần tử được lưu liên tiếp trong bộ nhớ, ta có thể truy xuất trực tiếp bất kỳ phần tử nào bằng chỉ số (index) mà không cần duyệt từ đầu.

    Ví dụ: arr[4] truy xuất ngay phần tử thứ 5.

    - Tối ưu hóa code và quản lý dữ liệu:

    - Dễ dàng lưu trữ nhiều giá trị cùng kiểu trong một biến duy nhất.

    - Giúp tổ chức dữ liệu và viết code gọn gàng hơn so với khai báo từng biến riêng lẻ.

    - Hỗ trợ cho các thuật toán sắp xếp và tìm kiếm:

       Vì mảng liên tục trong bộ nhớ, việc triển khai các thuật toán như sắp xếp (sort), tìm kiếm nhị phân (binary search) dễ dàng và hiệu quả.

    - Hỗ trợ cả primitive và object:

       Java cho phép mảng chứa các kiểu dữ liệu nguyên thủy (int, float, char…) hoặc object (String, lớp tự tạo…).

    - Dễ dàng kết hợp với các cấu trúc khác và phương thức:

       Có thể truyền mảng vào phương thức, trả về mảng, sao chép và clone, giúp tái sử dụng và modular hóa code.

2. Nhược điểm của mảng (Disadvantages)

    - Kích thước cố định (Fixed Size):

    - Sau khi khai báo, không thể tăng hoặc giảm số lượng phần tử.

    - Nếu cần số lượng phần tử thay đổi, phải dùng ArrayList hoặc Collection khác.

    - Chèn/xóa phần tử kém hiệu quả:

        Nếu muốn thêm/xóa phần tử giữa mảng, cần dời các phần tử còn lại, tốn thời gian O(n).

    - Không kiểm tra kiểu nội dung nâng cao:

        Mảng chỉ đảm bảo cùng kiểu dữ liệu, nhưng không giới hạn về giá trị hợp lệ (ví dụ, không ngăn chặn giá trị âm nếu không thêm logic riêng).

    - Dễ gặp lỗi ngoài chỉ số (ArrayIndexOutOfBoundsException):

        Truy xuất phần tử vượt quá chỉ số mảng sẽ gây runtime exception.

    - Không hỗ trợ các thao tác cao cấp như Collection:

        Mảng không có các phương thức tiện ích như add(), remove(), contains ()… như trong ArrayList, HashSet.

