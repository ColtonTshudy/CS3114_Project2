// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

import student.TestCase;

/**
 * PRQuadTreeTest will test all public methods in PRQuadTree to ensure that they
 * run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @author Benjamin Gallini (bengallini)
 * @version 9/22/2022
 */
public class PRQuadTreeTest extends TestCase {
    private PRQuadTree tree;
    private Point point;
    private Point point1;
    private Point point2;
    private Point point3;
    private KVPair<String, Point> pair;
    private KVPair<String, Point> pair0;
    private KVPair<String, Point> pair1;
    private KVPair<String, Point> pair2;
    private KVPair<String, Point> pair3;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        tree = new PRQuadTree();
        point = new Point(0, 0);
        point1 = new Point(0, 1);
        point2 = new Point(1, 0);
        point3 = new Point(1000, 1000);
        pair = new KVPair<String, Point>("A", point);
        pair0 = new KVPair<String, Point>("B", point);
        pair1 = new KVPair<String, Point>("C", point1);
        pair2 = new KVPair<String, Point>("D", point2);
        pair3 = new KVPair<String, Point>("F", point3);
    }


    /**
     * Tests the insert method
     */
    public void testInsert() {
        tree.insert(pair);

        StringBuilder str = new StringBuilder("Node at 0, 0, 1024:\n");
        str.append("(A, 0, 0)\n");
        str.append("1 quadtree nodes printed");
        assertEquals(tree.toString(), str.toString());

        tree.insert(pair0);
        assertEquals(tree.getSize(), 2);
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair3);
        tree.insert(new KVPair<String, Point>("G", new Point(257, 0)));

        str = new StringBuilder("Node at 0, 0, 1024: Internal\n");
        str.append("  Node at 512, 0, 512: Empty\n");
        str.append("  Node at 0, 0, 512: Internal\n");
        str.append("    Node at 256, 0, 256:\n");
        str.append("    (G, 257, 0)\n");
        str.append("    Node at 0, 0, 256:\n");
        str.append("    (A, 0, 0)\n");
        str.append("    (B, 0, 0)\n");
        str.append("    (C, 0, 1)\n");
        str.append("    (D, 1, 0)\n");
        str.append("    Node at 0, 256, 256: Empty\n");
        str.append("    Node at 256, 256, 256: Empty\n");

        str.append("  Node at 0, 512, 512: Empty\n");
        str.append("  Node at 512, 512, 512:\n");
        str.append("  (F, 1000, 1000)\n");
        str.append("9 quadtree nodes printed");

        assertEquals(tree.toString(), str.toString());
    }


    /**
     * Tests the remove method with a point
     */
    public void testRemove() {
        tree.insert(pair0);
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair3);
        tree.insert(new KVPair<String, Point>("G", new Point(257, 0)));
        assertEquals(tree.remove(point3).value(), pair3.value());
        assertEquals(tree.getSize(), 4);
        assertNull(tree.remove(point3));
        assertNull(tree.remove(new Point(0, 1023)));
    }


    /**
     * Tests the remove method with a pair
     */
    public void testRemovePair() {
        tree.insert(pair0);
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair3);
        tree.insert(new KVPair<String, Point>("G", new Point(257, 0)));
        assertEquals(tree.remove(pair3).value(), pair3.value());
        assertEquals(tree.getSize(), 4);
        assertNull(tree.remove(pair3));
        assertNull(tree.remove(new KVPair<String, Point>("G", new Point(0,
            1023))));
    }


    /**
     * Tests the find method
     */
    public void testFind() {
        tree.insert(pair);
        tree.insert(pair0);
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair3);
        tree.insert(new KVPair<String, Point>("G", new Point(257, 0)));
        assertEquals(tree.find(point)[0], pair);
        assertEquals(tree.find(point)[1], pair0);
        assertNull(tree.find(new Point(0, 1023)));
    }


    /**
     * Tests the regionSearch method
     */
    public void testRegionSearch() {

    }


    /**
     * Tests the duplicates method
     */
    public void testDuplicates() {
        StringBuilder str = new StringBuilder("Duplicate Points:");
        assertEquals(tree.duplicates(), str.toString());
        tree.insert(pair);
        tree.insert(pair0);
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair3);
        tree.insert(new KVPair<String, Point>("G", new Point(257, 0)));
        str.append("\n (0, 0)");
        //assertEquals(tree.duplicates(), str.toString());
    }


    /**
     * Tests the toString method
     */
    public void testToString() {
        tree.insert(pair);

        StringBuilder str = new StringBuilder("Node at 0, 0, 1024:\n");
        str.append("(A, 0, 0)\n");
        str.append("1 quadtree nodes printed");
        assertEquals(tree.toString(), str.toString());

        tree.insert(pair0);
        assertEquals(tree.getSize(), 2);
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair3);
        tree.insert(new KVPair<String, Point>("G", new Point(257, 0)));

        str = new StringBuilder("Node at 0, 0, 1024: Internal\n");
        str.append("  Node at 512, 0, 512: Empty\n");
        str.append("  Node at 0, 0, 512: Internal\n");
        str.append("    Node at 256, 0, 256:\n");
        str.append("    (G, 257, 0)\n");
        str.append("    Node at 0, 0, 256:\n");
        str.append("    (A, 0, 0)\n");
        str.append("    (B, 0, 0)\n");
        str.append("    (C, 0, 1)\n");
        str.append("    (D, 1, 0)\n");
        str.append("    Node at 0, 256, 256: Empty\n");
        str.append("    Node at 256, 256, 256: Empty\n");

        str.append("  Node at 0, 512, 512: Empty\n");
        str.append("  Node at 512, 512, 512:\n");
        str.append("  (F, 1000, 1000)\n");
        str.append("9 quadtree nodes printed");

        assertEquals(tree.toString(), str.toString());
    }
}
