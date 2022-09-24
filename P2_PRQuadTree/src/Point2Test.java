// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

import java.io.IOException;
import student.TestCase;

/**
 * This class tests the Point2 project
 *
 * @author CS Staff
 * @author Benjamin Gallini
 * @author Colton Tshudy
 * 
 * @version 9/23/2022
 */
public class Point2Test extends TestCase {
    private String nl;

    /**
     * Sets up the test
     */
    public void setUp() {
        nl = System.lineSeparator();
    }


    /**
     * Tests for a bad file
     * 
     * @throws IOException
     *             When there is no file
     */
    public void testBadFile() throws IOException {
        String[] args = new String[1];
        args[0] = "NO_input_file_exists.txt";
        Point2.main(args);
        String out = systemOut().getHistory();
        assertFuzzyEquals("File does not exist: NO_input_file_exists.txt", out);
    }


    /**
     * Tests the sample file
     * 
     * @throws IOException
     */
    public void testSampleFile() throws IOException {
        String[] args = new String[1];
        args[0] = "ExampleInput.txt";
        Point2.main(args);

        String expected = "Point inserted: (p1, 4, 4)" + nl
            + "Point inserted: (p1, 4, 4)" + nl + "Point inserted: (P1, 4, 4)"
            + nl + "Point inserted: (p2, 300, 15)" + nl
            + "Point inserted: (p4, 700, 4)" + nl
            + "Point inserted: (p5, 700, 4)" + nl
            + "Point inserted: (pointy, 200, 900)" + nl
            + "Point rejected: (bad1, 1134, 12)" + nl
            + "Point rejected: (bad2, 3, 3221)" + nl
            + "Point rejected: (bad3, -3, 2)" + nl + "SkipList dump:" + nl
            + "Node has depth 2, Value (null)" + nl
            + "Node has depth 1, Value (P1, P1, 4, 4)" + nl
            + "Node has depth 2, Value (p1, p1, 4, 4)" + nl
            + "Node has depth 1, Value (p1, p1, 4, 4)" + nl
            + "Node has depth 1, Value (p2, p2, 300, 15)" + nl
            + "Node has depth 1, Value (p4, p4, 700, 4)" + nl
            + "Node has depth 1, Value (p5, p5, 700, 4)" + nl
            + "Node has depth 2, Value (pointy, pointy, 200, 900)" + nl
            + "SkipList size is: 7" + nl + "QuadTree dump:" + nl
            + "Node at 0, 0, 1024: Internal" + nl
            + "  Node at 0, 0, 512: Internal" + nl + "    Node at 0, 0, 256:"
            + nl + "    (P1, 4, 4)" + nl + "    (p1, 4, 4)" + nl
            + "    (p1, 4, 4)" + nl + "    Node at 256, 0, 256:" + nl
            + "    (p2, 300, 15)" + nl + "    Node at 0, 256, 256: Empty" + nl
            + "    Node at 256, 256, 256: Empty" + nl + "  Node at 512, 0, 512:"
            + nl + "  (p5, 700, 4)" + nl + "  (p4, 700, 4)" + nl
            + "  Node at 0, 512, 512:" + nl + "  (pointy, 200, 900)" + nl
            + "  Node at 512, 512, 512: Empty" + nl + "9 quadtree nodes printed"
            + nl + "Point not found: (1552, 2)" + nl + "Point not found: (8, 8)"
            + nl + "Point removed: (p2, 300, 15)" + nl
            + "Point not found: (300, 15)" + nl + "Point not removed: pOINTy"
            + nl + "Point removed: (pointy, 200, 900)" + nl + "SkipList dump:"
            + nl + "Node has depth 2, Value (null)" + nl
            + "Node has depth 1, Value (P1, P1, 4, 4)" + nl
            + "Node has depth 2, Value (p1, p1, 4, 4)" + nl
            + "Node has depth 1, Value (p1, p1, 4, 4)" + nl
            + "Node has depth 1, Value (p4, p4, 700, 4)" + nl
            + "Node has depth 1, Value (p5, p5, 700, 4)" + nl
            + "SkipList size is: 5" + nl + "QuadTree dump:" + nl
            + "Node at 0, 0, 1024: Internal" + nl + "  Node at 0, 0, 512:" + nl
            + "  (P1, 4, 4)" + nl + "  (p1, 4, 4)" + nl + "  (p1, 4, 4)" + nl
            + "  Node at 512, 0, 512:" + nl + "  (p5, 700, 4)" + nl
            + "  (p4, 700, 4)" + nl + "  Node at 0, 512, 512: Empty" + nl
            + "  Node at 512, 512, 512: Empty" + nl + "5 quadtree nodes printed"
            + nl + "Duplicate points:" + nl + "(4, 4)" + nl + "(700, 4)" + nl
            + "Rectangle rejected: (500, 500, 0, 0)" + nl
            + "Points intersecting region (500, 500, 20, 20):" + nl
            + "5 quadtree nodes visited" + nl
            + "Points intersecting region (600, 0, 200, 200):" + nl
            + "Point found: (p5, 700, 4)" + nl + "Point found: (p4, 700, 4)"
            + nl + "2 quadtree nodes visited" + nl
            + "Points intersecting region (600, 0, 2000, 2000):" + nl
            + "Point found: (p5, 700, 4)" + nl + "Point found: (p4, 700, 4)"
            + nl + "3 quadtree nodes visited" + nl + "Found (p1, 4, 4)" + nl
            + "Found (p1, 4, 4)" + nl + "Point not found: p2" + nl
            + "Found (p4, 700, 4)" + nl + "Point not found: P4" + nl + "";

        assertEquals(expected, systemOut().getHistory());
    }

}
