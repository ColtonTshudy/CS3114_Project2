package main;

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
            while ((x.getForward(i) != null) && (x.getForward(i).key()
                .compareTo(key) < 0)) // go forward
                x = x.getForward(i); // Go one last step
        x = x.getForward(0); // Move to actual record, if it exists
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
}
