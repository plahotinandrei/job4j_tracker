package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Exit Program" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindAllItems() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        Item item1 = tracker.add(new Item("item 1"));
        Item item2 = tracker.add(new Item("item 2"));
        UserAction[] actions = {
                new ShowAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        String expected = "Menu." + System.lineSeparator()
                + "0. Show all items" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator()
                + "=== Show all items ====" + System.lineSeparator()
                + item1.toString() + System.lineSeparator()
                + item2.toString() + System.lineSeparator()
                + "Menu." + System.lineSeparator()
                + "0. Show all items" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenFindByNameItems() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "item", "1"}
        );
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("item"));
        tracker.add(new Item("item 2"));
        tracker.add(new Item("item 3"));
        tracker.add(new Item("item 4"));
        Item item2 = tracker.add(new Item("item"));
        UserAction[] actions = {
                new FindByNameAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        String expected = "Menu." + System.lineSeparator()
                + "0. Find items by name" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator()
                + "=== Find items by name ====" + System.lineSeparator()
                + item1.toString() + System.lineSeparator()
                + item2.toString() + System.lineSeparator()
                + "Menu." + System.lineSeparator()
                + "0. Find items by name" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenFindByIdItems() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "1", "1"}
        );
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("item"));
        tracker.add(new Item("item 2"));
        tracker.add(new Item("item 3"));
        tracker.add(new Item("item 4"));
        UserAction[] actions = {
                new FindByIdAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        String expected = "Menu." + System.lineSeparator()
                + "0. Find item by id" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator()
                + "=== Find item by id ====" + System.lineSeparator()
                + item1.toString() + System.lineSeparator()
                + "Menu." + System.lineSeparator()
                + "0. Find item by id" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator();
        assertThat(out.toString(), is(expected));
    }
}