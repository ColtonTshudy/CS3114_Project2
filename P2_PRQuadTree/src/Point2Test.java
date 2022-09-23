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

    /**
     * Sets up the test
     */
    public void setUp() {
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

}
