package java_core_basic.oop;

//
public class DemoOOP {
    public static void main(String[] args) {
        Animal dog = new Dog("Buddy", 3);
        dog.sound(); // polymorphism
        dog.breathe(); // Inherited non-abstract method
        dog.showAge(); // final method (kế thừa được)

        Animal cat = new Cat("Kitty", 2);
        cat.sound(); // polymorphism
        // overloading
        dog.feed();
        dog.feed("meat");

        ((Mammal) dog).move(); // gọi default method trong interface mammmal

        // static method hiding
        Parent.greet();
        Child.greet();

        // This and super
        new Child("ChildObject");

        // Access Modifier Demo
        AccessModifierDemo demo = new AccessModifierDemo();
        demo.publicMethod();
        // demo.privateMethod(); // không gọi được
        demo.protectedMethod(); // same package
        demo.defaultMethod(); // same package
    }
}
