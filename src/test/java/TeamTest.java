import battle.Pokemon;
import battle.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    private Team team;
    private Pokemon mk1, mk2, mk3, mk4, mk5, mk6, gya;

    @BeforeEach
    private void createRoster() {
        team = new Team();
        mk1 = new Pokemon("Magikarp", "Adamant", 10, 10, 10, 10, 10, 10);
        mk2 = new Pokemon("Magikarp", "Adamant", 10, 10, 10, 10, 10, 10);
        mk3 = new Pokemon("Magikarp", "Adamant", 10, 10, 10, 10, 10, 10);
        mk4 = new Pokemon("Magikarp", "Adamant", 10, 10, 10, 10, 10, 10);
        mk5 = new Pokemon("Magikarp", "Adamant", 10, 10, 10, 10, 10, 10);
        mk6 = new Pokemon("Magikarp", "Adamant", 10, 10, 10, 10, 10, 10);
        gya = new Pokemon("Gyarados", "Adamant", 10, 10, 10, 10, 10, 10);
    }

    @Test
    public void testNewTeamIsEmpty() {
        assertEquals(0, team.toString().length());
    }

    @Test
    public void testAddOnePokemonIsNotEmpty() {
        Team team = new Team();
        team.addPokemon(mk1);
        assertNotEquals(0, team.toString().length());
    }

    @Test
    public void testAddSevenPokemonTeamHasSizeSix() {
        team.addPokemon(mk1);
        team.addPokemon(mk2);
        team.addPokemon(mk3);
        team.addPokemon(mk4);
        team.addPokemon(mk5);
        team.addPokemon(mk6);
        team.addPokemon(gya);
        assertEquals(6, team.nonFaintedTeam());
    }

    @Test
    // This is making some pretty serious assumptions about what the expected behavior should be.
    // TODO: Talk with team about how to handle this.
    public void testCantAddSamePokemonTwice() {
        team.addPokemon(mk1);
        team.addPokemon(mk1);
        assertEquals(1, team.nonFaintedTeam());
    }

    @Test
    public void testGetAddedPokemon() {
        team.addPokemon(mk1);
        assertEquals(mk1, team.getPokemon(0));
    }

    @Test
    public void testGetPokemonOOB() {
        assertThrows(IndexOutOfBoundsException.class, () -> team.getPokemon(0));
    }

    @Test
    public void testNewTeamNoFainted() {
        assertEquals(0, team.nonFaintedTeam());
    }

    @Test
    public void testNewTeamAllFainted() {
        assertTrue(team.isFaintedTeam());
    }

    @Test
    public void testOneNonfaintedTeam() {
        team.addPokemon(mk1);
        assertEquals(1, team.nonFaintedTeam());
    }

    @Test
    public void testTeamNotAllFainted() {
        team.addPokemon(mk1);
        assertFalse(team.isFaintedTeam());
    }

    @Test
    public void testOneFaintedTeam() {
        team.addPokemon(mk1);
        mk1.setCurrentHP(0);
        mk1.checkState();
        assertEquals(0, team.nonFaintedTeam());
    }

    @Test
    public void testOneFaintedTeamAllFainted() {
        team.addPokemon(mk1);
        mk1.setCurrentHP(0);
        mk1.checkState();
        assertTrue(team.isFaintedTeam());
    }

    @Test
    public void testOneFaintedOneNonfaintedTeam() {
        team.addPokemon(mk1);
        team.addPokemon(mk2);
        mk1.setCurrentHP(0);
        mk1.checkState();
        assertEquals(1, team.nonFaintedTeam());
    }

    @Test
    public void testOneFaintedOneNonfaintedTeamNotAllFainted() {
        team.addPokemon(mk1);
        team.addPokemon(mk2);
        mk1.setCurrentHP(0);
        mk1.checkState();
        assertFalse(team.isFaintedTeam());
    }

    @Test
    public void testOneNonfaintedOneFaintedTeamNotAllFainted() {
        team.addPokemon(mk1);
        team.addPokemon(mk2);
        mk2.setCurrentHP(0);
        mk2.checkState();
        assertFalse(team.isFaintedTeam());
    }

    @Test
    // TODO: discuss with team whether or not the leading and trailing spaces as well as the extra space between entries
    //  are expected behavior
    public void testToStringOnePokemon() {
        team.addPokemon(mk1);
        assertEquals("(0) Magikarp", team.toString());
    }

    @Test
    public void testToStringTwoPokemon() {
        team.addPokemon(mk1);
        team.addPokemon(mk2);
        assertEquals("(0) Magikarp (1) Magikarp", team.toString());
    }

    @Test
    public void testToStringTwoDifferentPokemon() {
        team.addPokemon(mk1);
        team.addPokemon(gya);
        assertEquals("(0) Magikarp (1) Gyarados", team.toString());
    }

    @Test
    public void testToStringTwoDifferentPokemonDifferentOrder() {
        team.addPokemon(gya);
        team.addPokemon(mk1);
        assertEquals("(0) Gyarados (1) Magikarp", team.toString());
    }

    @Test
    public void testToStringTwoPokemonOneFainted() {
        team.addPokemon(mk1);
        team.addPokemon(mk2);
        assertEquals("(0) Magikarp (1) Magikarp", team.toString());
    }

    @Test
    public void testToStringFullTeam() {
        team.addPokemon(mk1);
        team.addPokemon(mk2);
        team.addPokemon(mk3);
        team.addPokemon(mk4);
        team.addPokemon(mk5);
        team.addPokemon(mk6);
        assertEquals("(0) Magikarp (1) Magikarp (2) Magikarp (3) Magikarp (4) Magikarp (5) Magikarp", team.toString());
    }
}