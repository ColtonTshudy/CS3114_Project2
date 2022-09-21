package starter;

import java.lang.reflect.Array;
import java.util.Random;
import student.TestableRandom;

// Be aware: There are always ways to misuse code.


/**
 * The SkipList class, used for efficient search, insert, remove
 * of KVPairs 
 *
 * @author CS Staff
 */
public class SkipList<K extends Comparable<K>, E> {

    private Random rng; // Randomness generator
    private int deepestLevel; // Current deepest level
    private SkipNode<K, E> head; // Header node for the skiplist
    private int size = 0; // The number of records in entire list

    public SkipList() {
        rng = new TestableRandom(); // allows for testing
        deepestLevel = 0;
        head = new SkipNode<K, E>(0, null);
        // the head node NEVER has data, it only helps us with first skips
    }


    /** Pick a level using a geometric distribution */
    private int randomLevel() {
        int level = 0;
        while (rng.nextBoolean()) {
            level++;
        }
        return level;
    }


    /** Make the header node deeper */
    private void adjustHead(int newLevel) {
        int i;
        SkipNode<K, E> temp = new SkipNode<K, E>(newLevel, null);
        for (i = 0; i <= deepestLevel; i++) {
            temp.setSkip(i, head.getSkip(i));
        }
        for (i = deepestLevel + 1; i < newLevel; i++) {
            temp.setSkip(i, null);
        }
        deepestLevel = newLevel;
        head = temp;
    }


    public int size() {
        return size;
    }


    /** Insert the newPair into the list */
    public void insert(KVPair<K, E> newPair) {
        int newLevel = randomLevel();
        if (deepestLevel < newLevel) {
            adjustHead(newLevel); // allow the head to have bigger skips
        }
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode<K, E>[] update = (SkipNode<K, E>[])Array.newInstance(
            SkipNode.class, deepestLevel + 1);
        // 'update' will have all the nodes that need to point to the new node

        Comparable<K> newKey = newPair.key();
        SkipNode<K, E> curr = head; // start at header node
        for (int i = deepestLevel; i >= 0; i--) { // starting from big skips...
            SkipNode<K, E> ahead = curr.getSkip(i);
            // find the insert position
            while (ahead != null && newKey.compareTo(ahead.getKey()) > 0) {
                curr = ahead;
            }
            update[i] = curr; // save ref for later when updating skips
        }

        curr = new SkipNode<K, E>(newLevel, newPair); // construct the new node!
        for (int i = 0; i <= newLevel; i++) {
            curr.setSkip(i, update[i].getSkip(i)); // sets new node's skips
            update[i].setSkip(i, curr); // puts new node in the skiplist
        }
        size++; // don't forget this
    }


    /**
     * Remove some record with key value "key" from the SkipList. 
     * In practice, this is the FIRST record with that key value.
     *
     * @param key Key value to find and remove
     * @return The KVPair of the removed node, or null if no key matches
     */
    public KVPair<K, E> remove(K key) {
        int i;
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode<K, E>[] update = (SkipNode<K, E>[])Array.newInstance(
            SkipNode.class, deepestLevel + 1);
        // 'update' gets all the nodes that need to point around the old node

        SkipNode<K, E> curr = head; // Start at header node
        for (i = deepestLevel; i >= 0; i--) { // starting from big skips...
            SkipNode<K, E> ahead = curr.getSkip(i);
            // find the remove position...
            while (ahead != null && key.compareTo(ahead.getKey()) > 0) {
                curr = ahead;
            }
            update[i] = curr; // save node for later, it MIGHT need an update
        }

        curr = curr.getSkip(0);
        // finally check if key k is in skipList ...
        if (curr != null && key.compareTo(curr.getKey()) == 0) {
            // start removal process, updating any skips
            for (i = 0; i < curr.getLevel(); i++) {
                update[i].setSkip(i, curr.getSkip(i));
            }
            size--; // don't forget!
            return curr.getPair(); // return the pair of the now-removed curr
        }
        return null; // Key k is not in list, so return null;
    }

    // ----------------------------------------------------------
    // Feature Needed: a 'dump' method which slowly goes though
    // skiplist and prints out each node and their level.
    // A toString method in SkipNode would help with that
    // ----------------------------------------------------------

    // ----------------------------------------------------------
    // Possibly helpful feature: a 'printAllMatching' method
    // which takes a key and uses it to skip quickly through
    // skiplist and print out any nodes that have that key
    // ----------------------------------------------------------

    // ----------------------------------------------------------
    // Possibly helpful feature: an 'exact remove' method, that
    // looks for matching key AND value before removing it ...
    // ----------------------------------------------------------

    // ----------------------------------------------------------
    // Possibly helpful feature: 'removeValue' method, that
    // slowly looks for matching value and removes it ...
    // Maybe it just finds the key for that value,
    // and removes using the key instead?
    // ----------------------------------------------------------

}
