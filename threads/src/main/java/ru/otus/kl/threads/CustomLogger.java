package ru.otus.kl.threads;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomLogger {

    private static final CustomLogger CUSTOM_LOGGER = new CustomLogger();

    private final Path logFilePath;

    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static CustomLogger getInstance() {
        return CUSTOM_LOGGER;
    }

    private CustomLogger() {
        this.logFilePath = getLogFilePath();
        new Thread(this::take).start();
    }

    public void put(String value, Object... args) {
        try {
            queue.put(String.format(value, args));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Path getLogFilePath() {
        try (InputStream input = CustomLogger.class.getClassLoader().getResourceAsStream("application.yml")) {
            Properties properties = new Properties();
            if (input == null) {
                throw new IllegalArgumentException();
            }

            properties.load(input);
            String filename = properties.getProperty("logfile");
            if (filename == null || filename.isEmpty()) {
                throw new IllegalArgumentException();
            }

            return Path.of(filename);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @SuppressWarnings("squid:S2189")
    private void take() {
        try {
            while (true) {
                String logString = queue.take();
                saveToFile(logString);
            }
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void saveToFile(String value) throws IOException {
        Files.writeString(logFilePath, value + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
