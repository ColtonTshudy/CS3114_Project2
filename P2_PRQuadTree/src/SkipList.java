// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

import java.lang.reflect.Array;
import java.util.Random;
import student.TestableRandom;

/**
 * The SkipList class, used for efficient search, insert, remove
 * of KVPairs
 * 
 * @author Colton Tshudy (coltont)
 * @author Benjamin Gallini (bengallini)
 * @version 9/23/2022
 *
 * @param <K>
 *            Comparable object type for the key
 * @param <E>
 *            Object type for the value/element
 */
public class SkipList<K extends Comparable<K>, E> {

    private Random rng; // Randomness generator
    private int deepestLevel; // Current deepest level
    private SkipNode<K, E> head; // Header node for the skiplist
    private int size = 0; // The number of records in entire list

    /**
     * Constructs a new SkipList of depth 0
     */
    public SkipList() {
        rng = new TestableRandom(); // allows for testing
        deepestLevel = 0;
        head = new SkipNode<K, E>(0, null);
        // the head node NEVER has data, it only helps with first skips
    }


    /**
     * Pick a level using a geometric distribution
     * 
     * @return a random level for a node
     */
    private int randomLevel() {
        int level = 0;
        while (rng.nextBoolean()) {
            level++;
        }
        return level;
    }


    /**
     * Make the header node deeper
     * 
     * @param newLevel
     */
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


    /**
     * Getter for size
     * 
     * @return size of the list
     */
    public int size() {
        return size;
    }


    /**
     * Returns the deepest level
     * 
     * @return deepest node of the skiplist
     */
    public int getDeepestLevel() {
        return deepestLevel + 1;
    }


    /**
     * Insert the newPair into the list
     * 
     * @param newPair
     *            new KVPair to insert
     */
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
                ahead = ahead.getSkip(i);
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
     * @param key
     *            Key value to find and remove
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
                ahead = ahead.getSkip(i);
            }
            update[i] = curr; // save node for later, it MIGHT need an update
        }

        curr = curr.getSkip(0);
        // finally check if key k is in skipList ...
        if (curr != null && key.compareTo(curr.getKey()) == 0) {
            // start removal process, updating any skips
            for (i = 0; i <= curr.getLevel(); i++) {
                update[i].setSkip(i, curr.getSkip(i));
            }
            size--; // don't forget!
            return curr.getPair(); // return the pair of the now-removed curr
        }
        return null; // Key k is not in list, so return null;
    }


    /**
     * Remove some record with key value "key" AND element value "element".
     *
     * @param pair
     *            KVPair to attempt to remove from the SkipList
     *
     * @return The KVPair of the removed node, or null if no match was found
     */
    public KVPair<K, E> remove(KVPair<K, E> pair) {
        int i;
        K key = pair.key();

        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode<K, E>[] rearNodes = (SkipNode<K, E>[])Array.newInstance(
            SkipNode.class, deepestLevel + 1);
        // 'update' gets all the nodes that need to point around the old node

        SkipNode<K, E> curr = head; // Start at header node

        for (i = deepestLevel; i >= 0; i--) { // starting from big skips...
            SkipNode<K, E> ahead = curr.getSkip(i);
            // find the remove position...
            while (ahead != null && key.compareTo(ahead.getKey()) >= 0
                && pair != ahead.getPair()) {
                curr = ahead;
                ahead = ahead.getSkip(i); // advance to the next node
            }
            rearNodes[i] = curr; // save node for later, it MIGHT need an update
        }

        curr = curr.getSkip(0);

        // finally check if key k is in skipList ...
        if (curr != null && pair == curr.getPair()) {
            // start removal process, updating any skips
            for (i = 0; i <= curr.getLevel(); i++) {
                rearNodes[i].setSkip(i, curr.getSkip(i));
            }
            size--; // don't forget!
            return curr.getPair(); // return the pair of the now-removed curr
        }
        return null; // Key k is not in list, so return null;
    }


    /**
     * Remove a key, element pair from the skiplist by element
     *
     * @param element
     *            element of KV pair to remove
     * @return removed key, element pair
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, E> removeByElement(E element) {
        SkipNode<K, E> rem = findNode(element); // find a matching node
        if (rem == null)
            return null; // It's not there

        // node was found, attempt removal
        return remove(rem.getPair());
    }


    /**
     * Returns a KVPair for the found element
     * 
     * @param key
     *            Key for the node being found
     * @return the first KVPair matching the key, null if not found
     */
    public KVPair<K, E> find(K key) {
        SkipNode<K, E> curr = findNode(key);
        if (curr == null)
            return null;
        return curr.getPair();
    }


    /**
     * Finds the first node of a given key
     * 
     * @param key
     *            The key being found
     * @return the first node matching the key, null if not found
     */
    private SkipNode<K, E> findNode(K key) {
        SkipNode<K, E> curr = head; // Start at header node
        for (int i = deepestLevel; i >= 0; i--) { // starting from big skips...
            SkipNode<K, E> ahead = curr.getSkip(i);
            // find the matching node position...
            while (ahead != null && key.compareTo(ahead.getKey()) > 0) {
                curr = ahead;
                ahead = ahead.getSkip(i); // advance to the next node
            }
        }
        curr = curr.getSkip(0); // Proceed to node with matching key

        if (curr != null && key.compareTo(curr.getKey()) == 0)
            return curr; // matching node found
        return null; // no matching node found
    }


    /**
     * Finds the first node of a given element
     *
     * @param element
     *            element of KV pair to remove
     * @return matching node, or null if not found
     */
    private SkipNode<K, E> findNode(E element) {
        SkipNode<K, E> cur = head.getSkip(0); // cursor pointer
        while (cur != null) {
            if (cur.getValue().equals(element))
                return cur;
            cur = cur.getSkip(0);
        }
        return null;
    }


    /**
     * Returns a string representation of the SkipList displaying the depth of
     * each node, each node's key and value, and the size of the SkipList
     * 
     * @return a string representing the SkipList structure
     */
    public String toString() {
        SkipNode<K, E> curr = head; // node cursor
        StringBuilder stb = new StringBuilder(); // builds output

        // for each node in the skiplist, minus head
        while (curr != null) {
            stb.append(curr.toString());

            // newline if there is another node
            stb.append(System.lineSeparator());

            curr = curr.getSkip(0); // advance cursor
        }

        stb.append("SkipList size is: ");
        stb.append(size);

        return stb.toString();
    }


    /**
     * Takes a key to find all nodes with the same key
     * 
     * @param key
     *            key to compare against
     * @return String containing all matching nodes toString
     */
    public String printAllMatching(K key) {
        SkipNode<K, E> curr = findNode(key); // node cursor
        StringBuilder stb = new StringBuilder(); // builds output

        if (curr == null) { // No nodes were found matching k
            return null;
        }

        // List out all nodes with key K
        while (curr != null && curr.getKey().equals(key)) {
            stb.append(curr.toString());
            curr = curr.getSkip(0);

            // Check if we need to add a line separator
            if (curr != null && curr.getKey().equals(key))
                stb.append(System.lineSeparator());
        }

        return stb.toString();
    }

}
