package ru.otus.kl.chain;

import lombok.extern.slf4j.Slf4j;
import ru.otus.kl.chain.handler.CsvHandler;
import ru.otus.kl.chain.handler.Extension;
import ru.otus.kl.chain.handler.Handler;
import ru.otus.kl.chain.handler.JsonHandler;
import ru.otus.kl.chain.handler.TxtHandler;
import ru.otus.kl.chain.handler.XmlHandler;

import java.io.IOException;
import java.util.List;

import static ru.otus.kl.chain.FileUtils.getExtension;
import static ru.otus.kl.chain.FileUtils.readFile;

@Slf4j
public class DefaultHandlerChain implements HandlerChain {

    private final Handler handler;

    public DefaultHandlerChain() {
        Handler handlerChain = new JsonHandler();
        handlerChain.linkWidth(new TxtHandler())
                .linkWidth(new XmlHandler())
                .linkWidth(new CsvHandler());
        this.handler = handlerChain;
    }

    @Override
    public void process(String inputFilenames, String outputFilename) throws IOException {
        List<String> inputFileNames = readFile(inputFilenames);
        checkExtension(inputFileNames);

        log.info("Start process ...");
        for (String filename : inputFileNames) {
            handler.handle(Extension.fromValue(getExtension(filename)), filename, outputFilename);
        }
        log.info("End process. Status: success");
    }

    private void checkExtension(List<String> filenames) {
        filenames.forEach(FileUtils::getExtension);
    }
}
