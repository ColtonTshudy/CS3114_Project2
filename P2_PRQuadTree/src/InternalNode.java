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
    private int length;
    private Point corner;

    /**
     * Internal Node constructor
     * 
     * @param center
     *            The center point of the internal node
     * @param length
     *            The length of the internal node area's size
     */
    public InternalNode(Point corner, int length) {
        this.corner = corner;
        this.length = length;
        children[0] = new FlyweightNode();
        children[1] = new FlyweightNode();
        children[2] = new FlyweightNode();
        children[3] = new FlyweightNode();
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


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Node at " + corner.toString() + ", " + length
            + ": Internal");
        return str.toString();
    }


    @Override
    public Point getCorner() {
        return corner;
    }


    @Override
    public int getLength() {
        return length;
    }

}
