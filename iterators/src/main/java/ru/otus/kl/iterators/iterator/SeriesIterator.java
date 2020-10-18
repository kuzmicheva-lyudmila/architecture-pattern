package ru.otus.kl.iterators.iterator;

import java.math.BigInteger;

public interface SeriesIterator {

    boolean hasNext();
    BigInteger getNext();
    void reset();
}
