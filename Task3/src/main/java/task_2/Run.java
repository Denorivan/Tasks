package task_2;

import java.nio.file.Path;

public class Run {


    public static void main(String[] args) {
        Path path = Path.of("Task3/src/main/resources/task2/fields.properties");

        LoadFromProperties instance = Create.loadFromProperty(LoadFromProperties.class, path);

        System.out.println(instance);
    }
}
