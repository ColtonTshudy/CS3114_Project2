package starter;

//Be aware: There are always ways to misuse code, never trust anyone's untested code
//For example, you probably want to modify this to be in the default package.

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

    public KVPair(K k, V v) {
        theKey = k;
        theVal = v;
    }


    public K key() {
        return theKey;
    }


    public V value() {
        return theVal;
    }


    public int compareTo(K it) {
        return theKey.compareTo(it);
    }


    public int compareTo(KVPair<K, V> it) {
        return theKey.compareTo(it.key());
    }


    public String toString() {
        return theKey.toString() + ", " + theVal.toString();
    }
}
