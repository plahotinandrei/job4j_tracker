package ru.job4j.oop;

public class Surgeon extends Doctor {
    public Surgeon() {
    }

    public Surgeon(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
    }

    public static void main(String[] args) {
        Surgeon surgeon = new Surgeon();
        surgeon.toHeal();
    }
}
