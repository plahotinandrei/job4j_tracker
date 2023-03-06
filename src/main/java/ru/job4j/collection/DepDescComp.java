package ru.job4j.collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        List<String> list1 = Arrays.asList(o1.split("/"));
        List<String> list2 = Arrays.asList(o2.split("/"));
        int rsl = list2.get(0).compareTo(list1.get(0));
        return rsl == 0 ? o1.compareTo(o2) : rsl;
    }
}