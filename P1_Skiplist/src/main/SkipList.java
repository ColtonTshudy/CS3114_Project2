package main;

/**
 * SkipList data structure Class
 * 
 * @author Open DSA
 * @version 9/2/2022
 */
import java.util.Random;

class SkipList<K extends Comparable<K>, E> {
    private SkipNode<K, E> head;
    private int level;
    private int size;
    static private Random ran = new Random(); // Hold the Random class object

    public SkipList() {
        head = new SkipNode<K, E>(null, null, 0);
        level = -1;
        size = 0;
    }


    /**
     * Returns the (first) matching matching element if one exists, null
     * otherwise
     *
     * @param key
     *            key of the element
     * @return <E>
     */
    public E find(K key) {
        SkipNode<K, E> x = head; // Dummy header node
        for (int i = level; i >= 0; i--) // For each level...
            while ((x.forward[i] != null) && (x.forward[i].key().compareTo(
                key) < 0)) // go forward
                x = x.forward[i]; // Go one last step
        x = x.forward[0]; // Move to actual record, if it exists
        if ((x != null) && (x.key().compareTo(key) == 0))
            return x.element(); // Got it
        else
            return null; // Its not there
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
     * Uses geometric distribution to randomly generate an int from 0 to infinity
     * 50% chance of added each successive level from 0
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
        size++; // Increment dictionary size
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
        for (int i = 0; i <= level; i++)
            head.forward[i] = temp.forward[i];
        level = newLevel;
    }
}
