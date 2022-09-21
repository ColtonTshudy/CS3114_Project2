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
        DataBase database = new DataBase();
        try (Scanner commandScanner = new Scanner(commandFile)) {
            while (commandScanner.hasNextLine()) {
                String line = commandScanner.nextLine().trim();
                if (line.isBlank()) {
                    continue;
                }
                database.doCommand(line);
            }
        }
    }

}
