package ru.otus.kl.template.operations;

import org.ejml.simple.SimpleMatrix;

import java.util.List;

import static ru.otus.kl.template.utils.MatrixUtils.convertMatrix;
import static ru.otus.kl.template.utils.MatrixUtils.getMatrix;

public class Transpose extends MatrixOperation {

    public Transpose(String inputFilename, String outputFilename) {
        super(inputFilename, outputFilename);
    }

    @Override
    List<String> process(List<String> data) {
        SimpleMatrix matrix = new SimpleMatrix(getMatrix(data));
        return convertMatrix(matrix.transpose());
    }
}
