
/**
 * Leaf node for the PRQuadTree structure
 * 
 * @author Colton Tshudy
 * @author Benjamin Gallini
 * @version 9/21/2022
 */
import java.lang.reflect.Array;

public class LeafNode implements BaseNode {
    private int arrayLength = 0;
    private int uniqueItems = 0;
    
    /**
     * Data array for the leaf node
     */
    public KVPair<String, Point>[] dataArray;

    @Override
    public boolean insert(KVPair<String, Point> newPoint) {
        if (isDupe(newPoint.value())) {
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
                if (!isDupe(removed.value()))
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
                if (!isDupe(removed.value()))
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
            .newInstance(SkipNode.class, arrayLength);
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
            .newInstance(SkipNode.class, arrayLength);
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
     * Checks if the point is a duplicate point
     * 
     * @param newPoint
     *            The point being checked
     * @return
     *         true if a duplicate
     */
    public boolean isDupe(Point point) {
        for (int i = 0; i < arrayLength; i++) {
            if (point.equals(dataArray[i].value()))
                return true;
        }
        return false;
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

}
