package java_core_basic.memory;

class Dog {
    private String name; // khi cấp phát sẽ nằm trên heap cùng đối tượng.
    private int age;

    //
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //
    public void suaGauGau() {
        System.out.println(ten + " sủa: Gâu gâu!");
    }
}

public class DemoMemory {
    public static void main(String[] args) {
        // Cấp phát tĩnh nằm trên stack frame khi frame được đưa vào stack
        int age = 25; // bien primitive
        String name = "thanh"; // chuỗi bất biến, lưu pool nội bộ + tham chiếu trên stack

        // gọi method (LIFO stack)
        hienThiThongTin(age, name);

        // Cấp phát động - nằm trên heap
        Dog dog = new Dog("dog", 3); // cấp phát qua new --> trên heap
        dog.suaGauGau();

        // Cấp phát động - mảng cũng nằm trên heap
        int[] mang = new int[] { 1, 2, 3 }; // mảng === object --> heap
    }

    // Stack ban đầu sẽ lưu lời gọi hàm và biến cục bộ
    public static void hienThiThongTin(int tuoi, String ten) {
        System.out.println("Họ tên: " + ten);
        System.out.println("Tuổi: " + tuoi);
    }
}
