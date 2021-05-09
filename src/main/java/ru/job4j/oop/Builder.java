package ru.job4j.oop;

public class Builder extends Engineer {
    public Builder() {
    }

    public Builder(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
    }

    public static void main(String[] args) {
        Builder builder = new Builder();
        String name = builder.getName();
        System.out.println("name: " + name);
    }
}
