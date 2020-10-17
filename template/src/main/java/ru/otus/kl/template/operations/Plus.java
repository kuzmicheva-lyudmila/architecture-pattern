package ru.otus.kl.template.operations;

import org.ejml.simple.SimpleMatrix;

import java.util.Collections;
import java.util.List;

import static ru.otus.kl.template.utils.MatrixUtils.convertMatrix;
import static ru.otus.kl.template.utils.MatrixUtils.getMatrix;

public class Plus extends MatrixOperation {

    public Plus(String inputFilename, String outputFilename) {
        super(inputFilename, outputFilename);
    }

    @Override
    List<String> process(List<String> data) {
        int separatorLineIndex = 0;
        while (separatorLineIndex < data.size() && !data.get(separatorLineIndex).isEmpty()) {
            separatorLineIndex ++;
        }

        if (separatorLineIndex == data.size()) {
            return Collections.emptyList();
        }

        SimpleMatrix matrixA = new SimpleMatrix(getMatrix(data.subList(0, separatorLineIndex)));
        SimpleMatrix matrixB = new SimpleMatrix(getMatrix(data.subList(separatorLineIndex + 1, data.size())));
        return convertMatrix(matrixA.plus(matrixB));
    }
}
