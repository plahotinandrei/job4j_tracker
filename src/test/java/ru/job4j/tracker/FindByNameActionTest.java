package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByNameActionTest {

    @Test
    public void whenFindByNameItemSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        String findName = "Item 2";
        tracker.add(new Item("Item 1"));
        Item item = new Item(findName);
        tracker.add(item);
        tracker.add(new Item("Item 3"));
        UserAction findByIdAction = new FindByNameAction(output);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(findName);
        findByIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ====" + ln
                        + item + ln
        );
    }
}