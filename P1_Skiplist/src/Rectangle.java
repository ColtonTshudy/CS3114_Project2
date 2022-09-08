// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Colton Tshudy (coltont)

/**
 * Represents a rectangle on a positive 2D plane, where 0,0 is the top left
 * corner. Rectangles are represented as the top left coordinate x,y with
 * width w and height h.
 * 
 * @author Colton Tshudy (coltont)
 * @version 9/5/2022
 */
public class Rectangle implements Comparable<Rectangle> {
    private String name;
    private int x;
    private int y;
    private int w;
    private int h;

    /**
     * Constructs a rectangle at (0,0) with no width or height
     */
    public Rectangle() {
        name = "";
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }


    /**
     * Constructs a rectangle at (x,y) with width w and height h
     * 
     * @param title
     *            name of rectangle
     * 
     * @param xl
     *            top left x coordinate
     * @param yl
     *            top left y coordinate
     * @param width
     *            width of rectangle
     * @param height
     *            height of rectangle
     */
    public Rectangle(String title, int xl, int yl, int width, int height) {
        name = title;
        x = xl;
        y = yl;
        w = width;
        h = height;
    }


    /**
     * Getter for name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Setter for name
     * 
     * @param title
     *            new name
     */
    public void setName(String title) {
        name = title;
    }


    /**
     * Getter for x
     * 
     * @return top left x coordinate
     */
    public int getX1() {
        return x;
    }


    /**
     * Getter for y
     * 
     * @return top left y coordinate
     */
    public int getY1() {
        return y;
    }


    /**
     * Setter for top left coordinate of rectangle
     * 
     * @param xl
     *            top left x coordinate
     * @param yl
     *            top left y coordinate
     */
    public void setCoords(int xl, int yl) {
        x = xl;
        y = yl;
    }


    /**
     * Getter for width
     * 
     * @return width
     */
    public int getWidth() {
        return w;
    }


    /**
     * Setter for width
     * 
     * @param width
     *            width of rectangle
     */
    public void setWidth(int width) {
        w = width;
    }


    /**
     * Getter for height
     * 
     * @return height of rectangle
     */
    public int getHeight() {
        return h;
    }


    /**
     * Setter for height
     * 
     * @param height
     *            height of rectangle
     */
    public void setHeight(int height) {
        h = height;
    }


    /**
     * Getter for bottom right x coordinate of rectangle
     * 
     * @return bottom right x coordinate
     */
    public int getX2() {
        return x + w;
    }


    /**
     * Getter for bottom right y coordinate of rectangle
     * 
     * @return bottom right y coordinate
     */
    public int getY2() {
        return y + h;
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
            Rectangle rec = (Rectangle)other;
            return x == rec.getX1() && y == rec.getY1() && w == rec.getWidth()
                && h == rec.getHeight();
        }
        return false;
    }


    /**
     * Returns a string representation of the Rectangle displaying the top left
     * coordinates x and y, and the width a height of the rectangle
     * 
     * @return a string representing rectangle
     */
    public String toString() {
        return x + ", " + y + ", " + w + ", " + h;
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
