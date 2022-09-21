
/**
 * Represents a rectangle on a positive 2D plane, where 0,0 is the top left
 * corner of the plane.
 * 
 * @author Colton Tshudy (coltont
 * @version 9/21/2022
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
     * Getter for x coordinate
     * 
     * @return x coordinate of this point
     */
    public int getX() {
        return x;
    }


    /**
     * Getter for y coordinate
     * 
     * @return x coordinate of this point
     */
    public int getY() {
        return y;
    }


    /**
     * Setter for x coordinate
     * 
     * @param x
     *            new x coordinate of this point
     */
    public void setX(int newX) {
        x = newX;
    }


    /**
     * Setter for y coordinate
     * 
     * @param y
     *            new x coordinate of this point
     */
    public void setY(int newY) {
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
     * Gives this point's location relative to the location of the given point,
     * as a direction.
     * Direction 1 = E, 2 = NE, 3 = N, 4 = NW, 5 = W, 6 = SW, 7 = S, 8 = SE.
     * 0 = the points share the same coordinates
     * 
     * @param other
     *            point to compare against
     * 
     * @return relative direction to given point
     */
    public int relativeDirection(Point other) {
        int delX = x - other.getX();
        int delY = y - other.getY();
        if (delX == 0 && delY == 0) // Same coordinates
            return 0;
        if (delX > 0 && delY == 0) // East
            return 1;
        if (delX > 0 && delY < 0) // North East
            return 2;
        if (delX == 0 && delY < 0) // North
            return 3;
        if (delX < 0 && delY < 0) // North West
            return 4;
        if (delX < 0 && delY == 0) // West
            return 5;
        if (delX < 0 && delY > 0) // South West
            return 6;
        if (delX == 0 && delY > 0) // South
            return 5;
        if (delX > 0 && delY > 0) // South East
            return 8;
        return -1; // No valid direction found
    }


    /**
     * Returns true if both rectangles are equal
     * 
     * @param other
     *            Rectangle to check against
     * 
     * @return true if both of their x, y, w, h are the equal
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            Point pt = (Point)other;
            return x == pt.getX() && y == pt.getY();
        }
        return false;
    }

}
