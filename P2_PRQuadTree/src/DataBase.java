// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

// import java.lang.reflect.Array;

/**
 * Database class for running file commands
 * 
 * @author Colton Tshudy
 * @author Benjamin Gallini (bengallini)
 * @version 2022.9.21
 */
public class DataBase {

    private SkipList<String, Point> skipList = new SkipList<String, Point>();
    private PRQuadTree quadTree = new PRQuadTree();

    /**
     * Database constructor
     */
    public DataBase() {
    }


    /**
     * Does command for the line
     * 
     * @param line
     *            Current line in the file
     */
    public void doCommand(String line) {
        String[] commands = line.split("\\s+");
        if (commands[0].equals("insert"))
            insert(commands);
        else if (commands[0].equals("remove") && commands.length == 2)
            removeName(commands[1]);
        else if (commands[0].equals("remove"))
            removeCords(commands);
        else if (commands[0].equals("regionsearch"))
            regionsearch(commands);
        else if (commands[0].equals("search"))
            search(commands[1]);
        else if (commands[0].equals("duplicates"))
            duplicates();
        else if (commands[0].equals("dump"))
            dump();
        else {
            System.out.print("Command not recognized \n");
        }
    }


    /**
     * Insert a new node
     * 
     * @param commands
     *            Command from the line
     */
    private void insert(String[] commands) {
        StringBuilder str = new StringBuilder();
        Point point = new Point(Integer.valueOf(commands[2]), Integer.valueOf(
            commands[3]));
        KVPair<String, Point> pair = new KVPair<String, Point>(commands[1],
            point);
        if (point.validPoint() /* && !entryExists(commands[1], point) */) {
            skipList.insert(pair);
            quadTree.insert(pair);
            str.append("Point inserted: (");
        }
        else {
            str.append("Point rejected: (");
        }
        str.append(pair.toString() + ")\n");
        System.out.print(str.toString());
    }


    /**
     * Removes the specified node
     * 
     * @param name
     *            Name of the node
     */
    private void removeName(String name) {
        StringBuilder str = new StringBuilder();
        KVPair<String, Point> removed = skipList.remove(name);
        if (removed == null)
            str.append("Point not removed: " + name + "\n");

        else {
            quadTree.remove(removed); // remove by pair
            str.append("Point removed: (" + removed.toString() + ")\n");
        }
        System.out.print(str.toString());
    }


    /**
     * Removes the specified node
     * 
     * @param commands
     *            The coordinates of the node
     */
    private void removeCords(String[] commands) {
        StringBuilder str = new StringBuilder();
        Point point = new Point(Integer.valueOf(commands[1]), Integer.valueOf(
            commands[2]));

        if (!point.validPoint()) {
            str.append("Point not found: (" + point.toString() + ")\n");
        }
        else {

            KVPair<String, Point> removed = quadTree.remove(point);
            if (removed == null)
                str.append("Point not found: (" + point.toString() + ")\n");

            else {
                skipList.remove(removed); // remove by pair
                str.append("Point removed: (" + removed.toString() + ")\n");
            }
        }
        System.out.print(str.toString());
    }


    /**
     * Prints the nodes inside of the specified region
     * 
     * @param commands
     *            Rectangle coordinates for search
     */
    private void regionsearch(String[] commands) {
        StringBuilder str = new StringBuilder();
        if (Integer.valueOf(commands[3]) <= 0 || Integer.valueOf(
            commands[4]) <= 0) {
            str.append("Rectangle rejected: (" + commands[1] + ", "
                + commands[2] + ", " + commands[3] + ", " + commands[4]
                + ")\n");
        }
        else {
            str.append("Points intersecting region (" + commands[1] + ", "
                + commands[2] + ", " + commands[3] + ", " + commands[4]
                + "):\n");

            str.append(quadTree.regionSearch(Integer.valueOf(commands[1]),
                Integer.valueOf(commands[2]), Integer.valueOf(commands[3]),
                Integer.valueOf(commands[4])));
        }
        System.out.print(str.toString());
    }


    /**
     * Prints and duplicate nodes
     */
    private void duplicates() {
        StringBuilder str = new StringBuilder();
        str.append(quadTree.duplicates());
        str.append("\n");
        System.out.print(str.toString());
    }


    /**
     * Prints node information for specified name
     * 
     * @param name
     *            Name of the node
     */
    private void search(String name) {
        String results = skipList.printAllMatching(name);
        StringBuilder str = new StringBuilder();
        if (results == null) {
            str.append("Point not found: " + name + "\n");
        }
        else {
            str.append(results + "\n");
        }
        System.out.print(str.toString());
    }


    /**
     * Prints a dump of the Quad Tree and Skip List
     */
    private void dump() {
        StringBuilder str = new StringBuilder();
        str.append(skipList.toString() + "\n");
        str.append("QuadTree dump:\n");
        str.append(quadTree.toString() + "\n");
        System.out.print(str.toString());
    }

}
