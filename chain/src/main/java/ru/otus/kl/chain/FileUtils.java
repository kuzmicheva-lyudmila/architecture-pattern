package ru.otus.kl.chain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

public class FileUtils {

    public static void saveToFile(String inputFilename, String outputFilename) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(inputFilename));
        Files.write(Paths.get(outputFilename), bytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static List<String> readFile(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }

    public static String getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElse("");
    }

    private FileUtils() {
    }
}
