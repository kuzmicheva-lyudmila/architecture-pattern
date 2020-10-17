package ru.otus.kl.template.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    private FileUtils() {
    }

    public static List<String> readData(String inputFilename) throws IOException {
        return Files.readAllLines(Paths.get(inputFilename));
    }

    public static void writeData(List<String> data, String outputFilename) throws IOException {
        Files.write(Paths.get(outputFilename), data);
    }
}
