// Be aware: There are always ways to misuse code, never trust anyone's untested
// code
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

/**
 * The KVPair class, for holding keys and values together,
 * and comparing them using only the Key
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
public class KVPair<K extends Comparable<K>, V>
    implements Comparable<KVPair<K, V>> {
    private K theKey;
    private V theVal;

    /**
     * Constructs a new KVPair object with given key and value
     * 
     * @param k
     *            key of the record
     * @param v
     *            value of the record
     */
    public KVPair(K k, V v) {
        theKey = k;
        theVal = v;
    }


    /**
     * Getter for the key
     * 
     * @return key of the record
     */
    public K key() {
        return theKey;
    }


    /**
     * Getter for the value
     * 
     * @return value of the record
     */
    public V value() {
        return theVal;
    }


    /**
     * Compares the key of this record against another key. Uses the
     * compareTo() of the key.
     * 
     * @param other
     *            The other record to compare to this record
     * 
     * @return <0 if this key is less than, >0 if this key is greater than, 0 if
     *         keys are equal
     */
    public int compareTo(K other) {
        return theKey.compareTo(other);
    }


    /**
     * Compares the key of this record against the key of another record. Uses
     * the compareTo() of the key.
     * 
     * @param other
     *            The other record to compare to this record
     * 
     * @return <0 if this key is less than, >0 if this key is greater than, 0 if
     *         keys are equal
     */
    public int compareTo(KVPair<K, V> other) {
        return theKey.compareTo(other.key());
    }


    /**
     * Returns true if both KVPairs are equal
     * 
     * @param other
     *            The other KVPair to check equality
     * 
     * @return true if both pairs have the exact same key and value, false
     *         otherwise
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        // Check if other is an instance of a KVPair
        if (other instanceof KVPair<?, ?>) {
            KVPair<?, ?> pair = (KVPair<?, ?>)other;
            return theKey.equals(pair.key()) && theVal.equals(pair.value());
        }
        return false;
    }


    /**
     * Returns a string representation of the KVPair displaying the key and
     * value of the record
     * 
     * @return a string representing the KVPair
     */
    public String toString() {
        return theKey.toString() + ", " + theVal.toString();
    }
}
