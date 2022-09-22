import java.lang.reflect.Array;

/**
 * SkipNode for use by the SkipList class
 *
 * @author CS Staff
 */
public class SkipNode<K extends Comparable<K>, V> {

    private KVPair<K, V> pair;
    private SkipNode<K, V>[] skips;

    @SuppressWarnings("unchecked")
    public SkipNode(int level, KVPair<K, V> pair) {
        this.pair = pair;
        skips = (SkipNode[])Array.newInstance(SkipNode.class, level + 1);
        for (int i = 0; i < skips.length; i++) {
            skips[i] = null;
        }
    }


    public K getKey() {
        return pair.key();
    }


    public V getValue() {
        return pair.value();
    }


    public KVPair<K, V> getPair() {
        return pair;
    }


    public void setSkip(int level, SkipNode<K, V> nextNode) {
        skips[level] = nextNode;
    }


    public SkipNode<K, V> getSkip(int level) {
        return skips[level];
    }


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
        return "Node has depth " + skips.length + ", Value (" + pair.toString()
            + ")";
    }

}
