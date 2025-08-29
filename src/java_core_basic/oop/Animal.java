package java_core_basic.oop;

public abstract class Animal {
    // fields
    protected String name;
    private int age;
    //

    // constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //

    // abstract method
    public abstract void sound();
    //

    // method có phần thân
    public void breathe() {
        System.out.println(name + "is breathing");
    }
    //

    //
    public final void showAge() { // final method không thể override được, nhưng kế thừa được
        System.out.println("name" + name);
        System.out.println("age" + age);
    }
    //

    // Method protected để sau override thử mở rộng access modifier
    protected void methodProtected() {
        System.out.println("This is method protected");
    }

    //
    // overloading
    public void feed() {
        System.out.println(name + "is eating");
    }

    public void feed(String food) {
        System.out.println(name + "is eating " + food);
    }
    //
}
