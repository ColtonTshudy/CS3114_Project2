package main;

import student.TestCase;

/**
 * SkipListTest will test all public methods in SkipList to ensure that they run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @version 9/3/2022
 */

public class SkipListTest extends TestCase {
    // Declare any necessary objects or final variables
    SkipList<String, Rectangle> sl1;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        sl1 = new SkipList<String, Rectangle>();
    }


    /**
     * This method will test the size method
     */
    public void testSize() {
        assertEquals(sl1.size(), 0);
        
        sl1.insert("Center", null);
    }
}
