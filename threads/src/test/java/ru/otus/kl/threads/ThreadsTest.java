package ru.otus.kl.threads;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class ThreadsTest {

    private static final String PROTOCOL_FILENAME = "protocol.txt";
    private static final int OPERATION_REPEAT_COUNT = 2;
    private static final int DIMENSION = 3;
    private static final int MAX_VALUE = 100;

    @Test
    void testMultiply() throws IOException, InterruptedException {

        List<String> expectedResult = new ArrayList<>(OPERATION_REPEAT_COUNT);
        for (int i = 0; i < OPERATION_REPEAT_COUNT; i++) {
            int[][] matrixA = new int[DIMENSION][DIMENSION];
            int[][] matrixB = new int[DIMENSION][DIMENSION];
            Random random = new Random();
            for (int row = 0; row < DIMENSION; row++) {
                for (int column = 0; column < DIMENSION; column++) {
                    matrixA[row][column] = random.nextInt(MAX_VALUE);
                    matrixB[row][column] = random.nextInt(MAX_VALUE);
                }
            }

            MatrixOperation matrixOperation = new MultiplyOperation(matrixA, matrixB, DIMENSION, PROTOCOL_FILENAME);
            matrixOperation.calc();

            expectedResult.add(generateExpectedResult(matrixA, matrixB));
        }

        List<String> actualResult = readResult();
        Assertions.assertTrue(actualResult.containsAll(expectedResult));
    }

    private String generateExpectedResult(int[][] matrixA, int[][] matrixB) {
        int[][] matrixResult = new int[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                int value = 0;
                for (int i = 0; i < DIMENSION; i++) {
                    value += matrixA[row][i] * matrixB[i][column];
                }
                matrixResult[row][column] = value;
            }
        }

        return Arrays.stream(matrixResult)
                .map(Arrays::toString)
                .reduce((a, b) -> a + b)
                .orElse("");
    }

    private List<String> readResult() throws IOException {
        return Files.readAllLines(Paths.get(PROTOCOL_FILENAME));
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        Files.deleteIfExists(Paths.get(PROTOCOL_FILENAME));
    }
}
