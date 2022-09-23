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
 * @author CS Staff
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
     * Returns a string representation of the KVPair displaying the key and
     * value of the record
     * 
     * @return a string representing the KVPair
     */
    public String toString() {
        return theKey.toString() + ", " + theVal.toString();
    }
}
