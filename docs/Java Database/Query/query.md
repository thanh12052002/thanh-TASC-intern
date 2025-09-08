# Trong một câu Query, thứ tự thực hiện các thành phần như thế nào
FROM/JOIN/ON/WHERE/GROUP BY/ HAVING/SELECT/DISTINCT/ORDER BY/ LIMIT/OFFSET (TOP)
1. FROM/JOINs: Xác định các bảng cần truy vấn và kết hợp chúng.
2. ON: Lọc các hàng trong quá trình JOIN dựa trên điều kiện ON.
3. WHERE: Lọc các hàng theo điều kiện trong WHERE sau khi đã JOIN
4. GROUP BY: nhóm các hàng sau khi lọc theo 1 tiêu chí vào cột nào đó.
5. HAVING: Sau khi nhóm có thể lọc các hàng kết quả theo điều kiện HAVING (cần bổ sung điều kiện này sẽ khác với khi dùng WHERE)
6. SELECT: chọn các cột cần lấy ra để hiển thị. Các hàm tổng hợp (SUM, COUNT,VV...) được thực hiện ở đây
7. DISTINCT: Loại bỏ các hàng trùng lặp
8. ORDER BY: Sắp xếp các hàng kết quả
9. LIMIT/OFFSET (or TOP): Hạn chế số lượng trả về.

## Các loại JOIN
JOIN được sử dụng để kết hợp các hàng từ hai hoặc nhiều bảng dựa trên một cột chung.
--> INNER JOIN: Trả về các hàng có giá trị khớp nhau ở cả hai bảng
--> LEFT JOIN: Trả về tất cả hàng bên trái, nếu giá trị khớp thì lấy giá trị hàng bên phải, ko thì để null
--> RIGHT JOIN: Ngược lại
--> FULL JOIN: Trả về mọi dòng từ cả hai bảng. Nếu khớp thì ghép, nếu không khớp thì bên còn lại là NULL.

- Giả sử dbms áp dụng thuật toán 2 vòng for cho các phần JOIN trên --> ta có tương ứng các thuật toán join sau.
1. INNER JOIN:
for (Row a : A) {
    for (Row b : B) {
        if (a.dept_id == b.dept_id) {
            result.add(combine(a, b)); // ghép A+B
        }
    }
}

2. LEFT JOIN:
for (Row a : A) {
    boolean matched = false;
    for (Row b : B) {
        if (a.dept_id == b.dept_id) {
            result.add(combine(a,b)); // ghép A+B
            matched = true;
        }
    }
    if (!matched) {
        result.add(combine(a,null)); // A không khớp → B = null
    }
}

3. FULL JOIN

List<ResultRow> result = new ArrayList<>();
boolean[] matchedB = new boolean[B.size()];

// Duyệt A
for (Row a : A) {
    boolean matched = false;
    for (int j = 0; j < B.size(); j++) {
        Row b = B.get(j);
        if (a.dept_id == b.dept_id) {
            result.add(combine(a, b));
            matchedB[j] = true;
            matched = true;
        }
    }
    if (!matched) {
        result.add(combine(a, null));
    }
}

// Duyệt lại B để tìm dòng chưa khớp
for (int j = 0; j < B.size(); j++) {
    if (!matchedB[j]) {
        result.add(combine(null, B.get(j)));
    }
}

### Subquery
- Subquery là một câu truy vấn SELECT được viết bên trong một câu truy vấn khác.
- Giá trị trả về: (1 row hoặc nhiều row) và được dùng làm điều kiện, bảng tạm, hoặc giá trị tính toán cho câu truy vấn bên ngoài.
- Có thể đặt trong WHERE, FROM, SELECT, HAVING


#### CTE
- CTE có thể coi là trường hợp đặc biệt của subquery, nhưng có một số điểm khác biệt:
1. Giống với Subquery
- CTE cũng tạo ra một bảng tạm mà outer query có thể dùng.
- Nó được định nghĩa trước outer query và dùng như một bảng.
- CTE có thể lồng nhiều cấp tương tự với subquery lồng nhau.
2. Khác với Subquery
- Có tên bảng tạm
- Tái sử dụng được
- Hỗ trợ đệ quy
3. Trường hợp dùng CTE
- Khi cần lọc tính toán bảng tạm trước JOIN hoặc SELECT.
- Khi query phức tạp nhiều bước
- Khi cần recursive query

##### Ranking Functions
1. ROW_NUMBER(): Gán một số duy nhất cho mỗi hàng trong một tập hợp kết quả, bắt đầu từ 1
2. RANK(): Gán cùng một thứ hạng cho các hàng có cùng giá trị. Thứ hạng tiếp theo bị bỏ qua.
3. DENSE_RANK(): Tương tự như RANK, nhưng không bỏ qua thứ hạng tiếp theo.
--> Xếp hạng các row làm gì đó.

#### View
1. View là một bảng ảo dựa trên kết quả của một câu truy vấn SQL. Nó không lưu trữ dữ liệu riêng, nhưng nó có thể truy vấn giống như 1 bảng thông thường.
2. Có thể CRUD trên view tuy nhiên có những hạn chế:  
- SELECT luôn có thể. 
- INSERT, UPDATE, DELETE: Có thể nếu VIEW đơn giản (như chỉ dựa trên 1 bảng ko có JOIN, GROUP BY, hoặc hàm tổng hợp). Nếu view phức tạp, các thao tác này thường không được hỗ trợ.
3. PROCEDURE 
- PROCEDURE là một tập hợp các câu lệch SQL được lưu trữ và thực thi dưới dạng một đơn vị duy nhất trên database.
- Mục đích:
--> Đóng gói logic: Giúp tái sử dụng các đoạn mã SQL phức tạp
--> Cải thiện hiệu suất: Giảm thời gian xử lý vì PROCEDURE được biên dịch và lưu trữ trên databse.
--> Bảo mật: Cho phép người dùng thực thi các thao tác mà không cần cấp quyền trên các bảng cơ sở
--> Sử dụng trong ứng dụng JAVA:
- Gọi PROCEDURE từ java qua CallableStatement trong JDBC hoặc ORM như Hibernate/JPA