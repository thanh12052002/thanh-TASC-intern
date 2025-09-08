-- JOIN--
-- lấy danh sách sinh viên đã đăng ký --
SELECT sv.id, mh.ten
FROM DangKyMonHoc as dkmh
JOIN SinhVienKhoa as svk ON svk.id = dkmh.sv_khoa_id
JOIN SinhVien as sv ON sv.id = svk.sinh_vien_id
JOIN LopHocPhan as lhp ON dkmh.lop_hoc_phan_id = lhp.id
JOIN MonHocKiHoc as mhkh ON lhp.mon_hoc_ki_hoc = mhkh.id
JOIN MonHoc as mh ON mhkh.mon_hoc_id = mh.id;

-- LEFT JOIN --
-- lấy tất cả sinh viên kể cả chưa đang ký 
SELECT *
FROM SinhVien sv
JOIN SinhVienKhoa svk ON sv.id = svk.sinh_vien_id
LEFT JOIN dang_ky_mon_hoc dkmh ON dkmh.sinh_vien_khoa_id = svk.id
LEFT JOIN lop_hoc_phan lhp ON dkmh.lop_hoc_phan_id = lhp.id
LEFT JOIN mon_hoc_ki_hoc mhkh ON lhp.mon_hoc_ki_hoc_id = mhkh.id
LEFT JOIN mon_hoc mh ON mh.id = mhkh.mon_hoc_id;

-- SubQuery--
-- Lay danh sach mon sinh vien chua dang ky
SELECT sv.ten, mh1.ten_mon_hoc
FROM SinhVien sv 
JOIN  SinhVienKhoa svk ON svk.sinh_vien_id = sv.id
JOIN Khoa khoa ON svk.id = khoa.sinh_vien_khoa_id
JOIN BoMon bm ON khoa.id = bm.khoa_id
JOIN MonHoc mh1 ON bm.id  = mh1.id
WHERE mh1.id NOT IN  (
	SELECT mh2.id
    FROM DangKyMonHoc dkmh
    JOIN LopHocPhan as lhp ON lhp.id = dkmh.lop_hoc_phan_id
    JOIN MonHocKiHoc mhkh ON mhkh.id  = lhp.mon_hoc_ki_hoc_id
    JOIN MonHoc mh2 ON mh2.id = mhkh.mon_hoc_id
    WHERE dkmh.sinh_vien_id = sv.id
);

-- CTE --
-- Lay mon tien quyet-- (WITH RECURSIVE)
WITH RECURSIVE  CTE_tien_quyet AS (
	SELECT mtq.mon_id, mtq.tien_quyet_id
    FROM mon_tien_quyet as mtq
    WHERE id = 101
    
    UNION ALL
    
    SELECT mon_tien_quyet.mon_id, mon_tien_quyet.tien_quyet_id
    FROM mon_tien_quyet
    JOIN CTE_tien_quyet ON mon_tien_quyet.mon_id = CTE_tien_quyet.id
)

-- CTE (WITH) --> xu ly bai toan nhieu buoc 
-- vi du:   buoc 1:  tinh so tin chi sinh vien da dang ky trong tung  ki,  buoc 2: check sv dk  qua 18 tin chi lay ra 
-- dung subquery --
SELECT sv.id, kh.id , SUM (mh.sotinchi) as tongTinChi
FROM SinhVien sv
JOIN SinhVienKhoa svk ON svk.sinh_vien_id = sv.id
JOIN DangKyMonhoc dkmh ON dkmh.sinh_vien_khoa_id = svk.id
JOIN LopHocPhan lhp ON dkmh.lop_hoc_phan_id = lhp.id
JOIN MonHocKiHoc mhkh ON lhp.mon_hoc_ki_hoc_id = mhkh.id
JOIN MonHoc mh ON mh.id = mhkh.mon_hoc_id
JOIN KiHoc kh ON kh.id = mhkh.ki_hoc_id
GROUP BY (sv.id, kh.id)
HAVING sum(mh.soTinchi) > 18;


-- Subquery--
SELECT IdSinhVien,IdKiHoc,tongTinChi
FROM (
	SELECT sv.id AS IdSinhVien,
		   kh.id AS IdKiHoc,
           SUM(mh.soTinChi) AS tongTinChi
    FROM SinhVien sv
    JOIN SinhVienKhoa svk ON svk.sinh_vien_id = sv.id
    JOIN DangKyMonHoc dkmh ON dkmh.sinh_vien_khoa_id = svk.id
    JOIN LopHocPhan lhp ON lhp.id = dkmh.lop_hoc_phan_id
    JOIN MonHocKiHoc mhkh ON lhp.mon_hoc_ki_hoc.id = mhkh.id
    JOIN MonHoc mh ON mh.id = mhkh.mon_hoc_id
    JOIN KiHoc kh ON kh.id = mhkh.ki_hoc_id
    GROUP BY (sv.id, kh.id)
) AS TongTinChi
WHERE TongTinChi.tongTinChi > 18;

-- Tính tổng tín chỉ theo sinh viên trong từng kỳ học
WITH TinChiTheoSV AS (
  SELECT svk.sinh_vien_id AS id_sinh_vien,
         kh.id AS id_ki_hoc,
         SUM(mh.so_tc) AS tong_tin_chi
  FROM dang_ky_mon_hoc dkmh
  JOIN sinh_vien_khoa svk ON dkmh.sinh_vien_khoa_id = svk.id
  JOIN lop_hoc_phan lhp ON dkmh.lop_hoc_phan_id = lhp.id
  JOIN mon_hoc_ki_hoc mhkh ON lhp.mon_hoc_ki_hoc_id = mhkh.id
  JOIN mon_hoc mh ON mh.id = mhkh.mon_hoc_id
  JOIN ki_hoc kh ON mhkh.ki_hoc_id = kh.id
  GROUP BY svk.sinh_vien_id, kh.id
)

-- Lọc sinh viên nào đã vượt 18 tín chỉ
SELECT *
FROM TinChiTheoSV
WHERE tong_tin_chi > 18;


--  Ranking And Partition By
-- Xếp hạng sinh viên theo tổng số tín chỉ đăng ký cho từng kì học
/*
<Window_Function>() OVER (
    [PARTITION BY ...]
    [ORDER BY ...]
    [ROWS BETWEEN ...]
)

RANK() OVER (
   PARTITION BY <cột phân vùng> 
   ORDER BY <cột sắp xếp> [ASC|DESC]
)
*/

SELECT
  sv.id AS idSinhVien,
  sv.ten_sinh_vien,
  kh.id AS idKiHoc,
  kh.ten_ki_hoc,
  SUM(mh.soTinChi) AS tongTinChi,
  RANK() OVER (PARTITION BY kh.id ORDER BY SUM(mh.soTinChi) DESC) AS xepHang
FROM dang_ky_mon_hoc dkmh
JOIN sinh_vien_khoa svk ON dkmh.sinh_vien_khoa_id = svk.id
JOIN sinh_vien sv ON sv.id = svk.sinh_vien_id
JOIN lop_hoc_phan lhp ON dkmh.lop_hoc_phan_id = lhp.id
JOIN mon_hoc_ki_hoc mhkh ON lhp.mon_hoc_ki_hoc_id = mhkh.id
JOIN mon_hoc mh ON mh.id = mhkh.mon_hoc_id
JOIN ki_hoc kh ON kh.id = mhkh.ki_hoc_id
GROUP BY sv.id, sv.ten_sinh_vien, kh.id, kh.ten_ki_hoc;

-- View --
-- Nghiep vu: Thường xuyên phải lấy thông tin: Sinh viên, kỳ học, tính điểm,  --> viết sẵn câu query view để dùng sẵn
CREATE VIEW v_ket_qua_hoc_tap AS
SELECT
  sv.id AS idSinhVien,
  sv.ten_sinh_vien,
  kh.id AS idKiHoc,
  mh.id AS idMonHoc,
  mh.ten_mon,
  mh.soTinChi,
  kq.diem,
  kq.trang_thai
FROM ket_qua_hoc_tap kq
JOIN sinh_vien sv ON sv.id = kq.sinh_vien_id
JOIN mon_hoc mh ON mh.id = kq.mon_id
JOIN mon_hoc_ki_hoc mhkh ON mhkh.mon_hoc_id = mh.id
JOIN ki_hoc kh ON kh.id = mhkh.ki_hoc_id;
-----
-- Su dung View--
SELECT * FROM v_ket_qua_hoc_tap WHERE idSinhVien = 1 AND trang_thai = 'DAT';


-- Procuder--
-- Nghiep vụ 
/*
Khi sinh viên đăng ký một lớp học phần, phải kiểm tra:

✅ Lớp còn slot (sĩ số chưa đầy)

✅ Sinh viên đã qua tất cả môn tiên quyết của môn học

✅ Không trùng lịch với lớp đã đăng ký trước đó

✅ Không vượt quá tổng số tín chỉ tối đa (ví dụ: 18 TC / kỳ)

✅ Môn học chưa học hoặc chưa qua

Bổ sung: Môn phải thuộc bộ môn của khoa đào tạo 
*/

DELIMITER $$

CREATE PROCEDURE dang_ky_lop_hoc_phan (
    IN p_sinh_vien_id BIGINT,
    IN p_lop_hoc_phan_id BIGINT,
    OUT p_ket_qua VARCHAR(255)
)
BEGIN
    DECLARE v_si_so_hien_tai INT DEFAULT 0;
    DECLARE v_si_so_toi_da INT DEFAULT 0;
    DECLARE v_mon_id BIGINT;
    DECLARE v_mon_tien_quyet BIGINT;
    DECLARE v_tong_tc INT DEFAULT 0;
    DECLARE v_so_tc_mon_moi INT DEFAULT 0;
    DECLARE v_ki_hoc_id BIGINT;

    -- 1. Lấy sĩ số hiện tại
    SELECT COUNT(*) INTO v_si_so_hien_tai
    FROM dang_ky_mon_hoc
    WHERE lop_hoc_phan_id = p_lop_hoc_phan_id;

    -- 2. Lấy sĩ số tối đa và môn học
    SELECT lhp.si_so_toi_da, mhkh.mon_hoc_id, mhkh.ki_hoc_id
    INTO v_si_so_toi_da, v_mon_id, v_ki_hoc_id
    FROM lop_hoc_phan lhp
    JOIN mon_hoc_ki_hoc mhkh ON lhp.mon_hoc_ki_hoc_id = mhkh.id
    WHERE lhp.id = p_lop_hoc_phan_id;

    -- 3. Kiểm tra sĩ số
    IF v_si_so_hien_tai >= v_si_so_toi_da THEN
        SET p_ket_qua = '❌ Lớp đã đầy';
        LEAVE proc_end;
    END IF;

    -- 4. Kiểm tra đã học hoặc đã qua môn
    IF EXISTS (
        SELECT 1 FROM ket_qua_hoc_tap
        WHERE sinh_vien_id = p_sinh_vien_id AND mon_id = v_mon_id AND diem >= 5
    ) THEN
        SET p_ket_qua = '❌ Đã học hoặc đã qua môn này';
        LEAVE proc_end;
    END IF;

    -- 5. Kiểm tra môn tiên quyết chưa qua
    IF EXISTS (
        SELECT 1
        FROM mon_tien_quyet mtq
        WHERE mtq.mon_id = v_mon_id
          AND NOT EXISTS (
            SELECT 1
            FROM ket_qua_hoc_tap kq
            WHERE kq.sinh_vien_id = p_sinh_vien_id
              AND kq.mon_id = mtq.tien_quyet_id
              AND kq.diem >= 5
        )
    ) THEN
        SET p_ket_qua = '❌ Chưa qua môn tiên quyết';
        LEAVE proc_end;
    END IF;

    -- 6. Kiểm tra trùng lịch học
    IF EXISTS (
        SELECT 1
        FROM dang_ky_mon_hoc dkmh
        JOIN lop_hoc_phan lhp2 ON dkmh.lop_hoc_phan_id = lhp2.id
        JOIN lich_hoc lh1 ON lh1.lop_hoc_phan_id = lhp2.id
        JOIN lich_hoc lh2 ON lh2.lop_hoc_phan_id = p_lop_hoc_phan_id
        WHERE dkmh.sinh_vien_khoa_id IN (
            SELECT id FROM sinh_vien_khoa WHERE sinh_vien_id = p_sinh_vien_id
        )
        AND lh1.thu = lh2.thu AND lh1.kip = lh2.kip AND lh1.tuan = lh2.tuan
    ) THEN
        SET p_ket_qua = '❌ Trùng lịch học';
        LEAVE proc_end;
    END IF;

    -- 7. Tính tổng tín chỉ hiện tại trong kỳ
    SELECT COALESCE(SUM(mh.soTinChi), 0) INTO v_tong_tc
    FROM dang_ky_mon_hoc dkmh
    JOIN lop_hoc_phan lhp ON dkmh.lop_hoc_phan_id = lhp.id
    JOIN mon_hoc_ki_hoc mhkh ON lhp.mon_hoc_ki_hoc_id = mhkh.id
    JOIN mon_hoc mh ON mhkh.mon_hoc_id = mh.id
    WHERE dkmh.sinh_vien_khoa_id IN (
        SELECT id FROM sinh_vien_khoa WHERE sinh_vien_id = p_sinh_vien_id
    )
    AND mhkh.ki_hoc_id = v_ki_hoc_id;

    -- 8. Lấy tín chỉ môn đang đăng ký
    SELECT soTinChi INTO v_so_tc_mon_moi FROM mon_hoc WHERE id = v_mon_id;

    IF v_tong_tc + v_so_tc_mon_moi > 18 THEN
        SET p_ket_qua = '❌ Quá giới hạn tín chỉ';
        LEAVE proc_end;
    END IF;

    -- ✅ Cuối cùng: INSERT đăng ký
    INSERT INTO dang_ky_mon_hoc(sinh_vien_khoa_id, lop_hoc_phan_id)
    VALUES (
        (SELECT id FROM sinh_vien_khoa WHERE sinh_vien_id = p_sinh_vien_id LIMIT 1),
        p_lop_hoc_phan_id
    );

    SET p_ket_qua = '✅ Đăng ký thành công';

    -- Kết thúc thủ tục
    proc_end: BEGIN END;

END$$

DELIMITER ;


/*Minh họa java gọi sử dụng
String ketQua = jdbcTemplate.execute((Connection con) -> {
    CallableStatement cs = con.prepareCall("{call dang_ky_lop_hoc_phan(?, ?, ?)}");
    cs.setLong(1, idSinhVien);
    cs.setLong(2, idLopHocPhan);
    cs.registerOutParameter(3, Types.VARCHAR);
    cs.execute();
    return cs.getString(3);
});
*/




