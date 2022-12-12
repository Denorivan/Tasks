package task_1;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.platform.commons.function.Try;
import task_1.creater.Timer;
import task_1.creater.FileService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import static task_1.creater.Path.DIRECTORY_PATH;
import static task_1.creater.Path.RESULT_PATH;


public class Run {
    public static Path directoryPath = Path.of(DIRECTORY_PATH.getPath());
    public static Path resultPath = Path.of(RESULT_PATH.getPath());

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);

        FileService fileService = new FileService(executorService);


        fileService.clearDirectory(DIRECTORY_PATH);


        fileService.createFiles(10, 1000, DIRECTORY_PATH);

        Timer timer = Timer.start();

        File[] files = directoryPath.toFile().listFiles();

        var futures = createTasks(files, executorService);

         CompletableFuture
                .allOf(futures)
                .thenApply(v -> Arrays.stream(futures)
                            .map(CompletableFuture::join)
                            .flatMap(List::stream)
                            .collect(Collectors.toList()))
                .thenApply(Run::margeViolations)
                .thenApply(list -> list.stream()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList()))
                .thenAccept(listViolation -> Try.call(() -> {
                    new ObjectMapper()
                        .enable(SerializationFeature.INDENT_OUTPUT)
                        .writeValue(resultPath.toFile(), listViolation);
                    System.out.println(timer.stop());
                    return true;
                }));

        executorService.shutdown();
    }

    @SuppressWarnings("All")
    private static CompletableFuture<List<Violation>>[] createTasks(File[] files, ExecutorService executorService){
        return Arrays.stream(files)
                .map(file -> CompletableFuture.supplyAsync(() -> countViolationByType(file), executorService))
                .toArray(CompletableFuture[]::new);
    }

    private static List<Violation> margeViolations(List<Violation> violations){
        List<String> types = violations
                .stream()
                .distinct()
                .map(Violation::getType).toList();

        List<Violation> allStatistics = new ArrayList<>();

        for(String type : types) {
            BigDecimal total = violations.stream()
                    .map(v -> (v.getType().equals(type)) ? v.getTotal() : BigDecimal.ZERO)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            allStatistics.add(new Violation().setType(type).setTotal(total));
        }
        return allStatistics;
    }

    private static List<Violation> countViolationByType(File file) {
        List<Violation> violations = new ArrayList<>();
        if(file.isFile() && file.getName().endsWith(".json")) {
            JsonFactory jFactory = new JsonFactory();
            try(JsonParser jParser = jFactory.createParser(file)) {
                while(jParser.nextToken() != JsonToken.END_ARRAY) {
                    Violation violation = new Violation();
                    JsonToken token = jParser.nextToken();
                    while(token  != JsonToken.END_OBJECT) {
                        String fieldName = jParser.getCurrentName();

                        if(token == null) return new ArrayList<>();

                        if("fineAmount".equals(fieldName)) {
                            jParser.nextToken();
                            violation.setTotal(jParser.getDecimalValue());
                        }
                        if("type".equals(fieldName)) {
                            jParser.nextToken();
                            violation.setType(jParser.getText());
                        }
                        token = jParser.nextToken();
                    }
                        Violation oldViolation = violations
                                .stream()
                                .filter(v -> v.getType().equals(violation.getType()))
                                .findFirst()
                                .orElse(null);

                        if(oldViolation == null) {
                            violations.add(violation);
                        } else {
                            BigDecimal newTotal = oldViolation.getTotal().add(violation.getTotal());
                            oldViolation.setTotal(newTotal);
                        }
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return violations;
    }
}
