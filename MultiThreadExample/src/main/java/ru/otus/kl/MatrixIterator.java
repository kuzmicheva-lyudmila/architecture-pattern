package ru.otus.kl;

import java.util.concurrent.atomic.AtomicInteger;

public class MatrixIterator {

    private Matrix matrix;

    private AtomicInteger currentRow;

    private AtomicInteger currentColumn;

    public MatrixIterator(Matrix matrix) {
        this.matrix = matrix;
        this.currentRow = new AtomicInteger(0);
        this.currentColumn = new AtomicInteger(0);
    }

    // synchronize
    public Cell nextCell() {
        currentRow.get()
        return null;
    }

    public Cell currentCell() {
        return new Cell(currentRow.get(), currentColumn.get());
    }
}
