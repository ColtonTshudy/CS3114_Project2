// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

/**
 * Internal node for the PRQuadTree structure
 * 
 * @author Colton Tshudy
 * @author Benjamin Gallini
 * @version 9/21/2022
 */

import java.lang.reflect.Array;

public class InternalNode implements BaseNode {
    /**
     * Leaf nodes of the internal node.
     * Node 0 = NE, 1 = NW, 2 = SW, 3 = SE
     */
    public BaseNode[] children = (BaseNode[])Array.newInstance(BaseNode.class, 4);
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
        int newLength = length/2;
        Point child0 = new Point(corner.getX() + newLength, corner.getY());
        children[0] = new FlyweightNode(child0, newLength);
        Point child1 = new Point(corner.getX(), corner.getY());
        children[1] = new FlyweightNode(child1, newLength);
        Point child2 = new Point(corner.getX(), corner.getY()+ newLength);
        children[2] = new FlyweightNode(child2, newLength);
        Point child3 = new Point(corner.getX()+ newLength, corner.getY()+ newLength);
        children[3] = new FlyweightNode(child3, newLength);
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
    public String toString(int indent) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < indent; i++) {
            str.append("  ");
        }
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

    @Override
    public KVPair<String, Point> remove(KVPair<String, Point> pair) {
        return null;
    }

}
