package java_core_basic.oop;

//Tạo interface cho động vật có vú
public interface Mammal {
    // method default
    default void move() {
        System.out.println("Mammal is moving");
    }
    //

    // method static
    static void describe() {
        System.out.println("Nothing");
    }
    //

    // method abstract (mặc đinh)
    private void methodPrivate() {
        // method là private bắt buộc phải có phần thân được dùng để sử dụng cho các
        // phần ulity cho method static hay default tránh gây lặp lại mã
    }
    //

    void helloMamal();
}
