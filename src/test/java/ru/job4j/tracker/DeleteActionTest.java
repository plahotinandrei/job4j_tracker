package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteActionTest {

    @Test
    public void whenItemWasDeletedSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item"));
        UserAction deleteAction = new DeleteAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        deleteAction.execute(input, tracker);
        assertThat(tracker.findAll()).isEmpty();
    }
}