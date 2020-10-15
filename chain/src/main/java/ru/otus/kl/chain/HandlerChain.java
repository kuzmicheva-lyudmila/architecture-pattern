package ru.otus.kl.chain;

import java.io.IOException;

public interface HandlerChain {

    void process(String inputFilenames, String outputFilename) throws IOException;
}
