package ua.com.my.Task1.Task1Tests.ArraySotr;

import org.junit.Assert;
import org.junit.Test;
import ua.com.my.Task1.ArraySotr.ArraySort;

public class ArraySortTest {

    @Test
    public void arraySort() {
        Integer[] arr = { 5, -2, 1, -8, 10 };
        Integer[] act = ArraySort.ArraySort(arr);
        Integer[] expected = {10, 5, 1};
        Assert.assertArrayEquals(act, expected);
    }
}