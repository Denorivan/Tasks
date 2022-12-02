package ua.com.my.Task2;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeAttributes {
    private final Pattern attributeNamePattern = Pattern.compile("(?<!sur)name\\s*=\\s*?\"(?<name>\\S*)\"");
    private final Pattern attributeSurnamePattern = Pattern.compile("surname\\s*?=\\s*?\"(?<surname>\\S*)\"");

    public void editAttributes(String inputPath, String outputPath) {
        try (FileInputStream fileInputStream = new FileInputStream(inputPath);
             Scanner scanner = new Scanner(fileInputStream,
                     StandardCharsets.UTF_8).useDelimiter("/>");
             FileWriter fileWriter = new FileWriter(outputPath, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            while (scanner.hasNext()) {
                bufferedWriter.write(findAndChangeAttributes(scanner.next()) + (scanner.hasNext() ? "/>" : ""));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data ", e);
        }
    }

    private String findAndChangeAttributes(String line) {
        Matcher nameMatcher = attributeNamePattern.matcher(line);
        Matcher surnameMatcher = attributeSurnamePattern.matcher(line);
        String name = "";
        String surname = "";

        if (surnameMatcher.find()) {
            surname = surnameMatcher.group("surname");
            line = line.replaceAll(attributeSurnamePattern.toString(), "");
        }
        if (nameMatcher.find()) {
            name = nameMatcher.group("name");
            String outputFormat = "name=\"%s %s\"";
            line = line.replaceAll(attributeNamePattern.toString(), String.format(outputFormat, name, surname));
        }
        return line;
    }
}
