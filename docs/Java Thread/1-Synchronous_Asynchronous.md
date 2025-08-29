# Synchronous vs Asynchronous trong Java

## 1. Khái niệm

### Synchronous (Đồng bộ)
- Là **cách thực thi các lệnh theo tuần tự**, lệnh sau chỉ bắt đầu khi lệnh trước hoàn thành.  
- Các tác vụ phải **chờ nhau**.  
- Ví dụ: gọi hàm A → hàm A hoàn thành → mới gọi hàm B.

### Asynchronous (Bất đồng bộ)
- Là **cách thực thi các lệnh không chờ nhau**, lệnh tiếp theo có thể bắt đầu ngay cả khi lệnh trước chưa hoàn thành.  
- Giúp **tăng hiệu năng**, **không block luồng chính**.  
- Ví dụ: gửi yêu cầu HTTP bất đồng bộ, xử lý callback khi nhận kết quả.

---

## 2. So sánh synchronous và asynchronous

| Tiêu chí                  | Synchronous               | Asynchronous                     |
|---------------------------|--------------------------|----------------------------------|
| Cách thực thi             | Tuần tự                  | Không tuần tự, song song         |
| Khả năng chờ              | Có, phải chờ lệnh trước  | Không, lệnh khác có thể chạy tiếp|
| Đơn giản                 | Dễ hiểu, dễ debug         | Phức tạp hơn, cần callback/Future|
| Hiệu năng                | Thấp nếu tác vụ chậm      | Cao, tận dụng concurrency        |
| Trường hợp sử dụng        | Task nhỏ, thứ tự quan trọng | Task dài, IO, network, CPU-bound|

---

## 3. Ưu và nhược điểm

### Synchronous
- **Ưu điểm:**  
  - Dễ lập trình, dễ debug  
  - Thứ tự thực thi rõ ràng
- **Nhược điểm:**  
  - Nếu tác vụ mất thời gian, luồng bị block  
  - Không tối ưu tài nguyên CPU/IO

### Asynchronous
- **Ưu điểm:**  
  - Không block luồng, nâng cao hiệu năng  
  - Thích hợp xử lý I/O, network, tác vụ song song
- **Nhược điểm:**  
  - Lập trình phức tạp hơn  
  - Cần quản lý callback, Future, Promise  
  - Debug khó hơn do không tuần tự

---

## 4. Từ khóa `synchronized` trong Java

1. **Khái niệm:**
   - `synchronized` là từ khóa dùng để **đồng bộ hóa luồng** (thread) khi truy cập tài nguyên **chia sẻ**.  
   - Đảm bảo rằng **chỉ một luồng được phép thực hiện một đoạn mã cùng lúc**.

2. **Cách dùng:**
   - Đồng bộ hóa phương thức:
     ```java
     public synchronized void increment() {
         count++;
     }
     ```
   - Đồng bộ hóa block:
     ```java
     public void increment() {
         synchronized(this) {
             count++;
         }
     }
     ```

3. **Đặc điểm:**
   - Chỉ một thread có quyền thực hiện synchronized block/phương thức cùng lúc.  
   - Ngăn ngừa **race condition** khi nhiều thread truy cập dữ liệu chung.  
   - Có thể gây **block** nếu nhiều thread chờ lock.

4. **Trường hợp sử dụng:**
   - Khi nhiều thread cùng truy cập hoặc thay đổi **biến/chung tài nguyên**.  
   - Khi muốn đảm bảo **tính toàn vẹn dữ liệu** trong ứng dụng đa luồng.

---

**Ghi chú:**
- `synchronized` là cơ chế **đồng bộ hóa trong Java**, còn Synchronous/Asynchronous là **kiểu thực thi luồng/ tác vụ**.  
- Synchronous chưa chắc là thread-safe, còn `synchronized` giúp thread-safe nhưng vẫn có thể chậm nếu lạm dụng.
