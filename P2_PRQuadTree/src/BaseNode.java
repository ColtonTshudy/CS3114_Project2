/**
 * General interface for nodes in the PRQuadTree
 * 
 * @author Colton Tshudy
 * @version 9/21/2022
 *
 */
public interface BaseNode {
    /**
     * Inserts a KVPair into the internal array
     * 
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert();


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

}
