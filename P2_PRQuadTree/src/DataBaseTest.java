// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

import student.TestCase;
// import student.TestableRandom;
import student.testingsupport.PrintStreamWithHistory;

/**
 * SkipListTest will test all public methods in SkipList to ensure that they run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @author Benjamin Gallini (bengallini)
 * @version 9/23/2022
 */
public class DataBaseTest extends TestCase {
    // Declare any necessary objects or final variables
    private DataBase database;
    private PrintStreamWithHistory sysout;
    private String nl;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        database = new DataBase();

        // Record console history
        sysout = systemOut();

        // Next line string
        nl = "\n";
    }


    /**
     * Tests doCommand method
     */
    public void testDoCommand() {
        database.doCommand("not a command");
        assertEquals(sysout.getHistory(), "Command not recognized \n");

        sysout.clearHistory();
    }


    /**
     * This method will test the insert command
     */
    public void testInsert() {
        // Check that a valid point was inserted
        database.doCommand("insert A 0 0");
        String expected = "Point inserted: (A, 0, 0)" + nl;
        assertEquals(expected, sysout.getHistory());

        sysout.clearHistory();

        // Check that a non-valid point was not inserted
        database.doCommand("insert A -1 0");
        expected = "Point rejected: (A, -1, 0)" + nl;
        assertEquals(expected, sysout.getHistory());
    }


    /**
     * This method will test the remove by name command
     */
    public void testRemoveName() {
        database.doCommand("remove A");
        String expected = "Point not removed: A\n";
        assertEquals(expected, sysout.getHistory());

        database.doCommand("insert A 0 0");
        sysout.clearHistory();

        database.doCommand("remove A");
        expected = "Point removed: (A, 0, 0)\n";
        assertEquals(expected, sysout.getHistory());
    }


    /**
     * This method will test the remove by coordinates command
     */
    public void testRemoveCoords() {
        database.doCommand("remove -1 -1");
        String expected = "Point not found: (-1, -1)\n";
        assertEquals(expected, sysout.getHistory());
        
        database.doCommand("insert A 1 1");
        sysout.clearHistory();
        database.doCommand("remove 1 1");
        expected = "Point removed: (A, 1, 1)\n";
        assertEquals(expected, sysout.getHistory());
        
        sysout.clearHistory();
        database.doCommand("remove 1 1");
        expected = "Point not found: (1, 1)\n";
        assertEquals(expected, sysout.getHistory());
    }


    /**
     * This method will test the regionsearch command
     */
    public void testRegionSearch() {
        database.doCommand("regionsearch 0 0 0 1");
        String expected = "Rectangle rejected: (0, 0, 0, 1)\n";
        assertEquals(expected, sysout.getHistory());
        sysout.clearHistory();

        database.doCommand("regionsearch 0 0 1 0");
        expected = "Rectangle rejected: (0, 0, 1, 0)\n";
        assertEquals(expected, sysout.getHistory());
        sysout.clearHistory();

        database.doCommand("regionsearch 0 0 1 1");
        expected = "Points intersecting region (0, 0, 1, 1):\n"
            + "1 quadtree nodes visited\n";
        assertEquals(expected, sysout.getHistory());
        sysout.clearHistory();
    }


    /**
     * Tests duplicated command
     */
    public void testDuplicates() {
        database.doCommand("insert A 1 1");
        database.doCommand("insert B 1 1");
        sysout.clearHistory();

        database.doCommand("duplicates");
        String expected = "Duplicate points:\n" + "(1, 1)\n";
        assertEquals(expected, sysout.getHistory());
    }


    /**
     * This method will test the search command
     */
    public void testSearch() {
        database.doCommand("insert A 1 1");
        sysout.clearHistory();

        database.doCommand("search A");
        String expected = "Found (A, 1, 1)\n";
        assertEquals(expected, sysout.getHistory());
        sysout.clearHistory();

        database.doCommand("search a");
        expected = "Point not found: a\n";
        assertEquals(expected, sysout.getHistory());
    }


    /**
     * This method will test the dump command
     */
    public void testDump() {
        database.doCommand("dump");
        String expected = "SkipList dump:\n"
            + "Node has depth 1, Value (null)\n" + "SkipList size is: 0\n"
            + "QuadTree dump:\n" + "Node at 0, 0, 1024: Empty\n"
            + "1 quadtree nodes printed\n";
        assertEquals(expected, sysout.getHistory());
    }
}
