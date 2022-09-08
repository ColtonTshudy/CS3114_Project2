

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
     * @param name
     *            name of rectangle
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @param width
     *            width of rectangle
     * @param height
     *            height of rectangle
     */
    public Rectangle(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        w = width;
        h = height;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getX1() {
        return x;
    }


    public int getY1() {
        return y;
    }


    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getWidth() {
        return w;
    }


    public void setWidth(int w) {
        this.w = w;
    }


    public int getHeight() {
        return h;
    }


    public void setHeight(int h) {
        this.h = h;
    }


    public int getX2() {
        return x + w;
    }


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
     * Generates a string for the rectangle
     * 
     * @return string representing rectangle
     */
    public String toString() {
        return x + ", " + y + ", " + w + ", " + h;
    }


    /**
     * Checks if the given rectangle intersects this rectangle
     * 
     * @param R
     *            given rectangle
     * @return 1 if rectangles intersect, -1 if they do not
     */
    @Override
    public int compareTo(Rectangle R) {
        int intersect = 1;
        // checks if given rectangle's coords are outside a given rectangle
        // rectangle is below (y1 > y2)
        if (this.getY1() >= R.getY2() || R.getY1() >= this.getY2())
            intersect = -1;
        // rectangle is right (x1 > x2)
        else if (this.getX1() >= R.getX2() || R.getX1() >= this.getX2())
            intersect = -1;
        // rectangle has no area
        else if (w <= 0 || h <= 0 || R.getHeight() <= 0 || R.getWidth() <= 0)
            intersect = -1;
        return intersect;
    }
}
