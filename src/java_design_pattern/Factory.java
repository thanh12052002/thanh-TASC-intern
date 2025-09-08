package java_design_pattern;

//
interface Vehicle {
    void drive();
}

class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a car");
    }
}

//
class MotorBike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a motobike");
    }
}

// Factory
abstract class VehicleFactory {
    // method factory
    public abstract Vehicle createVehicle();
}

// logic khoi tao rieng cho Car;
class CarFactory extends VehicleFactory {
    // Nếu logic new Car dài thì mỗi khi dùng new Car phải viết lại logic --> thay
    // vào đó dùng createVehicle thì mọi nơi khởi tạo chỉ cần .createVehicle();
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}

// logic khoi tao rieng cho Motobike
class MotobikeFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new MotorBike();
    }
}
//

public class Factory {
    public static void main(String args[]) {
        Vehicle car = new CarFactory().createVehicle();
        car.drive();
        // ở đây dựa vào đa hình có thể thay đổi car thành object khác mà không phải sửa
        // lại tất cả code dùng đến car khi tạo.
    }
}
