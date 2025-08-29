package java_collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
 * Khi đưa một key vào HashMap, Java sẽ gọi phương thức hashCode() của Object đó để sinh key. do đó cần override lại hashCode
 */
//
class StudentNoOverride {
    String name;

    public StudentNoOverride(String name) {
        this.name = name;
    }
}

//
class StudentOverride {
    String name;

    //
    public StudentOverride(String name) {
        this.name = name;
    }

    // override equals objets để nó ko so sánh địa chỉ 2 object ra false ngay
    // override hashCode để với name có giá trị giống nhau thì nó cũng cho hashCode
    // giống nhau (trùng key trong map).
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof StudentOverride))
            return false;
        StudentOverride other = (StudentOverride) o;
        return Objects.equals(this.name, other.name);
        /*
         * Objects.equals(a,b) là static utility method, nó hoạt động giống như (a == b)
         * || (a != null && a.equals(b));
         */
    }

    // override hash code để ngăn việc nếu ko override
    // thì cho 2 Student cùng name thì hashCode vẫn khác và ra 2 key khác nhau trong
    // map
    @Override
    public int hashCode() {
        return name.hashCode(); // String có sẵn override nên .equals() của String là so sánh nội dung
    }

}

public class Main {
    public static void main(String[] args) {
        //
        System.out.println("=== Không override hashCode() và equals() ===");
        StudentNoOverride s1 = new StudentNoOverride("Thanh");
        StudentNoOverride s2 = new StudentNoOverride("Thanh");
        //
        Map<StudentNoOverride, String> map = new HashMap<>();
        map.put(s1, "Sinh Vien 1");
        map.put(s2, "Sinh Vien 2");
        //
        System.out.println("map.size() = " + map.size()); // 2
        System.out.println("map.get(s1) = " + map.get(s1)); // Sinh Vien 1
        System.out.println("map.get(s2) = " + map.get(s2)); // Sinh Vien 2
        System.out.println("s1.equals(s2) = " + s1.equals(s2)); // false

        //
        System.out.println("\n=== Có override hashCode() và equals() ===");
        StudentOverride so1 = new StudentOverride("Thanh2");
        StudentOverride so2 = new StudentOverride("Thanh2");

        Map<StudentOverride, String> map2 = new HashMap<>();
        map2.put(so1, "Sinh viên A");
        map2.put(so2, "Ghi đè Sinh viên A"); // Ghi đè key giống nhau

        System.out.println("map2.size() = " + map2.size()); // 1
        System.out.println("map2.get(so1) = " + map2.get(so1)); // Ghi đè Sinh Viên A
        System.out.println("map2.get(so2) = " + map2.get(so2)); // Ghi đè Sinh viên A
        System.out.println("so1.equals(so2) = " + so1.equals(so2)); // true
    }
}

/*
 * 1. Override equals() và hashCode() – Được gì?
 * Mục đích Ý nghĩa và lợi ích
 * ✅ So sánh theo nội dung 2 object khác vùng nhớ nhưng cùng dữ liệu vẫn được
 * coi là bằng nhau
 * ✅ Tránh dữ liệu trùng trong Set HashSet loại bỏ đúng các object logic giống
 * nhau
 * ✅ Ghi đè đúng key trong Map HashMap.put() sẽ ghi đè giá trị nếu key giống
 * nhau về mặt logic
 * ✅ Tìm kiếm đúng trong Map HashMap.get(fakeKey) sẽ tìm ra key nếu dữ liệu
 * giống nhau
 * ✅ Bảo đảm nguyên tắc của Java a.equals(b) == true ⇒ a.hashCode() ==
 * b.hashCode()
 * ❌ 2. Không override equals() và hashCode() – Điều gì xảy ra?
 * Hậu quả Diễn giải cụ thể
 * ❌ So sánh theo địa chỉ (==) 2 object giống dữ liệu vẫn bị coi là khác nếu
 * khác địa chỉ
 * ❌ Dữ liệu bị lặp trong Set HashSet cho phép chèn nhiều object "giống nhau" về
 * dữ liệu
 * ❌ Map không tìm được key đúng HashMap.get() sẽ không tìm thấy key nếu là
 * object khác dù dữ liệu giống
 * ❌ Không ghi đè được key cũ HashMap.put() sẽ tạo key mới thay vì cập nhật giá
 * trị
 * ❌ Vi phạm nguyên tắc Java equals() trả về true, nhưng hashCode() khác nhau →
 * không nhất quán
 * 📌 Kết luận:
 * Có override Không override
 * So sánh theo nội dung So sánh theo địa chỉ (==)
 * HashMap hoạt động logic đúng HashMap không tìm thấy đúng key
 * Tránh trùng dữ liệu trong Set Set vẫn lưu object trùng về logic
 * Bắt buộc khi làm key trong Map Không nên dùng object làm key
 */

/*
 * HashSet không kế thừa HashMap (is-a), mà dùng nó như một thành phần nôi bộ
 * has-a.
 * HashSet<E> thực chất là một wrapper (bao bọc) cho HashMap<E,Object>;
 * public class HashSet<E> implements Set<E> {
 * private transient HashMap<E, Object> map;
 * private static final Object PRESENT = new Object();
 * 
 * public boolean add(E e) {
 * return map.put(e, PRESENT) == null;
 * }
 * }
 * Về kỹ thuật: HashSet không override put() của HashMap
 * 
 * Mặc dù HashSet dùng HashMap bên trong, nhưng:
 * 
 * Nó không kế thừa HashMap, mà chỉ dùng nó như thành phần nội bộ
 * 
 * Hàm add() của HashSet gọi map.put(e, PRESENT)
 * 
 * Nếu put(...) trả về null → nghĩa là chưa tồn tại → thêm mới → return true
 * 
 * Nếu put(...) trả về PRESENT → đã tồn tại → không thêm → return false
 * rong Java, phương thức put(K key, V value) của Map (và các class triển khai
 * như HashMap) có kiểu trả về là V – tức giá trị (value) cũ gắn với key, nếu
 * có.
 * 
 * ✅ Cú pháp:
 * V put(K key, V value);
 * 
 * ✅ Ý nghĩa:
 * Tình huống Giá trị trả về
 * key chưa từng tồn tại trong Map null (vì không có giá trị cũ)
 * key đã tồn tại Trả về value cũ gắn với key
 */