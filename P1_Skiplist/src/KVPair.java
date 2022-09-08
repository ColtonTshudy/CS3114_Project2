

/**
 * Key Value pairs for use in SkipList
 * 
 * @author Open DSA
 * @version 9/1/2022
 */
public class KVPair<K extends Comparable<K>, E> {
    K theKey;
    E theVal;

    public KVPair(K k, E v) {
        theKey = k;
        theVal = v;
    }


    /**
     * Getter method for key
     *
     * @return key
     */
    public K key() {
        return theKey;
    }


    /**
     * Getter method for element
     *
     * @return element
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
