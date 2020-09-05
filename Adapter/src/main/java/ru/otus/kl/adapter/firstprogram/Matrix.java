package ru.otus.kl.adapter.firstprogram;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class Matrix {

    private final int[][] values;

    public Matrix(int[][] values) {
        this.values = values;
    }

    public Matrix(List<String> lines) {
        this.values = lines.stream()
                .map(this::getInts)
                .toArray(int[][]::new);
    }

    public List<String> getLines() {
        return Stream.of(values)
                .map(Arrays::toString)
                .collect(Collectors.toList());
    }

    public static Matrix addition(Matrix matrixA, Matrix matrixB) {
        checkMatrixForAddition(matrixA, matrixB);

        int[][] result = new int[matrixA.getValues().length][matrixA.getValues()[0].length];
        for (int i = 0; i < matrixA.getValues().length; i++) {
            for (int j = 0; j < matrixA.getValues()[0].length; j++) {
                result[i][j] = matrixA.getValues()[i][j] + matrixB.getValues()[i][j];
            }
        }

        return new Matrix(result);
    }

    private int[] getInts(String s) {
        return Arrays.stream(s.split(";")).mapToInt(Integer::parseInt).toArray();
    }

    private static void checkMatrixForAddition(Matrix matrixA, Matrix matrixB) {
        if (matrixB.getValues().length == matrixA.getValues().length
                && matrixA.getValues()[0].length == matrixB.getValues()[0].length) {
            return;
        }

        throw new IllegalArgumentException("Matrices wasn''t same dimension");
    }
}
