package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Test;

public class MaxTest {

    @Test
    public void whenMaxOf2NumbersThen5() {
        Max number = new Max();
        int first = 2;
        int second = 5;
        int result = number.max(first, second);
        int expected = 5;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void whenMaxOf3NumbersThen7() {
        Max number = new Max();
        int first = 0;
        int second = 7;
        int third = 3;
        int result = number.max(first, second, third);
        int expected = 7;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void whenMaxOf4NumbersThen11() {
        Max number = new Max();
        int first = 1;
        int second = 11;
        int third = 4;
        int fourth = 8;
        int result = number.max(first, second, third, fourth);
        int expected = 11;
        Assert.assertEquals(result, expected);
    }
}