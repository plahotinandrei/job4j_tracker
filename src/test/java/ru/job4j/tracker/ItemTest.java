package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class ItemTest {

    @Test
    public void whenSortAscItem() {
        Item item1 = new Item("Andrey");
        Item item2 = new Item("Petr");
        Item item3 = new Item("Ivan");
        Item item4 = new Item("Andrey");
        List<Item> items = Arrays.asList(
            item1, item2, item3, item4
        );
        Collections.sort(items, new SortAscItem());
        List<Item> expect = Arrays.asList(
                item1, item4, item3, item2
        );
        assertThat(items, is(expect));
    }

    @Test
    public void whenSortDescItem() {
        Item item1 = new Item("Andrey");
        Item item2 = new Item("Petr");
        Item item3 = new Item("Ivan");
        Item item4 = new Item("Andrey");
        List<Item> items = Arrays.asList(
                item1, item2, item3, item4
        );
        Collections.sort(items, new SortDescItem());
        List<Item> expect = Arrays.asList(
                item2, item3, item4, item1
        );
        System.out.println(items);
        assertThat(items, is(expect));
    }
}