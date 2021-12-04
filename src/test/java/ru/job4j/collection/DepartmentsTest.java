package ru.job4j.collection;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DepartmentsTest {
    @Test
    public void whenMissed() {
        List<String> input = Arrays.asList("k1/sk1");
        List<String> expect = Arrays.asList("k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenNonChange() {
        List<String> input = Arrays.asList("k1", "k1/sk1");
        List<String> expect = Arrays.asList("k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortAsc() {
        List<String> input = Arrays.asList("k1", "k1/sk2", "k1/sk1/ssk2");
        List<String> expect = Arrays.asList("k1", "k1/sk1", "k1/sk1/ssk2", "k1/sk2");
        List<String> result = Departments.fillGaps(input);
        Departments.sortAsc(result);
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortAsc2() {
        List<String> input = Arrays.asList("k1/sk1/ssk2");
        List<String> expect = Arrays.asList("k1", "k1/sk1", "k1/sk1/ssk2");
        List<String> result = Departments.fillGaps(input);
        Departments.sortAsc(result);
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortDesc() {
        List<String> input = Arrays.asList("k1", "k2/sk1");
        List<String> expect = Arrays.asList("k2", "k2/sk1", "k1");
        List<String> result = Departments.fillGaps(input);
        Departments.sortDesc(result);
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortDesc2() {
        List<String> input = Arrays.asList(
                "k2/sk1", "k1/sk1/ssk1", "k2/sk1/ssk2", "k1/sk1/ssk2", "k1"
        );
        List<String> expect = Arrays.asList(
                "k2", "k2/sk1", "k2/sk1/ssk2", "k1", "k1/sk1", "k1/sk1/ssk1", "k1/sk1/ssk2"
        );
        List<String> result = Departments.fillGaps(input);
        Departments.sortDesc(result);
        assertThat(result, is(expect));
    }
}