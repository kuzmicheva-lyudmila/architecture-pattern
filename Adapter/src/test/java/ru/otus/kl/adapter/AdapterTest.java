package ru.otus.kl.adapter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.kl.adapter.firstprogram.FirstApplication;
import ru.otus.kl.adapter.firstprogram.MatrixOperations;
import ru.otus.kl.adapter.secondprogram.DefaultGeneratedMatrices;
import ru.otus.kl.adapter.secondprogram.GeneratedMatrices;
import ru.otus.kl.adapter.secondprogram.MatrixOperationsAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class AdapterTest {

    private static final String OUTPUT_FILENAME = "f1.txt";
    private static final String INPUT_FILENAME = "f0.txt";
    private static final String GENERATED_MATRICES_FILENAME = "f3.txt";

    @Test
    void testCheckAdapter() throws IOException {
        List<String> resultFromGeneratedMatrices = additionWithGeneratedMatrices();
        List<String> resultFromSaveToFileMatrices = additionWithMatricesFromFile();

        Assertions.assertTrue(
                resultFromSaveToFileMatrices.containsAll(resultFromGeneratedMatrices)
        );
    }

    private List<String> additionWithGeneratedMatrices() throws IOException {
        GeneratedMatrices generatedMatrices = new DefaultGeneratedMatrices(4, 4);
        MatrixOperations matrixOperationsWithAdapter = new MatrixOperationsAdapter(generatedMatrices);
        matrixOperationsWithAdapter.addition(INPUT_FILENAME, GENERATED_MATRICES_FILENAME);
        return Files.readAllLines(Paths.get(GENERATED_MATRICES_FILENAME));
    }

    private List<String> additionWithMatricesFromFile() throws IOException {
        FirstApplication.additionMatrices(INPUT_FILENAME, OUTPUT_FILENAME);
        return Files.readAllLines(Paths.get(OUTPUT_FILENAME));
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        Files.deleteIfExists(Paths.get(INPUT_FILENAME));
        Files.deleteIfExists(Paths.get(OUTPUT_FILENAME));
        Files.deleteIfExists(Paths.get(GENERATED_MATRICES_FILENAME));
    }
}
