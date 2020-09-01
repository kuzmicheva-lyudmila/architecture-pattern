package ru.kl.proxy.proxy;

public class SimpleMatrix implements Matrix{

    private final int[][] a;

    public SimpleMatrix(int[][] a) {
        this.a = a;
    }

    @Override
    public int get(int row, int clm) {
          return a[row][clm];
    }
}
