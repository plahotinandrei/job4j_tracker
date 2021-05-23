package ru.job4j.ex;

public class Fact {
    public static void main(String[] args) {
        int fact = new Fact().calc(2);
        System.out.println(fact);
    }

    public int calc(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("argument cannot be less than 0");
        }
        int rsl = 1;
        for (int index = 1; index <= n; index++) {
            rsl *= index;
        }
        return rsl;
    }
}