package ru.otus.kl;

public class MultiplyThread extends Thread {

    private MatrixIterator iterator;

    private Matrix a;

    private Matrix b;

    public MultiplyThread(MatrixIterator iterator, Matrix a, Matrix b) {
        this.iterator = iterator;
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        Cell nextCell = iterator.currentCell();
        while (nextCell != null) {
            Cell currentCell = iterator.setNextCell();
            int value =
        }
    }
}
