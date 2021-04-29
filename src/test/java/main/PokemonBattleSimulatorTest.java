package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonBattleSimulatorTest {
    private PokemonBattleSimulator pbs;

    @BeforeEach
    public void createSim() {
        pbs = new PokemonBattleSimulator();
    }

    @Test
    public void testDisplayArt() throws IOException {
        String expected = "\r\n" +
                String.join("\r\n", Files.readAllLines(Paths.get("data/art/mcharizardxoff-60.txt"), StandardCharsets.UTF_8)) +
                "\r\n";
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        pbs.displayArt();
        String actual = outStream.toString();
        System.setOut(System.out);
        assertEquals(expected, actual);
    }
}
