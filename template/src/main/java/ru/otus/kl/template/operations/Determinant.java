package ru.otus.kl.template.operations;

import org.ejml.simple.SimpleMatrix;

import java.util.Collections;
import java.util.List;

import static ru.otus.kl.template.utils.MatrixUtils.getMatrix;

public class Determinant extends MatrixOperation {

    public Determinant(String inputFilename, String outputFilename) {
        super(inputFilename, outputFilename);
    }

    @Override
    List<String> process(List<String> data) {
        SimpleMatrix inputMatrix = new SimpleMatrix(getMatrix(data));
        return Collections.singletonList(String.valueOf(inputMatrix.determinant()));
    }
}
