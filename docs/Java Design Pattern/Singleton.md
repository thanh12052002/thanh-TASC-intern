# Singleton Pattern
- Mục đích: Đảm bảo rằng chỉ có một đối tượng duy nhất của một class được tạo ra trong toàn bộ ứng dụng, và cung cấp điểm truy cập toàn cục tới đối tượng đó.
- Cách triển khai:
--> B1. Đặt constructor là private để ngăn tạo mới từ bên ngoài.
--> B2. Tạo một biến static duy nhất để lưu instance.
--> B3. Cung cấp một method getInstance() static để trả về instance.