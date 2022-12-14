// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

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
     * Getter method for size
     * 
     * @return size
     */
    public int getSize() {
        return size;
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
        KVPair<String, Point> result = removeRecursive(point, node, null);
        if (result != null) {
            size--;
        }
        return result;
    }


    /**
     * Removes a point from the tree
     * 
     * @param pair
     *            The pair being removed
     * @return
     *         The KVPair for the point removed
     */
    public KVPair<String, Point> remove(KVPair<String, Point> pair) {
        BaseNode node = head;
        KVPair<String, Point> result = removeRecursive(pair, node, null);
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
        str = new StringBuilder(result[0]);
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
        StringBuilder str = new StringBuilder();
        str.append("Duplicate points:");
        str = (dupeRecursive(str, head));
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
            dump = dumpRecursive(str, nodesPrinted, intNode.children()[1],
                indent);
            str = new StringBuilder(dump[0]);
            nodesPrinted = Integer.valueOf(dump[1]);

            dump = dumpRecursive(str, nodesPrinted, intNode.children()[0],
                indent);
            str = new StringBuilder(dump[0]);
            nodesPrinted = Integer.valueOf(dump[1]);

            dump = dumpRecursive(str, nodesPrinted, intNode.children()[2],
                indent);
            str = new StringBuilder(dump[0]);
            nodesPrinted = Integer.valueOf(dump[1]);

            dump = dumpRecursive(str, nodesPrinted, intNode.children()[3],
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
                    int nodeDirect = oldNode.dataArray()[0].value()
                        .findQuadrant(parent.getCorner(), parent.getLength());
                    parent.children()[nodeDirect] = newNode;
                }

                for (int i = 0; i < oldNode.getSize(); i++) {
                    insertRecursive(oldNode.dataArray()[i], newNode, parent);
                }
                direction = pair.value().findQuadrant(newNode.getCorner(),
                    newNode.getLength());
                insertRecursive(pair, newNode.children()[direction], newNode);
            }
            return true;
        }
        if (node.isFlyweight()) {
            LeafNode newNode = new LeafNode(node.getCorner(), node.getLength());
            if (parent != null) {
                int nodeDirect = pair.value().findQuadrant(parent.getCorner(),
                    parent.getLength());
                parent.children()[nodeDirect] = newNode;
            }
            else
                head = newNode;

            newNode.insert(pair);
            return true;
        }
        InternalNode newNode = (InternalNode)node;
        insertRecursive(pair, newNode.children()[direction], newNode);
        return false;
    }


    /**
     * Recursive function for remove
     * 
     * @param point
     *            The point being removed
     * @param node
     *            The current node to find the point
     * @param parent
     *            The parent of the node
     * @return
     *         The KVPair for the point
     */
    private KVPair<String, Point> removeRecursive(
        Point point,
        BaseNode node,
        InternalNode parent) {
        if (node.isLeaf()) {
            KVPair<String, Point> result = node.remove(point);
            toFlyweight((LeafNode)node, parent);
            return result;
        }

        if (node.isFlyweight())
            return null;

        InternalNode newNode = (InternalNode)node;
        int direction = point.findQuadrant(node.getCorner(), node.getLength());
        KVPair<String, Point> result = removeRecursive(point, newNode
            .children()[direction], (InternalNode)node);

        removeCollapse((InternalNode)node, parent);
        return result;
    }


    /**
     * Recursive function for remove
     * 
     * @param point
     *            The point being removed
     * @param node
     *            The current node to find the point
     * @param parent
     *            The parent of the node
     * @return
     *         The KVPair for the point
     */
    private KVPair<String, Point> removeRecursive(
        KVPair<String, Point> pair,
        BaseNode node,
        InternalNode parent) {
        if (node.isLeaf()) {
            KVPair<String, Point> result = node.remove(pair);
            toFlyweight((LeafNode)node, parent);
            return result;
        }

        if (node.isFlyweight())
            return null;

        InternalNode newNode = (InternalNode)node;
        int direction = pair.value().findQuadrant(node.getCorner(), node
            .getLength());

        KVPair<String, Point> result = removeRecursive(pair, newNode
            .children()[direction], (InternalNode)node);

        removeCollapse((InternalNode)node, parent);
        return result;
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
        return findRecursive(point, newNode.children()[direction]);
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
            for (int i = checkNode.getSize() - 1; i >= 0; i--) {
                if (inRect(checkNode.dataArray()[i].value(), x, y, w, h)) {
                    str.append("Point found: (" + checkNode.dataArray()[i]
                        .toString() + ")\n");
                }
            }
            nodesVisited++;
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

        if (intersect(internal.children()[1].getCorner(), internal.children()[1]
            .getLength(), x, y, w, h)) {

            String[] child1 = regionRecursive(str, internal.children()[1], x, y,
                w, h, 0);

            str = new StringBuilder(child1[0]);
            nodesVisited = nodesVisited + Integer.valueOf(child1[1]);
        }

        if (intersect(internal.children()[0].getCorner(), internal.children()[0]
            .getLength(), x, y, w, h)) {

            String[] child0 = regionRecursive(str, internal.children()[0], x, y,
                w, h, 0);

            str = new StringBuilder(child0[0]);
            nodesVisited = nodesVisited + Integer.valueOf(child0[1]);
        }

        if (intersect(internal.children()[2].getCorner(), internal.children()[2]
            .getLength(), x, y, w, h)) {

            String[] child2 = regionRecursive(str, internal.children()[2], x, y,
                w, h, 0);

            str = new StringBuilder(child2[0]);
            nodesVisited = nodesVisited + Integer.valueOf(child2[1]);
        }

        if (intersect(internal.children()[3].getCorner(), internal.children()[3]
            .getLength(), x, y, w, h)) {

            String[] child3 = regionRecursive(str, internal.children()[3], x, y,
                w, h, 0);

            str = new StringBuilder(child3[0]);
            nodesVisited = nodesVisited + Integer.valueOf(child3[1]);
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
    public boolean inRect(Point point, int x, int y, int w, int h) {
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
    private StringBuilder dupeRecursive(StringBuilder str, BaseNode node) {

        if (node.isLeaf()) {
            LeafNode curr = (LeafNode)node;
            Point data = curr.findDupe();
            if (data != null) {
                str.append("\n");
                str.append("(" + data.toString() + ")");
            }

            return str;
        }

        if (node.isFlyweight())

        {
            return str;
        }
        InternalNode curr = (InternalNode)node;
        str = dupeRecursive(str, curr.children()[1]);
        str = dupeRecursive(str, curr.children()[0]);
        str = dupeRecursive(str, curr.children()[2]);
        str = dupeRecursive(str, curr.children()[3]);
        return str;
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
    public boolean intersect(
        Point point1,
        int length,
        int x,
        int y,
        int w,
        int h) {

        return (point1.getX() < x + w && x < point1.getX() + length && point1
            .getY() < y + h && y < point1.getY() + length);

    }


    /**
     * Checks for and collapses the tree after a remove
     * 
     * @param node
     *            The leaf node child
     * @param parent
     *            The internal node parent
     */
    private void removeCollapse(InternalNode node, InternalNode parent) {

        boolean collapse = true;
        LeafNode newLeaf = new LeafNode(node.getCorner(), node.getLength());
        for (int i = 0; i < 4; i++) {
            if (node.children()[i].isLeaf()) {
                LeafNode childLeaf = (LeafNode)node.children()[i];
                for (int j = 0; j < childLeaf.getSize(); j++) {
                    collapse = newLeaf.insert(childLeaf.dataArray()[j]);
                }
            }
            else if (!node.children()[i].isFlyweight()) { // Is an internal node
                i = 4; // end loop
                collapse = false;
            }
        }
        if (collapse) {
            if (parent == null) {
                head = newLeaf;
            }
            else {
                int nodeDirect = node.getCorner().findQuadrant(parent
                    .getCorner(), parent.getLength());
                parent.children()[nodeDirect] = newLeaf;
            }
        }
    }


    /**
     * Checks if a leafNode is empty, if so turns into a flyweight
     * 
     * @param child
     *            The child leaf node
     * @param parent
     *            The parent of the leaf node
     */
    private void toFlyweight(LeafNode child, InternalNode parent) {
        if (child.getSize() == 0) { // Makes an empty child node a flyweight
            // node
            FlyweightNode newNode = new FlyweightNode(child.getCorner(), child
                .getLength());
            if (parent == null)
                head = newNode;
            else {
                int nodeDirect = child.getCorner().findQuadrant(parent
                    .getCorner(), parent.getLength());
                parent.children()[nodeDirect] = newNode;
            }
        }
    }
}
