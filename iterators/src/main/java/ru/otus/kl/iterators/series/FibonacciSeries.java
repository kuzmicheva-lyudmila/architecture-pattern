package ru.otus.kl.iterators.series;

import ru.otus.kl.iterators.iterator.FibonacciIterator;
import ru.otus.kl.iterators.iterator.IteratorType;
import ru.otus.kl.iterators.iterator.SeriesIterator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FibonacciSeries implements Series{

    private static final int DEFAULT_COUNT_LIMIT = 100;

    private final List<BigInteger> series;

    public FibonacciSeries() {
        this.series = initial();
    }

    private List<BigInteger> initial() {
        List<BigInteger> list = new ArrayList<>();
        list.add(BigInteger.ZERO);
        list.add( BigInteger.ONE);

        BigInteger previous = BigInteger.ZERO;
        BigInteger next = BigInteger.ONE;
        BigInteger sum;
        for (int i = 2; i <= DEFAULT_COUNT_LIMIT; i++) {
            sum = previous;
            previous = next;
            next = sum.add(previous);
            list.add(next);
        }
        return list;
    }

    public BigInteger get(int index) {
        return series.get(index);
    }

    public int size() {
        return series.size();
    }

    @Override
    public SeriesIterator createForwardIterator() {
        return new FibonacciIterator(this, IteratorType.FORWARD);
    }

    @Override
    public SeriesIterator createReverseIterator() {
        return new FibonacciIterator(this, IteratorType.REVERSE);
    }
}
