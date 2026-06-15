package test;

import org.junit.jupiter.api.*;
import toys.Flying;
import toys.RCPlane;
import toys.ToyFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 unit test for RCPlane
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRCPlane {

    /**
     * standard output capturer
     */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Test all aspects of the first RCPlane
     */
    @Test
    @Order(1)
    public void testFirstRCPlane() {
        RCPlane plane1 = (RCPlane) ToyFactory.makeToy("RC_PLANE Darkstar 100 400");
        assertEquals(300, plane1.getProductCode());
        assertEquals("Darkstar", plane1.getName());
        assertEquals(0, plane1.getHappiness());
        assertFalse(plane1.isRetired());
        assertEquals(0, plane1.getWear());
        assertEquals(0, plane1.getCurrentAltitude());
        assertEquals(400, plane1.getMaxAltitude());
        assertEquals(15, plane1.getSpeed());
        assertEquals("Toy{PC:300, N:Darkstar, H:0, R:false, W:0.0}, Flying{MA:400.0, CA:0.0}, RCPlane{S:15.0}", plane1.toString());

        plane1.play(10);
        String expected = "PLAYING(10): Toy{PC:300, N:Darkstar, H:0, R:false, W:0.0}, Flying{MA:400.0, CA:0.0}, RCPlane{S:15.0}" + System.lineSeparator();
        expected += "\tDarkstar took off!" + System.lineSeparator();
        expected += "\tDarkstar current altitude: 50.0 feet" + System.lineSeparator();
        expected += "\tDarkstar current speed: 35.0 mph";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(10, plane1.getHappiness());
        assertFalse(plane1.isRetired());
        assertEquals(15, plane1.getWear());
        assertEquals(50, plane1.getCurrentAltitude());
        assertEquals(400, plane1.getMaxAltitude());
        assertEquals(35, plane1.getSpeed());
        assertEquals(100, plane1.getMaxSpeed());

        outputStreamCaptor.reset();
        plane1.play(50);
        expected = "PLAYING(50): Toy{PC:300, N:Darkstar, H:10, R:false, W:15.0}, Flying{MA:400.0, CA:50.0}, RCPlane{S:35.0}" + System.lineSeparator();
        expected += "\tDarkstar current altitude: 300.0 feet" + System.lineSeparator();
        expected += "\tDarkstar current speed: 100.0 mph";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(60, plane1.getHappiness());
        assertFalse(plane1.isRetired());
        assertEquals(50, plane1.getWear());
        assertEquals(300, plane1.getCurrentAltitude());
        assertEquals(100, plane1.getSpeed());

        outputStreamCaptor.reset();
        plane1.play(47);
        expected = "PLAYING(47): Toy{PC:300, N:Darkstar, H:60, R:false, W:50.0}, Flying{MA:400.0, CA:300.0}, RCPlane{S:100.0}" + System.lineSeparator();
        expected += "\tDarkstar current altitude: 400.0 feet" + System.lineSeparator();
        expected += "\tDarkstar current speed: 100.0 mph" + System.lineSeparator();
        expected += "RETIRED: Toy{PC:300, N:Darkstar, H:107, R:true, W:150.0}, Flying{MA:400.0, CA:400.0}, RCPlane{S:100.0}";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(107, plane1.getHappiness());
        assertTrue(plane1.isRetired());
        assertEquals(150, plane1.getWear());
        assertEquals(400, plane1.getCurrentAltitude());
        assertEquals(100, plane1.getSpeed());
    }

    /**
     * More testing with second RCPlane
     */
    @Test
    @Order(2)
    public void testSecondRCPlane() {
        RCPlane plane2 = (RCPlane) ToyFactory.makeToy("RC_PLANE Skyhawk 100 420");
        assertEquals(301, plane2.getProductCode());
        assertEquals("Skyhawk", plane2.getName());
        assertEquals(0, plane2.getHappiness());
        assertFalse(plane2.isRetired());
        assertEquals(0, plane2.getWear());
        assertEquals(0, plane2.getCurrentAltitude());
        assertEquals(420, plane2.getMaxAltitude());
        assertEquals(15, plane2.getSpeed());
        assertEquals("Toy{PC:301, N:Skyhawk, H:0, R:false, W:0.0}, Flying{MA:420.0, CA:0.0}, RCPlane{S:15.0}", plane2.toString());

        plane2.play(98);
        String expected = "PLAYING(98): Toy{PC:301, N:Skyhawk, H:0, R:false, W:0.0}, Flying{MA:420.0, CA:0.0}, RCPlane{S:15.0}" + System.lineSeparator();
        expected += "\tSkyhawk took off!" + System.lineSeparator();
        expected += "\tSkyhawk current altitude: 420.0 feet" + System.lineSeparator();
        expected += "\tSkyhawk current speed: 100.0 mph";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(98, plane2.getHappiness());
        assertFalse(plane2.isRetired());
        assertEquals(15, plane2.getWear());
        assertEquals(420, plane2.getCurrentAltitude());
        assertEquals(420, plane2.getMaxAltitude());
        assertEquals(100, plane2.getSpeed());

        outputStreamCaptor.reset();
        plane2.play(1);
        expected = "PLAYING(1): Toy{PC:301, N:Skyhawk, H:98, R:false, W:15.0}, Flying{MA:420.0, CA:420.0}, RCPlane{S:100.0}" + System.lineSeparator();
        expected += "\tSkyhawk current altitude: 420.0 feet" + System.lineSeparator();
        expected += "\tSkyhawk current speed: 100.0 mph";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
        assertEquals(99, plane2.getHappiness());
        assertFalse(plane2.isRetired());
        assertEquals(115, plane2.getWear());
        assertEquals(420, plane2.getCurrentAltitude());
        assertEquals(100, plane2.getSpeed());

        // make sure RCPlane extends Flying
        Flying ft = plane2;
        assertEquals(99, ft.getHappiness());
    }
}