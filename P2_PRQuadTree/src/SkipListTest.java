// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

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
        b = new KVPair<String, String>("B", "object 2");
        c = new KVPair<String, String>("C", "object 3");
        e = new KVPair<String, String>("E", "object 4");

        // Add some objects to the list
        TestableRandom.setNextBooleans(false, true, false, false, true, true,
            true, false);
        sl2.insert(a);
        sl2.insert(b);
        sl2.insert(c);
        sl2.insert(e);
    }


    /**
     * This method will test the size method
     */
    public void testSize() {
        assertEquals(sl1.size(), 0);

        sl1.insert(a);

        assertEquals(sl1.size(), 1);

        sl1.removeByElement("object 1");

        assertEquals(sl1.size(), 0);

        for (int i = 0; i < 1000; i++) {
            temp = new KVPair<String, String>("A", "object 1" + i);
            sl1.insert(temp);
        }

        assertEquals(sl1.size(), 1000);

        for (int i = 0; i < 1000; i++)
            sl1.remove("A");

        assertEquals(sl1.size(), 0);
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

        KVPair<String, String> d = new KVPair<String, String>("D", "object 5");
        sl2.insert(d);

        String node5 = "Node has depth 2, Value (D, object 5)";
        size = "SkipList size is: 5";

        assertEquals(sl2.toString(), head + nl + node1 + nl + node2 + nl + node3
            + nl + node5 + nl + node4 + nl + size);
    }


    /**
     * This method will test the findByKey method
     */
    public void testFind() {
        assertTrue(sl2.find("A").equals(a));
        assertTrue(sl2.find("B").equals(b));
        assertTrue(sl2.find("C").equals(c));
        assertTrue(sl2.find("E").equals(e));

        assertNull(sl2.find("nothing"));
        assertNull(sl2.find("D"));
    }


    /**
     * This method will test the printAllMatching method
     */
    public void testPrintAllMatching() {
        String node1 = "Node has depth 1, Value (A, object 1)";

        assertEquals(sl2.printAllMatching("A"), node1);

        assertNull(sl2.printAllMatching("nothing"));
        assertNull(sl2.printAllMatching("D"));
    }

// /**
// * This method will test the findByElement method
// */
// public void testFindByElement() {
// assertTrue(sl2.findByElement("object 1").equals("object 1"));
// assertTrue(sl2.findByElement("object 2").equals("object 2"));
// assertTrue(sl2.findByElement("object 3").equals("object 3"));
// assertTrue(sl2.findByElement("object 4").equals("object 4"));
//
// assertNull(sl2.findByElement("nothing"));
// }


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
        String expected = head + nl + node1 + nl + node2 + nl + node3 + nl
            + size;
        assertEquals(a, sl2.remove("A"));
        assertEquals(expected, sl2.toString());

        // Invalid removal at beginning of list
        assertNull(sl2.remove("A"));
        assertEquals(expected, sl2.toString()); // check nothing was removed

        // Invalid removal in between keys
        assertNull(sl2.remove("D"));
        assertEquals(expected, sl2.toString()); // check nothing was removed

        // Invalid removal at end of list
        assertNull(sl2.remove("F"));
        assertEquals(expected, sl2.toString()); // check nothing was removed

        size = "SkipList size is: 2";

        // Removing at the end of the list
        expected = head + nl + node1 + nl + node2 + nl + size;
        assertEquals(e, sl2.remove("E"));
        assertEquals(expected, sl2.toString());

        // Test adding and removing a lot of entries, and removing from an empty
        // list
        for (int i = 0; i < 1000; i++)
            sl1.insert(a);

        for (int i = 0; i < 1500; i++)
            sl1.remove("A");

        // get the depth of the head node to create expected string dump
        int depth = sl1.getDeepestLevel();
        head = "Node has depth " + depth + ", Value (null)";

        size = "SkipList size is: 0";

        // check if the skip list only has 1 node (head)
        expected = head + nl + size;
        assertEquals(expected, sl1.toString());
    }


    /**
     * This method will test the removeByElement method
     */
    public void testRemoveByElement() {
        TestableRandom.setNextBooleans(false);

        KVPair<String, String> dupeKey;
        dupeKey = new KVPair<String, String>("A", "duplicate key");
        sl2.insert(dupeKey); // gets inserted after the existing key A

        // Expected string dump segments from skip list 2
        String head = "Node has depth 4, Value (null)";
        String node0 = "Node has depth 1, Value (A, duplicate key)";
        String node2 = "Node has depth 2, Value (B, object 2)";
        String node3 = "Node has depth 1, Value (C, object 3)";
        String node4 = "Node has depth 4, Value (E, object 4)";
        String size = "SkipList size is: 4";
        String nl = System.lineSeparator();

        // Removing at the beginning of the list with duplicate keys
        String expected = head + nl + node0 + nl + node2 + nl + node3 + nl
            + node4 + nl + size;
        assertEquals(a, sl2.removeByElement("object 1"));
        assertEquals(expected, sl2.toString());

        // Removing at the end of the list
        size = "SkipList size is: 3";
        expected = head + nl + node0 + nl + node2 + nl + node3 + nl + size;
        assertEquals(e, sl2.removeByElement("object 4"));
        assertEquals(expected, sl2.toString());

        // Invalid removal
        assertNull(sl2.removeByElement("object 5"));
        assertEquals(expected, sl2.toString()); // check that nothing was
                                                // removed
    }
}
