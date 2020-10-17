package ru.otus.kl.template;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TemplateTest {
    private static final String INPUT_FILENAME = "input.txt";
    private static final String OUTPUT_FILENAME = "output.txt";

    private static final int DIMENSION = 4;
    private static final List<String> INPUT_DATA = List.of("1,4,5,6", "3,4,6,3", "2,4,6,2", "4,2,5,6");

    private static final double[][] EXPECTED_DATA_PLUS = {
            new double[] {2d, 8d, 10d, 12d},
            new double[] {6d, 8d, 12d, 6d},
            new double[] {4d, 8d, 12d, 4d},
            new double[] {8d, 4d, 10d, 12d}
    };

    private static final double[][] EXPECTED_DATA_TRANSPOSE = {
            new double[] {1d, 3d, 2d, 4d},
            new double[] {4d, 4d, 4d, 2d},
            new double[] {5d, 6d, 6d, 5d},
            new double[] {6d, 3d, 2d, 6d}
    };

    private static final double EXPECTED_DATA_DETERMINANT = -48d;

    @Test
    void testOperationPlus() throws IOException {
        List<String> data = new ArrayList<>(INPUT_DATA);
        data.add("");
        data.addAll(INPUT_DATA);
        prepareInputData(data);
        new DefaultClient(INPUT_FILENAME, OUTPUT_FILENAME, DefaultClient.PLUS).execute();

        List<String> actualData = prepareActualData().subList(0, 4);
        List<String> expectedData = prepareExpectedData(EXPECTED_DATA_PLUS);
        assertThat(actualData).isNotEmpty()
                .isEqualTo(expectedData);
    }

    @Test
    void testOperationTranspose() throws IOException {
        prepareInputData(new ArrayList<>(INPUT_DATA));
        new DefaultClient(INPUT_FILENAME, OUTPUT_FILENAME, DefaultClient.TRANSPOSE).execute();

        List<String> actualData = prepareActualData().subList(0, 4);
        List<String> expectedData = prepareExpectedData(EXPECTED_DATA_TRANSPOSE);
        assertThat(actualData).isNotEmpty()
                .isEqualTo(expectedData);
    }

    @Test
    void testOperationDeterminant() throws IOException {
        prepareInputData(new ArrayList<>(INPUT_DATA));
        new DefaultClient(INPUT_FILENAME, OUTPUT_FILENAME, DefaultClient.DETERMINANT).execute();

        List<String> actualData = prepareActualData().subList(0, 1);
        List<String> expectedData = Collections.singletonList(String.valueOf(EXPECTED_DATA_DETERMINANT));
        assertThat(actualData).isNotEmpty()
                .isEqualTo(expectedData);
    }

    private void prepareInputData(List<String> data) throws IOException {
        Files.write(Path.of(INPUT_FILENAME), data);
    }

    private List<String> prepareActualData() throws IOException {
        return Files.readAllLines(Paths.get(OUTPUT_FILENAME));
    }

    private List<String> prepareExpectedData(double[][] array) {
        List<String> data = new ArrayList<>(DIMENSION);
        for (int row = 0; row < DIMENSION; row++) {
            data.add(Arrays.toString(array[row]));
        }

        return data;
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        Files.deleteIfExists(Paths.get(INPUT_FILENAME));
        Files.deleteIfExists(Paths.get(OUTPUT_FILENAME));
    }
}

