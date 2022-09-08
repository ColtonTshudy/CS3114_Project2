

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * This program maintains a SkipList of Rectangle objects.
 * 
 * COMMANDS
 * Insert: adds a rectangle with a name, positive x and y, and positive w and h.
 * insert {name} {x} {y} {w} {h}
 * 
 * Dump: returns dump of internal skiplist.
 * dump
 * 
 * Remove: removes the first rectangle of the given name or coordinate, returns
 * the rectangle removed.
 * remove {name}
 * remove {x} {y} {w} {h}
 * 
 * Region Search: returns all rectangles within the region given.
 * regionsearch {x} {y} {w} {h}
 * 
 * Intersections: returns all intersecting rectangle pairs.
 * intersections
 * 
 * Search: returns the rectangle of the given name.
 * search {name}
 * 
 * @author Colton Tshudy
 * @version 9/5/2022
 */
public class Rectangle1 {

    /**
     * @param args
     *            File name containing commands listed above
     * @throws FileNotFoundException
     * @throws ParseException
     */
    @SuppressWarnings("unused")
    public static void main(String[] args)
        throws FileNotFoundException,
        ParseException {
        FileReader reader;

        reader = args.length == 1 ? new FileReader(args[0]) : new FileReader();

        if (args.length > 1)
            System.out.println(
                "Too many arguments. Please specify 1 file name.");
    }
}
