
/**
 * Represents a rectangle on a positive 2D plane, where 0,0 is the top left
 * corner of the plane.
 * 
 * @author Colton Tshudy (coltont
 * 
 * @version 9/5/2022
 *
 */
public class Point {
    int x;
    int y;
    private int MAX_COORD = 1023;
    private int MIN_COORD = 0;

    /**
     * Constructs a new point in the x y plane
     * 
     * @param newX
     *            x coordinate of the point
     * @param newY
     *            y coordinate of the point
     */
    public Point(int newX, int newY) {
        x = newX;
        y = newY;
    }


    /**
     * Checks whether this point is within the would boundaries
     * 
     * @return true if both x and y are within the bounds of MAX_COORD and
     *         MIN_COORD
     */
    public boolean validPoint() {
        return (x <= MAX_COORD && y <= MAX_COORD) && (x > MIN_COORD
            && y > MIN_COORD);
    }


    /**
     * Returns a string representation of the Point, "x, y"
     * 
     * @return a string representing point
     */
    public String toString() {
        return x + ", " + y;
    }

    /**
     * Checks if the given rectangle intersects this rectangle
     * 
     * @param rec
     *            given rectangle
     * @return 1 if rectangles intersect, -1 if they do not
     */
    @Override
    public int compareTo(Rectangle rec) {
        int intersect = 1;
        // checks if given rectangle's coords are outside a given rectangle
        // rectangle is below (y1 > y2)
        if (this.getY1() >= rec.getY2() || rec.getY1() >= this.getY2())
            intersect = -1;
        // rectangle is right (x1 > x2)
        else if (this.getX1() >= rec.getX2() || rec.getX1() >= this.getX2())
            intersect = -1;
        // rectangle has no area
        else if (w <= 0 || h <= 0 || rec.getHeight() <= 0 || rec
            .getWidth() <= 0)
            intersect = -1;
        return intersect;
    }

}
