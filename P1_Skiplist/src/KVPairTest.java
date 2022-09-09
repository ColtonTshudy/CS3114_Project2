// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

import student.TestCase;

/**
 * KVPairTest will test all public methods in KVPair to ensure that they run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @version 9/3/2022
 */
public class KVPairTest extends TestCase {
    // Declare any necessary objects or final variables
    private KVPair<String, String> kv1;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        kv1 = new KVPair<String, String>("A", "element 1");
    }


    /**
     * This method will test the getters
     */
    public void testGetters() {
        assertEquals(kv1.key(), "A");
        assertEquals(kv1.value(), "element 1");
    }
    
    /**
     * This method will test the getters
     */
    public void testSetters() {
        assertEquals(kv1.key(), "A");
        assertEquals(kv1.value(), "element 1");
    }
    
    /**
     * This method will test the toString() method
     */
    public void testToString() {
        assertEquals(kv1.toString(), "A, element 1");
    }

}
