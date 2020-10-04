package ru.otus.kl.chain.handler;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static ru.otus.kl.chain.FileUtils.saveToFile;

@Slf4j
public abstract class Handler {

    private final Extension extension;
    private Handler next;

    public Handler(Extension extension) {
        this.extension = extension;
    }

    public Handler linkWidth(Handler next) {
        this.next = next;
        return next;
    }

    public boolean handle(Extension extension, String inputFilename, String outputFilename) throws IOException {
        if (this.extension == extension) {
            log.info("The handler {} gets file {}", extension, inputFilename);
            saveToFile(inputFilename, outputFilename);
            return true;
        }

        return handleNext(extension, inputFilename, outputFilename);
    }

    protected boolean handleNext(Extension extension, String inputFilename, String outputFilename) throws IOException {
        if (next == null) {
            return false;
        }
        return next.handle(extension, inputFilename, outputFilename);
    }
}
