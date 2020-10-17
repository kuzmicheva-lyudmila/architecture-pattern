package ru.otus.kl.template;

import ru.otus.kl.template.operations.Determinant;
import ru.otus.kl.template.operations.MatrixOperation;
import ru.otus.kl.template.operations.Plus;
import ru.otus.kl.template.operations.Transpose;

import java.io.IOException;

public class DefaultClient {

    public static final String DETERMINANT = "determinant";
    public static final String TRANSPOSE = "transpose";
    public static final String PLUS = "plus";

    private final String inputFilename;
    private final String outputFilename;
    private final String matrixCommand;

    public DefaultClient(String inputFilename, String outputFilename, String matrixCommand) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
        this.matrixCommand = matrixCommand;
    }

    public void execute() throws IOException {
        MatrixOperation matrixOperation;
        switch (matrixCommand) {
            case DETERMINANT:
                matrixOperation = new Determinant(inputFilename, outputFilename);
                break;
            case TRANSPOSE:
                matrixOperation = new Transpose(inputFilename, outputFilename);
                break;
            case PLUS:
                matrixOperation = new Plus(inputFilename, outputFilename);
                break;
            default:
                matrixOperation = null;
        }

        if (matrixOperation != null) {
            matrixOperation.execute();
        }
    }
}
