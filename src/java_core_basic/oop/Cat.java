package java_core_basic.oop;

public class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void sound() {
        System.out.println(name + " says Meow!");
    }
}
