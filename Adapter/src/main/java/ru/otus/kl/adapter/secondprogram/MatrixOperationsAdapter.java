package ru.otus.kl.adapter.secondprogram;

import ru.otus.kl.adapter.firstprogram.MatrixOperations;
import ru.otus.kl.adapter.firstprogram.FirstApplication;

import java.io.IOException;

public class MatrixOperationsAdapter implements MatrixOperations {

    private final GeneratedMatrices generatedMatrices;

    public MatrixOperationsAdapter(GeneratedMatrices generatedMatrices) {
        this.generatedMatrices = generatedMatrices;
    }

    @Override
    public void addition(String inputFileName, String outputFileName) throws IOException {
        generatedMatrices.generateMatrices(inputFileName);
        FirstApplication.additionMatrices(inputFileName, outputFileName);
    }
}
