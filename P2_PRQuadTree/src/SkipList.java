import java.lang.reflect.Array;
import java.util.Random;
import student.TestableRandom;

/**
 * The SkipList class, used for efficient search, insert, remove
 * of KVPairs
 *
 * @author CS Staff
 */
public class SkipList<K extends Comparable<K>, E> {

    private Random rng; // Randomness generator
    private int deepestLevel; // Current deepest level
    private SkipNode<K, E> head; // Header node for the skiplist
    private int size = 0; // The number of records in entire list

    public SkipList() {
        rng = new TestableRandom(); // allows for testing
        deepestLevel = 0;
        head = new SkipNode<K, E>(0, null);
        // the head node NEVER has data, it only helps us with first skips
    }


    /**
     * Pick a level using a geometric distribution
     * 
     * @return a random level for a node
     */
    private int randomLevel() {
        int level = 0;
        while (rng.nextBoolean()) {
            level++;
        }
        return level;
    }


    /**
     * Make the header node deeper
     * 
     * @param newLevel
     */
    private void adjustHead(int newLevel) {
        int i;
        SkipNode<K, E> temp = new SkipNode<K, E>(newLevel, null);
        for (i = 0; i <= deepestLevel; i++) {
            temp.setSkip(i, head.getSkip(i));
        }
        for (i = deepestLevel + 1; i < newLevel; i++) {
            temp.setSkip(i, null);
        }
        deepestLevel = newLevel;
        head = temp;
    }


    public int size() {
        return size;
    }


    /**
     * Insert the newPair into the list
     * 
     * @param newPair
     *            new KVPair to insert
     */
    public void insert(KVPair<K, E> newPair) {
        int newLevel = randomLevel();
        if (deepestLevel < newLevel) {
            adjustHead(newLevel); // allow the head to have bigger skips
        }
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode<K, E>[] update = (SkipNode<K, E>[])Array.newInstance(
            SkipNode.class, deepestLevel + 1);
        // 'update' will have all the nodes that need to point to the new node

        Comparable<K> newKey = newPair.key();
        SkipNode<K, E> curr = head; // start at header node
        for (int i = deepestLevel; i >= 0; i--) { // starting from big skips...
            SkipNode<K, E> ahead = curr.getSkip(i);
            // find the insert position
            while (ahead != null && newKey.compareTo(ahead.getKey()) > 0) {
                curr = ahead;
                ahead = ahead.getSkip(i);
            }
            update[i] = curr; // save ref for later when updating skips
        }

        curr = new SkipNode<K, E>(newLevel, newPair); // construct the new node!
        for (int i = 0; i <= newLevel; i++) {
            curr.setSkip(i, update[i].getSkip(i)); // sets new node's skips
            update[i].setSkip(i, curr); // puts new node in the skiplist
        }
        size++; // don't forget this
    }


    /**
     * Remove some record with key value "key" from the SkipList.
     * In practice, this is the FIRST record with that key value.
     *
     * @param key
     *            Key value to find and remove
     * @return The KVPair of the removed node, or null if no key matches
     */
    public KVPair<K, E> remove(K key) {
        int i;
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode<K, E>[] update = (SkipNode<K, E>[])Array.newInstance(
            SkipNode.class, deepestLevel + 1);
        // 'update' gets all the nodes that need to point around the old node

        SkipNode<K, E> curr = head; // Start at header node
        for (i = deepestLevel; i >= 0; i--) { // starting from big skips...
            SkipNode<K, E> ahead = curr.getSkip(i);
            // find the remove position...
            while (ahead != null && key.compareTo(ahead.getKey()) > 0) {
                curr = ahead;
            }
            update[i] = curr; // save node for later, it MIGHT need an update
        }

        curr = curr.getSkip(0);
        // finally check if key k is in skipList ...
        if (curr != null && key.compareTo(curr.getKey()) == 0) {
            // start removal process, updating any skips
            for (i = 0; i < curr.getLevel(); i++) {
                update[i].setSkip(i, curr.getSkip(i));
            }
            size--; // don't forget!
            return curr.getPair(); // return the pair of the now-removed curr
        }
        return null; // Key k is not in list, so return null;
    }


    /**
     * Returns a string representation of the SkipList displaying the depth of
     * each node, each node's key and value, and the size of the SkipList
     * 
     * @return a string representing the SkipList structure
     */
    public String toString() {
        SkipNode<K, E> curr = head; // node cursor
        StringBuilder stb = new StringBuilder(); // builds output

        // for each node in the skiplist, minus head
        while (curr != null) {
            stb.append(curr.getPair().toString());

            // newline if there is another node
            stb.append(System.lineSeparator());

            curr = curr.getSkip(0); // advance cursor
        }

        stb.append("SkipList size is: ");
        stb.append(size);

        return stb.toString();
    }


    /**
     * Takes a key to find all nodes with the same key
     * 
     * @param key
     *            key to compare against
     * @return String containing all matching nodes toString
     */
    public String printAllMatching(K key) {
        SkipNode<K, E> curr = findNode(key); // node cursor
        StringBuilder stb = new StringBuilder(); // builds output

        if (curr == null) { // No nodes were found matching k
            return null;
        }

        stb.append("Nodes matching key \"");
        stb.append(key);
        stb.append("\":");

        // List out all nodes with key K
        while (!curr.getKey().equals(key)) {
            stb.append(System.lineSeparator());
            stb.append(curr.toString());
        }

        return stb.toString();
    }


    /**
     * Remove some record with key value "key" and element value "element".
     *
     * @param key
     *            Key value to find and remove
     * 
     * @param element
     *            Element value to find and remove
     * @return The KVPair of the removed node, or null if no KVPair matches
     */
    public KVPair<K, E> remove(K key, E element) {
        int i;
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode<K, E>[] update = (SkipNode<K, E>[])Array.newInstance(
            SkipNode.class, deepestLevel + 1);
        // 'update' gets all the nodes that need to point around the old node

        SkipNode<K, E> curr = head; // Start at header node
        for (i = deepestLevel; i >= 0; i--) { // starting from big skips...
            SkipNode<K, E> ahead = curr.getSkip(i);
            // find the remove position...
            while (ahead != null && key.compareTo(ahead.getKey()) > 0 && element
                .equals(ahead.getValue())) {
                curr = ahead;
            }
            update[i] = curr; // save node for later, it MIGHT need an update
        }

        curr = curr.getSkip(0);
        // finally check if key k is in skipList ...
        if (curr != null && key.compareTo(curr.getKey()) == 0) {
            // start removal process, updating any skips
            for (i = 0; i < curr.getLevel(); i++) {
                update[i].setSkip(i, curr.getSkip(i));
            }
            size--; // don't forget!
            return curr.getPair(); // return the pair of the now-removed curr
        }
        return null; // Key k is not in list, so return null;
    }


    /**
     * Remove a key, element pair from the skiplist by element
     *
     * @param element
     *            element of KV pair to remove
     * @return removed key, element pair
     */
    @SuppressWarnings("unchecked")
    public E removeByElement(E element) {
        int i;
        SkipNode<K, E> rem = findNode(element); // find a matching node
        if (rem == null)
            return null; // It's not there

        SkipNode<K, E> back = head; // one step behind cursor on that level
        SkipNode<K, E> curr = back.getSkip(0); // cursor to find rear nodes

        // nodes pointing to removal node
        SkipNode<K, E>[] rearNodes = new SkipNode[deepestLevel + 1];

        for (i = curr.getLevel(); i >= 0; i--) {
            while (curr != rem) {
                back = curr;
                curr = curr.getSkip(i);
            }
            rearNodes[i] = back; // Track rear nodes at level i
        }

        // re-link the nodes behind the removed node
        for (i = curr.getLevel(); i >= 0; i--)
            rearNodes[i].setSkip(i, curr.getSkip(i));
        size--;
        return curr.getValue(); // Return the removed node
    }


    /**
     * Returns a KVPair for the found element
     * 
     * @param key
     *            Key for the node being found
     * @return the first KVPair matching the key, null if not found
     */
    public KVPair<K, E> find(K key) {
        SkipNode<K, E> curr = findNode(key);
        if (curr == null)
            return null;
        else
            return curr.getPair();
    }


    /**
     * Finds the first node of a given key
     * 
     * @param key
     *            The key being found
     * @return the first node matching the key, null if not found
     */
    private SkipNode<K, E> findNode(K key) {
        SkipNode<K, E> curr = head; // Start at header node
        for (int i = deepestLevel; i >= 0; i--) { // starting from big skips...
            SkipNode<K, E> ahead = curr.getSkip(i);
            // find the matching node position...
            while (ahead != null && key.compareTo(ahead.getKey()) > 0) {
                curr = ahead;
            }
        }
        curr = curr.getSkip(0); // Proceed to node with matching key

        return curr;
    }


    /**
     * Finds a node based on the element
     *
     * @param element
     *            element of KV pair to remove
     * @return matching node, or null if not found
     */
    private SkipNode<K, E> findNode(E element) {
        SkipNode<K, E> cur = head.getSkip(0); // cursor pointer
        while (cur != null) {
            if (cur.getValue().equals(element))
                return cur;
            cur = cur.getSkip(0);
        }
        return null;
    }

}
