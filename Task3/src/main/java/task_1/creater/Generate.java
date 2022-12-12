package task_1.creater;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
class Generate {
    private String firstName;
    private String lastName;
    private String type;
    private Double fineAmount;
    private final String dateTime;

    public Generate(String firstName, String lastName, String type, Double fineAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.fineAmount = fineAmount;
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static List<Generate> create(int Range) {
        var violations = new LinkedList<Generate>();
        var firstName = Arrays.asList("Tom", "Fred", "Ron", "Denis", "Jack");
        var lastName = Arrays.asList("One", "Two", "Three", "Four", "Five");
        var typeMap = Map.of("PARKING", 100.0,
                "SPEEDING", 200.0,
                "ALCOHOL", 300.0,
                "RED_LIGHT", 400.0,
                "Mobile", 500.0);

        int random = new Random().nextInt(Range);
        int randomFirstName = new Random().nextInt(firstName.size() - 1);
        int randomLastName = new Random().nextInt(lastName.size() - 1);
        int randomType = new Random().nextInt(typeMap.size() - 1);

        for(int i = 0; i < random; i++) {
            String type = new ArrayList<>(typeMap.keySet()).get(randomType);
            Double fineAmount = typeMap.get(type);

            Generate generate = new Generate(
                    firstName.get(randomFirstName),
                    lastName.get(randomLastName),
                    type,
                    fineAmount
            );

            violations.add(generate);

            randomFirstName = new Random().nextInt(firstName.size() - 1);
            randomLastName = new Random().nextInt(lastName.size() - 1);
            randomType = new Random().nextInt(typeMap.size() - 1);
        }
        return violations;
    }
}

