import java.io.IOException;
import student.TestCase;

/**
 * This class tests the Point2 project
 *
 * @author CS Staff
 */
public class Point2Test extends TestCase {

    public void setUp() {
    }

    public void testBadFile() throws IOException {
        String[] args = new String[1];
        args[0] = "NO_input_file_exists.txt";
        Point2.main(args);
        String out = systemOut().getHistory();
        assertFuzzyEquals("File does not exist: NO_input_file_exists.txt", out);
    }

}
