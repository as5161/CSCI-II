package hotpotato;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5.8 tester for Player
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPlayer {
    /** standard output capturer */
    public final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Order(1)
    public void testPlayer() {
        Player snorlax = new Player("Snorlax", 18);
        Player pikachu = new Player("Pikachu", 15);
        
        // test getName()
        assertEquals("Snorlax", snorlax.getName());
        assertEquals("Pikachu", pikachu.getName());
        
        // test getId()
        assertEquals(18, snorlax.getId());
        assertEquals(15, pikachu.getId());
        
        // test toString()
        String expected = "Snorlax(18)";
        assertEquals(expected, snorlax.toString().trim());
        outputStreamCaptor.reset();
        
        expected = "Pikachu(15)";
        assertEquals(expected, pikachu.toString().trim());
        outputStreamCaptor.reset();

        // test equals()
        Player snorlax2 = new Player("Snorlax", 18);
        Player snorlax3 = new Player("Snorlax", 15);
        Player pikachu2 = new Player("Pikachu", 15);
        Player pikachu3 = new Player("Pikachu", 18);
        
        assertEquals(snorlax, snorlax2);
        assertNotEquals(snorlax, snorlax3);
        assertNotEquals(snorlax, pikachu3);

        assertEquals(pikachu, pikachu2);
        assertNotEquals(pikachu, pikachu3);
        assertNotEquals(pikachu, snorlax3);
    }
}
