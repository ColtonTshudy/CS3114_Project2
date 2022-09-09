// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

import student.TestCase;

/**
 * SkipNodeTest will test all public methods in SkipNode to ensure that they run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @version 9/3/2022
 */
public class SkipNodeTest extends TestCase {
    // Declare any necessary objects or final variables
    private SkipNode<String, String> sn1;
    private SkipNode<String, String> sn2;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        sn1 = new SkipNode<String, String>("A", "element 1", 2);
        sn2 = new SkipNode<String, String>("B", "element 2", 0);
    }


    /**
     * This method will test the getters
     */
    public void testGetters() {
        assertEquals(sn1.key(), "A");
        assertEquals(sn1.element(), "element 1");
    }


    /**
     * This method will test assigning nodes to forward array
     */
    public void testForward() {
        sn1.forward[0] = sn2;
        sn1.forward[1] = sn1;
        sn1.forward[2] = null;
        
        assertEquals(sn1.forward[0], sn2);
        assertEquals(sn1.forward[1], sn1);
        assertNull(sn1.forward[2]);
    }


    /**
     * This method will test the toString() method
     */
    public void testToString() {
        assertEquals(sn1.toString(), "A, element 1");
        sn1.forward[1] = sn2;
        assertEquals(sn1.forward[1].toString(), "B, element 2");
    }

}
