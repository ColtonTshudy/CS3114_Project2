package main;

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
    Rectangle recC;
    Rectangle recT;
    Rectangle recB;
    Rectangle recL;
    Rectangle recR;
    Rectangle recWide;
    Rectangle recTall;
    Rectangle recNull;
    
    
    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        recC = new Rectangle("Center", 5, 5, 5, 5);
        recT = new Rectangle("Top", 5, 0, 5, 5);
        recB = new Rectangle("Bottom", 5, 10, 5, 5);
        recL = new Rectangle("Left", 0, 5, 5, 5);
        recR = new Rectangle("Right", 10, 5, 5, 5);
        recWide = new Rectangle("Wide", 3, 6, 20, 3);
        recTall = new Rectangle("Tall", 6, 3, 3, 20);
        recNull = new Rectangle();
    }
    
    /**
     * This method will test the getters
     */
    public void testGetters()
    {
        assertEquals(recC.getX1(), 5);
        assertEquals(recC.getY1(), 5);
        assertEquals(recC.getX2(), 10);
        assertEquals(recC.getY2(), 10);
        assertEquals(recC.getHeight(), 5);
        assertEquals(recC.getWidth(), 5);
    }

    /**
     * This method will test true cases of intersectsWith()
     */
    public void testDoesIntersectsWith()
    {
        assertTrue(recC.intersectsWith(recC));
        assertTrue(recC.intersectsWith(recC));
        assertTrue(recC.intersectsWith(recC));
    }
    
    /**
     * This method will test false cases of intersectsWith()
     */
    public void testNotIntersectsWith()
    {
        assertFalse(recC.intersectsWith(recT));
        assertFalse(recC.intersectsWith(recB));
        assertFalse(recC.intersectsWith(recL));
        assertFalse(recC.intersectsWith(recR));
        assertFalse(recC.intersectsWith(recNull));
        assertFalse(recNull.intersectsWith(recC));
    }
}
