package ru.otus.kl.adapter.firstprogram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DefaultMatrixOperations implements MatrixOperations {

    @Override
    public void addition(String inputFilename, String outputFilename) throws IOException {
        List<String> lineList = readFromFile(inputFilename);

        int separateIndex = lineList.indexOf("");
        if (separateIndex < 0) {
            throw new IllegalArgumentException("Error on read file: didn''t found a separate between matrices");
        }

        Matrix result = Matrix.addition(
                new Matrix(lineList.subList(0, separateIndex)),
                new Matrix(lineList.subList(separateIndex + 1, lineList.size()))
        );
        saveToFile(result, Path.of(outputFilename));
    }

    private List<String> readFromFile(String inputFilename) throws IOException {
        return Files.readAllLines(Paths.get(inputFilename));
    }

    private void saveToFile(Matrix result, Path outputFilename) throws IOException {
        Files.write(outputFilename, result.getLines());
    }
}
