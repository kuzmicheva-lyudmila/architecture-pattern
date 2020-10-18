package ru.otus.kl.iterators;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.otus.kl.iterators.series.FibonacciSeries;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestIterators {

    private static final String OUTPUT_FILENAME = "output.txt";

    private static final int MIN_VALUE = 10;
    private static final int MAX_VALUE = 100;

    private static final List<String> EXPECTED_FORWARD_SERIES = List.of("13", "21", "34", "55", "89");
    private static final List<String> EXPECTED_REVERSE_SERIES = List.of("89", "55", "34", "21", "13");

    @Test
    void testForwardSeries() throws IOException {
        Client client = new DefaultClient(OUTPUT_FILENAME, new FibonacciSeries());
        client.getForwardRange(BigInteger.valueOf(MIN_VALUE), BigInteger.valueOf(MAX_VALUE));

        List<String> actualData = readData();
        assertThat(actualData).isNotEmpty()
                .isEqualTo(EXPECTED_FORWARD_SERIES);
    }

    @Test
    void testReverseSeries() throws IOException {
        Client client = new DefaultClient(OUTPUT_FILENAME, new FibonacciSeries());
        client.getReverseRange(BigInteger.valueOf(MIN_VALUE), BigInteger.valueOf(MAX_VALUE));

        List<String> actualData = readData();
        assertThat(actualData).isNotEmpty()
                .isEqualTo(EXPECTED_REVERSE_SERIES);
    }

    private List<String> readData() throws IOException {
        return Files.readAllLines(Paths.get(OUTPUT_FILENAME));
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        Files.deleteIfExists(Paths.get(OUTPUT_FILENAME));
    }
}
