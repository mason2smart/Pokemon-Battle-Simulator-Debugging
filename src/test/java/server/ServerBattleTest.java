package server;

import battle.Pokemon;
import battle.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckConnections() {
        serverBattle.checkConnections();
    }

    @Test
    void testLoadBattle() {
        when(t1.getPokemon(anyInt())).thenReturn(new Pokemon("nam", null, "nat", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        when(t2.getPokemon(anyInt())).thenReturn(new Pokemon("nam", null, "nat", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

        serverBattle.loadBattle();
    }

    @Test
    void testBattle() {
        when(t1.getPokemon(anyInt())).thenReturn(new Pokemon("nam", null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        when(t1.isFaintedTeam()).thenReturn(true);
        when(t1.toString()).thenReturn("toStringResponse");
        when(t2.getPokemon(anyInt())).thenReturn(new Pokemon("nam", null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        when(t2.isFaintedTeam()).thenReturn(true);
        when(t2.toString()).thenReturn("toStringResponse");

        serverBattle.battle();
    }

    @Test
    void testParsePokemonFile() {
        when(t1.getPokemon(anyInt())).thenReturn(new Pokemon("nam", null, "nat", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        when(t2.getPokemon(anyInt())).thenReturn(new Pokemon("nam", null, "nat", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

        serverBattle.parsePokemonFile(new Team(), "filePath");
    }

    @Test
    void testParseFile() {
        serverBattle.parseFile();
    }

    @Test
    void testBattleDamage() {
        int result = serverBattle.battleDamage(null, 0, null);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testCheckTypeEffectiveness() {
        double result = serverBattle.checkTypeEffectiveness(new String[]{"pokeType"}, "moveType");
        Assertions.assertEquals(0d, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme