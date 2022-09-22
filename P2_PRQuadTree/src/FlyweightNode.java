/**
 * Flyweight node representing no data for a given quadrant
 * 
 * @author Colton Tshudy
 * @author Benjamin Gallini
 * @version 9/21/2022
 */
public class FlyweightNode implements BaseNode {

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
