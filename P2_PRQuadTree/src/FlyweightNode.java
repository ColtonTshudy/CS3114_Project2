/**
 * Flyweight node representing no data for a given quadrant
 * 
 * @author Colton Tshudy
 * @version 9/21/2022
 */
public class FlyweightNode implements BaseNode {

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
