// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

/**
 * Key Value pairs for use in SkipList
 * 
 * @author Open DSA
 * @version 9/1/2022
 * 
 * @param <K>
 *            type for key, must be comparable
 * @param <E>
 *            type for value
 */
public class KVPair<K extends Comparable<K>, E> {
    private K theKey;
    private E theVal;

    /**
     * Constructor for a key value pair
     * 
     * @param k
     *            key of type K
     * @param v
     *            value of type E
     */
    public KVPair(K k, E v) {
        theKey = k;
        theVal = v;
    }


    /**
     * Getter method for key
     *
     * @return key
     *         key of kv pair
     */
    public K key() {
        return theKey;
    }


    /**
     * Getter method for element
     *
     * @return element
     *         value of kv pair
     */
    public E value() {
        return theVal;
    }


    /**
     * ToString method
     *
     * @return string representation of KV Pair, "{key}, {element.toString()}"
     */
    public String toString() {
        return theKey.toString() + ", " + theVal.toString();
    }
}
