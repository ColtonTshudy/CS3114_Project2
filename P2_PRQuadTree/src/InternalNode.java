/**
 * Internal node for the PRQuadTree structure
 * 
 * @author Colton Tshudy
 * @version 9/21/2022
 */
public class InternalNode implements BaseNode {
    /**
     * Leaf nodes of the internal node.
     * Node 0 = NE, 1 = NW, 2 = SW, 3 = SE
     */
    public LeafNode[] children = new LeafNode[4];

    @Override
    public boolean insert() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public KVPair<String, Point> remove(String key) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public KVPair<String, Point> remove(Point point) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public KVPair<String, Point>[] search(String key) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public KVPair<String, Point>[] search(Point point) {
        // TODO Auto-generated method stub
        return null;
    }

}
