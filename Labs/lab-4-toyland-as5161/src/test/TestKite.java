package test;

import org.junit.jupiter.api.*;
import toys.Color;
import toys.Flying;
import toys.Kite;
import toys.ToyFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 unit test for Kite
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestKite {
    /** standard output capturer */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /** Test all aspects of the first Kite */
    @Test
    @Order(1)
    public void testFirstKite() {
        Kite kite1 = (Kite) ToyFactory.makeToy("KITE Cometa GREEN DELTA 100");
        assertEquals(400, kite1.getProductCode());
        assertEquals("Cometa", kite1.getName());
        assertEquals(0, kite1.getHappiness());
        assertFalse(kite1.isRetired());
        assertEquals(0, kite1.getWear());
        assertEquals(0, kite1.getCurrentAltitude());
        assertEquals(100, kite1.getMaxAltitude());
        assertEquals(Color.GREEN, kite1.getColor());
        assertEquals(Kite.Type.DELTA, kite1.getType());
        assertEquals(100, kite1.getLineLength());
        assertEquals( "Toy{PC:400, N:Cometa, H:0, R:false, W:0.0}, Flying{MA:100.0, CA:0.0}, Kite{C:GREEN, T:DELTA, LL:100}", kite1.toString());

        kite1.play(10);
        String expected = "PLAYING(10): Toy{PC:400, N:Cometa, H:0, R:false, W:0.0}, Flying{MA:100.0, CA:0.0}, Kite{C:GREEN, T:DELTA, LL:100}" + System.lineSeparator();
        expected += "\tCometa took off!" + System.lineSeparator();
        expected += "\tCometa current altitude: 5.0 feet" + System.lineSeparator();
        expected += "\tFlying the GREEN, DELTA kite Cometa with 100 feets of line";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(10, kite1.getHappiness());
        assertFalse(kite1.isRetired());
        assertEquals(0.5, kite1.getWear());
        assertEquals(5, kite1.getCurrentAltitude());
        assertEquals(100, kite1.getMaxAltitude());
        assertEquals(100, kite1.getLineLength());

        outputStreamCaptor.reset();
        kite1.play(5);
        expected = "PLAYING(5): Toy{PC:400, N:Cometa, H:10, R:false, W:0.5}, Flying{MA:100.0, CA:5.0}, Kite{C:GREEN, T:DELTA, LL:100}" + System.lineSeparator();
        expected += "\tCometa current altitude: 7.5 feet" + System.lineSeparator();
        expected += "\tFlying the GREEN, DELTA kite Cometa with 100 feets of line";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(15, kite1.getHappiness());
        assertFalse(kite1.isRetired());
        assertEquals(0.75, kite1.getWear());
        assertEquals(7.5, kite1.getCurrentAltitude());
        assertEquals(100, kite1.getLineLength());

        outputStreamCaptor.reset();
        kite1.play(100);
        expected = "PLAYING(100): Toy{PC:400, N:Cometa, H:15, R:false, W:0.75}, Flying{MA:100.0, CA:7.5}, Kite{C:GREEN, T:DELTA, LL:100}" + System.lineSeparator();
        expected += "\tCometa current altitude: 57.5 feet" + System.lineSeparator();
        expected += "\tFlying the GREEN, DELTA kite Cometa with 100 feets of line" + System.lineSeparator();
        expected += "RETIRED: Toy{PC:400, N:Cometa, H:115, R:true, W:5.75}, Flying{MA:100.0, CA:57.5}, Kite{C:GREEN, T:DELTA, LL:100}";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(115, kite1.getHappiness());
        assertTrue(kite1.isRetired());
        assertEquals(5.75, kite1.getWear());
        assertEquals(57.5, kite1.getCurrentAltitude());
        assertEquals(100, kite1.getMaxAltitude());
        assertEquals(100, kite1.getLineLength());
    }

    /** More testing with second Kite */
    @Test
    @Order(2)
    public void testSecondKite() {
        Kite kite2 = (Kite) ToyFactory.makeToy("KITE Pipa RED DIAMOND 500");
        assertEquals(401, kite2.getProductCode());
        assertEquals("Pipa", kite2.getName());
        assertEquals(0, kite2.getHappiness());
        assertFalse(kite2.isRetired());
        assertEquals(0, kite2.getWear());
        assertEquals(0, kite2.getCurrentAltitude());
        assertEquals(500, kite2.getMaxAltitude());
        assertEquals(Color.RED, kite2.getColor());
        assertEquals(Kite.Type.DIAMOND, kite2.getType());
        assertEquals(500, kite2.getLineLength());
        assertEquals("Toy{PC:401, N:Pipa, H:0, R:false, W:0.0}, Flying{MA:500.0, CA:0.0}, Kite{C:RED, T:DIAMOND, LL:500}", kite2.toString());

        kite2.play(42);
        String expected = "PLAYING(42): Toy{PC:401, N:Pipa, H:0, R:false, W:0.0}, Flying{MA:500.0, CA:0.0}, Kite{C:RED, T:DIAMOND, LL:500}" + System.lineSeparator();
        expected += "\tPipa took off!" + System.lineSeparator();
        expected += "\tPipa current altitude: 21.0 feet" + System.lineSeparator();
        expected += "\tFlying the RED, DIAMOND kite Pipa with 500 feets of line";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(42, kite2.getHappiness());
        assertFalse(kite2.isRetired());
        assertEquals(2.1, kite2.getWear());
        assertEquals(21, kite2.getCurrentAltitude());
        assertEquals(500, kite2.getMaxAltitude());
        assertEquals(500, kite2.getLineLength());

        // make sure Kite extends Flying
        Flying ft = kite2;
        assertEquals(42, ft.getHappiness());
    }
}