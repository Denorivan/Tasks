package ua.com.my.Task2;

import java.util.Map;

public class Run {
    private static String inputPath = "src/main/resources/input/persons.xml";
    private static String inputStatisticPath = "src/main/resources/years";
    private static String outputXmlPath = "src/main/resources/output/persons.xml";
    private static String outputJsonPath = "src/main/resources/output/statistic.json";
    public static void run(){
        ChangeAttributes changeAttributes = new ChangeAttributes();
        changeAttributes.editAttributes(inputPath, outputXmlPath);

        DataStatistics dataStatistics = new DataStatistics();
        Map<StringBuilder, Double> statisticsData = dataStatistics.getStatistics(inputStatisticPath);

        Statistics statistics = new Statistics();
        statistics.getStatistic(statisticsData, outputJsonPath);
    }
}
