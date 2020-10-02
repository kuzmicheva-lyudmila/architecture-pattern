package ru.otus.kl.adapter.firstprogram;

import java.io.IOException;

public class FirstApplication {

    private FirstApplication() {
    }

    public static void additionMatrices(String inputFileName, String outputFileName) throws IOException {
        MatrixOperations matrixOperations = new DefaultMatrixOperations();
        matrixOperations.addition(inputFileName, outputFileName);
    }
}
