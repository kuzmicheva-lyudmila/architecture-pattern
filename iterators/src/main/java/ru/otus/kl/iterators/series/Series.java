package ru.otus.kl.iterators.series;

import ru.otus.kl.iterators.iterator.SeriesIterator;

public interface Series {

    SeriesIterator createForwardIterator();
    SeriesIterator createReverseIterator();
}
