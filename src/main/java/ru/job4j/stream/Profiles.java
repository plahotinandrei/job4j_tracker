package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {

    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map((p) -> p.getAddress())
                .collect(Collectors.toList());
    }

    public static List<Address> collectSortWithoutDuplicate(List<Profile> profiles) {
        return profiles.stream()
                .map((p) -> p.getAddress())
                .sorted((a1, a2) -> a1.getCity().compareTo(a2.getCity()))
                .distinct()
                .collect(Collectors.toList());
    }
}
