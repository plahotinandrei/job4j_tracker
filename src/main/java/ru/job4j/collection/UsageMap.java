package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> users = new HashMap<>();
        users.put("ivanov@mail.ru", "Иванов Иван Иванович");
        users.put("petrov@gmail.com", "Петров Андрей Александрович");
        users.put("sidorov@yandex.ru", "Сидоров Денис Федорович");
        for (String key:users.keySet()) {
            String value = users.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
