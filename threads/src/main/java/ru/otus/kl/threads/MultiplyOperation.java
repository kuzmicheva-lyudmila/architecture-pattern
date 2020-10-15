package ru.otus.kl.threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

public class MultiplyOperation implements MatrixOperation {

    private static final int THREAD_COUNT = 5;

    private final CustomLogger customLogger = CustomLogger.getInstance();

    private final String protocolFile;
    private final int[][] matrixA;
    private final int[][] matrixB;
    private final int dimension;

    private AtomicReference<Integer[][]> matrixResult;

    private synchronized boolean needCalculateCell(int row, int column) {
        Integer[][] matrix = matrixResult.get();
        if (matrix[row][column] == null) {
            matrix[row][column] = -1;
            return true;
        }

        return false;
    }

    public MultiplyOperation(int[][] matrixA, int[][] matrixB, int dimension, String protocolFile) {
        this.protocolFile = protocolFile;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.dimension = dimension;
    }

    @Override
    public void calc() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        matrixResult = new AtomicReference<>(new Integer[dimension][dimension]);
        CompletableFuture.runAsync(this::calculateMatrix)
                .thenRun(
                    () -> Arrays.stream(matrixResult.get())
                            .forEach(
                                    array -> customLogger.put("Result matrix: %s", Arrays.toString(array))
                            )
                )
                .thenRun(this::saveResult)
                .thenRun(
                        () -> {
                            customLogger.put("Success");
                            latch.countDown();
                        }
                );

        latch.await();
    }

    private void calculateMatrix() {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            service.submit(
                    () -> {
                        try {
                            task();
                            latch.countDown();
                        } catch (InterruptedException e) {
                            customLogger.put(
                                    "%s: was interrupted! %s",
                                    Thread.currentThread().getName(),
                                    e.getMessage()
                            );
                            Thread.currentThread().interrupt();
                            throw new IllegalThreadStateException();
                        }
                        return null;
                    }
            );
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            customLogger.put("%s: CountDownLatch interrupted! %s", Thread.currentThread().getName(), e.getMessage());
            Thread.currentThread().interrupt();
            throw new IllegalThreadStateException();
        } finally {
            service.shutdown();
        }

    }

    private void task() throws InterruptedException {
        sleep(1000);
        customLogger.put("%s: start task", Thread.currentThread().getName());
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                if (needCalculateCell(row, column)) {
                    customLogger.put(
                            "%s: calculate cell row = %d column = %d",
                            Thread.currentThread().getName(),
                            row,
                            column
                    );
                    matrixResult.get()[row][column] = calculateCell(row, column);
                }
                sleep(100);
            }
        }
        customLogger.put("%s: end task", Thread.currentThread().getName());
    }

    private int calculateCell(int row, int column) {
        int result = 0;
        for (int i = 0; i < dimension; i++) {
            result += matrixA[row][i] * matrixB[i][column];
        }

        return result;
    }

    private void saveResult() {
        Path protocolFilePath = Paths.get(protocolFile);
        if (new File(protocolFilePath.toString()).exists()) {
            saveStringToFile(protocolFilePath, "\n".getBytes());
        }

        Arrays.stream(matrixResult.get())
                .forEach(
                        array -> saveStringToFile(protocolFilePath, Arrays.toString(array).getBytes())
                );
        customLogger.put("Protocol saved");
    }

    private void saveStringToFile(Path protocolFilePath, byte[] bytes) {
        try {
            Files.write(protocolFilePath, bytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            customLogger.put("Syntax error on paring the protocol file path: %s", e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }
}
