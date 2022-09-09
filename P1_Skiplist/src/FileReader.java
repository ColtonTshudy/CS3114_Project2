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
     * Maximum length of commands
     */
    public static final int MAX_CMD_LEN = 6;
    /**
     * Skip List data structure for holding rectangles
     */
    private SkipList<String, Rectangle> rectangles;

    /**
     * Default constructor, effectively does nothing
     */
    public FileReader() {
        rectangles = new SkipList<String, Rectangle>();
        System.out.println("Please provide a file name.");
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
     * Executes a single given command, entered as a string on a single line,
     * with no more than 6 tokens
     * 
     * @param command
     *            Any valid SkipList command in string format
     * @throws ParseException
     */
    public void enterCommand(String command) throws ParseException {
        parseAndExecute(command, 1); // parses command for tokens, then attempts
                                     // to execute the command
    }


    /**
     * Extracts tokens from the text file
     * 
     * @param commandFile
     *            plain text file containing commands with their arguments
     * @throws FileNotFoundException
     * @throws ParseException
     */
    private void readTokens(String commandFile)
        throws FileNotFoundException,
        ParseException {
        Scanner scan = new Scanner(new File(commandFile));

        int lineIndex = 0; // keeps track of which line the first scanner is on
                           // in the commands file

        // Scan each line of the command file for valid tokens
        while (scan.hasNextLine()) {
            lineIndex++; // increment line index (which line of file is read)
            parseAndExecute(scan.nextLine(), lineIndex); // Separates line into
                                                         // tokens
        }
    }


    /**
     * Turns the given string into an array of strings, called tokens, and then
     * attempts to execute the command
     * 
     * @param command
     *            single command all on one line
     * @param lineIndex
     *            index of the command on the text file
     * 
     * @throws ParseException
     */
    private void parseAndExecute(String command, int lineIndex)
        throws ParseException {

        String[] tokens = new String[MAX_CMD_LEN]; // Array for holding tokens
        int tokenIndex = 0; // Token index counter

        Scanner lineScan = new Scanner(command); // Scans the single line string

        while (lineScan.hasNext()) {
            if (tokenIndex > MAX_CMD_LEN - 1) { // too many arguments (max
                                                // possible = 6)
                lineScan.close();
                throw new ParseException("Too many arguments", lineIndex);
            }
            tokens[tokenIndex] = lineScan.next();
            tokenIndex++;
        }
        lineScan.close(); // Close the scanner

        if (tokens[0] != null)
            executeTokens(tokens, lineIndex);
    }


    /**
     * Executes command based on tokens. If not valid command is found, prints
     * a default message to the console.
     * 
     * @param tokens
     *            String array of up to 6 tokens, pertaining to a command
     * @param lineIndex
     *            Index of the command in the text file
     * 
     */
    private void executeTokens(String[] tokens, int lineIndex) {
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
                System.out.println("Unknown command on line " + lineIndex);
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

        // Check if the rectangle is legal, if yes, then insert
        if (checkValidRec(rec))
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

        // Check if the rectangle exists, if so then return it
        if (rec == null) {
            stb.append("not removed: (");
            stb.append(name);
            stb.append(")");
        }
        else { // If rectangle did not exist, notify user
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
        Rectangle tool = new Rectangle("Removal Tool", x, y, w, h);
        Rectangle rec = rectangles.removeByElement(tool);

        // String building for generating output string
        StringBuilder stb = new StringBuilder();

        // Check if the region meets the required w, h > 0
        if (checkValidRec(tool)) {
            stb.append("Rectangle rejected: (");
            stb.append(tool);
            stb.append(")");
            System.out.println(stb.toString());
            return; // End the function early
        }

        stb.append("Rectangle ");

        // Check if a rectangle has been removed
        if (rec == null) { // No, so notify user that it failed to remove
            stb.append("not removed: ");
            stb.append("(");
            stb.append(tool);
            stb.append(")");
        }
        else { // Yes, so add the removed rectangle to the output
            stb.append("removed: ");
            stb.append("(");
            stb.append(rec.getName());
            stb.append(", ");
            stb.append(rec);
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
        Rectangle tool = new Rectangle("Region Tool", x, y, w, h);

        // String building for generating output string
        StringBuilder stb = new StringBuilder();

        // Check if the region meets the required w, h > 0
        if (w < MIN_WH || h < MIN_WH) {
            stb.append("Rectangle rejected: (");
            stb.append(tool);
            stb.append(")");
            System.out.println(stb.toString());
            return; // End the function early
        }

        stb.append("Rectangles intersecting region ");
        stb.append("(");
        stb.append(tool);
        stb.append("): ");

        // Cursor pointer for checking each rectangle
        SkipNode<String, Rectangle> cur = rectangles.getHead();
        cur = cur.forward[0];

        // Iterates through all nodes
        while (cur != null) {
            Rectangle rec = cur.element(); // rectangle in question
            if (rec.compareTo(tool) > 0) {
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
        while (cur1 != null) {
            Rectangle rec1 = cur1.element();

            // Iterates cur2 through all nodes
            while (cur2 != null) {
                Rectangle rec2 = cur2.element();
                if (rec1.compareTo(rec2) > 0 && cur1 != cur2) {
                    stb.append(System.lineSeparator());
                    stb.append(intersectStr(rec1, rec2));
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
                stb.append(System.lineSeparator());
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
            stb.insert(0, "Rectangles found:");
        else {
            stb.append("Rectangle not found: (");
            stb.append(name);
            stb.append(")");
        }

        System.out.println(stb.toString());
    }


    /**
     * Returns true if the rectangle given is valid. Rectangle must have
     * coordinates within (inclusive) 0,0 to 1024, 1024, width and height of >0,
     * and the name must start with a letter
     * 
     * @param rec
     *            Rectangle in question
     * 
     * @return true if the rectangle is valid
     * 
     */
    private boolean checkValidRec(Rectangle rec) {
        char fc = rec.getName().charAt(0); // first character of name
        int x = rec.getX1();
        int y = rec.getY1();
        int w = rec.getWidth();
        int h = rec.getHeight();

        // Check if the rectangle is legal, if yes, then insert
        return (x < MIN_XY || y < MIN_XY || w < MIN_WH || h < MIN_WH || (x
            + w) > MAX_XY || (y + h) > MAX_XY || !Character.isAlphabetic(fc));
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
