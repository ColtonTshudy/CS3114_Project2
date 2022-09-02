package main;

/**
 * Key Value pairs for use in SkipList
 * 
 * @author Open DSA
 * @version 9/1/2022
 */
public class KVPair<K extends Comparable<K>, E>
    implements Comparable<KVPair<K, E>> {
    K theKey;
    E theVal;

    KVPair(K k, E v) {
        theKey = k;
        theVal = v;
    }


    /**
     * Compare KV Pairs
     *
     * @return -difference if this is lower, 0 if exact, +difference if higher 
     */
    public int compareTo(KVPair<K, E> it) {
        return theKey.compareTo(it.key());
    }


    /**
     * Compare Key to the key of this KV Pair
     *
     * @return -difference if this is lower, 0 if exact, +difference if higher
     */
    public int compareTo(K it) {
        return theKey.compareTo(it);
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
     * @return string representation of KV Pair, "key, element.toString()"
     */
    public String toString() {
        return theKey.toString() + ", " + theVal.toString();
    }
}
