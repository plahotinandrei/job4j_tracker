package ru.job4j.oop;

public class Max {
    public int max(int first, int second) {
        return Math.max(first, second);
    }

    public int max(int first, int second, int third) {
        int tmp = max(first, second);
        return max(tmp, third);
    }

    public int max(int first, int second, int third, int fourth) {
        int tmp = max(first, second, third);
        return max(tmp, fourth);
    }
}