package ru.otus.kl.chain;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChainTest {

    private static final String INPUT_FILENAME = "input.txt";
    private static final String JSON_FILENAME = "first.json";
    private static final String TXT_FILENAME = "second.txt";
    private static final String XML_FILENAME = "third.xml";
    private static final String CSV_FILENAME = "four.csv";
    private static final String OUTPUT_FILENAME = "output.txt";
    private static final String OUTPUT_EXPECTED_FILENAME = "output_expected.txt";

    public static final String JSON_DATA
            = "{\n" +
            "  \"menu\": {\n" +
            "    \"id\": \"file\",\n" +
            "    \"value\": \"File\"\n" +
            "  }\n" +
            "}\n";
    public static final String TXT_DATA
            = "Цепочка Обязанностей (Chain of responsibility) - поведенческий шаблон проектирования\n";
    public static final String XML_DATA
            = "<menu id=\"file\" value=\"File\">\n" +
            "    <menuitem value=\"New\" onclick=\"CreateNewDoc()\"/>\n" +
            "</menu>\n";
    public static final String CSV_DATA = "java.io.IOException;java.nio.file.Files;java.nio.file.Path;\n";

    @Test
    void testCheckChain() throws IOException {
        prepareData();
        new DefaultHandlerChain().process(INPUT_FILENAME, OUTPUT_FILENAME);

        List<String> outputData = FileUtils.readFile(OUTPUT_FILENAME);
        List<String> outputExpectedData = FileUtils.readFile(OUTPUT_EXPECTED_FILENAME);
        assertThat(outputData).isNotEmpty()
                .isEqualTo(outputExpectedData);
    }

    private void prepareData() throws IOException {
        Files.write(Paths.get(JSON_FILENAME), Collections.singleton(JSON_DATA));
        Files.write(Paths.get(TXT_FILENAME), Collections.singleton(TXT_DATA));
        Files.write(Paths.get(XML_FILENAME), Collections.singleton(XML_DATA));
        Files.write(Path.of(CSV_FILENAME), Collections.singleton(CSV_DATA));
        Files.write(Path.of(INPUT_FILENAME), List.of(JSON_FILENAME, XML_FILENAME, CSV_FILENAME, TXT_FILENAME));
        Files.write(Path.of(OUTPUT_EXPECTED_FILENAME), List.of(JSON_DATA, XML_DATA, CSV_DATA, TXT_DATA));
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        Files.deleteIfExists(Paths.get(INPUT_FILENAME));
        Files.deleteIfExists(Paths.get(JSON_FILENAME));
        Files.deleteIfExists(Paths.get(TXT_FILENAME));
        Files.deleteIfExists(Paths.get(XML_FILENAME));
        Files.deleteIfExists(Paths.get(CSV_FILENAME));
        Files.deleteIfExists(Paths.get(OUTPUT_FILENAME));
        Files.deleteIfExists(Paths.get(OUTPUT_EXPECTED_FILENAME));
    }
}
