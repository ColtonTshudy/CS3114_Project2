/**
 * Leaf node for the PRQuadTree structure
 * 
 * @author Colton Tshudy
 * @version 9/21/2022
 */
public class LeafNode implements BaseNode {
    private KVPair<String, Point>[] dataArray;
    
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
