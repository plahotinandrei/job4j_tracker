package ru.job4j.oop;

public class DummyDic {
    public String engToRus(String eng) {
        return "Неизвестное слово. " + eng;
    }

    public static void main(String[] args) {
        DummyDic dummy1 = new DummyDic();
        String log = dummy1.engToRus("word");
        System.out.println(log);
    }
}
