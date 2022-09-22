/**
 * Internal node for the PRQuadTree structure
 * 
 * @author Colton Tshudy
 * @author Benjamin Gallini
 * @version 9/21/2022
 */
public class InternalNode implements BaseNode {
    /**
     * Leaf nodes of the internal node.
     * Node 0 = NE, 1 = NW, 2 = SW, 3 = SE
     */
    public BaseNode[] children = new LeafNode[4];

    private Point center;

    /**
     * Internal Node constructor
     */
    public InternalNode(Point center) {
        this.center = center;
        children[0] = new FlyweightNode();
        children[1] = new FlyweightNode();
        children[2] = new FlyweightNode();
        children[3] = new FlyweightNode();
    }


    /**
     * Finds center of the internal node
     * 
     * @return center point
     */
    public Point center() {
        return center;
    }


    @Override
    public boolean insert(KVPair<String, Point> newPoint) {
        return false;
    }


    @Override
    public KVPair<String, Point> remove(String key) {
        return null;
    }


    @Override
    public KVPair<String, Point> remove(Point point) {
        return null;
    }


    @Override
    public KVPair<String, Point>[] search(String key) {
        return null;
    }


    @Override
    public KVPair<String, Point>[] search(Point point) {
        return null;
    }


    @Override
    public Boolean isLeaf() {
        return false;
    }


    @Override
    public Boolean isFlyweight() {
        return false;
    }

}
