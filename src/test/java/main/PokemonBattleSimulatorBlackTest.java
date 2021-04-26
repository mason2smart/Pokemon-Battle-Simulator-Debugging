package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokemonBattleSimulatorBlackTest {
    PokemonBattleSimulator pokemonBattleSimulator = new PokemonBattleSimulator();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        PokemonBattleSimulator pokemonBattleSimulator = new PokemonBattleSimulator();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void NewBattleSimTest() throws InterruptedException {
        Thread a = new Thread (){
            @Override
            public void run() {
                pokemonBattleSimulator.runSimulator();
            }
        };a.run();
        assertEquals("Welcome to the 2016 Pokemon Battle Simulator!!!", outputStreamCaptor.toString()
                .trim());
        a.destroy();
    }

    @Test
    void testRunSimulator() {
        pokemonBattleSimulator.runSimulator();
    }

    @Test
    void testDisplayArt() {
        pokemonBattleSimulator.displayArt();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme