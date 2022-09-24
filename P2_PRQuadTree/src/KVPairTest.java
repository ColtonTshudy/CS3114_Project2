// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Gallini (bengallini)
// -- Colton Tshudy

import student.TestCase;

/**
 * KVPair test class
 * 
 * @author Benjamin Gallini
 * @author Colton Tshudy
 * @version 9/23/2022
 */
public class KVPairTest extends TestCase {
    private KVPair<String, String> pair;

    /**
     * Sets up the tests
     */
    public void setUp() {
        pair = new KVPair<String, String>("A", "apple");
    }


    /**
     * Tests compareTo method
     */
    public void testCompareTo() {
        KVPair<String, String> pair2 = new KVPair<String, String>("B",
            "banana");
        assertTrue(pair.compareTo(pair2) < 0);
        assertTrue(pair2.compareTo(pair) > 0);
        assertEquals(pair2.compareTo(pair2), 0);
        assertTrue(pair.compareTo("B") < 0);
        assertTrue(pair2.compareTo("A") > 0);
        assertEquals(pair2.compareTo("B"), 0);
    }


    /**
     * Tests key method
     */
    public void testKey() {
        assertEquals(pair.key(), "A");
    }


    /**
     * Tests value method
     */
    public void testValue() {
        assertEquals(pair.value(), "apple");
    }


    /**
     * Tests toString method
     */
    public void testToString() {
        assertEquals(pair.toString(), "A, apple");
    }


    /**
     * Tests equals method
     */
    public void testEquals() {
        assertEquals(pair, pair); // same reference

        KVPair<String, String> pair2 = new KVPair<String, String>("A", "apple");
        assertEquals(pair2, pair); // same values

        assertFalse(pair.equals(null)); // null value
        assertFalse(pair.equals("Test")); // different object class

        // different values
        pair2 = new KVPair<String, String>("B", "apple");
        assertFalse(pair.equals(pair2));

        pair2 = new KVPair<String, String>("A", "banana");
        assertFalse(pair.equals(pair2));

        // different key and value classes
        KVPair<Integer, String> pairInt = new KVPair<Integer, String>(1, "A");
        assertFalse(pair.equals(pairInt));

        KVPair<String, Integer> pairInt2 = new KVPair<String, Integer>("A", 1);
        assertFalse(pair.equals(pairInt2));
    }
}
