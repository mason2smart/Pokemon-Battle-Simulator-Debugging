import battle.BattleLoop;
import battle.Pokemon;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BattleLoopTest {
    private BattleLoop loop;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    private void createBattleLoop() {
        loop = new BattleLoop();
        System.setOut(new PrintStream(outContent));
    }


    // Tests if proper message and damage is displayed for an ineffective move
    @Test
    public void testBattleDamageTypeEff0() {
        String [] type1 = {"Rock", "Ground"};
        String [] type2 = {"Fire", "Flying"};
        String[] moves = {"Earthquake", "Sand Attack", "Defense Curl", "Rock Throw"};
        Pokemon p1 = new Pokemon("Geodude", type1, "Adamant", 27, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        p1.setMoves(moves);
        String[] moves2 = {"Ember", "Metal Claw", "SmokeScreen", "Flamethrower"};
        Pokemon p2 = new Pokemon("Charizard", type2, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        p2.setMoves(moves2);
        int damage = loop.battleDamage(p1, 0, p2);
        assertEquals(damage, 0);
        assertEquals("Earthquake does " + damage + " dmg to Charizard (type effectiveness implemented!)" +
                "\nIt doesn't affect " + p2.getName() + "...", outContent.toString());
    }

    // Tests if proper message and damage is displayed for a normally effective move
    @Test
    public void testBattleDamageTypeEff1() {
        String [] type1 = {"Electric"};
        String [] type2 = {"Fire", "Flying"};
        String[] moves = {"Tackle", "Thunderbolt", "Quick Attack", "Growl"};
        Pokemon p1 = new Pokemon("Pikachu", type1, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        p1.setMoves(moves);
        String[] moves2 = {"Ember", "Metal Claw", "SmokeScreen", "Flamethrower"};
        Pokemon p2 = new Pokemon("Charizard", type2, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        p2.setMoves(moves2);
        int damage = loop.battleDamage(p1, 0, p2);
        assertTrue(damage >= 5);
        assertEquals("Tackle does " + damage + " dmg to Charizard (type effectiveness implemented!)", outContent.toString());
    }

    // Tests if proper message and damage is displayed for a not-so-effective move
    @Test
    public void testBattleDamageTypeEffHalf() {
        String [] type1 = {"Rock", "Ground"};
        String [] type2 = {"Fire", "Flying"};
        String[] moves = {"Earthquake", "Sand Attack", "Defense Curl", "Rock Throw"};
        Pokemon p1 = new Pokemon("Geodude", type1, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        p1.setMoves(moves);
        String[] moves2 = {"Ember", "Metal Claw", "SmokeScreen", "Flamethrower"};
        Pokemon p2 = new Pokemon("Charizard", type2, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        p2.setMoves(moves2);
        int damage = loop.battleDamage(p2, 0, p1);
        assertTrue(damage <= 5);
        assertEquals("Ember does " + damage + " dmg to Geodude (type effectiveness implemented!)" +
                "\nIt's not very effective...", outContent.toString());
    }

    // Tests if proper message and damage is displayed for a super effective move
    @Test
    public void testBattleDamageTypeEff2() {
        String [] type1 = {"Electric"};
        String [] type2 = {"Fire", "Flying"};
        String[] moves = {"Tackle", "Thunderbolt", "Quick Attack", "Growl"};
        Pokemon p1 = new Pokemon("Pikachu", type1, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        p1.setMoves(moves);
        String[] moves2 = {"Ember", "Metal Claw", "SmokeScreen", "Flamethrower"};
        Pokemon p2 = new Pokemon("Charizard", type2, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        p2.setMoves(moves2);
        int damage = loop.battleDamage(p1, 1, p2);
        assertTrue(damage >= 20);
        assertEquals("Thunderbolt does " + damage + " dmg to Charizard (type effectiveness implemented!)" +
                "\nIt's super effective!", outContent.toString());
    }

    /* Type Eff 4 in battleDamage would've been tested but there is no implementation of it in checkTypeEffectiveness */

    // Tests for proper effectiveness for regular move
    @Test
    public void testCheckTypeEffectiveness1() {
        String [] type = {"Electric"};
        String movetype = "Fire";
        assertEquals(1, loop.checkTypeEffectiveness(type, movetype));
    }

    // Tests for proper effectiveness for not-so-effective move
    @Test
    public void testCheckTypeEffectivenessHalf() {
        String [] type = {"Fire"};
        String movetype = "Fire";
        assertEquals(0.5, loop.checkTypeEffectiveness(type, movetype));
    }

    // Tests for proper effectiveness for ineffective move
    @Test
    public void testCheckTypeEffectiveness0() {
        String [] type = {"Flying"};
        String movetype = "Ground";
        assertEquals(0, loop.checkTypeEffectiveness(type, movetype));
    }

    // Tests for proper effectiveness for super effective move
    @Test
    public void testCheckTypeEffectiveness2() {
        String [] type = {"Fire"};
        String movetype = "Water";
        assertEquals(2, loop.checkTypeEffectiveness(type, movetype));
    }

    // Tests for error thrown for invalid Pokemon type
    @Test
    public void testCheckTypeEffectivenessInvalidPokeType() {
        String[] type = {"a"};
        String movetype = "Water";
        assertThrows(NullPointerException.class, () -> loop.checkTypeEffectiveness(type, movetype));
    }

    // Tests for error thrown for invalid move type
    @Test
    public void testCheckTypeEffectivenessInvalidMoveType() {
        String[] type = {"Fire"};
        String movetype = "a";
        assertThrows(NullPointerException.class, () -> loop.checkTypeEffectiveness(type, movetype));
    }

    // TODO: Add tests for parseFile, parsePokemonFile, battle, and startBattle
}
