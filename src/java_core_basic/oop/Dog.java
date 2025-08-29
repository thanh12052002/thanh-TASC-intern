package java_core_basic.oop;

public class Dog extends Animal implements Mammal {
    public Dog(String name, int age) {
        // Bắt buộc phải gọi constructor lớp cha ko sẽ báo lỗi
        super(name, age);
    }

    // Bắt buộc phải override triển khai method abstract của Animal, và Mammal
    // Nếu không sẽ báo lỗi biên dịch
    @Override()
    public void sound() {
        System.out.println(name + " says woof! ");
    }

    @Override()
    public void helloMamal() {
        System.out.println("Implements abstract method");
    }
    //

    public void methodProtected() {
        System.out.println("This is method protected");
    }

}
