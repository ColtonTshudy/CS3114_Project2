// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

import student.TestCase;

/**
 * LeafNodeTest will test all public methods in LeafNode to ensure that they run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @author Benjamin Gallini (bengallini)
 * @version 9/22/2022
 */
public class LeafNodeTest extends TestCase {
    private LeafNode node;
    private KVPair<String, Point> pair;
    private Point point;
    private KVPair<String, Point> pair2;
    private Point point2;
    private KVPair<String, Point> pair3;
    private Point point3;
    private KVPair<String, Point> pair4;
    private Point point4;
    private KVPair<String, Point> pairDupe;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        point = new Point(0, 0);
        node = new LeafNode(point, 10);
        pair = new KVPair<String, Point>("A", point);
        node.insert(pair);
        point2 = new Point(1, 1);
        pair2 = new KVPair<String, Point>("B", point2);
        point3 = new Point(2, 1);
        pair3 = new KVPair<String, Point>("C", point3);
        point4 = new Point(1, 2);
        pair4 = new KVPair<String, Point>("D", point4);
        pairDupe = new KVPair<String, Point>("Dupe", point);
    }


    /**
     * Tests insert method
     */
    public void testInsert() {
        assertTrue(node.insert(pair2));
        assertTrue(node.insert(pair3));
        assertEquals(node.getSize(), 3);
        assertFalse(node.insert(pair4));
        assertEquals(node.getSize(), 3);
        assertFalse(node.insert(pairDupe));
        assertEquals(node.getSize(), 3);
        assertEquals(node.dataArray()[0], pair);
    }


    /**
     * Tests remove method with element
     */
    public void testRemove() {
        assertEquals(node.remove(point), pair);
        assertNull(node.remove(point));
        node.insert(pair);
        node.insert(pairDupe);
        node.insert(pair2);
        assertEquals(node.getUniqueItems(), 2);
        assertEquals(node.remove(point2), pair2);
        assertEquals(node.getUniqueItems(), 1);
        assertEquals(node.remove(point), pair);
        assertEquals(node.getUniqueItems(), 1);
        assertEquals(node.dataArray()[0], pairDupe);
        assertEquals(node.dataArray()[1], null);
        assertEquals(node.remove(point), pairDupe);
        assertEquals(node.getSize(), 0);
        assertNull(node.remove(point));
    }


    /**
     * Tests remove method with pair
     */
    public void testRemovePair() {
        assertEquals(node.remove(pair), pair);
        assertNull(node.remove(pair));

        node.insert(pair);
        node.insert(pairDupe);
        node.insert(pair2);
        assertEquals(node.getUniqueItems(), 2);
        assertEquals(node.remove(pair2), pair2);
        assertEquals(node.getUniqueItems(), 1);
        assertEquals(node.remove(pair), pair);
        assertEquals(node.dataArray()[0], pairDupe);
        assertEquals(node.dataArray()[1], null);
        assertEquals(node.getUniqueItems(), 1);
        assertNull(node.remove(pair));
        assertEquals(node.remove(pairDupe), pairDupe);
        assertEquals(node.getSize(), 0);
        assertNull(node.remove(pair));
    }


    /**
     * Tests search with both a key and element
     */
    public void testSearch() {
        assertEquals(node.search(point)[0], pair);
        assertNull(node.search(point2)[0]);
        assertEquals(node.search("A")[0], pair);
        assertNull(node.search("B")[0]);
    }


    /**
     * Tests isLeaf method
     */
    public void testIsLeaf() {
        assertTrue(node.isLeaf());
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
        StringBuilder str = new StringBuilder();
        str.append("  Node at 0, 0, 10:\n");
        str.append("  (A, 0, 0)");
        assertEquals(node.toString(1), str.toString());
        node.insert(pair2);
        StringBuilder str2 = new StringBuilder();
        str2.append("    Node at 0, 0, 10:\n");
        str2.append("    (B, 1, 1)\n");
        str2.append("    (A, 0, 0)");
        assertEquals(node.toString(2), str2.toString());
        node.remove(point);
        node.remove(point2);
        assertEquals(node.toString(0), "Node at 0, 0, 10:");
    }


    /**
     * Tests getCorner
     */
    public void testGetCorner() {
        assertEquals(node.getCorner(), point);
    }


    /**
     * Tests getLength
     */
    public void testGetLength() {
        assertEquals(node.getLength(), 10);
    }


    /**
     * Tests findDupe
     */
    public void testFindDupe() {
        node.insert(pair2);
        assertNull(node.findDupe());
        node.insert(pairDupe);
        assertEquals(node.findDupe(), point);
        KVPair<String, Point> pair2Dupe = new KVPair<String, Point>("BDupe",
            point2);
        node.insert(pair2);
        node.insert(pair2Dupe);
        assertEquals(node.findDupe(), point);

    }


    /**
     * Tests the expand array method
     */
    public void testExpandArray() {
        KVPair<String, Point> pairTest = new KVPair<String, Point>("A", point);
        assertEquals(node.getArrayMax(), 10);
        for (int i = 0; i <= 7; i++) {
            assertTrue(node.insert(pairTest));
            assertEquals(node.getArrayMax(), 10);
        }
        assertTrue(node.insert(pairTest));
        assertEquals(node.getArrayMax(), 20);
    }

}
