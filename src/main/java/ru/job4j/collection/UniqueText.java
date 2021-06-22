package ru.job4j.collection;

import java.util.HashSet;

public class UniqueText {
    public static boolean isEquals(String originText, String duplicateText) {
        boolean rsl = true;
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();
        /* for-each origin -> new HashSet. */
        for (String el:origin) {
            check.add(el);
        }
        /* for-each text -> hashSet.contains */
        for (String el:text) {
            if (!check.contains(el)) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }
}
