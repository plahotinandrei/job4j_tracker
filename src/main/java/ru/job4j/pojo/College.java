package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Plahotin Andrey Anatolevich");
        student.setGroup("S-21");
        student.setEntered(new Date());
        System.out.println("name: " + student.getFullName());
        System.out.println("group: " + student.getGroup());
        System.out.println("date: " + student.getEntered());
    }
}
