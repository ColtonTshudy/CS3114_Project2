/**
 * PR Quad Tree functoin
 * 
 * @author Colton Tshudy
 * @author Benjamin Gallini
 * @version 9/21/2022
 */
public class PRQuadTree {
    private BaseNode head;

    /**
     * PR Quad Tree constructor
     */
    public PRQuadTree() {
        head = new LeafNode();
    }


    /**
     * Inserts a new point into the tree
     * 
     * @param pair
     *            Name and point being inserted into the tree
     */
    public void insert(KVPair<String, Point> pair) {
        BaseNode node = head;
        insertRecursive(pair, node, null, 511);
    }


    /**
     * Removes a point from the tree
     * 
     * @param point
     *            The point being removed
     * @return
     *         The KVPair for the point removed
     */
    public KVPair<String, Point> remove(Point point) {
        BaseNode node = head;
        return removeRecursive(point, node);
    }


    /**
     * Finds and returns an array with this point
     * 
     * @param point
     *            The point being found
     * @return
     *         A array of KVPairs with the point
     */
    public KVPair<String, Point>[] find(Point point) {
        BaseNode node = head;
        return findRecursive(point, node);
    }


    /**
     * Returns the nodes in a region
     * 
     * @param x
     *            X coordinate of region
     * @param y
     *            Y coordinate of region
     * @param w
     *            Width of region
     * @param h
     *            Height of region
     * @return
     *         String of points in the region
     */
    public String regionSearch(int x, int y, int w, int h) {
        StringBuilder str = new StringBuilder();
        // :)
        return str.toString();
    }


    /**
     * Finds duplicates in the tree
     * 
     * @return
     *         Returns a formatted string of duplicate points
     */
    public String duplicates() {
        return null;
    }


    /**
     * Returns a string of the tree
     * 
     * @return
     *         String representation of the tree
     */
    public String toString() {
        return null;
    }


    /**
     * Recursive function for insert
     * 
     * @param pair
     *            The pair being inserted
     * @param node
     *            The current node
     * @param parent
     *            The parent node of the current node
     * @param shift
     *            The amount to shift a new center point by
     * @return
     *         True if successful
     */
    private boolean insertRecursive(
        KVPair<String, Point> pair,
        BaseNode node,
        InternalNode parent,
        int shift) {
        int direction = pair.value().relativeDirection(parent.center());
        if (node.isLeaf()) {
            if (!node.insert(pair)) {
                Point newCenter = findNewCenter(direction, shift, parent
                    .center());
                InternalNode newNode = new InternalNode(newCenter);
                parent.children[direction] = newNode;
                LeafNode oldNode = (LeafNode)node;
                for (int i = 0; i < oldNode.getSize(); i++) {
                    insertRecursive(oldNode.dataArray[i], newNode, parent,
                        shift);
                }
            }
            return true;
        }
        if (node.isFlyweight()) {
            LeafNode newNode = new LeafNode();
            parent.children[direction] = newNode;
            newNode.insert(pair);
        }
        InternalNode newNode = (InternalNode)node;
        direction = pair.value().relativeDirection(newNode.center());
        insertRecursive(pair, newNode.children[direction], (InternalNode)node,
            shift / 2);
        return false;
    }


    /**
     * Recursive function for remove
     * 
     * @param point
     *            The point being removed
     * @param node
     *            The current node to find the point
     * @return
     *         The KVPair for the point
     */
    private KVPair<String, Point> removeRecursive(Point point, BaseNode node) {
        if (node.isLeaf()) {
            return node.remove(point);
        }

        if (node.isFlyweight())
            return null;

        InternalNode newNode = (InternalNode)node;
        int direction = point.relativeDirection(newNode.center());
        removeRecursive(point, newNode.children[direction]);
        return null;
    }


    /**
     * Recursive function for find
     * 
     * @param point
     *            The point being found
     * @param node
     *            The node being checked for the point
     * @return
     *         An array of KVPoints containing the points
     */
    private KVPair<String, Point>[] findRecursive(Point point, BaseNode node) {
        if (node.isLeaf())
            return node.search(point);

        if (node.isFlyweight())
            return null;

        InternalNode newNode = (InternalNode)node;
        int direction = point.relativeDirection(newNode.center());
        removeRecursive(point, newNode.children[direction]);
        return null;
    }


    /**
     * Finds a new center for a new internal node
     * 
     * @param direction
     *            Direction represented as an int
     * @param shift
     *            Amount to shift the center point by
     * @param oldCenter
     *            THe old center point
     * @return
     *         The new center point
     */
    private Point findNewCenter(int direction, int shift, Point oldCenter) {
        int newX = 0;
        int newY = 0;
        if (direction == 0) {
            newX = oldCenter.getX() + shift;
            newY = oldCenter.getY() - shift;
        }
        if (direction == 1) {
            newX = oldCenter.getX() - shift;
            newY = oldCenter.getY() - shift;
        }
        if (direction == 2) {
            newX = oldCenter.getX() - shift;
            newY = oldCenter.getY() + shift;
        }
        if (direction == 3) {
            newX = oldCenter.getX() + shift;
            newY = oldCenter.getY() + shift;
        }
        return new Point(newX, newY);
    }


    /**
     * Checks if a point is in a rectangle
     * 
     * @param point
     *            The point being checked
     * @param x
     *            X coordinate of region
     * @param y
     *            Y coordinate of region
     * @param w
     *            Width of region
     * @param h
     *            Height of region
     * @return
     *         True if in the rectangle
     */
    private boolean inRect(Point point, int x, int y, int w, int h) {
        return point.getX() >= x && point.getX() <= x + w && point.getY() >= y
            && point.getY() <= y + h;
    }
}
