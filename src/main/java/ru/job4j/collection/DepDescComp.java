package ru.job4j.collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        List<String> list1 = Departments.fillGaps(Arrays.asList(o1));
        List<String> list2 = Departments.fillGaps(Arrays.asList(o2));
        int sortFirstDeps = list1.remove(0).compareTo(list2.remove(0));
        if (sortFirstDeps == 0) {
            if (list1.size() != list2.size()) {
                return list1.size() > list2.size() ? 1 : -1;
            } else {
                for (String dep1:list1) {
                    for (String dep2:list2) {
                        int rsl = dep1.compareTo(dep2);
                        if (dep1.compareTo(dep2) != 0) {
                            return -rsl;
                        }
                    }
                }
            }
        }
        return -sortFirstDeps;
    }
}