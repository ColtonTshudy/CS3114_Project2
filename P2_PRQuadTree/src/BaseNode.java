/**
 * General interface for nodes in the PRQuadTree
 * 
 * @author Colton Tshudy
 * @author Benjamin Gallini
 * @version 9/21/2022
 */
public interface BaseNode {

    /**
     * Finds the corner of the node
     * 
     * @return the corner point
     */
    public Point getCorner();


    /**
     * Finds the length of the node's rectangle
     * 
     * @return the length integer
     */
    public int getLength();


    /**
     * Inserts a KVPair into the internal array
     * 
     * @param newPoint
     *            The point being added to the node
     * 
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert(KVPair<String, Point> newPoint);


    /**
     * Removes a KVPair from the internal array by its key
     * 
     * @param key
     *            String key of the pair
     * 
     * @return the removed KVPair
     */
    public KVPair<String, Point> remove(String key);


    /**
     * Removes a KVPair from the internal array by its element
     * 
     * @param point
     *            Point element of the pair
     * 
     * @return the removed KVPair
     */
    public KVPair<String, Point> remove(Point point);


    /**
     * Searches for all KVPairs with matching key
     * 
     * @param key
     *            String key of the pair
     * 
     * @return an array of the matching KVPairs
     */
    public KVPair<String, Point>[] search(String key);


    /**
     * Searches for all KVPairs with matching element
     * 
     * @param point
     *            Point element of the pair
     * 
     * @return an array of the matching KVPairs
     */
    public KVPair<String, Point>[] search(Point point);


    /**
     * Checks if the node is a leaf
     * 
     * @return
     *         True if a leaf
     */
    public Boolean isLeaf();


    /**
     * Checks if the node is a flyweight
     * 
     * @return
     *         True if a flyweight
     */
    public Boolean isFlyweight();


    /**
     * Converts the node to a string format
     * 
     * @return the node as a string
     */
    public String toString();
}
