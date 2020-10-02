package ru.otus.kl.adapter;

import ru.otus.kl.adapter.firstprogram.DefaultMatrixOperations;
import ru.otus.kl.adapter.firstprogram.MatrixOperations;
import ru.otus.kl.adapter.secondprogram.MatrixOperationsAdapter;
import ru.otus.kl.adapter.secondprogram.DefaultGeneratedMatrices;
import ru.otus.kl.adapter.secondprogram.GeneratedMatrices;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

public class MainApplication {

    private static final String OUTPUT_FILENAME = "f1.txt";
    private static final String INPUT_FILENAME = "f0.txt";
    private static final String GENERATED_MATRICES_FILENAME = "f3.txt";

    public static void main(String[] args) throws IOException, URISyntaxException {
        MatrixOperations matrixOperations = new DefaultMatrixOperations();
        additionMatrices(matrixOperations, OUTPUT_FILENAME);

        GeneratedMatrices generatedMatrices = new DefaultGeneratedMatrices(4, 4);
        MatrixOperations matrixOperationsWithAdapter = new MatrixOperationsAdapter(generatedMatrices);
        additionMatrices(matrixOperationsWithAdapter, GENERATED_MATRICES_FILENAME);
    }

    private static void additionMatrices(
            MatrixOperations matrixOperations,
            String outputFilename
    ) throws URISyntaxException, IOException {
        URL url = MainApplication.class.getClassLoader().getResource(INPUT_FILENAME);
        if (url == null) {
            throw new IllegalArgumentException("Error on read file: file didn''t found");
        }

        matrixOperations.addition(
                Path.of(url.toURI()).toString(),
                Path.of(Path.of(url.toURI()).getParent().toString(), outputFilename).toString()
        );
    }
}
