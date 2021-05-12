package ru.job4j.tracker;

public class StartUI {
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.add(new Item("First item"));
        tracker.add(new Item("Second item"));
        tracker.add(new Item("Third item"));
        Item firstItem = tracker.findById(1);
        Item secondItem = tracker.findById(2);
        Item thirdItem = tracker.findById(3);
        System.out.println(firstItem.getName());
        System.out.println(secondItem.getName());
        System.out.println(thirdItem.getName());
    }
}
