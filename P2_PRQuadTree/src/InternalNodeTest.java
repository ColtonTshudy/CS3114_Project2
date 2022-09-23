// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

import student.TestCase;

/**
 * InternalNode will test all public methods in InternalNode to ensure that they run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @author Benjamin Gallini (bengallini)
 * @version 9/22/2022
 */
public class InternalNodeTest extends TestCase{
    private InternalNode node;
    private KVPair<String, Point> pair;
    private Point corner;
    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        corner = new Point(0,0);
        node = new InternalNode(corner, 10);
        pair = new KVPair<String, Point>("A", corner);
    }
    
    /**
     * Tests internal node constructor
     */
    public void testInternalNode() {
        BaseNode[] children = node.children;
        Point corner0 = new Point(5 ,0);
        assertEquals(children[0].getCorner(), corner0);
        assertEquals(children[0].getLength(), 5);
        Point corner1 = new Point(0 ,0);
        assertEquals(children[1].getCorner(), corner1);
        assertEquals(children[1].getLength(), 5);
        Point corner2 = new Point(0 ,5);
        assertEquals(children[2].getCorner(), corner2);
        assertEquals(children[2].getLength(), 5);
        Point corner3 = new Point(5 ,5);
        assertEquals(children[3].getCorner(), corner3);
        assertEquals(children[3].getLength(), 5);
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
        assertNull(node.remove("A"));
    }


    /**
     * Tests remove search with both a key and element
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
        assertFalse(node.isFlyweight());
    }


    /**
     * Tests toString method
     */
    public void testToString() {
        assertEquals(node.toString(0), "Node at 0, 0, 10: Internal");
        assertEquals(node.toString(1), "  Node at 0, 0, 10: Internal");
    }


    /**
     * Tests getCorner
     */
    public void testGetCorner() {
        assertEquals(node.getCorner(), corner);
    }


    /**
     * Tests getLength
     */
    public void testGetLength() {
        assertEquals(node.getLength(), 10);
    }
    
    
}
