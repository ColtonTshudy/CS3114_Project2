// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

import java.lang.reflect.Array;

/**
 * Nodes used to comprise the SkipLists
 * 
 * @author Colton Tshudy (coltont)
 * @author Benjamin Gallini (bengallini)
 * @version 9/23/2022
 *
 * @param <K>
 *            Comparable object type for the key
 * @param <V>
 *            Object type for the value/element
 */
public class SkipNode<K extends Comparable<K>, V> {

    private KVPair<K, V> pair;
    private SkipNode<K, V>[] skips;

    /**
     * Constructor for a SkipNode
     * 
     * @param level
     *            level of the node
     * @param pair
     *            KVPair (record) of the node
     */
    @SuppressWarnings("unchecked")
    public SkipNode(int level, KVPair<K, V> pair) {
        this.pair = pair;
        skips = (SkipNode[])Array.newInstance(SkipNode.class, level + 1);
        for (int i = 0; i < skips.length; i++) {
            skips[i] = null;
        }
    }


    /**
     * Getter for the key of the internal KVPair
     * 
     * @return key of the record
     */
    public K getKey() {
        return pair.key();
    }


    /**
     * Getter for the value of the internal KVPair
     * 
     * @return value of the record
     */
    public V getValue() {
        return pair.value();
    }


    /**
     * Getter for the node's KVPair
     * 
     * @return KVPair of the node
     */
    public KVPair<K, V> getPair() {
        return pair;
    }


    /**
     * Sets the pointer of a given level to a given node
     * 
     * @param level
     *            level to be set
     * @param nextNode
     *            node to be linked
     */
    public void setSkip(int level, SkipNode<K, V> nextNode) {
        skips[level] = nextNode;
    }


    /**
     * 
     * 
     * @param level
     * @return
     */
    public SkipNode<K, V> getSkip(int level) {
        return skips[level];
    }


    /**
     * Returns the level of the node
     * 
     * @return level of the node
     */
    public int getLevel() {
        return skips.length - 1;
    }


    /**
     * Returns a string representation of the SkipNode displaying the key and
     * value
     * 
     * @return a string representation of the SkipNode
     */
    public String toString() {
        String pairString = "null";
        if (pair != null)
            pairString = pair.toString();

        return "Node has depth " + skips.length + ", Value (" + pairString
            + ")";
    }

}
