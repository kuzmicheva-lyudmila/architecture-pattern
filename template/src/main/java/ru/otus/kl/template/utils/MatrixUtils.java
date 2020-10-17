package ru.otus.kl.template.utils;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixUtils {

    private MatrixUtils() {
    }

    public static double[][] getMatrix(List<String> data) {
        int dimension = data.size();
        double[][] matrix = new double[dimension][dimension];
        for (int i = 0 ; i < dimension; i++) {
            matrix[i] = Arrays.stream(data.get(i).split(",")).mapToDouble(Double::parseDouble).toArray();
        }
        return matrix;
    }

    public static List<String> convertMatrix(SimpleMatrix matrix) {
        int rowCount = matrix.getMatrix().getNumRows();
        int colCount = matrix.getMatrix().getNumCols();

        List<String> data = new ArrayList<>(rowCount);
        for (int row = 0; row < rowCount; row++) {
            double[] currentRow = new double[rowCount];
            for (int col = 0; col < colCount; col++) {
                currentRow[col] = matrix.get(row, col);
            }
            data.add(Arrays.toString(currentRow));
        }

        return data;
    }
}
