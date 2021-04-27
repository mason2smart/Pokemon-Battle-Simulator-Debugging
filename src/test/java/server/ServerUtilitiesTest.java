package server;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerUtilitiesTest {
    @Test
    public void testWriteToFileFromReader() throws IOException {
        ServerUtilities.writeToFileFromReader(new BufferedReader(new FileReader("data/test/readTest.txt")), new PrintWriter("data/test/writeTest.txt"));
        String fileContents = String.join("\n", Files.readAllLines(Paths.get("data/test/writeTest.txt"), StandardCharsets.UTF_8));
        assertEquals("Hello, world!\nHow's it going?", fileContents);
    }

    @Test
    public void testWriteToReaderFromFile() throws IOException {
        ServerUtilities.writeToReaderFromFile(new BufferedReader(new FileReader("data/test/readTest1.txt")), new PrintWriter("data/test/writeTest1.txt"));
        String fileContents = String.join("\n", Files.readAllLines(Paths.get("data/test/writeTest1.txt"), StandardCharsets.UTF_8));
        assertEquals("Hello, world!\nHow's it going?", fileContents);
    }
}
