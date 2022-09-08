// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * This program maintains a SkipList of Rectangle objects.
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
