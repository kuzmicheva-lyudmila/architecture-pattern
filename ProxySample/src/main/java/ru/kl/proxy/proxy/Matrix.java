package ru.kl.proxy.proxy;

public interface Matrix {

    default Matrix mult(Matrix a, int size) {
        return new MultMatrix(this, a, size);
    }

    default Matrix plus(Matrix a) {
        return new PlusMatrix(this, a);
    }

    int get(int row, int clm);
}
