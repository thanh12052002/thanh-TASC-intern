package java_core_basic.static_final;

public class StaticAndFinalExample {
    // 1. Static field: Biến đếm số đối tượng
    static int objectCount = 0;

    // 2. Final field: Không thể thay đổi sau khi khởi tạo
    final int ma;

    // 3. Final static constant: Hằng số
    public static final double PI = 3.14;

    // 4. static block: Khối lệch static chỉ chạy 1 lần khi class load
    static {
        System.out.println("static block: Class is loaded");
    }

    // 5. constructor - mỗi lần tạo object mới, biến static đếm tăng dần
    public StaticAndFinalExample(int id) {
        this.ma = id; // gán final biến instance
        objectCount++; // đếm số lần tạo đối tượng
        System.out.println("Object mới được tạo và số object hiện là: " + objectCount);
    }

    // 6. static ultility method - cho những phần dùng chung tái sử dụng như
    // validate, không cần tạo object
    public static boolean isValidId(int id) {
        return id > 0;
    }

    // 7. Final reference: tham chiếu không đổi, nhưng nội dung có thể thay đổi
    public void modifyObjectFinal() {
        final StringBuilder name = new StringBuilder("Thanh");
        // name = new StringBuilder("World") // Lỗi không thể gán lại biến final để tham
        // chiếu tới object mới
        name.append("Dep trai"); // Hợp lệ có thay đổi nội dung bên trong object là được gán cho biến final
        System.out.println("Modified final object: " + name);
    }

    // 8. Static nested class - Lớp tĩnh bên trong 1 class
    public static class NestedClass {
        public static void showNestedCLass() {
            System.out.println("Đây là Static Nested Class");
        }
    }

    // Main
    public static void main(String[] args) {
        // Gọi method static qua tên lớp
        System.out.println("Is ID valid" + StaticAndFinalExample.isValidId(5));

        // Goi static field qua ten class
        System.out.println("Initial object count: " + StaticAndFinalExample.objectCount);

        // Tao object --> Truy cap bien final va static
        StaticAndFinalExample obj1 = new StaticAndFinalExample(101);
        System.out.println("Object 1 ID (final) la:" + obj1.ma);

        // Thu sua mot noi dung object final
        obj1.modifyObjectFinal();

        // goi class nested
        StaticAndFinalExample.NestedClass.showNestedCLass();

        // Truy cap class thong qua instance
        System.out.println("Acess static object: " + obj1.objectCount);
    }
}
