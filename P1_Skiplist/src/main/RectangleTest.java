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
    Rectangle recNoW;
    Rectangle recNoH;
    Rectangle recNeg1;
    Rectangle recNeg2;
    Rectangle recNull;
    
    
    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        recC = new Rectangle(5, 5, 5, 5); // center
        recT = new Rectangle(5, 0, 5, 5); // top of center
        recB = new Rectangle(5, 10, 5, 5); // bottom of center
        recL = new Rectangle(0, 5, 5, 5); // left of center
        recR = new Rectangle(10, 5, 5, 5); // right of center 
        recWide = new Rectangle(3, 6, 20, 3); // intersecting width
        recTall = new Rectangle(6, 3, 3, 20); // intersecting height
        recNoW = new Rectangle(6, 6, 0, 5); // no width
        recNoH = new Rectangle(6, 6, 5, 0); // no height
        recNeg1 = new Rectangle(-10, -10, 100, 100); // negative intersecting
        recNeg2 = new Rectangle(-10, -10, 5, 5); // negative non-intersecting
        recNull = new Rectangle(); // default constructor (null instance varaibles)
    }
    
    /**
     * This method will test the getters and setters
     */
    public void testGettersSetters()
    {
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
    }

    /**
     * This method will test true cases of intersectsWith()
     */
    public void testDoesIntersectsWith()
    {
        assertTrue(recC.intersectsWith(recC));
        assertTrue(recC.intersectsWith(recWide));
        assertTrue(recC.intersectsWith(recTall));
        assertTrue(recC.intersectsWith(recNeg1));
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
        assertFalse(recC.intersectsWith(recNoW));
        assertFalse(recC.intersectsWith(recNoH));
        assertFalse(recNoW.intersectsWith(recC));
        assertFalse(recNoH.intersectsWith(recC));
        assertFalse(recC.intersectsWith(recNull));
        assertFalse(recNull.intersectsWith(recC));
        assertFalse(recC.intersectsWith(recNeg2));
    }
    
    /**
     * This method will test equals
     */
    public void testEquals() {
        // True equalities against rectangles
        assertTrue(recC.equals(recC));
        recT = new Rectangle(5, 5, 5, 5);
        assertTrue(recC.equals(recT));
        
        // False equalities against rectangles
        assertFalse(recC.equals(recNull));
        
        recT = new Rectangle(1, 5, 5, 5); // diff x
        assertFalse(recC.equals(recT));
        recT = new Rectangle(5, 1, 5, 5); // diff y
        assertFalse(recC.equals(recT));
        recT = new Rectangle(5, 5, 1, 5); // diff w
        assertFalse(recC.equals(recT));
        recT = new Rectangle(5, 5, 5, 1); // diff h
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
