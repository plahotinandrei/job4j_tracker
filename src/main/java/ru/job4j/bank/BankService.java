package ru.job4j.bank;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Класс описывает логику сервиса банка
 */
public class BankService {
    /**
     * Хранение пользователей банка и их счетов осуществляется
     * в коллекции типа HashMap, где объект типа User будет ключом,
     * а список счетов значением
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает пользователя банка и добавляет его в
     * коллекцию users
     * @param user пользователь банка
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод осуществляет добавление счета account
     * пользователю типа User в коллекцию users
     * @param passport номер пасспорта пользователя банка
     * @param account банковский счет пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = this.findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод ищет пользователя по номеру паспорта
     * @param passport номер пасспорта пользователя банка
     * @return user пользователь банка или null
     * если пользователь не найден
     */
    public User findByPassport(String passport) {
        return users.keySet().stream()
                .filter(u -> u.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод ищет счет пользователя по реквизитам
     * @param passport номер пасспорта пользователя банка
     * @param requisite номер реквизита банковского счета
     * @return account банковский счет пользователя
     * или null если счет не найден
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = this.findByPassport(passport);
        if (user != null) {
            return users.get(user).stream()
                    .filter(a -> a.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод для перечисления денег с одного счёта на другой счёт.
     * @param srcPassport номер пасспорта пользователя который
     * переводит деньги
     * @param srcRequisite номер счета с которого переводят деньги
     * @param destPassport номер пасспорта пользователя которому
     * переводит деньги
     * @param destRequisite номер счета на который переводят деньги
     * @param amount денежная сумма перевода
     * @return true если перевод осуществлен успешно,
     * false при неуспешном переводе
     */
    public boolean transferMoney(
        String srcPassport,
        String srcRequisite,
        String destPassport,
        String destRequisite,
        double amount
    ) {
        boolean rsl = false;
        Account srcAccount = this.findByRequisite(srcPassport, srcRequisite);
        Account destAccount = this.findByRequisite(destPassport, destRequisite);
        if (
            srcAccount != null
            && destAccount != null
            && srcAccount.getBalance() >= amount
        ) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
