

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
    private int size; // number of KV pairs in the data structure
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
    public E findByKey(K key) {
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
    public E findByElement(E element) {
        SkipNode<K, E> cur = head.forward[0]; // cursor pointer
        while (cur != null) {
            if (cur.element().equals(element))
                return cur.element();
            cur = cur.forward[0];
        }
        return null;
    }

    /**
     * Returns a linkedlist of all elements that return >0 by comparing each
     * element
     * element to a given element
     *
     * @param element
     *            element to compare against
     * @return <E>
     */
/*
 * @SuppressWarnings("unchecked")
 * public SkipNode<K, E> findAllComparesGreater(E element) {
 * SkipNode<K, E> listHead = new SkipNode<K, E>(null, null, 1);
 * SkipNode<K, E> listCur = listHead;
 * SkipNode<K, E> cur = head.forward[0]; // cursor pointer
 * int i = 0; // index of similarNodes
 * while (cur != null) {
 * if (cur.element().compareTo(element) > 0) {
 * listCur.forward[0] = cur;
 * listCur = listCur.forward[0];
 * i++;
 * }
 * cur = cur.forward[0];
 * }
 * return listHead;
 * }
 */


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
        for (lev = 0; ran.nextBoolean(); lev++)
            ; // advance level
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
        SkipNode<K, E>[] rearNodes = new SkipNode[level + 1];

        SkipNode<K, E> cur = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((cur.forward[i] != null) && (cur.forward[i].key().compareTo(
                key) < 0))
                cur = cur.forward[i];
            rearNodes[i] = cur; // Track end at level i
        }
        cur = new SkipNode<K, E>(key, elem, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            cur.forward[i] = rearNodes[i].forward[i]; // Who x points to
            rearNodes[i].forward[i] = cur; // Who points to x
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
    public E removeByKey(K key) {
        SkipNode<K, E> cur = head; // cursor to find removal node
        // nodes pointing to removal node
        SkipNode<K, E>[] rearNodes = new SkipNode[level + 1];

        // search for node with matching key
        for (int i = level; i >= 0; i--) {
            while ((cur.forward[i] != null) && (cur.forward[i].key().compareTo(
                key) < 0))
                cur = cur.forward[i];
            rearNodes[i] = cur; // Track end at level i
        }

        SkipNode<K, E> target = cur.forward[0]; // node to remove

        if ((target != null) && (target.key().compareTo(key) == 0)) {
            // re-link the nodes behind the removed node
            for (int i = target.forward.length - 1; i >= 0; i--)
                rearNodes[i].forward[i] = target.forward[i];
            size--;
            return target.element(); // Return the removed node
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
    public E removeByElement(E element) {
        SkipNode<K, E> cur = findNode(element); // find a matching node
        if (cur == null)
            return null; // It's not there

        K key = cur.key(); // key of found node
        return removeByKey(key); // removes and returns the node
    }


    /**
     * Finds a node based on the element
     *
     * @param element
     *            element of KV pair to remove
     * @return matching node, or null if not found
     */
    private SkipNode<K, E> findNode(E element) {
        SkipNode<K, E> cur = head.forward[0]; // cursor pointer
        while (cur != null) {
            if (cur.element().equals(element))
                return cur;
            cur = cur.forward[0];
        }
        return null;
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
     * Returns the head node of the Skip List
     *
     * @return head node of skiplist
     */
    public SkipNode<K, E> getHead() {
        return head;
    }


    /**
     * Generates a string for the skiplist
     * 
     * @return string representing the skiplist structure
     */
    public String toString() {
        SkipNode<K, E> cur = head; // node cursor
        StringBuilder stb = new StringBuilder(); // builds output

        // for each node in the skiplist, minus head
        while (cur != null) {
            stb.append("Node has depth ");
            stb.append(cur.forward.length-1);
            stb.append(", Value (");
            if (cur.key() != null) {
                stb.append(cur.key());
                stb.append(", ");
            }
            stb.append(cur.element());
            stb.append(")");

            // newline if there is another node
            stb.append(System.lineSeparator());

            cur = cur.forward[0]; // advance cursor
        }
        
        stb.append("SkipList size is: ");
        stb.append(size);

        return stb.toString();
    }
}
