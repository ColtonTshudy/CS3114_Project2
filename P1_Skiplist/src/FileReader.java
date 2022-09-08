// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import java.io.File;

/**
 * Reads an input file containing commands and executes them on the SkipList
 * continaing rectangle objects
 * 
 * @author Colton Tshudy (coltont)
 * @version 09/07/2022
 */
public class FileReader {
    /**
     * Maximum region border
     */
    public static final int MAX_XY = 1024;
    /**
     * Minimum region border
     */
    public static final int MIN_XY = 0;
    /**
     * Minimum width and height
     */
    public static final int MIN_WH = 1;
    /**
     * Skip List data structure for holding rectangles
     */
    private SkipList<String, Rectangle> rectangles;

    /**
     * Default constructor, effectively does nothing
     */
    public FileReader() {
        System.out.println("Please provide a file name");
    }


    /**
     * Constructor for CommandReader, passes the file to readTokens
     * 
     * @param commandFile
     *            plain text file containing commands with their arguments
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public FileReader(String commandFile)
        throws FileNotFoundException,
        ParseException {
        rectangles = new SkipList<String, Rectangle>();
        readTokens(commandFile);
    }


    /**
     * Extracts tokens from the text file
     * 
     * @param commandFile
     *            plain text file containing commands with their arguments
     * @throws FileNotFoundException
     * @throws ParseException
     */
    void readTokens(String commandFile)
        throws FileNotFoundException,
        ParseException {
        Scanner scan = new Scanner(new File(commandFile));

        // Scan each line of the command file for valid tokens
        while (scan.hasNextLine()) {
            int lineIndex = 0;
            lineIndex++; // increment line index (which line of file is read)
            String[] tokens = new String[6]; // maximum number of tokens

            int tokenIndex = 0; // token index
            Scanner lineScan = new Scanner(scan.nextLine()); // scans this line

            // separates line into tokens
            while (lineScan.hasNext()) {
                if (tokenIndex > 5) { // too many arguments (max possible = 6)
                    lineScan.close();
                    scan.close();
                    throw new ParseException("Too many arguments", lineIndex);
                }
                tokens[tokenIndex] = lineScan.next();
                tokenIndex++;
            }

            if (tokens[0] != null)
                executeTokens(tokens, lineIndex);
        }
    }


    private void executeTokens(String[] tokens, int line) {
        int[] intArg = new int[6]; // represents integer arguments, converted
                                   // from tokens
        switch (tokens[0]) {
            case "insert":
                for (int i = 2; i < 6; i++)
                    intArg[i] = Integer.parseInt(tokens[i]);
                insert(tokens[1], intArg[2], intArg[3], intArg[4], intArg[5]);
                break;
            case "dump":
                dump();
                break;
            case "remove":
                if (tokens[2] == null) // remove by name
                    remove(tokens[1]);
                else { // remove by element
                    for (int i = 1; i < 5; i++)
                        intArg[i] = Integer.parseInt(tokens[i]);
                    remove(intArg[1], intArg[2], intArg[3], intArg[4]);
                }
                break;
            case "regionsearch":
                for (int i = 1; i < 5; i++)
                    intArg[i] = Integer.parseInt(tokens[i]);
                regionsearch(intArg[1], intArg[2], intArg[3], intArg[4]);
                break;
            case "intersections":
                intersections();
                break;
            case "search":
                search(tokens[1]);
                break;
            default:
                System.out.println("Unknown command on line " + line);
                break;
        }
    }


    /**
     * Adds a rectangle with a name, positive x and y, and positive w and h.
     * Must be within the borders of (0, 0) and (1024, 1024), and have a letter
     * as the first character of the name
     * 
     * @param name
     *            name of rectangle
     * @param x
     *            x coordinate of rectangle
     * @param y
     *            y coordinate of rectangle
     * @param w
     *            width of rectangle
     * @param h
     *            height of rectangle
     * @return a message regarding success or failure to insert
     */
    private void insert(String name, int x, int y, int w, int h) {
        Rectangle rec = new Rectangle(name, x, y, w, h);

        // String building for generating output string
        StringBuilder stb = new StringBuilder();
        stb.append("Rectangle ");

        char fc = name.toLowerCase().charAt(0); // first character of string

        // Check if the rectangle is legal, if yes, then insert
        if (x < MIN_XY || y < MIN_XY || w < MIN_WH || h < MIN_WH || (x
            + w) > MAX_XY || (y + h) > MAX_XY || (fc - 'a' > 25))
            stb.append("rejected: (");
        else {
            stb.append("inserted: (");
            rectangles.insert(name, rec);
        }

        // Output string
        stb.append(rec.getName());
        stb.append(", ");
        stb.append(rec);
        stb.append(")");
        System.out.println(stb.toString());
    }


    /**
     * Prints the structure of the internal Skip List to the console
     */
    private void dump() {
        System.out.println("SkipList dump:\r\n" + rectangles);
    }


    /**
     * Removes a rectangle of a given name
     * 
     * @param name
     *            name of rectangle to remove
     */
    private void remove(String name) {
        // Attempt to remove rectangle
        Rectangle rec = rectangles.removeByKey(name);

        // String building for generating output string
        StringBuilder stb = new StringBuilder();
        stb.append("Rectangle ");

        // Check if the rectangle is legal, if yes, then insert
        if (rec == null) {
            stb.append("not removed: (");
            stb.append(name);
            stb.append(")");
        }
        else {
            stb.append("removed: (");
            stb.append(rec.getName());
            stb.append(", ");
            stb.append(rec);
            stb.append(")");
        }

        // Output string
        System.out.println(stb.toString());
    }


    /**
     * Removes a rectangle of the given size and coordinates
     * 
     * @param x
     *            x coordinate of rectangle to remove
     * @param y
     *            y coordinate of rectangle to remove
     * @param w
     *            width of rectangle to remove
     * @param h
     *            height of rectangle to remove
     */
    private void remove(int x, int y, int w, int h) {
        // Attempt to remove rectangle
        Rectangle rem = new Rectangle("Removal Tool", x, y, w, h);
        Rectangle rec = rectangles.removeByElement(rem);

        // String building for generating output string
        StringBuilder stb = new StringBuilder();

        // Check if the region meets the required w, h > 0
        if (w < MIN_WH || h < MIN_WH) {
            stb.append("Rectangle rejected: (");
            stb.append(rem);
            stb.append(")");
            System.out.println(stb.toString());
            return; // End the function early
        }

        stb.append("Rectangle ");

        // Check if the rectangle is legal, if yes, then insert
        if (rec == null) {
            stb.append("not removed: ");
            stb.append("(");
            stb.append(rem);
            stb.append(")");
        }
        else {
            stb.append("removed: ");
            stb.append("(");
            stb.append(rem.getName());
            stb.append(", ");
            stb.append(rem);
            stb.append(")");
        }

        // Output string
        System.out.println(stb.toString());
    }


    /**
     * Prints all rectangles intersecting with the given rectangle. Must have a
     * width and height greater than 0.
     * 
     * @param x
     *            x coordinate of rectangle
     * @param y
     *            y coordinate of rectangle
     * @param w
     *            width of rectangle
     * @param h
     *            height of rectangle
     */
    private void regionsearch(int x, int y, int w, int h) {
        // Rectangle for testing intersection
        Rectangle regs = new Rectangle("Region Tool", x, y, w, h);

        // String building for generating output string
        StringBuilder stb = new StringBuilder();

        // Check if the region meets the required w, h > 0
        if (w < MIN_WH || h < MIN_WH) {
            stb.append("Rectangle rejected: (");
            stb.append(regs);
            stb.append(")");
            System.out.println(stb.toString());
            return; // End the function early
        }

        stb.append("Rectangles intersecting region ");
        stb.append("(");
        stb.append(regs);
        stb.append("): ");

        // Cursor pointer for checking each rectangle
        SkipNode<String, Rectangle> cur = rectangles.getHead();
        cur = cur.forward[0];

        // Iterates through all nodes
        while (cur != null) {
            Rectangle rec = cur.element(); // rectangle in question
            if (rec.compareTo(regs) > 0) {
                stb.append("\n"); // found intersecting rec
                stb.append("(");
                stb.append(rec.getName());
                stb.append(", ");
                stb.append(rec);
                stb.append(")");
            }
            cur = cur.forward[0];
        }

        System.out.println(stb.toString());
    }


    /**
     * Prints all intersection pairs
     */
    private void intersections() {
        // String building for generating output string
        StringBuilder stb = new StringBuilder();
        stb.append("Intersections pairs:");

        if (rectangles.size() < 2) {
            // There are no pairs if list has fewer than 2 nodes
            System.out.println(stb.toString());
            return;
        }

        // Cursor pointer for checking each rectangle
        SkipNode<String, Rectangle> cur1 = rectangles.getHead();
        SkipNode<String, Rectangle> cur2 = rectangles.getHead();

        cur1 = cur1.forward[0]; // Go to first list item
        cur2 = cur2.forward[0]; // Go to second list item

        // Iterates cur1 through all nodes
        while (cur1 != null && cur2 != null) {
            Rectangle rec1 = cur1.element();

            // Iterates cur2 through all nodes
            boolean foundPair = false;
            while (cur2 != null && !foundPair) {
                Rectangle rec2 = cur2.element();
                if (rec1.compareTo(rec2) > 0 && cur1 != cur2) {
                    stb.append(System.lineSeparator());
                    stb.append(intersectStr(rec1, rec2));
                    foundPair = true; // Found a pair, raise end loop flag
                }
                cur2 = cur2.forward[0];
            }
            cur1 = cur1.forward[0]; // Go to next node in list
            cur2 = rectangles.getHead().forward[0]; // Restart cur2
        }

        System.out.println(stb.toString());
    }


    /**
     * Prints all rectangles intersecting with the given rectangle
     * 
     * @param name
     *            name of the rectangle to find
     * 
     */
    private void search(String name) {
        // String building for generating output string
        StringBuilder stb = new StringBuilder();

        boolean foundFlag = false; // raised if any rectangles are found

        // Cursor to traverse skip list to find all matches
        SkipNode<String, Rectangle> cur = rectangles.getHead();
        cur = cur.forward[0];

        // Check all rectangles for all matching names
        while (cur != null) {
            Rectangle rec = cur.element();
            if (rec.getName().equals(name)) {
                stb.append("("); // found a match
                stb.append(name);
                stb.append(", ");
                stb.append(rec);
                stb.append(")");
                foundFlag = true;
            }
            cur = cur.forward[0];
        }

        if (foundFlag)
            stb.insert(0, "Rectangles found:\n");
        else {
            stb.append("Rectangle not found: (");
            stb.append(name);
            stb.append(")");
        }

        System.out.println(stb.toString());
    }


    /**
     * Represents a rectangle intersection by a string
     * 
     * @param rec1
     *            first rectangle
     * @param rec2
     *            second rectangle
     * @return string representing an intersection
     */
    private String intersectStr(Rectangle rec1, Rectangle rec2) {
        StringBuilder stb = new StringBuilder();
        stb.append("(");
        stb.append(rec1.getName());
        stb.append(", ");
        stb.append(rec1);
        stb.append(" | ");
        stb.append(rec2.getName());
        stb.append(", ");
        stb.append(rec2);
        stb.append(")");
        return stb.toString();
    }

}
