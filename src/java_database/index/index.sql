CREATE TABLE sinh_vien (
    id BIGINT PRIMARY KEY, -- Clustered index mặc định
    ten VARCHAR(100),
    ma_lop VARCHAR(20),
    email VARCHAR(100)
);

-- Tạo Non-clustered index trên tên
CREATE INDEX idx_ten_sv ON sinh_vien(ten);


EXPLAIN SELECT * FROM sinh_vien WHERE ten = 'Nguyen Van A';
/*
Kết quả trả về gồm:

key: Tên index được sử dụng (nếu có)

type: Loại join/index scan (ref, range, ALL...)

rows: Số dòng dự đoán sẽ quét
*/