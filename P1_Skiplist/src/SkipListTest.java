

import student.TestCase;
import student.TestableRandom;

/**
 * SkipListTest will test all public methods in SkipList to ensure that they run
 * and perform as expected
 *
 * @author Colton Tshudy (coltont)
 * @version 9/3/2022
 */

public class SkipListTest extends TestCase {
    // Declare any necessary objects or final variables
    SkipList<String, String> sl1;
    SkipList<String, String> sl2;

    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        sl1 = new SkipList<String, String>();

        sl2 = new SkipList<String, String>();

        // Add some objects to the list
        TestableRandom.setNextBooleans(false, true, false, false, true, true,
            true, false);
        sl2.insert("A", "object 1");
        sl2.insert("B", "object 2");
        sl2.insert("C", "object 3");
        sl2.insert("E", "object 4");
    }


    /**
     * This method will test the size method
     */
    public void testSize() {
        assertEquals(sl1.size(), 0);

        sl1.insert("A", "object 1");

        assertEquals(sl1.size(), 1);

        sl1.removeByElement("object 1");

        assertEquals(sl1.size(), 0);

        for (int i = 0; i < 1000; i++)
            sl1.insert("A", "object " + i);

        assertEquals(sl1.size(), 1000);

        for (int i = 0; i < 1000; i++)
            sl1.removeByKey("A");

        assertEquals(sl1.size(), 0);

        // System.out.println(sl1);
    }


    /**
     * This method will test the insert method
     */
    public void testInsert() {
        // Objects inserted in setUp

        // Expected toString (dump)
        String expected = "Node has depth 3, Value (null)\r\n"
            + "Node has depth 0, Value (A, object 1)\r\n"
            + "Node has depth 1, Value (B, object 2)\r\n"
            + "Node has depth 0, Value (C, object 3)\r\n"
            + "Node has depth 3, Value (E, object 4)\r\n"
            + "SkipList size is: 4";

        assertEquals(sl2.toString(), expected);

        // Inserting a KVpair that will land between 2 existing nodes
        TestableRandom.setNextBooleans(true, false);
        sl2.insert("D", "object 5");

        expected = "Node has depth 3, Value (null)\r\n"
            + "Node has depth 0, Value (A, object 1)\r\n"
            + "Node has depth 1, Value (B, object 2)\r\n"
            + "Node has depth 0, Value (C, object 3)\r\n"
            + "Node has depth 1, Value (D, object 5)\r\n"
            + "Node has depth 3, Value (E, object 4)\r\n"
            + "SkipList size is: 5";

        assertEquals(sl2.toString(), expected);
    }


    /**
     * This method will test the findByKey method
     */
    public void testFindByKey() {
        assertTrue(sl2.findByKey("A").equals("object 1"));
        assertTrue(sl2.findByKey("B").equals("object 2"));
        assertTrue(sl2.findByKey("C").equals("object 3"));
        assertTrue(sl2.findByKey("E").equals("object 4"));

        assertNull(sl2.findByKey("nothing"));
        assertNull(sl2.findByKey("D"));
    }


    /**
     * This method will test the findByElement method
     */
    public void testFindByElement() {
        assertTrue(sl2.findByElement("object 1").equals("object 1"));
        assertTrue(sl2.findByElement("object 2").equals("object 2"));
        assertTrue(sl2.findByElement("object 3").equals("object 3"));
        assertTrue(sl2.findByElement("object 4").equals("object 4"));

        assertNull(sl2.findByElement("nothing"));
    }


    /**
     * This method will test the removeByKey method
     */
    public void testRemoveByKey() {
        // Expected string dump segments from skip list 2
        String head = "Node has depth 3, Value (null)";
        String node1 = "Node has depth 1, Value (B, object 2)";
        String node2 = "Node has depth 0, Value (C, object 3)";
        String node3 = "Node has depth 3, Value (E, object 4)";
        String size = "SkipList size is: 3";

        // Removing at the beginning of the list
        assertTrue(sl2.removeByKey("A").equals("object 1"));
        assertTrue(sl2.toString().equals(head + "\r\n" + node1 + "\r\n" + node2
            + "\r\n" + node3 + "\r\n" + size));

        // Invalid removal at beginning of list
        assertNull(sl2.removeByKey("A"));
        assertTrue(sl2.toString().equals(head + "\r\n" + node1 + "\r\n" + node2
            + "\r\n" + node3 + "\r\n" + size)); // check that nothing was removed
        assertEquals(sl2.size(), 3); // check that size was not edited

        // Invalid removal in between keys
        assertNull(sl2.removeByKey("D"));
        assertTrue(sl2.toString().equals(head + "\r\n" + node1 + "\r\n" + node2
            + "\r\n" + node3 + "\r\n" + size)); // check that nothing was removed
        assertEquals(sl2.size(), 3); // check that size was not edited

        // Invalid removal at end of list
        assertNull(sl2.removeByKey("F"));
        assertTrue(sl2.toString().equals(head + "\r\n" + node1 + "\r\n" + node2
            + "\r\n" + node3 + "\r\n" + size)); // check that nothing was removed
        assertEquals(sl2.size(), 3); // check that size was not edited

        size = "SkipList size is: 2";
        
        // Removing at the end of the list
        assertTrue(sl2.removeByKey("E").equals("object 4"));
        assertTrue(sl2.toString().equals(head + "\r\n" + node1 + "\r\n"
            + node2 + "\r\n" + size));

        // Test adding and removing a lot of entries, and removing from an empty
        // list
        for (int i = 0; i < 1000; i++)
            sl1.insert("A", "object");

        for (int i = 0; i < 1500; i++)
            sl1.removeByKey("A");

        // get the depth of the head node to create expected string dump
        int depth = sl1.getHead().forward.length-1;
        head = "Node has depth " + depth + ", Value (null)";

        size = "SkipList size is: 0";
        
        // check if the skip list only has 1 node (head)
        assertTrue(sl1.toString().equals(head + "\r\n" + size));
    }


    /**
     * This method will test the removeByElement method
     */
    public void testRemoveByElement() {
        // Expected string dump segments from skip list 2
        String head = "Node has depth 3, Value (null)";
        String node1 = "Node has depth 1, Value (B, object 2)";
        String node2 = "Node has depth 0, Value (C, object 3)";
        String node3 = "Node has depth 3, Value (E, object 4)";
        String size = "SkipList size is: 3";

        // Removing at the beginning of the list
        assertTrue(sl2.removeByElement("object 1").equals("object 1"));
        assertTrue(sl2.toString().equals(head + "\r\n" + node1 + "\r\n" + node2
            + "\r\n" + node3 + "\r\n" + size ));

        // Invalid removal
        assertNull(sl2.removeByElement("object 5"));
        assertTrue(sl2.toString().equals(head + "\r\n" + node1 + "\r\n" + node2
            + "\r\n" + node3 + "\r\n" + size )); // check that nothing was removed
        assertEquals(sl2.size(), 3); // check that size was not edited
    }


    /**
     * This method will test the getHead method
     */
    public void testGetHead() {
        SkipNode<String, String> test = sl2.getHead();
        assertTrue(test.forward[0].element().equals("object 1"));
    }
}
