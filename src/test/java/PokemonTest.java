import battle.Pokemon;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {
    private Pokemon defaultPikachu;

    @BeforeEach
    private void createDefaultPikachu() {
        defaultPikachu = new Pokemon("Pikachu", "Adamant", 10, 10, 10, 10, 10, 10);
    }

    @Test
    public void testPokemon1InvalidNameFails() {
        assertThrows(NullPointerException.class, () -> new Pokemon("Pokachu", "Adamant", 10, 10, 10, 10, 10, 10));
    }

    @Test
    public void testPokemon1InvalidNatureFails() {
        assertThrows(NullPointerException.class, () -> new Pokemon("Pikachu", "Adomant", 10, 10, 10, 10, 10, 10));
    }

    @Test
    public void testPokemon2InvalidNameFails() {
        String[] moves = {"Tackle"};
        assertThrows(NullPointerException.class, () -> new Pokemon("Pokachu", "Adamant", 10, 10, 10, 10, 10, 10, moves));
    }

    @Test
    public void testPokemon2InvalidNatureFails() {
        String[] moves = {"Tackle"};
        assertThrows(NullPointerException.class, () -> new Pokemon("Pikachu", "Adomant", 10, 10, 10, 10, 10, 10, moves));
    }

    @Test
    public void testPokemon2InvalidMovesFails() {
        String[] moves = {"Tockle"};
        assertThrows(NullPointerException.class, () -> new Pokemon("Pikachu", "Adomant", 10, 10, 10, 10, 10, 10, moves));
    }

    @Test
    public void testPokemon3InvalidNameFails() {
        String[] type = {"Electric"};
        assertThrows(NullPointerException.class, () -> new Pokemon("Pokachu", type, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
    }

    @Test
    public void testPokemon3InvalidNatureFails() {
        String[] type = {"Electric"};
        assertThrows(NullPointerException.class, () -> new Pokemon("Pikachu", type, "Adomant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
    }

    @Test
    public void testPokemon3InvalidTypeFails() {
        String[] type = {"Elactric"};
        assertThrows(NullPointerException.class, () -> new Pokemon("Pikachu", type, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
    }

    @Test
    public void testPokemon1HasCorrectName() {
        assertEquals("Pikachu", defaultPikachu.getName());
    }

    @Test
    public void testPokemon1HasCorrectNature() {
        assertEquals("Adamant", defaultPikachu.getNature());
    }

    @Test
    public void testPokemon1HasCorrectType() {
        String[] expectedType = {"Electric"};
        assertArrayEquals(expectedType, defaultPikachu.getType());
    }

    @Test
    public void testPokemon1HasCorrectTypes() {
        Pokemon charizard = new Pokemon("Charizard", "Adamant", 10, 10, 10, 10, 10, 10);
        String[] expectedType = {"Fire", "Flying"};
        assertArrayEquals(expectedType, charizard.getType());
    }

    @Test
    public void testPokemon2OneMoveHasCorrectName() {
        String[] moves = {"Tackle"};
        Pokemon pikachu = new Pokemon("Pikachu", "Adamant", 10, 10, 10, 10, 10, 10, moves);
        assertEquals("Pikachu", pikachu.getName());
    }

    @Test
    public void testPokemon2HasCorrectName() {
        String[] moves = {"Tackle", "Thunderbolt", "Quick Attack", "Growl"};
        Pokemon pikachu = new Pokemon("Pikachu", "Adamant", 10, 10, 10, 10, 10, 10, moves);
        assertEquals("Pikachu", pikachu.getName());
    }

    @Test
    public void testPokemon2HasCorrectNature() {
        String[] moves = {"Tackle", "Thunderbolt", "Quick Attack", "Growl"};
        Pokemon pikachu = new Pokemon("Pikachu", "Adamant", 10, 10, 10, 10, 10, 10, moves);
        assertEquals("Adamant", pikachu.getNature());
    }

    @Test
    public void testPokemon2HasCorrectType() {
        String[] moves = {"Tackle", "Thunderbolt", "Quick Attack", "Growl"};
        Pokemon pikachu = new Pokemon("Pikachu", "Adamant", 10, 10, 10, 10, 10, 10, moves);
        String[] expectedType = {"Electric"};
        assertArrayEquals(expectedType, pikachu.getType());
    }

    @Test
    public void testPokemon2HasCorrectTypes() {
        String[] moves = {"Tackle", "Thunderbolt", "Quick Attack", "Growl"};
        Pokemon charizard = new Pokemon("Charizard", "Adamant", 10, 10, 10, 10, 10, 10, moves);
        String[] expectedType = {"Fire", "Flying"};
        assertArrayEquals(expectedType, charizard.getType());
    }

    @Test
    public void testPokemon2HasCorrectMoves() {
        String[] moves = {"Tackle", "Thunderbolt", "Quick Attack", "Growl"};
        Pokemon pikachu = new Pokemon("Pikachu", "Adamant", 10, 10, 10, 10, 10, 10, moves);
        assertEquals(moves[0], pikachu.getMove(0).getName());
        assertEquals(moves[1], pikachu.getMove(1).getName());
        assertEquals(moves[2], pikachu.getMove(2).getName());
        assertEquals(moves[3], pikachu.getMove(3).getName());
    }

    @Test
    public void testMovesToString() {
        String[] moves = {"Tackle", "Thunderbolt", "Quick Attack", "Growl"};
        Pokemon pikachu = new Pokemon("Pikachu", "Adamant", 10, 10, 10, 10, 10, 10, moves);
        assertEquals("(0) Tackle (1) Thunderbolt (2) Quick Attack (3) Growl", pikachu.movesToString());
    }

    @Test
    public void testPokemon3HasCorrectName() {
        String[] type = {"Electric"};
        Pokemon pikachu = new Pokemon("Pikachu", type, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        assertEquals("Pikachu", pikachu.getName());
    }

    @Test
    public void testPokemon3HasCorrectNature() {
        String[] type = {"Electric"};
        Pokemon pikachu = new Pokemon("Pikachu", type, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        assertEquals("Adamant", pikachu.getNature());
    }

    @Test
    public void testPokemon3HasCorrectType() {
        String[] type = {"Electric"};
        Pokemon pikachu = new Pokemon("Pikachu", type, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        assertArrayEquals(type, pikachu.getType());
    }

    @Test
    public void testPokemon3HasCorrectTypes() {
        String[] type = {"Fire", "Flying"};
        Pokemon charizard = new Pokemon("Charizard", type, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        assertArrayEquals(type, charizard.getType());
    }

    // Pokemon base stats must be positive.
    @Test
    public void testSetHP() {
        defaultPikachu.setHP(100);
        assertEquals(100, defaultPikachu.getHP());
    }

    @Test
    public void testSetHPZero() {
        defaultPikachu.setHP(0);
        assertEquals(1, defaultPikachu.getHP());
    }

    @Test
    public void testSetHPNegative() {
        defaultPikachu.setHP(-1);
        assertEquals(1, defaultPikachu.getHP());
    }

    @Test
    public void testSetAtk() {
        defaultPikachu.setAtk(100);
        assertEquals(100, defaultPikachu.getAtk());
    }

    @Test
    public void testSetAtkZero() {
        defaultPikachu.setAtk(0);
        assertEquals(1, defaultPikachu.getAtk());
    }

    @Test
    public void testSetAtkNegative() {
        defaultPikachu.setAtk(-1);
        assertEquals(1, defaultPikachu.getAtk());
    }

    @Test
    public void testSetDef() {
        defaultPikachu.setDef(100);
        assertEquals(100, defaultPikachu.getDef());
    }

    @Test
    public void testSetDefZero() {
        defaultPikachu.setDef(0);
        assertEquals(1, defaultPikachu.getDef());
    }

    @Test
    public void testSetDefNegative() {
        defaultPikachu.setDef(-1);
        assertEquals(1, defaultPikachu.getDef());
    }

    @Test
    public void testSetSpA() {
        defaultPikachu.setSpA(100);
        assertEquals(100, defaultPikachu.getSpA());
    }

    @Test
    public void testSetSpAZero() {
        defaultPikachu.setSpA(0);
        assertEquals(1, defaultPikachu.getSpA());
    }

    @Test
    public void testSetSpANegative() {
        defaultPikachu.setSpA(-1);
        assertEquals(1, defaultPikachu.getSpA());
    }

    @Test
    public void testSetSpD() {
        defaultPikachu.setSpD(100);
        assertEquals(100, defaultPikachu.getSpD());
    }

    @Test
    public void testSetSpDZero() {
        defaultPikachu.setSpD(0);
        assertEquals(1, defaultPikachu.getSpD());
    }

    @Test
    public void testSetSpDNegative() {
        defaultPikachu.setSpD(-1);
        assertEquals(1, defaultPikachu.getSpD());
    }

    @Test
    public void testSetSpe() {
        defaultPikachu.setSpe(100);
        assertEquals(100, defaultPikachu.getSpe());
    }

    @Test
    public void testSetSpeZero() {
        defaultPikachu.setSpe(0);
        assertEquals(1, defaultPikachu.getSpe());
    }

    @Test
    public void testSetSpeNegative() {
        defaultPikachu.setSpe(-1);
        assertEquals(1, defaultPikachu.getSpe());
    }

    // All current stats can take on any value besides HP, which must range from [0, maxHP]
    @Test
    public void testSetCurrentHP() {
        defaultPikachu.setCurrentHP(defaultPikachu.getHP()-1);
        assertEquals(defaultPikachu.getHP()-1, defaultPikachu.getCurrentHP());
    }

    @Test
    public void testSetCurrentHPZero() {
        defaultPikachu.setCurrentHP(0);
        assertEquals(0, defaultPikachu.getCurrentHP());
    }

    @Test
    public void testSetCurrentHPNegative() {
        defaultPikachu.setCurrentHP(-1);
        assertEquals(0, defaultPikachu.getCurrentHP());
    }

    @Test
    public void testSetCurrentHPAboveMax() {
        defaultPikachu.setCurrentHP(defaultPikachu.getHP()+1);
        assertEquals(defaultPikachu.getHP(), defaultPikachu.getCurrentHP());
    }

    @Test
    public void testSetCurrentAtkNegative() {
        defaultPikachu.setCurrentAtk(-1);
        assertEquals(-1, defaultPikachu.getCurrentAtk());
    }

    @Test
    public void testSetCurrentAtkZero() {
        defaultPikachu.setCurrentAtk(0);
        assertEquals(0, defaultPikachu.getCurrentAtk());
    }

    @Test
    public void testSetCurrentAtk() {
        defaultPikachu.setCurrentAtk(defaultPikachu.getAtk()-1);
        assertEquals(defaultPikachu.getAtk()-1, defaultPikachu.getCurrentAtk());
    }

    @Test
    public void testSetCurrentAtkAboveMax() {
        defaultPikachu.setCurrentAtk(defaultPikachu.getAtk()+1);
        assertEquals(defaultPikachu.getAtk()+1, defaultPikachu.getCurrentAtk());
    }

    @Test
    public void testSetCurrentDefNegative() {
        defaultPikachu.setCurrentDef(-1);
        assertEquals(-1, defaultPikachu.getCurrentDef());
    }

    @Test
    public void testSetCurrentDefZero() {
        defaultPikachu.setCurrentDef(0);
        assertEquals(0, defaultPikachu.getCurrentDef());
    }

    @Test
    public void testSetCurrentDef() {
        defaultPikachu.setCurrentDef(defaultPikachu.getDef()-1);
        assertEquals(defaultPikachu.getDef()-1, defaultPikachu.getCurrentDef());
    }

    @Test
    public void testSetCurrentDefAboveMax() {
        defaultPikachu.setCurrentDef(defaultPikachu.getDef()+1);
        assertEquals(defaultPikachu.getDef()+1, defaultPikachu.getCurrentDef());
    }

    @Test
    public void testSetCurrentSpANegative() {
        defaultPikachu.setCurrentSpA(-1);
        assertEquals(-1, defaultPikachu.getCurrentSpA());
    }

    @Test
    public void testSetCurrentSpAZero() {
        defaultPikachu.setCurrentSpA(0);
        assertEquals(0, defaultPikachu.getCurrentSpA());
    }

    @Test
    public void testSetCurrentSpA() {
        defaultPikachu.setCurrentSpA(defaultPikachu.getSpA()-1);
        assertEquals(defaultPikachu.getSpA()-1, defaultPikachu.getCurrentSpA());
    }

    @Test
    public void testSetCurrentSpAAboveMax() {
        defaultPikachu.setCurrentSpA(defaultPikachu.getSpA()+1);
        assertEquals(defaultPikachu.getSpA()+1, defaultPikachu.getCurrentSpA());
    }

    @Test
    public void testSetCurrentSpDNegative() {
        defaultPikachu.setCurrentSpD(-1);
        assertEquals(-1, defaultPikachu.getCurrentSpD());
    }

    @Test
    public void testSetCurrentSpDZero() {
        defaultPikachu.setCurrentSpD(0);
        assertEquals(0, defaultPikachu.getCurrentSpD());
    }

    @Test
    public void testSetCurrentSpD() {
        defaultPikachu.setCurrentSpD(defaultPikachu.getSpD()-1);
        assertEquals(defaultPikachu.getSpD()-1, defaultPikachu.getCurrentSpD());
    }

    @Test
    public void testSetCurrentSpDAboveMax() {
        defaultPikachu.setCurrentSpD(defaultPikachu.getSpD()+1);
        assertEquals(defaultPikachu.getSpD()+1, defaultPikachu.getCurrentSpD());
    }

    @Test
    public void testSetCurrentSpeNegative() {
        defaultPikachu.setCurrentSpe(-1);
        assertEquals(-1, defaultPikachu.getCurrentSpe());
    }

    @Test
    public void testSetCurrentSpeZero() {
        defaultPikachu.setCurrentSpe(0);
        assertEquals(0, defaultPikachu.getCurrentSpe());
    }

    @Test
    public void testSetCurrentSpe() {
        defaultPikachu.setCurrentSpe(defaultPikachu.getSpe()-1);
        assertEquals(defaultPikachu.getSpe()-1, defaultPikachu.getCurrentSpe());
    }

    @Test
    public void testSetCurrentSpeAboveMax() {
        defaultPikachu.setCurrentSpe(defaultPikachu.getSpe()+1);
        assertEquals(defaultPikachu.getSpe()+1, defaultPikachu.getCurrentSpe());
    }

    // In Pokemon, stats are boosted in stages ranging from [-6, 6].
    @Test
    public void testBoostAtkZero() {
        defaultPikachu.setCurrentAtk(100);
        defaultPikachu.boostAtk(0);
        assertEquals(100, defaultPikachu.getCurrentAtk());
    }

    @Test
    public void testBoostAtkNegative() {
        defaultPikachu.setCurrentAtk(100);
        defaultPikachu.boostAtk(-1);
        assertEquals(67, defaultPikachu.getCurrentAtk());
    }

    @Test
    public void testBoostAtkPositive() {
        defaultPikachu.setCurrentAtk(100);
        defaultPikachu.boostAtk(1);
        assertEquals(150, defaultPikachu.getCurrentAtk());
    }

    @Test
    public void testBoostAtkOutOfRangeNegative() {
        assertThrows(InvalidArgumentException.class, () -> defaultPikachu.boostAtk(-7));
    }

    @Test
    public void testBoostAtkOutOfRangePositive() {
        assertThrows(InvalidArgumentException.class, () -> defaultPikachu.boostAtk(7));
    }

    // All Pokemon have between 1 and 4 moves.
    @Test
    public void testSetNoMoves() {
        String[] moves = {};
        assertThrows(InvalidArgumentException.class, () -> defaultPikachu.setMoves(moves));
    }

    @Test
    public void testSetOneMove() {
        String[] moves = {"Tackle"};
        defaultPikachu.setMoves(moves);
        assertEquals(moves[0], defaultPikachu.getMove(0).getName());
    }

    @Test
    public void testSetFourMoves() {
        String[] moves = {"Tackle", "Growl", "Thunderbolt", "Quick Attack"};
        defaultPikachu.setMoves(moves);
        assertEquals(moves[0], defaultPikachu.getMove(0).getName());
        assertEquals(moves[1], defaultPikachu.getMove(1).getName());
        assertEquals(moves[2], defaultPikachu.getMove(2).getName());
        assertEquals(moves[3], defaultPikachu.getMove(3).getName());
    }

    @Test
    public void testSetFiveMoves() {
        String[] moves = {"Tackle", "Growl", "Thunderbolt", "Quick Attack", "Splash"};
        assertThrows(InvalidArgumentException.class, () -> defaultPikachu.setMoves(moves));
    }

    @Test
    public void testSetInvalidMove() {
        String[] moves = {"Big Slap!"};
        assertThrows(NullPointerException.class, () -> defaultPikachu.setMoves(moves));
    }

    @Test
    public void testCheckStateNonfainted() {
        defaultPikachu.setCurrentHP(5);
        defaultPikachu.checkState();
        assertEquals(1, defaultPikachu.getState());
    }

    @Test
    public void testCheckStateFainted() {
        defaultPikachu.setCurrentHP(0);
        defaultPikachu.checkState();
        assertEquals(0, defaultPikachu.getState());
    }

    @Test
    public void testStatsToString() {
        String expected = defaultPikachu.getHP() + " " +
                defaultPikachu.getAtk() + " " +
                defaultPikachu.getDef() + " " +
                defaultPikachu.getSpA() + " " +
                defaultPikachu.getSpD() + " " +
                defaultPikachu.getSpe();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        defaultPikachu.statsToString();
        String actual = outStream.toString();
        System.setOut(System.out);
        assertEquals(expected, actual);
    }

    // Pokemon natures affect stats. Essentially each nature gives a buff to one stat and a debuff to another, or does
    // nothing. To achieve "Each Choice" coverage here, we're testing the following natures:
    // Hardy, lonely, bold, rash, calm, jolly, quiet

    private Pokemon setBaseStatsForTesting(String nature) {
        Pokemon pikachu = new Pokemon("Pikachu", nature, 10, 10, 10, 10, 10, 10);
        pikachu.setHP(100);
        pikachu.setAtk(100);
        pikachu.setDef(100);
        pikachu.setSpA(100);
        pikachu.setSpD(100);
        pikachu.setSpe(100);
        return pikachu;
    }

    @Test
    public void testCalculateNatureHardy() {
        Pokemon pikachu = setBaseStatsForTesting("Hardy");
        pikachu.calculateNature();
        assertEquals(100, pikachu.getHP());
        assertEquals(100, pikachu.getAtk());
        assertEquals(100, pikachu.getDef());
        assertEquals(100, pikachu.getSpA());
        assertEquals(100, pikachu.getSpD());
        assertEquals(100, pikachu.getSpe());
    }

    @Test
    public void testCalculateNatureLonely() {
        Pokemon pikachu = setBaseStatsForTesting("Lonely");
        pikachu.calculateNature();
        assertEquals(100, pikachu.getHP());
        assertEquals(110, pikachu.getAtk());
        assertEquals(90, pikachu.getDef());
        assertEquals(100, pikachu.getSpA());
        assertEquals(100, pikachu.getSpD());
        assertEquals(100, pikachu.getSpe());
    }

    @Test
    public void testCalculateNatureBold() {
        Pokemon pikachu = setBaseStatsForTesting("Bold");
        pikachu.calculateNature();
        assertEquals(100, pikachu.getHP());
        assertEquals(90, pikachu.getAtk());
        assertEquals(110, pikachu.getDef());
        assertEquals(100, pikachu.getSpA());
        assertEquals(100, pikachu.getSpD());
        assertEquals(100, pikachu.getSpe());
    }

    @Test
    public void testCalculateNatureRash() {
        Pokemon pikachu = setBaseStatsForTesting("Rash");
        pikachu.calculateNature();
        assertEquals(100, pikachu.getHP());
        assertEquals(100, pikachu.getAtk());
        assertEquals(100, pikachu.getDef());
        assertEquals(110, pikachu.getSpA());
        assertEquals(90, pikachu.getSpD());
        assertEquals(100, pikachu.getSpe());
    }

    @Test
    public void testCalculateNatureCalm() {
        Pokemon pikachu = setBaseStatsForTesting("Calm");
        pikachu.calculateNature();
        assertEquals(100, pikachu.getHP());
        assertEquals(90, pikachu.getAtk());
        assertEquals(100, pikachu.getDef());
        assertEquals(100, pikachu.getSpA());
        assertEquals(110, pikachu.getSpD());
        assertEquals(100, pikachu.getSpe());
    }

    @Test
    public void testCalculateNatureJolly() {
        Pokemon pikachu = setBaseStatsForTesting("Jolly");
        pikachu.calculateNature();
        assertEquals(100, pikachu.getHP());
        assertEquals(100, pikachu.getAtk());
        assertEquals(100, pikachu.getDef());
        assertEquals(90, pikachu.getSpA());
        assertEquals(100, pikachu.getSpD());
        assertEquals(110, pikachu.getSpe());
    }

    @Test
    public void testCalculateNatureQuiet() {
        Pokemon pikachu = setBaseStatsForTesting("Quiet");
        pikachu.calculateNature();
        assertEquals(100, pikachu.getHP());
        assertEquals(100, pikachu.getAtk());
        assertEquals(100, pikachu.getDef());
        assertEquals(110, pikachu.getSpA());
        assertEquals(100, pikachu.getSpD());
        assertEquals(90, pikachu.getSpe());
    }

    // Data taken from reference implementation here:
    // https://pycosites.com/pkmn/stat.php
    @Test
    public void testStatCalculations() {
        String[] type = {"Water"};
        Pokemon magikarp = new Pokemon("Magikarp", type, "Adamant", 100, 20, 10, 55, 15, 20, 80, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        assertEquals(162, magikarp.getHP());
        assertEquals(40, magikarp.getAtk());
        assertEquals(127, magikarp.getDef());
        assertEquals(42, magikarp.getSpA());
        assertEquals(57, magikarp.getSpD());
        assertEquals(177, magikarp.getSpe());
    }
}
