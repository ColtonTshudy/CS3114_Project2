/**
 * Flyweight node representing no data for a given quadrant
 * 
 * @author Colton Tshudy
 * @author Benjamin Gallini
 * @version 9/21/2022
 */
public class FlyweightNode implements BaseNode {
    private Point corner;
    private int length;

    /**
     * Flyweight node constructor
     * 
     * @param corner
     *            The corner of the rectangle
     * @param length
     *            The length of the sides of the rectangle
     */
    public FlyweightNode(Point corner, int length) {
        this.corner = corner;
        this.length = length;
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
        return true;
    }


    @Override
    public String toString(int indent) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < indent; i++) {
            str.append("  ");
        }
        str.append("Node at " + corner.toString() + ", " + length
            + ": Empty");
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


    @Override
    public KVPair<String, Point> remove(KVPair<String, Point> pair) {
        return null;
    }
}
