package java_core_basic.oop;

class Parent {
    static void greet() {
        System.out.println("Hello from Parent");
    }
}

public class Child extends Parent {
    static void greet() { // hide parent's static method
        System.out.println("Hello from child");
    }

    public Child(String msg) {
        this.show(); // this dùng gọi method cùng class
        super.greet(); // gọi static từ Parent (vẫn hợp lệ nhưng gọi theo kiểu class thì rõ ràng hơn)
    }

    void show() {
        System.out.println("This is: " + this.getClass().getSimpleName());
    }
}
