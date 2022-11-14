package ua.com.my.Task1.ArraySotr;

import java.util.Arrays;
import java.util.Collections;


public class ArraySort {
    public static Integer[] ArraySort(Integer[] array){

        return Arrays.stream(array)
                .filter(i -> i >= 0)
                .sorted(Collections.reverseOrder())
                .toArray(Integer[]::new);

    }
}
