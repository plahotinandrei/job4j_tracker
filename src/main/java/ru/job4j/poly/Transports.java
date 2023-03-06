package ru.job4j.poly;

public class Transports {
    public static void main(String[] args) {
        Vehicle icarus = new Bus();
        Vehicle paz = new Bus();
        Vehicle boeing = new Plane();
        Vehicle tu = new Plane();
        Vehicle sapsan = new Train();
        Vehicle ermak = new Train();
        Vehicle[] vehicles = {icarus, paz, boeing, tu, sapsan, ermak};
        for (Vehicle vehicle:vehicles) {
            vehicle.move();
            vehicle.fuel();
        }
    }
}
