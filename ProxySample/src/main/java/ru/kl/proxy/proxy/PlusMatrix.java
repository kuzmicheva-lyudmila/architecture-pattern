package ru.kl.proxy.proxy;

public class PlusMatrix implements Matrix{

    private final Matrix a;

    private final Matrix b;

    public PlusMatrix(Matrix a, Matrix b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int get(int row, int clm) {
        return a.get(row, clm) + b.get(row, clm);
    }
}
