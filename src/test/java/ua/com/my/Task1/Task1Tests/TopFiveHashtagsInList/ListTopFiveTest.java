package ua.com.my.Task1.Task1Tests.TopFiveHashtagsInList;

import org.junit.Test;
import ua.com.my.Task1.TopFiveHashtagsInList.ListTopFive;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListTopFiveTest {

    @Test
    public void topFiveHashTags() {
        HashMap actual = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("#Слово1 #Слово2 #Слово3 #Слово4 #Слово5 #Слово6 Слово1 #Слово1");
        list.add("#Слово1 #Слово2 #Слово3 #Слово4 #Слово5");
        list.add("#Слово1 #Слово2 #Слово3 #Слово4");
        list.add("#Слово1 #Слово2 #Слово3");
        list.add("#Слово1 #Слово2");
        list.add("#Слово1");

        actual = ListTopFive.topFiveHashTags(list);
        HashMap<Object, Object> expected = new HashMap<>();
        expected.put("#Слово1", 6);
        expected.put("#Слово2", 5);
        expected.put("#Слово3", 4);
        expected.put("#Слово4", 3);
        expected.put("#Слово5",  2);
        assertThat(actual, is(expected));
    }
}