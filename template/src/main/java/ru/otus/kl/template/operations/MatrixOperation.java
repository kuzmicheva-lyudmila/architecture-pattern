package ru.otus.kl.template.operations;

import java.io.IOException;
import java.util.List;

import static ru.otus.kl.template.utils.FileUtils.readData;
import static ru.otus.kl.template.utils.FileUtils.writeData;

public abstract class MatrixOperation {

    private final String inputFilename;
    private final String outputFilename;

    MatrixOperation(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
    }

    public void execute() throws IOException {
        List<String> data = readData(inputFilename);
        List<String> result = process(data);
        writeData(result, outputFilename);
    }

    abstract List<String> process(List<String> data);
}
