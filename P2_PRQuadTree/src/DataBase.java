
public class DataBase {

    public DataBase() {
        SkipList<String, Point> skipList = new SkipList<String, Point>();
        QuadTree quadTree = new QuadTree();
    }
    
    public void doCommand(String line) {
        String[] commands = line.split("\\s+");
        
    }
}
