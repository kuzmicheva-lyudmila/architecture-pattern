package ru.otus.kl;

import lombok.Getter;

import java.util.Random;

@Getter
public class Matrix {

    private static final int MAX_VALUE = 100;

    private final int dim;

    private final int[][] values;

    public Matrix(int dim) {
        this.dim = dim;
        this.values = generateValues();
    }

    public Matrix(int[][] values) {
        this.dim = values.length;
        this.values = values.clone();
    }

    private int[][] generateValues() {
        Random random = new Random();
        int[][] randomValues = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                randomValues[i][j] = random.nextInt(MAX_VALUE);
            }
        }
        return randomValues;
    }
}
