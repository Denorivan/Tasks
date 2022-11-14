package ua.com.my.Task1.TopFiveHashtagsInList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListTopFive {
    public static HashMap topFiveHashTags(ArrayList<String> list) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        list.stream()
                .flatMap(line -> Stream.of(line.split("\\s+")).distinct())
                .filter(word -> word.startsWith("#"))
                .collect(Collectors.toMap(key -> key, val -> 1, Integer::sum))
                .entrySet().stream()
                .sorted((e1, e2) -> {
                    int value = e1.getValue().compareTo(e2.getValue()) * -1;
                    if (value == 0) {
                        value = e1.getKey().compareTo(e2.getKey());
                    }
                    return value;
                })
                .limit(5)
                .forEach(e ->result.put(e.getKey(),e.getValue()));

        System.out.println(list);
        return result;
    }
}
