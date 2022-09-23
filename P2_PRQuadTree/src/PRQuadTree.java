import java.lang.reflect.Array;

/**
 * PR Quad Tree function
 * 
 * @author Colton Tshudy
 * @author Benjamin Gallini
 * @version 9/21/2022
 */
public class PRQuadTree {
    private BaseNode head;
    private int size;

    /**
     * PR Quad Tree constructor
     */
    public PRQuadTree() {
        Point headPoint = new Point(0, 0);
        head = new FlyweightNode(headPoint, 1024);
        size = 0;
    }


    /**
     * Inserts a new point into the tree
     * 
     * @param pair
     *            Name and point being inserted into the tree
     */
    public void insert(KVPair<String, Point> pair) {
        BaseNode node = head;
        insertRecursive(pair, node, null);
        size++;
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
        KVPair<String, Point> result = removeRecursive(point, node);
        if (result != null) {
            size--;
        }
        return result;
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
        String[] result = regionRecursive(str, head, x, y, w, h, 0);
        str.append(result[0]);
        str.append(result[1] + " quadtree nodes visited\n");
        return str.toString();
    }


    /**
     * Finds duplicates in the tree
     * 
     * @return
     *         Returns a formatted string of duplicate points
     */
    public String duplicates() {
        Point[] result = (Point[])Array.newInstance(Point.class, size);

        result = dupeRecursive(result, head);
        StringBuilder str = new StringBuilder();
        str.append("Duplicate Points:\n");
        for (int i = 0; i < result.length; i++) {
            str.append("(" + result[i].toString() + ")\n");
        }
        return str.toString();
    }


    /**
     * Returns a string of the tree
     * 
     * @return
     *         String representation of the tree
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        String[] dump = dumpRecursive(new StringBuilder(), 0, head, 0);
        str.append(dump[0]);
        str.append(dump[1] + " quadtree nodes printed");
        String result = str.toString();
        return result;
    }


    /**
     * Recursive method for dump
     * 
     * @param str
     *            The current string
     * @param nodesPrinted
     *            The amount of nodes printed
     * @param node
     *            The current node
     * @param indent
     *            The amount of indent needed
     * @return The array of strings for dump
     */
    private String[] dumpRecursive(
        StringBuilder str,
        int nodesPrinted,
        BaseNode node,
        int indent) {

        String[] dump = new String[2];
        str.append(node.toString(indent) + "\n");
        nodesPrinted++;
        dump[0] = str.toString();
        dump[1] = Integer.toString(nodesPrinted);

        if (!node.isLeaf() && !node.isFlyweight()) {

            InternalNode intNode = (InternalNode)node;
            indent++;
            dump = dumpRecursive(str, nodesPrinted, intNode.children[0],
                indent);
            str = new StringBuilder(dump[0]);
            nodesPrinted = Integer.valueOf(dump[1]);

            dump = dumpRecursive(str, nodesPrinted, intNode.children[1],
                indent);
            str = new StringBuilder(dump[0]);
            nodesPrinted = Integer.valueOf(dump[1]);

            dump = dumpRecursive(str, nodesPrinted, intNode.children[2],
                indent);
            str = new StringBuilder(dump[0]);
            nodesPrinted = Integer.valueOf(dump[1]);

            dump = dumpRecursive(str, nodesPrinted, intNode.children[3],
                indent);
            str = new StringBuilder(dump[0]);
            nodesPrinted = Integer.valueOf(dump[1]);
        }
        return dump;
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
        InternalNode parent) {

        int direction = pair.value().findQuadrant(node.getCorner(), node
            .getLength());

        if (node.isLeaf()) {
            if (!node.insert(pair)) {
                InternalNode newNode = new InternalNode(node.getCorner(), node
                    .getLength());
                LeafNode oldNode = (LeafNode)node;

                if (parent == null)
                    head = newNode;
                else {
                    int nodeDirect = oldNode.dataArray[0].value().findQuadrant(
                        parent.getCorner(), parent.getLength());
                    parent.children[nodeDirect] = newNode;
                }

                for (int i = 0; i < oldNode.getSize(); i++) {
                    insertRecursive(oldNode.dataArray[i], newNode, parent);
                }
                direction = pair.value().findQuadrant(newNode.getCorner(),
                    newNode.getLength());
                insertRecursive(pair, newNode.children[direction], newNode);
            }
            return true;
        }
        if (node.isFlyweight()) {
            LeafNode newNode = new LeafNode(node.getCorner(), node.getLength());
            if (parent != null) {
                int nodeDirect = pair.value().findQuadrant(
                    parent.getCorner(), parent.getLength());
                parent.children[nodeDirect] = newNode;
            }
            else
                head = newNode;

            newNode.insert(pair);
            return true;
        }
        InternalNode newNode = (InternalNode)node;
        insertRecursive(pair, newNode.children[direction], newNode);
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
        int direction = point.findQuadrant(node.getCorner(), node.getLength());
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
        int direction = point.findQuadrant(node.getCorner(), node.getLength());
        removeRecursive(point, newNode.children[direction]);
        return null;
    }


    /**
     * Recursive method for region search
     * 
     * @param str
     *            String being built for the search
     *            The point being checked
     * @param x
     *            X coordinate of region
     * @param y
     *            Y coordinate of region
     * @param w
     *            Width of region
     * @param h
     *            Height of region
     * @param nodesVisited
     *            Number of nodes visited
     * @return
     *         an array of strings built for the region search and nodes visited
     */
    private String[] regionRecursive(
        StringBuilder str,
        BaseNode node,
        int x,
        int y,
        int w,
        int h,
        int nodesVisited) {
        String[] result = new String[2];
        if (node.isLeaf()) {
            LeafNode checkNode = (LeafNode)node;
            for (int i = 0; i < checkNode.getSize(); i++) {
                if (inRect(checkNode.dataArray[i].value(), x, y, w, h)) {
                    str.append("Point found: (" + checkNode.dataArray[i]
                        .toString() + ")\n");
                    nodesVisited++;
                }
            }
            result[0] = str.toString();
            result[1] = Integer.toString(nodesVisited);
            return result;
        }
        if (node.isFlyweight()) {
            nodesVisited++;
            result[0] = str.toString();
            result[1] = Integer.toString(nodesVisited);
            return result;
        }
        InternalNode internal = (InternalNode)node;
        nodesVisited++;
        if (intersect(internal.children[0].getCorner(), internal.children[0]
            .getLength(), x, y, w, h)) {
            String[] child = regionRecursive(str, internal.children[0], x, y, w,
                h, nodesVisited);
            str.append(child[0]);
            nodesVisited = Integer.valueOf(child[1]);
        }
        if (intersect(internal.children[1].getCorner(), internal.children[0]
            .getLength(), x, y, w, h)) {
            String[] child = regionRecursive(str, internal.children[1], x, y, w,
                h, nodesVisited);
            str.append(child[0]);
            nodesVisited = Integer.valueOf(child[1]);
        }
        if (intersect(internal.children[2].getCorner(), internal.children[0]
            .getLength(), x, y, w, h)) {
            String[] child = regionRecursive(str, internal.children[2], x, y, w,
                h, nodesVisited);
            str.append(child[0]);
            nodesVisited = Integer.valueOf(child[1]);
        }
        if (intersect(internal.children[3].getCorner(), internal.children[0]
            .getLength(), x, y, w, h)) {
            String[] child = regionRecursive(str, internal.children[3], x, y, w,
                h, nodesVisited);
            str.append(child[0]);
            nodesVisited = Integer.valueOf(child[1]);
        }
        result[0] = str.toString();
        result[1] = Integer.toString(nodesVisited);
        return result;
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


    /**
     * Finds all duplicates in the tree
     * 
     * @param array
     *            THe array of duplicates
     * @param node
     *            The current node being checked
     * @return
     *         An array of duplicates
     */
    private Point[] dupeRecursive(Point[] array, BaseNode node) {

        if (node.isLeaf()) {
            LeafNode curr = (LeafNode)node;
            Point[] data = curr.findDupe();
            for (int i = 0; i < data.length; i++) {
                array[array.length] = data[i];
            }
            return array;
        }

        if (node.isFlyweight()) {
            return array;
        }
        InternalNode curr = (InternalNode)node;
        array = dupeRecursive(array, curr.children[0]);
        array = dupeRecursive(array, curr.children[1]);
        array = dupeRecursive(array, curr.children[2]);
        array = dupeRecursive(array, curr.children[3]);
        return array;
    }


    /**
     * Checks if two rectangles are intersecting
     * 
     * @param point1
     *            The corner of the first rectangle
     * @param length
     *            The length of the first rectangle
     * @param x
     *            X coordinate of region
     * @param y
     *            Y coordinate of region
     * @param w
     *            Width of region
     * @param h
     *            Height of region
     * @return
     *         True if intersecting
     */
    private boolean intersect(
        Point point1,
        int length,
        int x,
        int y,
        int w,
        int h) {
        return (point1.x < x + w && x < point1.x + length && point1.y < y + h
            && y < point1.y + length);
    }
}
