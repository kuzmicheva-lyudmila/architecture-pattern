package ru.otus.kl.iterators.iterator;

import ru.otus.kl.iterators.series.FibonacciSeries;

import java.math.BigInteger;

public class FibonacciIterator implements SeriesIterator{

    private final FibonacciSeries fibonacciSeries;
    private final IteratorType type;

    private int currentPosition;

    public FibonacciIterator(FibonacciSeries fibonacciSeries, IteratorType type) {
        this.fibonacciSeries = fibonacciSeries;
        this.type = type;
        setFirstPosition();
    }

    @Override
    public boolean hasNext() {
        if (this.type == IteratorType.FORWARD) {
            return currentPosition < fibonacciSeries.size();
        } else if (this.type == IteratorType.REVERSE) {
            return currentPosition >= 0;
        }

        return false;
    }

    @Override
    public BigInteger getNext() {
        if (!hasNext()) {
            return null;
        }

        BigInteger element = fibonacciSeries.get(currentPosition);
        if (this.type == IteratorType.FORWARD) {
            currentPosition++;
        } else if (this.type == IteratorType.REVERSE) {
            currentPosition--;
        }
        return element;
    }

    @Override
    public void reset() {
        setFirstPosition();
    }

    private void setFirstPosition() {
        if (this.type == IteratorType.REVERSE) {
            this.currentPosition = fibonacciSeries.size() - 1;
        } else {
            this.currentPosition = 0;
        }
    }
}
