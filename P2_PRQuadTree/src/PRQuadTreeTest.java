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
    private Point point4;
    private Point point2;
    private Point point6;
    private KVPair<String, Point> pair1;
    private KVPair<String, Point> pairC1;
    private KVPair<String, Point> pair2;
    private KVPair<String, Point> pair4;
    private KVPair<String, Point> pair5;
    private KVPair<String, Point> pair6;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        tree = new PRQuadTree();
        point = new Point(4, 4);
        point2 = new Point(300, 15);
        point4 = new Point(700, 4);
        point6 = new Point(200, 900);
        pair1 = new KVPair<String, Point>("p1", point);
        pairC1 = new KVPair<String, Point>("P1", point);
        pair2 = new KVPair<String, Point>("p2", point2);
        pair4 = new KVPair<String, Point>("p4", point4);
        pair5 = new KVPair<String, Point>("p5", point4);
        pair6 = new KVPair<String, Point>("p6", point6);
    }


    /**
     * Tests the insert method
     */
    public void testInsert() {
        tree.insert(pair1);
        StringBuilder str = new StringBuilder("Node at 0, 0, 1024:\n");
        str.append("(p1, 4, 4)\n");
        str.append("1 quadtree nodes printed");
        assertEquals(tree.toString(), str.toString());

        tree.insert(pair1);
        assertEquals(tree.getSize(), 2);
        tree.insert(pairC1);
        tree.insert(pair2);
        tree.insert(pair4);
        tree.insert(pair5);
        tree.insert(pair6);

        str = new StringBuilder("Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512: Internal\r\n" + "    Node at 0, 0, 256:\r\n"
            + "    (P1, 4, 4)\r\n" + "    (p1, 4, 4)\r\n" + "    (p1, 4, 4)\r\n"
            + "    Node at 256, 0, 256:\r\n" + "    (p2, 300, 15)\r\n"
            + "    Node at 0, 256, 256: Empty\r\n"
            + "    Node at 256, 256, 256: Empty\r\n"
            + "  Node at 512, 0, 512:\r\n" + "  (p5, 700, 4)\r\n"
            + "  (p4, 700, 4)\r\n" + "  Node at 0, 512, 512:\r\n"
            + "  (p6, 200, 900)\r\n" + "  Node at 512, 512, 512: Empty\r\n"
            + "9 quadtree nodes printed");

        assertEquals(tree.toString(), str.toString());
    }


    /**
     * Tests the remove method with a point
     */
    public void testRemove() {
        assertNull(tree.remove(point));
        tree.insert(pairC1);
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair4);
        tree.insert(new KVPair<String, Point>("G", new Point(257, 0)));
        assertEquals(tree.remove(point4).value(), pair4.value());
        assertEquals(tree.getSize(), 4);
        assertNull(tree.remove(point4));
        assertNull(tree.remove(new Point(0, 1023)));
    }


    /**
     * Tests the remove method with a pair
     */
    public void testRemovePair() {
        assertNull(tree.remove(pair1));
        tree.insert(pairC1);
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair4);
        tree.insert(new KVPair<String, Point>("G", new Point(257, 0)));
        assertEquals(tree.remove(pair4).value(), pair4.value());
        assertEquals(tree.getSize(), 4);
        assertNull(tree.remove(pair4));
        assertNull(tree.remove(new KVPair<String, Point>("G", new Point(0,
            1023))));
    }


    /**
     * Tests the collapses method
     */
    public void testCollapse() {
        tree.insert(pair1);
        tree.remove(pair1);
        String compare0 = "Node at 0, 0, 1024: Empty\n"
            + "1 quadtree nodes printed";
        assertEquals(tree.toString(), compare0);

        tree.insert(pair1);
        tree.insert(pair1);
        tree.insert(pairC1);
        tree.insert(pair2);
        tree.insert(pair4);
        tree.insert(pair5);
        tree.insert(pair6);
        tree.remove(point2);
        String compare = "Node at 0, 0, 1024: Internal" + "\n"
            + "  Node at 0, 0, 512:" + "\n" + "  (P1, 4, 4)" + "\n"
            + "  (p1, 4, 4)" + "\n" + "  (p1, 4, 4)" + "\n"
            + "  Node at 512, 0, 512:" + "\n" + "  (p5, 700, 4)" + "\n"
            + "  (p4, 700, 4)" + "\n" + "  Node at 0, 512, 512:" + "\n"
            + "  (p6, 200, 900)" + "\n" + "  Node at 512, 512, 512: Empty"
            + "\n" + "5 quadtree nodes printed";
        assertEquals(tree.toString(), compare);

        tree.remove(pair6);
        String compare2 = "Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512:\r\n" + "  (P1, 4, 4)\r\n"
            + "  (p1, 4, 4)\r\n" + "  (p1, 4, 4)\r\n"
            + "  Node at 512, 0, 512:\r\n" + "  (p5, 700, 4)\r\n"
            + "  (p4, 700, 4)\r\n" + "  Node at 0, 512, 512: Empty\r\n"
            + "  Node at 512, 512, 512: Empty\r\n" + "5 quadtree nodes printed";
        assertEquals(tree.toString(), compare2);

        tree.remove(pair1);
        String compare3 = "Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512:\r\n" + "  (P1, 4, 4)\r\n"
            + "  (p1, 4, 4)\r\n" + "  Node at 512, 0, 512:\r\n"
            + "  (p5, 700, 4)\r\n" + "  (p4, 700, 4)\r\n"
            + "  Node at 0, 512, 512: Empty\r\n"
            + "  Node at 512, 512, 512: Empty\r\n" + "5 quadtree nodes printed";
        assertEquals(tree.toString(), compare3);

        tree.remove(point4);
        tree.remove(point4);

        String compare4 = "Node at 0, 0, 1024:\r\n" + "(P1, 4, 4)\r\n"
            + "(p1, 4, 4)\r\n" + "1 quadtree nodes printed";
        assertEquals(tree.toString(), compare4);
    }


    /**
     * Tests the find method
     */
    public void testFind() {
        tree.insert(pair1);
        tree.insert(pairC1);
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair4);
        tree.insert(new KVPair<String, Point>("G", new Point(257, 0)));
        assertEquals(tree.find(point)[0], pair1);
        assertEquals(tree.find(point)[1], pairC1);
        assertNull(tree.find(new Point(0, 1023)));
    }


    /**
     * Tests the regionSearch method
     */
    public void testRegionSearch() {
        tree.insert(pair1);
        tree.insert(pair1);
        tree.insert(pairC1);
        tree.insert(pair2);
        tree.insert(pair4);
        tree.insert(pair5);
        String compare1 = "6 quadtree nodes visited\n";
        assertEquals(tree.regionSearch(500, 500, 20, 20), compare1);
        String compare2 = "Point found: (p5, 700, 4)\r\n"
            + "Point found: (p4, 700, 4)\r\n" + "2 quadtree nodes visited\n";
        assertEquals(tree.regionSearch(600, 0, 200, 200), compare2);
    }


    /**
     * Tests the duplicates method
     */
    public void testDuplicates() {
        StringBuilder str = new StringBuilder("Duplicate points:");
        assertEquals(tree.duplicates(), str.toString());
        tree.insert(pair1);
        tree.insert(pairC1);
        tree.insert(pair1);
        tree.insert(pair2);
        tree.insert(pair4);
        tree.insert(new KVPair<String, Point>("G", new Point(257, 0)));
        str.append("\n(4, 4)");
        assertEquals(tree.duplicates(), str.toString());
    }


    /**
     * Tests the toString method
     */
    public void testToString() {
        tree.insert(pair1);
        StringBuilder str = new StringBuilder("Node at 0, 0, 1024:\n");
        str.append("(p1, 4, 4)\n");
        str.append("1 quadtree nodes printed");
        assertEquals(tree.toString(), str.toString());

        tree.insert(pair1);
        assertEquals(tree.getSize(), 2);
        tree.insert(pairC1);
        tree.insert(pair2);
        tree.insert(pair4);
        tree.insert(pair5);
        tree.insert(pair6);

        str = new StringBuilder("Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512: Internal\r\n" + "    Node at 0, 0, 256:\r\n"
            + "    (P1, 4, 4)\r\n" + "    (p1, 4, 4)\r\n" + "    (p1, 4, 4)\r\n"
            + "    Node at 256, 0, 256:\r\n" + "    (p2, 300, 15)\r\n"
            + "    Node at 0, 256, 256: Empty\r\n"
            + "    Node at 256, 256, 256: Empty\r\n"
            + "  Node at 512, 0, 512:\r\n" + "  (p5, 700, 4)\r\n"
            + "  (p4, 700, 4)\r\n" + "  Node at 0, 512, 512:\r\n"
            + "  (p6, 200, 900)\r\n" + "  Node at 512, 512, 512: Empty\r\n"
            + "9 quadtree nodes printed");

        assertEquals(tree.toString(), str.toString());
    }


    /**
     * Tests inRect method
     */
    public void testInRect() {
        assertTrue(tree.inRect(point, 3, 3, 2, 2));
        assertFalse(tree.inRect(point, 5, 3, 2, 2));
        assertFalse(tree.inRect(point, 0, 3, 2, 2));
        assertFalse(tree.inRect(point, 3, 5, 2, 2));
        assertFalse(tree.inRect(point, 3, 0, 2, 2));
    }


    /**
     * Tests intersect method
     */
    public void testIntersect() {
        assertTrue(tree.intersect(point, 2, 5, 5, 2, 2));
        assertFalse(tree.intersect(point, 2, 7, 4, 2, 2));
        assertFalse(tree.intersect(point, 2, 0, 4, 2, 2));
        assertFalse(tree.intersect(point, 2, 4, 7, 2, 2));
        assertFalse(tree.intersect(point, 2, 4, 0, 2, 2));
    }
}
