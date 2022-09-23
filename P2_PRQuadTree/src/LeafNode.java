// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

/**
 * Leaf node for the PRQuadTree structure
 * 
 * @author Colton Tshudy
 * @author Benjamin Gallini
 * @version 9/21/2022
 */
import java.lang.reflect.Array;

/**
 * LeafNode class
 * 
 * @author Benjamin Gallini
 * @author Colton Tshudy
 * @version 9/23/22
 */
public class LeafNode implements BaseNode {
    private int arrayLength = 0;
    private int uniqueItems = 0;
    private Point corner;
    private int length;

    /**
     * LeafNode constructor
     * 
     * @param corner
     *            The corner of the leaf node area
     * @param length
     *            The length of the sides of the leaf node
     */
    public LeafNode(Point corner, int length) {
        this.corner = corner;
        this.length = length;
    }

    /**
     * Data array for the leaf node
     */
    @SuppressWarnings("unchecked")
    private KVPair<String, Point>[] dataArray = (KVPair<String, Point>[])Array
        .newInstance(KVPair.class, 100);

    /**
     * Getter for dataArray
     * 
     * @return array of data points
     */
    public KVPair<String, Point>[] dataArray() {
        return dataArray;
    }


    @Override
    public boolean insert(KVPair<String, Point> newPoint) {
        if (isDupe(newPoint)) {
            dataArray[arrayLength] = newPoint;
            arrayLength++;
            return true;
        }
        if (uniqueItems < 3) {
            dataArray[arrayLength] = newPoint;
            arrayLength++;
            uniqueItems++;
            return true;
        }
        return false;
    }


    @Override
    public KVPair<String, Point> remove(String key) {
        KVPair<String, Point> removed;
        for (int i = 0; i < arrayLength; i++) {
            if (dataArray[i].key().equals(key)) {
                removed = dataArray[i];
                for (int j = i; j < arrayLength; j++) {
                    dataArray[j] = dataArray[j + 1];
                }
                arrayLength--;
                if (!isDupe(removed))
                    uniqueItems--;
                return removed;
            }
        }
        return null;
    }


    @Override
    public KVPair<String, Point> remove(Point point) {
        KVPair<String, Point> removed;
        for (int i = 0; i < arrayLength; i++) {
            if (dataArray[i].value().equals(point)) {
                removed = dataArray[i];
                for (int j = i; j < arrayLength; j++) {
                    dataArray[j] = dataArray[j + 1];
                }
                arrayLength--;
                if (!isDupe(removed))
                    uniqueItems--;
                return removed;
            }
        }
        return null;
    }


    @Override
    public KVPair<String, Point>[] search(String key) {
        @SuppressWarnings("unchecked")
        KVPair<String, Point>[] found = (KVPair<String, Point>[])Array
            .newInstance(KVPair.class, arrayLength);
        int amountFound = 0;
        for (int i = 0; i < arrayLength; i++) {
            if (dataArray[i].key().equals(key)) {
                found[amountFound] = dataArray[i];
                amountFound++;
            }
        }
        return found;
    }


    @Override
    public KVPair<String, Point>[] search(Point point) {
        @SuppressWarnings("unchecked")
        KVPair<String, Point>[] found = (KVPair<String, Point>[])Array
            .newInstance(KVPair.class, arrayLength);
        int amountFound = 0;
        for (int i = 0; i < arrayLength; i++) {
            if (dataArray[i].value().equals(point)) {
                found[amountFound] = dataArray[i];
                amountFound++;
            }
        }
        return found;
    }


    /**
     * Finds duplicates in the node
     * 
     * @return
     *         An array of KVPair for duplicates
     */
    public Point[] findDupe() {
        int dupeFound = 0;
        Point[] found = (Point[])Array.newInstance(Point.class, arrayLength);
        for (int i = 0; i < arrayLength; i++) {
            if (isDupe(dataArray[i]) && notInArray(found, dataArray[i].value(),
                dupeFound)) {
                found[dupeFound] = dataArray[i].value();
                dupeFound++;
            }
        }
        return found;
    }


    /**
     * Returns the size of the node's data array
     * 
     * @return
     *         size of array
     */
    public int getSize() {
        return arrayLength;
    }


    @Override
    public Boolean isLeaf() {
        return true;
    }


    @Override
    public Boolean isFlyweight() {
        return false;
    }


    @Override
    public String toString(int indent) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            str.append("  ");
        }
        str.append("Node at " + corner.toString() + ", " + length + ":");
        for (int i = 0; i < arrayLength; i++) {
            str.append("\n");
            for (int j = 0; j < indent; j++) {
                str.append("  ");
            }
            str.append("(" + dataArray[i].toString() + ")");

        }
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


    /**
     * Checks if the point is a duplicate point
     * 
     * @param newPoint
     *            The point being checked
     * @return
     *         true if a duplicate
     */
    private boolean isDupe(KVPair<String, Point> pair) {
        boolean dupeFound = false;
        int i = 0;
        while (i < arrayLength && !dupeFound) {
            dupeFound = pair.value().equals(dataArray[i].value()) && !pair.key()
                .equals(dataArray[i].key());
            i++;
        }
        return dupeFound;
    }


    /**
     * Checks if a point is in the point array
     * 
     * @param array
     *            The array of points
     * @param point
     *            The point being checked
     * @param len
     *            The length of the array
     * @return True if not in array
     */
    private boolean notInArray(Point[] array, Point point, int len) {
        for (int i = 0; i < len; i++) {
            if (array[i].equals(point))
                return false;
        }
        return true;
    }


    @Override
    public KVPair<String, Point> remove(KVPair<String, Point> pair) {
        KVPair<String, Point> removed;
        for (int i = 0; i < arrayLength; i++) {
            if (dataArray[i].equals(pair)) {
                removed = dataArray[i];
                for (int j = i; j < arrayLength; j++) {
                    dataArray[j] = dataArray[j + 1];
                }
                arrayLength--;
                if (!isDupe(removed))
                    uniqueItems--;
                return removed;
            }
        }
        return null;
    }
}
