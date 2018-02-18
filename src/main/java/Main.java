import evaluator.Evaluator;
import operation.OperationParser;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by mtumilowicz on 2018-02-02.
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/operations.txt"))) {
            System.out.println(Evaluator.eval(reader
                    .lines()
                    .map(OperationParser::parse)
                    .collect(Collectors.toList())));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
