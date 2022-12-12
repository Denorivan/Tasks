package task_1.creater;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Path {
    DIRECTORY_PATH("Task3/src/main/resources/task1/DataBase"),
    RESULT_PATH("Task3/src/main/resources/task1/Violation.json");

    private String path;
}
