// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

/**
 * Nodes used to comprise the SkipLists
 * 
 * @author Open DSA
 * @version 9/1/2022
 *
 * @param <K>
 *            type for key, must be comparable
 * @param <E>
 *            type for value
 */
class SkipNode<K extends Comparable<K>, E> {
    private KVPair<K, E> rec;
    /**
     * SkipNode array of all nodes ahead of this node, with the level of each
     * pointer denoted by the index
     */
    protected SkipNode<K, E>[] forward; // holds forward pointers

    /**
     * Getter for node's kvpair element
     * 
     * @return value of the internal record
     */
    public E element() {
        return rec.value();
    }


    /**
     * Getter for node's kvpair key
     * 
     * @return key of the internal record
     */
    public K key() {
        return rec.key();
    }


    /**
     * Constructs a SkipNode with a KVPair containing key K and element E, with
     * a depth of level
     * 
     * @param key
     *            key of the KVPair
     * @param elem
     *            element of the KVPair
     * @param level
     *            intended depth of the node (level, length of forward)
     */
    @SuppressWarnings("unchecked")
    public SkipNode(K key, E elem, int level) {
        rec = new KVPair<K, E>(key, elem); // create the KVPair
        forward = new SkipNode[level + 1]; // depth of node
        for (int i = 0; i < level; i++) // set all forward points to null
            forward[i] = null;
    }


    /**
     * Returns a string representation of the SkipNode displaying the key and
     * value
     * 
     * @return a string representation of the SkipNode
     */
    public String toString() {
        return rec.toString();
    }
}
