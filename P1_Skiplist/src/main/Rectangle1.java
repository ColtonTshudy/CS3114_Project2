package main;

import java.io.FileNotFoundException;

/**
 * This program maintains a SkipList of Rectangle objects.
 * 
 * COMMANDS
 * Insert: adds a rectangle with a name, positive x and y, and positive w and h
 * insert {name} {x} {y} {w} {h}
 * 
 * Dump: returns dump of internal skiplist
 * dump
 * 
 * Remove: removes the first rectangle of the given name or coordinate, returns
 * the rectangle removed
 * remove {name}
 * remove {x} {y} {w} {h}
 * 
 * Region Search: returns all rectangles within the region given
 * regionsearch {x} {y} {w} {h}
 * 
 * Intersections: returns all intersecting rectangle pairs
 * intersections
 * 
 * Search: returns the rectangle of the given name
 * search {name}
 * 
 * @author Colton Tshudy
 * @version 9/5/2022
 */
public class Rectangle1 {
    SkipList<String, Rectangle> skpl = new SkipList<String, Rectangle>();

    /**
     * @param args
     *            commands for interacting with the internal skiplist
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(args[0]);
    }


    /**
     * Adds a rectangle with a name, positive x and y, and positive w and h
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
    public String insert(String name, int x, int y, int w, int h) {
        Rectangle rec = new Rectangle(x, y, w, h);

        // String building for generating output string
        StringBuilder stb = new StringBuilder();
        stb.append("Rectangle ");

        // Check if the rectangle is legal, if yes, then insert
        if (x <= 0 || y <= 0 || w <= 0 || h <= 0)
            stb.append("rejected: (");
        else {
            stb.append("accepted: (");
            skpl.insert(name, rec);
        }

        // Output string
        stb.append(name);
        stb.append(", ");
        stb.append(rec.toString());
        stb.append(")");
        return stb.toString();
    }

    /**
     * Dumps the state of the internal skiplist
     * 
     * @return a string representation of the internal skiplist
     */
    public String dump()
    {
        return "";
    }

}
