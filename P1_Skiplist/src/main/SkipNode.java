package main;

class SkipNode<K extends Comparable<K>, E> {
    private KVPair<K, E> rec;
    private SkipNode<K, E>[] forward;

    public E element() {
        return rec.value();
    }


    public K key() {
        return rec.key();
    }


    @SuppressWarnings("unchecked")
    public SkipNode(K key, E elem, int level) {
        rec = new KVPair<K, E>(key, elem);
        forward = new SkipNode[level + 1];
        for (int i = 0; i < level; i++)
            forward[i] = null;
    }


    public String toString() {
        return rec.toString();
    }


    /**
     * Getter method for connected nodes
     * 
     * @param i
     *            level index of connected nodes
     * @return connected nodes
     */
    public SkipNode<K, E> getForward(int i) {
        return forward[i];
    }

}
