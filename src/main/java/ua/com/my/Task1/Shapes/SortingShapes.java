package ua.com.my.Task1.Shapes;

import java.util.HashMap;
import java.util.Map;

public class SortingShapes {
    public static Map SortingShapes(HashMap<String, Integer> mapOfShapes){

        mapOfShapes.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(System.out::println);

        return mapOfShapes;
    }
}
