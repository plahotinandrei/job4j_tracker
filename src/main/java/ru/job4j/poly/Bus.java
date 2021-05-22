package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Автобус едет по трассе");
    }

    @Override
    public void passengers(int number) {
        System.out.println("Колличество пассажиров: " + number);
    }

    @Override
    public double refuel(double volume) {
        return 0;
    }
}
