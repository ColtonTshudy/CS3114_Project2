// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

import student.TestCase;

/**
 * KVPairTest will test all public methods in SkipList to ensure that they run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @version 9/3/2022
 */
public class KVPairTest extends TestCase {
    // Declare any necessary objects or final variables
    private KVPair<String, String> kv1;
    private KVPair<String, String> kv2;
    private KVPair<String, String> kv3;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        kv1 = new KVPair<String, String>("A", "element 1");
        kv2 = new KVPair<String, String>("B", "element 2");
        kv3 = new KVPair<String, String>("C", "element 1");
    }


    /**
     * This method will test the method
     */
    public void testNotIntersectsWith() {

    }

}
