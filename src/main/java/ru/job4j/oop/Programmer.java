package ru.job4j.oop;

public class Programmer extends Engineer {
    private String language;

    public Programmer() {
    }

    public Programmer(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
    }

    public Programmer(String name, String surname, String education, String birthday, String language) {
        super(name, surname, education, birthday);
        this.language = language;
    }

    public void writeCode() {
        System.out.println("write code");
    }

    public String getLanguage() {
        return this.language;
    }

    public static void main(String[] args) {
        Programmer programmer = new Programmer("Ivan", "Ivanov", "MGU", "03.01.1991");
        String name = programmer.getName();
        System.out.println("name: " + name);
        programmer.toDesign();
        programmer.writeCode();
        Programmer javaProgrammer = new Programmer("Denis", "Petrov", "SpbGU", "02.05.1988", "Java");
        name = programmer.getName();
        System.out.println("name: " + name);
        programmer.toDesign();
        programmer.writeCode();
        String language = javaProgrammer.getLanguage();
        System.out.println("language: " + language);
    }
}
