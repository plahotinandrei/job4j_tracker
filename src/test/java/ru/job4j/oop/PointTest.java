package ru.job4j.oop;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class PointTest {

    @Test
    public void when000To010Then1() {
        Point a = new Point(0, 0, 0);
        Point b = new Point(0, 1, 0);
        double result = a.distance3d(b);
        double expected = 1;
        assertThat(result, closeTo(expected, 0.001));
    }

    @Test
    public void when111To161Then5() {
        Point a = new Point(1, 1, 1);
        Point b = new Point(1, 6, 1);
        double result = a.distance3d(b);
        double expected = 5;
        assertThat(result, closeTo(expected, 0.001));
    }
}