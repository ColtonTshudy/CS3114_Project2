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
     * This method will test the insert command
     */
    public void testInsert() {
        // Check that a valid point was inserted
        database.doCommand("insert A 0 0");
        String expected = "Point inserted: (A, 0, 0)" + nl;
        assertEquals(expected, sysout.getHistory());

        sysout.clearHistory();

        /*
         * // Check that a duplicate point was not inserted
         * database.doCommand("insert A 0 0");
         * expected = "Point rejected: (A, 0, 0)" + nl;
         * assertEquals(expected, sysout.getHistory());
         * 
         * sysout.clearHistory();
         */

        // Check that a non-valid point was not inserted
        database.doCommand("insert A -1 0");
        expected = "Point rejected: (A, -1, 0)" + nl;
        assertEquals(expected, sysout.getHistory());
    }


    /**
     * Tests sampleInput method
     */
    public void testSampleInsert() {
    }


    /**
     * This method will test the remove by name command
     */
    public void testRemoveName() {

    }


    /**
     * This method will test the remove by coordinates command
     */
    public void testRemoveCoords() {
        database.doCommand("remove -1 -1");
    }


    /**
     * This method will test the regionsearch command
     */
    public void testRegionSearch() {

    }

//
//    /**
//     * This method will test the duplicates command
//     */
//    public void testDuplicates() {
//        database.doCommand("insert A 0 0");
//        database.doCommand("insert A 1023 1023");
//        database.doCommand("insert A 1023 0");
//        database.doCommand("insert A 0 1023");
//
//        database.doCommand("dump");
//    }


    /**
     * This method will test the search command
     */
    public void testSearch() {

    }


    /**
     * This method will test the dump command
     */
    public void testDump() {

    }
}
