// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

import student.TestCase;

/**
 * RectangleTest will test all public methods in Rectangle to make
 * sure that they run and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @version 9/3/2022
 */
public class RectangleTest extends TestCase {
    // Declare any necessary objects or final variables
    private Rectangle recC;
    private Rectangle recT;
    private Rectangle recB;
    private Rectangle recL;
    private Rectangle recR;
    private Rectangle recWide;
    private Rectangle recTall;
    private Rectangle recNoW;
    private Rectangle recNoH;
    private Rectangle recNeg1;
    private Rectangle recNeg2;
    private Rectangle recNull;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        recC = new Rectangle("Center", 5, 5, 5, 5); // center
        recT = new Rectangle("Top", 5, 0, 5, 5); // top of center
        recB = new Rectangle("Below", 5, 10, 5, 5); // bottom of center
        recL = new Rectangle("Left", 0, 5, 5, 5); // left of center
        recR = new Rectangle("Right", 10, 5, 5, 5); // right of center
        recWide = new Rectangle("Wide", 3, 6, 20, 3); // intersecting width
        recTall = new Rectangle("Tall", 6, 3, 3, 20); // intersecting height
        recNoW = new Rectangle("No Width", 6, 6, 0, 5); // no width
        recNoH = new Rectangle("No Height", 6, 6, 5, 0); // no height
        recNeg1 = new Rectangle("N1", -10, -10, 100, 100); // neg intersecting
        recNeg2 = new Rectangle("N2", -10, -10, 5, 5); // neg non-intersecting
        recNull = new Rectangle(); // default constructor
    }


    /**
     * This method will test the getters and setters
     */
    public void testGettersSetters() {
        assertEquals(recC.getName(), "Center");
        assertEquals(recC.getX1(), 5);
        assertEquals(recC.getY1(), 5);
        assertEquals(recC.getX2(), 10);
        assertEquals(recC.getY2(), 10);
        assertEquals(recC.getHeight(), 5);
        assertEquals(recC.getWidth(), 5);

        recC.setCoords(100, 100);
        assertEquals(recC.getX1(), 100);
        assertEquals(recC.getY1(), 100);
        recC.setWidth(100);
        recC.setHeight(100);
        assertEquals(recC.getX2(), 200);
        assertEquals(recC.getY2(), 200);
        recC.setName("new");
        assertEquals(recC.getName(), "new");
    }


    /**
     * This method will test true cases of compareTo()
     */
    public void testDoesIntersectsWith() {
        assertEquals(recC.compareTo(recC), 1);
        assertEquals(recC.compareTo(recWide), 1);
        assertEquals(recC.compareTo(recTall), 1);
        assertEquals(recC.compareTo(recNeg1), 1);
    }


    /**
     * This method will test false cases of compareTo()
     */
    public void testNotIntersectsWith() {
        assertEquals(recC.compareTo(recT), -1);
        assertEquals(recC.compareTo(recB), -1);
        assertEquals(recC.compareTo(recL), -1);
        assertEquals(recC.compareTo(recR), -1);
        assertEquals(recC.compareTo(recNoW), -1);
        assertEquals(recC.compareTo(recNoH), -1);
        assertEquals(recNoW.compareTo(recC), -1);
        assertEquals(recNoH.compareTo(recC), -1);
        assertEquals(recC.compareTo(recNull), -1);
        assertEquals(recNull.compareTo(recC), -1);
        assertEquals(recC.compareTo(recNeg2), -1);
    }


    /**
     * This method will test equals
     */
    public void testEquals() {
        // True equalities against rectangles
        assertTrue(recC.equals(recC));
        recT = new Rectangle("Center", 5, 5, 5, 5);
        assertTrue(recC.equals(recT));

        // False equalities against rectangles
        assertFalse(recC.equals(recNull));

        recT = new Rectangle("x", 1, 5, 5, 5); // diff x
        assertFalse(recC.equals(recT));
        recT = new Rectangle("y", 5, 1, 5, 5); // diff y
        assertFalse(recC.equals(recT));
        recT = new Rectangle("w", 5, 5, 1, 5); // diff w
        assertFalse(recC.equals(recT));
        recT = new Rectangle("h", 5, 5, 5, 1); // diff h
        assertFalse(recC.equals(recT));

        // False equalities against other object types
        String str = "string";
        assertFalse(recC.equals(str));
        assertFalse(recC.equals(null));
    }


    /**
     * This method will test toString
     */
    public void testToString() {
        String recCstr = "5, 5, 5, 5";
        assertTrue(recC.toString().equals(recCstr));
    }
}
