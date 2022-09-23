// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)
// -- Benjamin Gallini (bengallini)

/**
 * Represents a rectangle on a positive 2D plane, where 0,0 is the top left
 * corner of the plane.
 * 
 * @author Colton Tshudy (coltont)
 * @author Benjamin Gallini (bengallini)
 * @version 9/21/2022
 *
 */
public class Point {
    private int x;
    private int y;

    // these should be constant global vars in the future
    private int maxCoord = 1023;
    private int minCoord = 0;

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
     * @param newX
     *            new x coordinate of this point
     */
    public void setX(int newX) {
        x = newX;
    }


    /**
     * Setter for y coordinate
     * 
     * @param newY
     *            new y coordinate of this point
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
        return (x <= maxCoord && y <= maxCoord) && (x >= minCoord
            && y >= minCoord);
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
     * Gives this point's quadrant relative to the given square's origin
     * Quadrant 0 = NE, 1 = NW, 2 = SW, 3 = SE
     * 0 = the points share the same coordinates
     * 
     * @param topLeft
     *            Upper left point of the square
     * 
     * @param size
     *            Side length of the square
     * 
     * @return the number index of the point within the quadrant
     */
    public int findQuadrant(Point topLeft, int size) {
        if (!inSquare(topLeft, size)) // Not within this square
            return -1;

        // Get origin point of square and delta with respect to point
        int centerX = topLeft.getX() + size / 2;
        int centerY = topLeft.getY() + size / 2;
        int delX = x - centerX;
        int delY = y - centerY;

        if (delX > 0 && delY <= 0) // North East and +x axis
            return 0;
        if (delX <= 0 && delY < 0) // North West and -y axis
            return 1;
        if (delX < 0) // && delY >= 0 South West and -x axis
            return 2;
        // (delX >= 0 && delY > 0 || delX == delY)
        // South East and +y axis and origin
        return 3;
    }


    /**
     * Checks if this point within a given square, denoted by a top left corner
     * and side length
     * 
     * @param topLeft
     *            top left point of the square
     * @param size
     *            side length of the square
     * @return true if this point is within the square, false otherwise
     */
    public boolean inSquare(Point topLeft, int size) {
        if (x < topLeft.getX()) // point is to the left
            return false;
        if (x > topLeft.getX() + size) // point is to the right
            return false;
        if (y < topLeft.getY()) // point above
            return false;
        if (y > topLeft.getY() + size) // point is below
            return false;
        return true; // point is within or on the bounds of the square
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
