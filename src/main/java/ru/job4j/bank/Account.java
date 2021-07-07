package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель банковского счета
 */
public class Account {
    private String requisite;
    private double balance;

    public Account(String requisite, double balance) {
        /**
         * Номер реквизита банковского счета
         */
        this.requisite = requisite;
        /**
         * Баланс банковского счета
         */
        this.balance = balance;
    }

    /**
     * Метод возвращает номер реквизита банковского счета
     * @return requisite
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * Метод принимает на вход номер реквизита
     * и записывает в поле requisite
     * @param requisite номер реквизита банковского счета
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Метод возвращает баланс банковского счета
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Метод принимает на вход баланс
     * и записывает в поле balance
     * @param balance баланс банковского счета
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Сравнивание объектов типа User осуществляется
     * по полю requisite - номер реквизита банковского счета
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
