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
}
