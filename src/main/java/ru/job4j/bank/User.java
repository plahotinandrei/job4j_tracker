package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель пользователя банка
 */
public class User {
    /**
     * Номер пасспорта пользователя банка
     */
    private String passport;
    /**
     * Имя пользователя банка
     */
    private String username;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param passport номер пасспорта пользователя банка
     * @param username имя пользователя банка
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Метод возвращает номер пасспорта
     * @return passport номер пасспорта пользователя банка
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Метод принимает на вход номер пасспорта
     * и записывает в поле passport
     * @param passport номер пасспорта пользователя банка
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Метод возвращает имя пользователя
     * @return username имя пользователя банка
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод принимает на вход имя пользователя
     * и записывает в поле username
     * @param username имя пользователя банка
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Сравнивание объектов типа User осуществляется
     * по полю passport - номер пасспорта пользователя банка
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
