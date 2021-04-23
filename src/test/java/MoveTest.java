import battle.Move;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    private Move move;

    @BeforeEach
    private void createMove() {
        move = new Move("acid");
    }

    // Tests for error thrown at invalid move name
    @Test
    public void testMoveInvalidNameFails() {
        assertThrows(NullPointerException.class, () -> new Move("acyd"));
    }

    // Tests if move has correct labels and stats
    @Test
    public void testMoveHasCorrectLabels() {
        assertEquals("acid", move.getName());
        assertEquals("Poison", move.getType());
        assertEquals("Special", move.getCategory());
        assertEquals(40, move.getBasePower());
        assertEquals(30, move.getPP());
        assertEquals("Has a 10% chance to lower the target's Special Defense by 1 stage.", move.getDesc());
    }

    // Tests if move's string is properly outputted
    @Test
    public void testMoveToString() {
        assertEquals("acid Poison Special 40 30 Has a 10% chance to lower the target's Special Defense by 1 " +
                "stage.", move.toString());
    }
}
