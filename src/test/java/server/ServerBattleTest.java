package server;

import battle.Pokemon;
import battle.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ServerBattleTest {
    @Mock
    Team t1;
    @Mock
    Team t2;
    //Field sc of type Scanner - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    Socket s1;
    @Mock
    Socket s2;
    @Mock
    BufferedReader reader1;
    @Mock
    BufferedReader reader2;
    @Mock
    PrintWriter writer1;
    @Mock
    PrintWriter writer2;
    @InjectMocks
    ServerBattle serverBattle;

    private Pokemon charizard, geodude, pikachu;

    @BeforeEach
    void setUp() {
        createPokemon();
        MockitoAnnotations.openMocks(this);
    }

    private void createPokemon() {
        String[] type1 = {"Rock", "Ground"};
        String[] moves1 = {"Earthquake", "Sand Attack", "Defense Curl", "Rock Throw"};
        geodude = new Pokemon("Geodude", type1, "Adamant", 27, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        geodude.setMoves(moves1);

        String[] type2 = {"Fire", "Flying"};
        String[] moves2 = {"Ember", "Metal Claw", "SmokeScreen", "Flamethrower"};
        charizard = new Pokemon("Charizard", type2, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        charizard.setMoves(moves2);

        String[] type3 = {"Electric"};
        String[] moves3 = {"Tackle", "Thunderbolt", "Quick Attack", "Growl"};
        pikachu = new Pokemon("Pikachu", type1, "Adamant", 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        pikachu.setMoves(moves3);
    }

    // Tests if proper damage is dealt for an ineffective move
    @Test
    public void testBattleDamageTypeEff0() {
        assertEquals(serverBattle.battleDamage(geodude, 0, charizard), 0);
    }

    // Tests if proper messages are displayed for an ineffective move
    @Test
    public void testBattleDamageTypeEff0Writer1CorrectMessages() {
        serverBattle.battleDamage(geodude, 0, charizard);
        InOrder inOrder = inOrder(writer1);
        inOrder.verify(writer1, times(1)).println("Earthquake does 0 dmg to Charizard (type effectiveness implemented!)");
        inOrder.verify(writer1, times(1)).flush();
        inOrder.verify(writer1, times(1)).println("It doesn't affect Charizard...");
        inOrder.verify(writer1, times(1)).flush();
    }

    @Test
    public void testBattleDamageTypeEff0Writer2CorrectMessages() {
        serverBattle.battleDamage(geodude, 0, charizard);
        InOrder inOrder = inOrder(writer2);
        inOrder.verify(writer2, times(1)).println("Earthquake does 0 dmg to Charizard (type effectiveness implemented!)");
        inOrder.verify(writer2, times(1)).flush();
        inOrder.verify(writer2, times(1)).println("It doesn't affect Charizard...");
        inOrder.verify(writer2, times(1)).flush();
    }

    @Test
    public void testBattleDamageTypeEff1() {
        int damage = serverBattle.battleDamage(pikachu, 0, charizard);
        assertTrue(damage >= 5);
    }

    @Test
    public void testBattleDamageTypeEff1Writer1CorrectMessage() {
        int damage = serverBattle.battleDamage(pikachu, 0, charizard);
        InOrder inOrder = inOrder(writer1);
        inOrder.verify(writer1, times(1)).println("Tackle does " + damage + " dmg to Charizard (type effectiveness implemented!)");
        inOrder.verify(writer1, times(1)).flush();
    }

    @Test
    public void testBattleDamageTypeEff1Writer2CorrectMessage() {
        int damage = serverBattle.battleDamage(pikachu, 0, charizard);
        InOrder inOrder = inOrder(writer2);
        inOrder.verify(writer2, times(1)).println("Tackle does " + damage + " dmg to Charizard (type effectiveness implemented!)");
        inOrder.verify(writer2, times(1)).flush();
    }

    @Test
    public void testBattleDamageTypeEffHalf() {
        int damage = serverBattle.battleDamage(charizard, 0, geodude);
        assertTrue(damage <= 5);
    }

    @Test
    public void testBattleDamageTypeEffHalfWriter1CorrectMessage() {
        int damage = serverBattle.battleDamage(charizard, 0, geodude);
        InOrder inOrder = inOrder(writer1);
        inOrder.verify(writer1, times(1)).println("Ember does " + damage + " dmg to Geodude (type effectiveness implemented!)");
        inOrder.verify(writer1, times(1)).flush();
        inOrder.verify(writer1, times(1)).println("It's not very effective...");
        inOrder.verify(writer1, times(1)).flush();
    }

    @Test
    public void testBattleDamageTypeEffHalfWriter2CorrectMessage() {
        int damage = serverBattle.battleDamage(charizard, 0, geodude);
        InOrder inOrder = inOrder(writer2);
        inOrder.verify(writer2, times(1)).println("Ember does " + damage + " dmg to Geodude (type effectiveness implemented!)");
        inOrder.verify(writer2, times(1)).flush();
        inOrder.verify(writer2, times(1)).println("It's not very effective...");
        inOrder.verify(writer2, times(1)).flush();
    }

    @Test
    public void testBattleDamageTypeEff2() {
        int damage = serverBattle.battleDamage(pikachu, 1, charizard);
        assertTrue(damage >= 20);
    }

    @Test
    public void testBattleDamageTypeEff2Writer1CorrectMessage() {
        int damage = serverBattle.battleDamage(pikachu, 1, charizard);
        InOrder inOrder = inOrder(writer1);
        inOrder.verify(writer1, times(1)).println("Thunderbolt does " + damage + " dmg to Charizard (type effectiveness implemented!)");
        inOrder.verify(writer1, times(1)).flush();
        inOrder.verify(writer1, times(1)).println("It's super effective!");
        inOrder.verify(writer1, times(1)).flush();
    }

    @Test
    public void testBattleDamageTypeEff2Writer2CorrectMessage() {
        int damage = serverBattle.battleDamage(pikachu, 1, charizard);
        InOrder inOrder = inOrder(writer2);
        inOrder.verify(writer2, times(1)).println("Thunderbolt does " + damage + " dmg to Charizard (type effectiveness implemented!)");
        inOrder.verify(writer2, times(1)).flush();
        inOrder.verify(writer2, times(1)).println("It's super effective!");
        inOrder.verify(writer2, times(1)).flush();
    }

    /* Type Eff 4 in battleDamage would've been tested but there is no implementation of it in checkTypeEffectiveness */

    // Tests for proper effectiveness for regular move
    @Test
    public void testCheckTypeEffectiveness1() {
        String [] type = {"Electric"};
        String movetype = "Fire";
        assertEquals(1, serverBattle.checkTypeEffectiveness(type, movetype));
    }

    // Tests for proper effectiveness for not-so-effective move
    @Test
    public void testCheckTypeEffectivenessHalf() {
        String [] type = {"Fire"};
        String movetype = "Fire";
        assertEquals(0.5, serverBattle.checkTypeEffectiveness(type, movetype));
    }

    // Tests for proper effectiveness for ineffective move
    @Test
    public void testCheckTypeEffectiveness0() {
        String [] type = {"Flying"};
        String movetype = "Ground";
        assertEquals(0, serverBattle.checkTypeEffectiveness(type, movetype));
    }

    // Tests for proper effectiveness for super effective move
    @Test
    public void testCheckTypeEffectiveness2() {
        String [] type = {"Fire"};
        String movetype = "Water";
        assertEquals(2, serverBattle.checkTypeEffectiveness(type, movetype));
    }

    // Tests for error thrown for invalid Pokemon type
    @Test
    public void testCheckTypeEffectivenessInvalidPokeType() {
        String[] type = {"a"};
        String movetype = "Water";
        assertThrows(NullPointerException.class, () -> serverBattle.checkTypeEffectiveness(type, movetype));
    }

    // Tests for error thrown for invalid move type
    @Test
    public void testCheckTypeEffectivenessInvalidMoveType() {
        String[] type = {"Fire"};
        String movetype = "a";
        assertThrows(NullPointerException.class, () -> serverBattle.checkTypeEffectiveness(type, movetype));
    }

    @Test
    public void testParsePokemonFile() {
        Team t = new Team();
        String expected = "(0) Azumarill (1) Bisharp (2) Dragonite (3) Garchomp (4) Landorus-Therian (5) Charizard-Mega-Y";
        serverBattle.parsePokemonFile(t, "data/team.txt");
        assertEquals(expected, t.toString());
    }

    @Test
    public void testParsePokemonFileOther() {
        Team t = new Team();
        String expected = "(0) Volcarona (1) Dragonite (2) Chandelure (3) Lucario (4) Starmie (5) Scizor";
        serverBattle.parsePokemonFile(t, "data/test/teamTest.txt");
        assertEquals(expected, t.toString());
    }

    // Data taken from reference implementation here:
    // https://pycosites.com/pkmn/stat.php
    @Test
    public void testParsePokemonFileCorrectStats() {
        Team t = new Team();
        serverBattle.parsePokemonFile(t, "data/test/teamTest.txt");
        Pokemon volcarona = t.getPokemon(0);
        assertEquals(357, volcarona.getHP());
        assertEquals(140, volcarona.getAtk());
        assertEquals(184, volcarona.getDef());
        assertEquals(336, volcarona.getSpA());
        assertEquals(246, volcarona.getSpD());
        assertEquals(299, volcarona.getSpe());
    }

    @Test
    public void testParsePokemonFileCorrectMoves() {
        Team t = new Team();
        serverBattle.parsePokemonFile(t, "data/test/teamTest.txt");
        Pokemon volcarona = t.getPokemon(0);
        assertEquals("Fiery Dance", volcarona.getMove(0).getName());
        assertEquals("Bug Buzz", volcarona.getMove(1).getName());
        assertEquals("Giga Drain", volcarona.getMove(2).getName());
        assertEquals("Hyper Beam", volcarona.getMove(3).getName());
    }
}