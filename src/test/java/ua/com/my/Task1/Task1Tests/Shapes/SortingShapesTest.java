package ua.com.my.Task1.Task1Tests.Shapes;

import org.junit.Test;
import ua.com.my.Task1.Shapes.SortingShapes;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SortingShapesTest {

    @Test
    public void sortingShapes() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("1", 10);
        map.put("2", 11);
        map.put("3", 12);
        Map act = SortingShapes.SortingShapes(map);
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("3", 12);
        expected.put("2", 11);
        expected.put("1", 10);

        assertThat(act, is(expected));

    }
}