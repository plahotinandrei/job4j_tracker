package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int y) {
        return y - x;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumAllOperation(int a) {
        return sum(a) + multiply(a) + minus(a) + divide(a);
    }

    public static void main(String[] args) {
        int result = Calculator.sum(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        int rsl1 = calculator.multiply(5);
        System.out.println(rsl1);
        int rsl2 = Calculator.minus(4);
        System.out.println(rsl2);
        int rsl3 = calculator.divide(25);
        System.out.println(rsl3);
        int rsl4 = calculator.sumAllOperation(5);
        System.out.println(rsl4);
    }
}
