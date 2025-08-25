package java_core_basic.string;

public class StringDemo {
    public static void main(String[] args) {
        // 1. String là immutable (bất biến)
        String s1 = "Hello";
        s1.concat(" World");
        System.out.println("s1 = " + s1); // "Hello" vì s1 không thay đổi
        String s2 = s1.concat(" Java");
        System.out.println("s2 = " + s2); // "Hello Java"

        // 2. String pool vs new String()
        String literal1 = "Welcome";
        String literal2 = "Welcome";
        System.out.println("literal1 == literal2: " + (literal1 == literal2)); // true

        String obj1 = new String("Welcome");
        System.out.println("literal1 == obj1: " + (literal1 == obj1)); // false

        // Giải thích:
        // literal1, literal2 trỏ cùng chuỗi trong pool
        // obj1 là object trong heap, tham chiếu đến chuỗi "Welcome" trong pool

        // 3. Giải thích nếu có "con trỏ như C"
        // s1 --> object trên heap --> "Welcome" ở pool
        // s2 --> "Welcome" ở pool
        // --> *s1 là địa chỉ heap, **s1 là địa chỉ pool (nếu có pointer như C)

        // 4. String.intern()
        String s4 = new String("Hello").intern(); // ép về pool
        String s5 = "Hello";
        System.out.println("s4 == s5: " + (s4 == s5)); // true

        // 5. So sánh chuỗi
        String strA = "java";
        String strB = "JAVA";
        String strC = "java";

        System.out.println("== comparison: " + (strA == strC)); // true
        System.out.println("equals: " + strA.equals(strB)); // false
        System.out.println("equalsIgnoreCase: " + strA.equalsIgnoreCase(strB)); // true
        System.out.println("compareTo: " + strA.compareTo(strC)); // 0 (equal)
        System.out.println("compareTo: " + strA.compareTo(strB)); // >0 (so sánh theo mã Unicode)

        // 6. Các phương thức thường dùng
        String name = "thanh";
        System.out.println("Length: " + name.length());
        System.out.println("Char at 2: " + name.charAt(2));
        System.out.println("Substring (0-5): " + name.substring(0, 5));
        System.out.println("To Upper: " + name.toUpperCase());
        System.out.println("To Lower: " + name.toLowerCase());

        // 7. String là final, không thể kế thừa
        // class MyString extends String {} //Lỗi compile: Cannot inherit from final
    }
}
