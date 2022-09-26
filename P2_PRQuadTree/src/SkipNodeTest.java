// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

import student.TestCase;

/**
 * SkipNodeTest will test all public methods in SkipList to ensure that they run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @author Benjamin Gallini (bengallini)
 * @version 9/3/2022
 */
public class SkipNodeTest extends TestCase {
    private SkipNode<String, Point> node;
    private SkipNode<String, Point> node2;
    private KVPair<String, Point> pair;
    private KVPair<String, Point> pair2;
    private Point point;

    /**
     * Sets up
     * the tests
     */
    public void setUp() {
        point = new Point(1, 1);
        pair = new KVPair<String, Point>("A", point);
        node = new SkipNode<String, Point>(1, pair);
        pair2 = new KVPair<String, Point>("B", point);
        node2 = new SkipNode<String, Point>(1, pair2);
    }


    /**
     * Tests key function
     */
    public void testGetKey() {
        assertEquals(node.getKey(), "A");
    }


    /**
     * Tests element function
     */
    public void testGetValue() {
        assertEquals(node.getValue(), point);
    }


    /**
     * Tests getPair
     */
    public void testGetPair() {
        assertEquals(node.getPair(), pair);
    }


    /**
     * Tests setSkip and getSkip method
     */
    public void testSetSkip() {
        assertEquals(node.getSkip(1), null);
        node.setSkip(1, node2);
        assertEquals(node.getSkip(1), node2);
    }


    /**
     * Tests the
     * level function
     */
    public void testLevel() {
        assertEquals(node.getLevel(), 1);
    }


    /**
     * Tests to string
     */
    public void testToString() {
        assertEquals(node.toString(), "Node has depth 2, Value (A, A, 1, 1)");
        SkipNode<String, Point> node2 = new SkipNode<String, Point>(1, null);
        assertEquals(node2.toString(), "Node has depth 2, Value (null)");
    }
}
