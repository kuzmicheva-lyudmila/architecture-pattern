package ru.otus.factory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.factory.abstractfactory.ActionFactory;
import ru.otus.factory.abstractfactory.ActionFactoryForFile;
import ru.otus.factory.factory.MergeSorter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

class FactoryTest {

    private static final int ARRAY_LENGTH = 50;
    private static final String INPUT_FILENAME = "f0.txt";
    private static final String OUTPUT_FILENAME = "f1.txt";

    @Test
    void testCheckAdapter() throws IOException {
        int[] inputData = new Random().ints().limit(ARRAY_LENGTH).toArray();

        saveToFile(inputData);
        ActionFactory actions = new ActionFactoryForFile(INPUT_FILENAME, OUTPUT_FILENAME);
        actions.sorting("merge");
        String resultData = readFromFile();

        String expectedData = Arrays.toString(new MergeSorter().sort(inputData));

        Assertions.assertEquals(resultData, expectedData);
    }

    private void saveToFile(int[] values) throws IOException {
        String result = Arrays.stream(values)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(";"));
        Files.writeString(Paths.get(INPUT_FILENAME), result);
    }

    private String readFromFile() throws IOException {
        return Files.readString(Paths.get(OUTPUT_FILENAME)).trim();
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        Files.deleteIfExists(Paths.get(INPUT_FILENAME));
        Files.deleteIfExists(Paths.get(OUTPUT_FILENAME));
    }
}
