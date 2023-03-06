package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CountDiapasonTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = CountDiapason.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSquareFunctionThenSquareResults() {
        List<Double> result = CountDiapason.diapason(3, 6, x -> 4 * x * x + 3);
        List<Double> expected = Arrays.asList(39D, 67D, 103D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExpFunctionThenExpResults() {
        List<Double> result = CountDiapason.diapason(1, 5, x -> Math.pow(2, x) - 1);
        List<Double> expected = Arrays.asList(1D, 3D, 7D, 15D);
        assertThat(result, is(expected));
    }
}