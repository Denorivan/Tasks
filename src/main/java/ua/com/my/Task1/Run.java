package ua.com.my.Task1;

import ua.com.my.Task1.ArraySotr.ArraySort;
import ua.com.my.Task1.Shapes.SortingShapes;
import ua.com.my.Task1.TopFiveHashtagsInList.ListTopFive;

import java.util.ArrayList;
import java.util.HashMap;

public class Run {
    public static void Run(){
        Integer[] arr = { 5, -2, 1, -8, 10 };
        ArraySort.ArraySort(arr);


        ArrayList<String> list = new ArrayList<>();
        list.add("#Слово1 #Слово2 #Слово3 #Слово4 #Слово5 #Слово6 Слово1 #Слово1");
        list.add("#Слово1 #Слово2 #Слово3 #Слово4 #Слово5");
        list.add("#Слово1 #Слово2 #Слово3 #Слово4");
        list.add("#Слово1 #Слово2 #Слово3");
        list.add("#Слово1 #Слово2");
        list.add("#Слово1");
        ListTopFive.topFiveHashTags(list);

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("1", 10);
        map.put("2", 11);
        map.put("3", 12);
        SortingShapes.SortingShapes(map);


    }
}
