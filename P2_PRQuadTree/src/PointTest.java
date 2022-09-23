// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Gallini (bengallini)
// -- Colton Tshudy

import student.TestCase;

/**
 * Point test class
 * 
 * @author Benjamin Gallini
 * @author Colton Tshudy
 * @version 9/23/2022
 */
public class PointTest extends TestCase {
    private Point point1;

    /**
     * Sets up test cases
     */
    public void setUp() {
        point1 = new Point(0, 0);
    }


    /**
     * Tests getX method
     */
    public void testGetX() {
        assertEquals(point1.getX(), 0);
    }


    /**
     * Tests getY method
     */
    public void testGetY() {
        assertEquals(point1.getY(), 0);
    }


    /**
     * Tests setX method
     */
    public void testSetX() {
        point1.setX(1);
        assertEquals(point1.getX(), 1);
    }


    /**
     * Tests setY method
     */
    public void testSetY() {
        point1.setY(1);
        assertEquals(point1.getY(), 1);
    }


    /**
     * Tests validPoint method
     */
    public void testValidPoint() {
        assertTrue(point1.validPoint());
        point1.setX(-1);
        assertFalse(point1.validPoint());
        point1.setX(1024);
        assertFalse(point1.validPoint());
        point1.setX(0);
        point1.setY(-1);
        assertFalse(point1.validPoint());
        point1.setY(1024);
        assertFalse(point1.validPoint());
    }


    /**
     * Tests toString method
     */
    public void testToString() {
        assertEquals(point1.toString(), "0, 0");
    }


    /**
     * Tests findQuadrant method
     */
    public void testFindQuadrant() {
        Point corner = new Point(0, 0);
        assertEquals(point1.findQuadrant(corner, 1024), 1);
        point1.setX(513);
        assertEquals(point1.findQuadrant(corner, 1024), 0);
        point1.setY(513);
        assertEquals(point1.findQuadrant(corner, 1024), 3);
        point1.setX(511);
        assertEquals(point1.findQuadrant(corner, 1024), 2);
        point1.setX(-1);
        assertEquals(point1.findQuadrant(corner, 1024), -1);
    }


    /**
     * Tests inSquare method
     */
    public void testInSquare() {
        Point corner = new Point(0, 0);
        assertTrue(point1.inSquare(corner, 1));
        point1.setX(-1);
        assertFalse(point1.inSquare(corner, 1));
        point1.setX(2);
        assertFalse(point1.inSquare(corner, 1));
        point1.setY(-1);
        assertFalse(point1.inSquare(corner, 2));
        point1.setY(3);
        assertFalse(point1.inSquare(corner, 2));
    }


    /**
     * Tests equals method
     */
    public void testEquals() {
        Point point2 = new Point(0, 1);
        assertFalse(point1.equals(null));
        assertFalse(point1.equals(1));
        assertFalse(point1.equals(point2));
        point2.setY(0);
        assertTrue(point1.equals(point1));
        assertTrue(point1.equals(point2));
        point2.setX(1);
        assertFalse(point1.equals(point2));
    }
}
