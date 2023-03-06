package ru.job4j.poly;

public class Train implements Vehicle {
    @Override
    public void move() {
        System.out.println("Передвигается по рельсам");
    }

    @Override
    public void fuel() {
        System.out.println("Электричество");
    }
}
