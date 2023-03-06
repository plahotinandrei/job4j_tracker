package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        Store tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(out));
        actions.add(new Exit());
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceAction(out));
        actions.add(new Exit());
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteAction(out));
        actions.add(new Exit());
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        Store tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Exit());
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Exit Program" + ln
        ));
    }

    @Test
    public void whenFindAllItems() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        Item item1 = tracker.add(new Item("item 1"));
        Item item2 = tracker.add(new Item("item 2"));
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ShowAction(out));
        actions.add(new Exit());
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        String expected = "Menu." + ln
                + "0. Show all items" + ln
                + "1. Exit Program" + ln
                + "=== Show all items ====" + ln
                + item1 + ln
                + item2 + ln
                + "Menu." + ln
                + "0. Show all items" + ln
                + "1. Exit Program" + ln;
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenFindByNameItems() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "item", "1"}
        );
        Store tracker = new MemTracker();
        Item item1 = tracker.add(new Item("item"));
        tracker.add(new Item("item 2"));
        tracker.add(new Item("item 3"));
        tracker.add(new Item("item 4"));
        Item item2 = tracker.add(new Item("item"));
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindByNameAction(out));
        actions.add(new Exit());
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        String expected = "Menu." + ln
                + "0. Find items by name" + ln
                + "1. Exit Program" + ln
                + "=== Find items by name ====" + ln
                + item1 + ln
                + item2 + ln
                + "Menu." + ln
                + "0. Find items by name" + ln
                + "1. Exit Program" + ln;
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenFindByIdItems() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item1 = tracker.add(new Item("item"));
        tracker.add(new Item("item 2"));
        tracker.add(new Item("item 3"));
        tracker.add(new Item("item 4"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item1.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindByIdAction(out));
        actions.add(new Exit());
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        String expected = "Menu." + ln
                + "0. Find item by id" + ln
                + "1. Exit Program" + ln
                + "=== Find item by id ====" + ln
                + item1 + ln
                + "Menu." + ln
                + "0. Find item by id" + ln
                + "1. Exit Program" + ln;
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1", "0"}
        );
        Store tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Exit());
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Exit Program" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu." + ln
                        + "0. Exit Program" + ln
                )
        );
    }
}