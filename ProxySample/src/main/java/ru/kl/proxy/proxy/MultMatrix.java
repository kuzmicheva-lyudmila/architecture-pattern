package ru.kl.proxy.proxy;

public class MultMatrix implements Matrix {

    private final Matrix a;

    private final Matrix b;

    private final int size;

    public MultMatrix(Matrix a, Matrix b, int size) {
        this.a = a;
        this.b = b;
        this.size = size;
    }

    @Override
    public int get(int row, int clm) {
        int result = 0;
        for (int cell = 0; cell < size; cell++) {
            result += a.get(row, cell) * b.get(cell, clm);
        }

        return result;
    }
}
