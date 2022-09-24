// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

import student.TestCase;

/**
 * FlyweightNode will test all public methods in FlyweightNode to ensure that
 * they run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @author Benjamin Gallini (bengallini)
 * @version 9/22/2022
 */
public class FlyweightNodeTest extends TestCase {
    private FlyweightNode node;
    private KVPair<String, Point> pair;
    private Point corner;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        corner = new Point(0, 0);
        node = new FlyweightNode(corner, 1);
        pair = new KVPair<String, Point>("A", corner);
    }


    /**
     * Tests insert method
     */
    public void testInsert() {
        assertFalse(node.insert(pair));
    }


    /**
     * Tests remove method with both a key and element
     */
    public void testRemove() {
        assertNull(node.remove(corner));
        assertNull(node.remove(pair));
    }


    /**
     * Tests search method with both a key and element
     */
    public void testSearch() {
        assertNull(node.search(corner));
        assertNull(node.search("A"));
    }


    /**
     * Tests isLeaf method
     */
    public void testIsLeaf() {
        assertFalse(node.isLeaf());
    }


    /**
     * Tests isFlyweight method
     */
    public void testIsFlyweight() {
        assertTrue(node.isFlyweight());
    }


    /**
     * Tests toString method
     */
    public void testToString() {
        assertEquals(node.toString(0), "Node at 0, 0, 1: Empty");
        assertEquals(node.toString(1), "  Node at 0, 0, 1: Empty");
    }


    /**
     * Tests getCorner method
     */
    public void testGetCorner() {
        assertEquals(node.getCorner(), corner);
    }


    /**
     * Tests getLength method
     */
    public void testGetLength() {
        assertEquals(node.getLength(), 1);
    }
}
