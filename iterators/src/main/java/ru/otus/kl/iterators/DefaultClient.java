package ru.otus.kl.iterators;

import ru.otus.kl.iterators.series.Series;
import ru.otus.kl.iterators.iterator.SeriesIterator;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultClient implements Client{

    private final String outputFilename;
    private final Series series;

    public DefaultClient(String outputFilename, Series series) {
        this.outputFilename = outputFilename;
        this.series = series;
    }

    @Override
    public void getReverseRange(BigInteger minValue, BigInteger maxValue) throws IOException {
        getRange(minValue, maxValue, series.createReverseIterator());
    }

    @Override
    public void getForwardRange(BigInteger minValue, BigInteger maxValue) throws IOException {
        getRange(minValue, maxValue, series.createForwardIterator());
    }

    private void getRange(BigInteger minValue, BigInteger maxValue, SeriesIterator iterator) throws IOException {
        checkRange(minValue, maxValue);
        List<BigInteger> list = generateRangeList(minValue, maxValue, iterator);
        saveToFile(list);
    }

    private void checkRange(BigInteger minValue, BigInteger maxValue) {
        if (minValue.compareTo(maxValue) > 0) {
            createIllegalException(minValue, maxValue);
        }
    }

    private List<BigInteger> generateRangeList(BigInteger minValue, BigInteger maxValue, SeriesIterator iterator) {
        List<BigInteger> list = new ArrayList<>();
        while (iterator.hasNext()) {
            BigInteger element = iterator.getNext();
            if (element.compareTo(minValue) >= 0 && element.compareTo(maxValue) <=0) {
                list.add(element);
            }
        }

        if (list.size() == 0) {
            createIllegalException(minValue, maxValue);
        }

        return list;
    }

    private void createIllegalException(BigInteger minValue, BigInteger maxValue) {
        throw new IllegalArgumentException(
                String.format("Incorrect range %s .. %s", minValue.toString(), maxValue.toString())
        );
    }

    private void saveToFile(List<BigInteger> result) throws IOException {
        List<String> stringResult = result.stream().map(BigInteger::toString).collect(Collectors.toList());
        Files.write(Paths.get(outputFilename), stringResult);
    }
}
