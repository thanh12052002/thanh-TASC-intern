package java_core_basic.datatype;

import java.util.Scanner;

public class PrimitiveAndObjectDemo {
    // Instance variables (có giá trị mặc đinh)
    int defaultInt;
    String defaultString;
    boolean defaultBoolean;

    //
    public static void main(String args[]) {
        // call constructor default
        PrimitiveAndObjectDemo demo = new PrimitiveAndObjectDemo();
        //

        // 1. Primitive lưu trữ giá trị trực tiếp
        int a = 10; // Biến a có data type là primitive và lưu trữ giá trị 10 bên trong ô nhớ.
        int b = a; // copy giá trị của a cho b;
        b = 20;
        System.out.println("a = " + a); // 10
        System.out.println("b = " + b); // 20
        //

        // 2. Object lưu trữ địa chỉ
        Integer objA = new Integer(10);
        Integer objB = objA; // copy địa chỉ cho biến objB, B và A trỏ cùng 1 địa chỉ trong heap.
        objB = 20;
        System.out.println("objA = " + objA); // 10
        System.out.println("objB = " + objB); // 20
        /*
         * objA và objB cùng trỏ vào địa chỉ heap khi gán ở bên trên nhưng sau khi objB
         * = 20 thì giá trị objA không bị thay đổi.
         * là vì java đang tự động autoboxing khi gán objB = 20, nó sẽ làm là objB =
         * Integer.valueOf(20); khi này objB được gán và
         * trỏ tới 1 đối tượng mới. Do đó thay đổi objB không còn ảnh hưởng tới objA.
         */
        //

        // 3. Boxing / Unboxing
        int primitiveValue = 100;
        Integer boxed = primitiveValue; // auto-boxing
        int unboxed = boxed; // auto-unboxing
        System.out.println("Boxed = " + boxed + ", Unboxed = " + unboxed);

        // 4. So sánh primitive và wrapper
        Integer w1 = 10;
        int p1 = 10;
        System.out.println("w1 == p1: " + (w1 == p1)); // true (unboxing w1 để so sánh)

        // 5. Khởi tạo rõ ràng
        long longNumber = 123456789L;
        // int tooBig = 2147483648; //Lỗi: vượt quá int (phải thêm 'L' để là long)

        // 6. Object gán bằng null
        String s1 = null;

        // 7. Object gán bằng new
        Scanner scanner = new Scanner(System.in); // đúng constructor

        // 8. Gán từ object khác
        String s2 = "hello";
        String s3 = s2; // OK
        // s3 = scanner; // Lỗi: không cùng kiểu

        // 9. Biến local không có giá trị mặc định
        int localVar;
        // System.out.println(localVar); // Lỗi: chưa khởi tạo

        // 10. Instance variable có giá trị mặc định
        System.out.println("defaultInt = " + demo.defaultInt); // 0
        System.out.println("defaultString = " + demo.defaultString); // null
        System.out.println("defaultBoolean = " + demo.defaultBoolean); // false
    }
}
