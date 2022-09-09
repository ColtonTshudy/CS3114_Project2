// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

import java.io.FileNotFoundException;
import java.text.ParseException;
import student.TestCase;
import student.TestableRandom;
import student.testingsupport.PrintStreamWithHistory;

/**
 * FileReaderTest will test all public methods in FileReader to ensure that they
 * run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @version 9/3/2022
 */
public class FileReaderTest extends TestCase {
    // Declare any necessary objects or final variables
    private FileReader reader;
    private PrintStreamWithHistory sysout;
    private String nl;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        // Record console history
        sysout = systemOut();

        // Next line string
        nl = System.lineSeparator();
    }


    /**
     * This method will test the FileReader constructor errors
     */
    public void testConstructorError() {
        reader = new FileReader();
        assertEquals(systemOut().getHistory(), "Please provide a file name."
            + nl);

        // checks that FileReader cannot be initialized with a non-existent file
        Exception exception = null;
        try {
            reader = new FileReader("nothing.txt");
            fail("FileReader() is not throwing an exception");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("FileReader() is throwing the wrong type of exception",
            exception instanceof FileNotFoundException);

        // checks that FileReader throws an error for too many tokens on a line
        exception = null;
        try {
            reader = new FileReader("SkipListBadInput.txt");
            fail("FileReader() is not throwing an exception");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("FileReader() is throwing the wrong type of exception",
            exception instanceof ParseException);
    }


    /**
     * This method will test the output of FileReader with the given sample
     */
    public void testFileReaderOutput() {
        // Set the randomness
        TestableRandom.setNextBooleans(false, false, false, false, false, false,
            false, false, false, false);

        // Read the sample input file
        try {
            reader = new FileReader("SkipListSampleInput.txt");
        }
        catch (Exception e) {
            reader = new FileReader();
        }

        String expected = "Rectangle rejected: (r_r, -1, -20, 3, 4)" + nl
            + "Rectangle rejected: (rec, 7, -8, 1, 3)" + nl
            + "Rectangle rejected: (virtual_rec0, 1, 1, 0, 0)" + nl
            + "Rectangle rejected: (virtual_REC0, 0, 0, 11, 0)" + nl
            + "Rectangle rejected: (inExistRec_0, 1, 1, -1, -2)" + nl
            + "Rectangle rejected: (11, 11, 0, 0)" + nl + "Intersections pairs:"
            + nl + "SkipList dump:" + nl + "Node has depth 1, Value (null)" + nl
            + "SkipList size is: 0" + nl + "Rectangle not found: (r_r)" + nl
            + "Rectangle not removed: (r_r)" + nl
            + "Rectangle rejected: (1, 1, 0, 0)" + nl
            + "Rectangles intersecting region (-5, -5, 20, 20): " + nl
            + "Rectangle rejected: (5, 5, 0, 0)" + nl
            + "Rectangle inserted: (goodRect, 5, 3, 56, 56)" + nl
            + "Rectangle inserted: (goodRect2, 111, 400, 20, 20)" + nl
            + "Rectangle inserted: (goodRect3, 25, 3, 6, 6)" + nl
            + "SkipList dump:" + nl + "Node has depth 1, Value (null)" + nl
            + "Node has depth 1, Value (goodRect, 5, 3, 56, 56)" + nl
            + "Node has depth 1, Value (goodRect2, 111, 400, 20, 20)" + nl
            + "Node has depth 1, Value (goodRect3, 25, 3, 6, 6)" + nl
            + "SkipList size is: 3" + nl
            + "Rectangle removed: (goodRect2, 111, 400, 20, 20)" + nl
            + "Intersections pairs:" + nl
            + "(goodRect, 5, 3, 56, 56 | goodRect3, 25, 3, 6, 6)" + nl
            + "(goodRect3, 25, 3, 6, 6 | goodRect, 5, 3, 56, 56)" + nl
            + "Rectangles found:" + nl + "(goodRect3, 25, 3, 6, 6)" + nl
            + "Rectangle rejected: (-900, 5, 0, 5000)" + nl + "";

        assertEquals(systemOut().getHistory(), expected);
    }


    /**
     * This method will test the output of FileReader for customized tests
     */
    public void testFileReaderOutputOddballs() {
        // Set the randomness
        TestableRandom.setNextBooleans(false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false);

        // Read the sample input file
        reader = new FileReader();
        assertEquals(systemOut().getHistory(), "Please provide a file name."
            + nl);

        String expected = "Please provide a file name." + nl
            + "Rectangle inserted: (A, 5, 5, 5, 5)" + nl
            + "Rectangle inserted: (B, 3, 6, 10, 3)" + nl
            + "Rectangle inserted: (C, 6, 3, 3, 10)" + nl
            + "Rectangle inserted: (D, 5, 5, 5, 5)" + nl
            + "Rectangle inserted: (E, 10, 10, 5, 5)" + nl
            + "Rectangle inserted: (A, 15, 15, 5, 5)" + nl
            + "Rectangle inserted: (A, 20, 20, 5, 5)" + nl
            + "Rectangle inserted: (A, 25, 25, 5, 5)" + nl
            + "Rectangle inserted: (A, 30, 30, 5, 5)" + nl
            + "Rectangle inserted: (N, 0, 0, 100, 100)" + nl
            + "Rectangle rejected: (0badName, 1, 1, 1, 1)" + nl
            + "Rectangle rejected: (name, 1025, 1, 1, 1)" + nl
            + "Rectangle rejected: (name, 1, 1025, 1, 1)" + nl
            + "Intersections pairs:" + nl
            + "(A, 30, 30, 5, 5 | N, 0, 0, 100, 100)" + nl
            + "(A, 25, 25, 5, 5 | N, 0, 0, 100, 100)" + nl
            + "(A, 20, 20, 5, 5 | N, 0, 0, 100, 100)" + nl
            + "(A, 15, 15, 5, 5 | N, 0, 0, 100, 100)" + nl
            + "(A, 5, 5, 5, 5 | B, 3, 6, 10, 3)" + nl
            + "(A, 5, 5, 5, 5 | C, 6, 3, 3, 10)" + nl
            + "(A, 5, 5, 5, 5 | D, 5, 5, 5, 5)" + nl
            + "(A, 5, 5, 5, 5 | N, 0, 0, 100, 100)" + nl
            + "(B, 3, 6, 10, 3 | A, 5, 5, 5, 5)" + nl
            + "(B, 3, 6, 10, 3 | C, 6, 3, 3, 10)" + nl
            + "(B, 3, 6, 10, 3 | D, 5, 5, 5, 5)" + nl
            + "(B, 3, 6, 10, 3 | N, 0, 0, 100, 100)" + nl
            + "(C, 6, 3, 3, 10 | A, 5, 5, 5, 5)" + nl
            + "(C, 6, 3, 3, 10 | B, 3, 6, 10, 3)" + nl
            + "(C, 6, 3, 3, 10 | D, 5, 5, 5, 5)" + nl
            + "(C, 6, 3, 3, 10 | N, 0, 0, 100, 100)" + nl
            + "(D, 5, 5, 5, 5 | A, 5, 5, 5, 5)" + nl
            + "(D, 5, 5, 5, 5 | B, 3, 6, 10, 3)" + nl
            + "(D, 5, 5, 5, 5 | C, 6, 3, 3, 10)" + nl
            + "(D, 5, 5, 5, 5 | N, 0, 0, 100, 100)" + nl
            + "(E, 10, 10, 5, 5 | N, 0, 0, 100, 100)" + nl
            + "(N, 0, 0, 100, 100 | A, 30, 30, 5, 5)" + nl
            + "(N, 0, 0, 100, 100 | A, 25, 25, 5, 5)" + nl
            + "(N, 0, 0, 100, 100 | A, 20, 20, 5, 5)" + nl
            + "(N, 0, 0, 100, 100 | A, 15, 15, 5, 5)" + nl
            + "(N, 0, 0, 100, 100 | A, 5, 5, 5, 5)" + nl
            + "(N, 0, 0, 100, 100 | B, 3, 6, 10, 3)" + nl
            + "(N, 0, 0, 100, 100 | C, 6, 3, 3, 10)" + nl
            + "(N, 0, 0, 100, 100 | D, 5, 5, 5, 5)" + nl
            + "(N, 0, 0, 100, 100 | E, 10, 10, 5, 5)" + nl
            + "Rectangles intersecting region (-1000, -1000, 100000, 100000): "
            + nl + "(A, 30, 30, 5, 5)" + nl + "(A, 25, 25, 5, 5)" + nl
            + "(A, 20, 20, 5, 5)" + nl + "(A, 15, 15, 5, 5)" + nl
            + "(A, 5, 5, 5, 5)" + nl + "(B, 3, 6, 10, 3)" + nl
            + "(C, 6, 3, 3, 10)" + nl + "(D, 5, 5, 5, 5)" + nl
            + "(E, 10, 10, 5, 5)" + nl + "(N, 0, 0, 100, 100)" + nl
            + "Rectangles intersecting region (5, 5, 5, 5): " + nl
            + "(A, 5, 5, 5, 5)" + nl + "(B, 3, 6, 10, 3)" + nl
            + "(C, 6, 3, 3, 10)" + nl + "(D, 5, 5, 5, 5)" + nl
            + "(N, 0, 0, 100, 100)" + nl + "Rectangle rejected: (5, 5, -1, 5)"
            + nl + "Rectangle rejected: (5, 5, 5, -1)" + nl
            + "Rectangles found:" + nl + "(A, 30, 30, 5, 5)" + nl
            + "(A, 25, 25, 5, 5)" + nl + "(A, 20, 20, 5, 5)" + nl
            + "(A, 15, 15, 5, 5)" + nl + "(A, 5, 5, 5, 5)" + nl
            + "Rectangle removed: (A, 5, 5, 5, 5)" + nl
            + "Rectangle removed: (D, 5, 5, 5, 5)" + nl
            + "Rectangle not removed: (5, 5, 5, 5)" + nl
            + "Rectangle rejected: (-1, 5, 5, 5)" + nl;

        try {
            // Inserts
            reader.enterCommand("insert A 5 5 5 5");
            reader.enterCommand("insert B 3 6 10 3");
            reader.enterCommand("insert C 6 3 3 10");
            reader.enterCommand("insert D 5 5 5 5");
            reader.enterCommand("insert E 10 10 5 5");
            reader.enterCommand("insert A 15 15 5 5");
            reader.enterCommand("insert A 20 20 5 5");
            reader.enterCommand("insert A 25 25 5 5");
            reader.enterCommand("insert A 30 30 5 5");
            reader.enterCommand("insert N 0 0 100 100"); // contains all (9)

            // Fail inserts
            reader.enterCommand("insert 0badName 1 1 1 1");
            reader.enterCommand("insert name 1025 1 1 1");
            reader.enterCommand("insert name 1 1025 1 1");

            // Intersections
            reader.enterCommand("intersections");

            // Regionsearch
            reader.enterCommand("regionsearch -1000 -1000 100000 100000");
            reader.enterCommand("regionsearch 5 5 5 5");
            reader.enterCommand("regionsearch 5 5 -1 5");
            reader.enterCommand("regionsearch 5 5 5 -1");

            // Search
            reader.enterCommand("search A");

            // Removes
            reader.enterCommand("remove 5 5 5 5");
            reader.enterCommand("remove 5 5 5 5");
            reader.enterCommand("remove 5 5 5 5");
            reader.enterCommand("remove -1 5 5 5");

            assertEquals(systemOut().getHistory(), expected);
        }
        catch (ParseException e) {
            fail("Incorrect command");
        }
    }

}
