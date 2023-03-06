package ru.job4j.poly;

public class Bus implements Vehicle {

    @Override
    public void move() {
        System.out.println("Двигается по скоростным трассам");
    }

    @Override
    public void fuel() {
        System.out.println("Бензин");
    }
}
