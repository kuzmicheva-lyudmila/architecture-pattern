package ru.otus.factory.abstractfactory;

import lombok.extern.slf4j.Slf4j;
import ru.otus.factory.factory.Sorter;
import ru.otus.factory.factory.SortingAction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
public class ActionFactoryForFile implements ActionFactory {

    private final String inputFilename;
    private final String outputFilename;

    public ActionFactoryForFile(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
    }

    @Override
    public void sorting(String sortingType) {
        int[] inputArray;
        try {
            inputArray = readFromFile();
        } catch (IOException e) {
            log.error("Can't read file {}", inputFilename, e);
            return;
        }

        Sorter sorter = SortingAction.getSorter(sortingType);
        if (sorter == null) {
            log.error("Can't find a sorter for type = {}", sortingType);
            return;
        }
        int[] outputArray = sorter.sort(inputArray);

        try {
            writeToFile(outputArray);
        } catch (IOException e) {
            log.error("Can't write to file {}", inputFilename, e);
        }
    }

    private int[] readFromFile() throws IOException {
        String inputLine = Files.readString(Paths.get(inputFilename));
        if (inputLine.equals("")) {
            throw new IOException();
        }

        return Arrays.stream(inputLine.split(";")).mapToInt(Integer::parseInt).toArray();
    }

    private void writeToFile(int[] outputArray) throws IOException {
        Files.write(Paths.get(outputFilename), Collections.singletonList(Arrays.toString(outputArray)));
    }
}
