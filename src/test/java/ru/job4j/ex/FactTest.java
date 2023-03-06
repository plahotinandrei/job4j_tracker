package ru.job4j.ex;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FactTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenNIsLess0() {
        new Fact().calc(-2);
    }

    @Test
    public void when3then6() {
        int rsl = new Fact().calc(3);
        assertThat(rsl, is(6));
    }
}