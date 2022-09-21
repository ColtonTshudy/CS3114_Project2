import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main for CS3114 Quadtree/SkipList Point project
 * Usage: java Point2 <command-file>
 *
 * @author CS Staff
 */
public class Point2 {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Point2 <command-file>");
            return;
        }

        String filename = args[0].trim();
        File commandFile = new File(filename);
        if (!commandFile.exists()) {
            System.out.println("File does not exist: " + filename);
            return;
        }

        // TODO: construct a homemade DataBase object
        // A DataBase will be made of one SkipList and one PRQuadtree.
        // It will keep these two structures SYNCRONIZED.
        // When something gets added/removed from one structure,
        // the other also gets it added/removed
        DataBase database = new DataBase();
        try (Scanner commandScanner = new Scanner(commandFile)) {
            while (commandScanner.hasNextLine()) {
                String line = commandScanner.nextLine().trim();
                if (line.isBlank()) {
                    continue;
                }
                database.doCommand(line);
                // TODO: call theDatabase.doCommand(line).
                // it will parse the line and attempt to
                // The database will have methods that execute certain
                // commands using the data structure that is most efficient
                database.doCommand(line);
            }
        }
    }

}
