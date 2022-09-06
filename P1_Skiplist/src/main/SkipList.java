package main;

/**
 * SkipList data structure class
 * 
 * @author Open DSA
 * @version 9/1/2022
 */
import student.TestableRandom;

class SkipList<K extends Comparable<K>, E> {
    private SkipNode<K, E> head;
    private int level;
    private int size; // length of the skiplist at the lowest level, excluding
                      // head
    TestableRandom ran = new TestableRandom(); // Hold the Random class object

    public SkipList() {
        head = new SkipNode<K, E>(null, null, 0);
        level = -1;
        size = 0;
    }


    /**
     * Returns the (first) matching element (by key) if one exists, null
     * otherwise
     *
     * @param key
     *            key of the element
     * @return <E>
     */
    public E find(K key) {
        SkipNode<K, E> cur = head; // cursor pointer
        for (int i = level; i >= 0; i--) // For each level...
            while ((cur.forward[i] != null) && (cur.forward[i].key().compareTo(
                key) < 0)) // go forward
                cur = cur.forward[i]; // Go one last step
        cur = cur.forward[0]; // Move to actual record, if it exists

        if ((cur != null) && (cur.key().compareTo(key) == 0))
            return cur.element(); // Got it
        else
            return null; // Its not there
    }


    /**
     * Returns the (first) matching element (by element) if one exists, null
     * otherwise
     *
     * @param element
     *            element matching the given object
     * @return <E>
     */
    public E find(E element) {
        SkipNode<K, E> cur = head.forward[0]; // cursor pointer
        while (cur != null) {
            if (cur.element().equals(element))
                return cur.element();
            cur = cur.forward[0];
        }
        return null;
    }


    /**
     * Returns the number of KV pairs in the skiplist
     *
     * @return size of skiplist
     */
    public int size() {
        return size;
    }


    /**
     * Uses geometric distribution to randomly generate an int from 0 to
     * infinity. 50% chance of adding each successive level from 0
     *
     * @return randomly generated node depth
     */
    // Pick a level using a geometric distribution
    private int randomLevel() {
        int lev;
        for (lev = 0; Math.abs(ran.nextInt()) % 2 == 0; lev++)
            ; // Do nothing
        return lev;
    }


    /**
     * Insert a key, element pair into the skip list
     *
     * @param key
     *            key of new KV pair
     * @param elem
     *            element of new KV pair
     */
    @SuppressWarnings("unchecked")
    public void insert(K key, E elem) {
        int newLevel = randomLevel(); // New node's level
        if (newLevel > level) // If new node is deeper
            adjustHead(newLevel); // adjust the header
        // Track end of level
        SkipNode<K, E>[] update = new SkipNode[level + 1];
        SkipNode<K, E> x = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (x.forward[i].key().compareTo(
                key) < 0))
                x = x.forward[i];
            update[i] = x; // Track end at level i
        }
        x = new SkipNode<K, E>(key, elem, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment size
    }


    /**
     * Remove a key, element pair from the skiplist by key
     *
     * @param key
     *            key of KV pair to remove
     * @return removed key, element pair
     */
    @SuppressWarnings("unchecked")
    public SkipNode<K, E> remove(K key) {
        SkipNode<K, E> cur = head; // cursor to find removal node
        // nodes pointing to removal node
        SkipNode<K, E>[] rearNodes = new SkipNode[level + 1];

        // search for node
        for (int i = level; i >= 0; i--) {
            while ((cur.forward[i] != null) && (cur.forward[i].key().compareTo(
                key) < 0))
                cur = cur.forward[i];
            rearNodes[i] = cur; // Track end at level i
        }

        if ((cur != null) && (cur.key().compareTo(key) == 0)) {
            // re-link the nodes behind the removed node
            for (int i = cur.forward.length; i <= 0; i--)
                rearNodes[i] = cur.forward[i];
            return cur; // Return the removed node
        }
        else
            return null; // Its not there
    }


    /**
     * Remove a key, element pair from the skiplist by element
     *
     * @param element
     *            element of KV pair to remove
     * @return removed key, element pair
     */
    @SuppressWarnings("unchecked")
    public void remove(E element) {

    }


    /**
     * Adds new levels to the head if newly generated node is deeper than head
     *
     * @param newLevel
     *            new depth of SkipList
     */
    private void adjustHead(int newLevel) {
        SkipNode<K, E> temp = head;
        head = new SkipNode<K, E>(null, null, newLevel);

        // Re-link nodes to followers
        for (int i = 0; i <= level; i++)
            head.forward[i] = temp.forward[i];
        level = newLevel;
    }


    /**
     * Generates a string for the skiplist
     * 
     * @return string representing the skiplist structure
     */
    public String toString() {
        SkipNode<K, E> cur = head; // node cursor
        StringBuilder stb = new StringBuilder(); // builds output

        // for each node in the skiplist
        while (cur != null) {
            stb.append("Node has depth ");
            stb.append(cur.forward.length);
            stb.append(", Value (");
            stb.append(cur.key());
            stb.append(", ");
            stb.append(cur.element());
            stb.append(")");

            // newline if there is another node
            if (cur.forward[0] != null)
                stb.append(System.lineSeparator());

            cur = cur.forward[0]; // advance cursor
        }

        return stb.toString();
    }
}
