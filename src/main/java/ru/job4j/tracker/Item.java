package ru.job4j.tracker;

import lombok.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"created"})
public class Item implements Comparable<Item> {

    private int id;

    private String name;

    private LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Item item) {
        int rsl = this.name.compareTo(item.name);
        if (rsl == 0) {
            rsl = this.created.compareTo(item.created);
        }
        return rsl;
    }
}