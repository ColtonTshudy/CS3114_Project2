// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

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
    private SkipList<String, String> sl1;
    private SkipList<String, String> sl2;
    
    private KVPair<String, String> a;
    private KVPair<String, String> b;
    private KVPair<String, String> c;
    private KVPair<String, String> e;
    private KVPair<String, String> temp;


    /**
     * sets up each test method before it runs
     */
    public void setUp() {
        sl1 = new SkipList<String, String>();
        sl2 = new SkipList<String, String>();
        
        a = new KVPair<String, String>("A", "object 1");

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
        // Expected toString (dump)
        String head = "Node has depth 4, Value (null)";
        String node1 = "Node has depth 1, Value (A, object 1)";
        String node2 = "Node has depth 2, Value (B, object 2)";
        String node3 = "Node has depth 1, Value (C, object 3)";
        String node4 = "Node has depth 4, Value (E, object 4)";
        String size = "SkipList size is: 4";
        String nl = System.lineSeparator();

        assertEquals(sl2.toString(), head + nl + node1 + nl + node2 + nl + node3
            + nl + node4 + nl + size);

        // Inserting a KVpair that will land between 2 existing nodes
        TestableRandom.setNextBooleans(true, false);
        sl2.insert("D", "object 5");

        String node5 = "Node has depth 2, Value (D, object 5)";
        size = "SkipList size is: 5";

        assertEquals(sl2.toString(), head + nl + node1 + nl + node2 + nl + node3
            + nl + node5 + nl + node4 + nl + size);
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
        String head = "Node has depth 4, Value (null)";
        String node1 = "Node has depth 2, Value (B, object 2)";
        String node2 = "Node has depth 1, Value (C, object 3)";
        String node3 = "Node has depth 4, Value (E, object 4)";
        String size = "SkipList size is: 3";
        String nl = System.lineSeparator();

        // Removing at the beginning of the list
        assertTrue(sl2.removeByKey("A").equals("object 1"));
        assertTrue(sl2.toString().equals(head + nl + node1 + nl + node2 + nl
            + node3 + nl + size));

        // Invalid removal at beginning of list
        assertNull(sl2.removeByKey("A"));
        assertTrue(sl2.toString().equals(head + nl + node1 + nl + node2 + nl
            + node3 + nl + size)); // check nothing was removed

        // Invalid removal in between keys
        assertNull(sl2.removeByKey("D"));
        assertTrue(sl2.toString().equals(head + nl + node1 + nl + node2 + nl
            + node3 + nl + size)); // check nothing was // removed

        // Invalid removal at end of list
        assertNull(sl2.removeByKey("F"));
        assertTrue(sl2.toString().equals(head + nl + node1 + nl + node2 + nl
            + node3 + nl + size)); // check nothing was removed

        size = "SkipList size is: 2";

        // Removing at the end of the list
        assertTrue(sl2.removeByKey("E").equals("object 4"));
        assertTrue(sl2.toString().equals(head + nl + node1 + nl + node2 + nl
            + size));

        // Test adding and removing a lot of entries, and removing from an empty
        // list
        for (int i = 0; i < 1000; i++)
            sl1.insert("A", "object");

        for (int i = 0; i < 1500; i++)
            sl1.removeByKey("A");

        // get the depth of the head node to create expected string dump
        int depth = sl1.getHead().forward.length;
        head = "Node has depth " + depth + ", Value (null)";

        size = "SkipList size is: 0";

        // check if the skip list only has 1 node (head)
        assertTrue(sl1.toString().equals(head + nl + size));
    }


    /**
     * This method will test the removeByElement method
     */
    public void testRemoveByElement() {
        TestableRandom.setNextBooleans(false);
        sl2.insert("A", "duplicate key");

        // Expected string dump segments from skip list 2
        String head = "Node has depth 4, Value (null)";
        String node0 = "Node has depth 1, Value (A, duplicate key)";
        String node2 = "Node has depth 2, Value (B, object 2)";
        String node3 = "Node has depth 1, Value (C, object 3)";
        String node4 = "Node has depth 4, Value (E, object 4)";
        String size = "SkipList size is: 4";
        String nl = System.lineSeparator();

        // Removing in the middle of the list with duplicate object names
        assertTrue(sl2.removeByElement("object 1").equals("object 1"));
        assertTrue(sl2.toString().equals(head + nl + node0 + nl + node2 + nl
            + node3 + nl + node4 + nl + size));

        sl2.insert("A", "object 1");

        // Removing at the beginning of the list with duplicate objects names
        assertTrue(sl2.removeByElement("object 1").equals("object 1"));
        assertTrue(sl2.toString().equals(head + nl + node0 + nl + node2 + nl
            + node3 + nl + node4 + nl + size));

        // Invalid removal
        assertNull(sl2.removeByElement("object 5"));
        assertTrue(sl2.toString().equals(head + nl + node0 + nl + node2 + nl
            + node3 + nl + node4 + nl + size)); // check that nothing was
                                                // removed
    }


    /**
     * This method will test the getHead method
     */
    public void testGetHead() {
        SkipNode<String, String> test = sl2.getHead();
        assertTrue(test.forward[0].element().equals("object 1"));
    }
}
