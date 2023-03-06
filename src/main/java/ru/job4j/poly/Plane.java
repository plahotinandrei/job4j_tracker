package ru.job4j.poly;

public class Plane implements Vehicle {
    @Override
    public void move() {
        System.out.println("Летает по воздуху");
    }

    @Override
    public void fuel() {
        System.out.println("Реактивное топливо");
    }
}
