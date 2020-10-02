package ru.otus.kl.adapter.secondprogram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefaultGeneratedMatrices implements GeneratedMatrices {

    private final int height;
    private final int width;

    public DefaultGeneratedMatrices(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public void generateMatrices(String outputFilename) throws IOException {
        List<String> matrixA = createMatrix(height, width);
        List<String> matrixB = createMatrix(height, width);

        saveToFile(matrixA, matrixB, outputFilename);
    }

    private List<String> createMatrix(int height, int width) {
        return IntStream.range(0, height)
                    .mapToObj(i -> new Random().ints().limit(width))
                    .map(s -> s.mapToObj(String::valueOf).collect(Collectors.joining(";")))
                    .collect(Collectors.toList());
    }

    private void saveToFile(List<String> matrixA, List<String> matrixB, String outputFilename) throws IOException {
        Path path = Paths.get(outputFilename);
        Files.write(path, matrixA);
        Files.write(path, List.of(""), StandardOpenOption.APPEND);
        Files.write(path, matrixB, StandardOpenOption.APPEND);
    }
}