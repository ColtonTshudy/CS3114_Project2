package main;

public class Rectangle {
    private String name;
    private int x;
    private int y;
    private int w;
    private int h;
    
    // Default constructor
    public Rectangle() {
        name = "";
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }
    
    // Epic constructor
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
     * Checks if the given rectangle intersects this rectangle
     * 
     * @param R
     *            given rectangle
     * @return true if the rectangles intersect, false otherwise
     */
    public boolean intersectsWith(Rectangle R) {
        boolean intersect = true;
        // checks if given rectangle's coords are outside a given rectangle
        // rectangle is below
        if (this.getY1() >= R.getY2() || this.getY2() <= R.getY1())
            intersect = false;
        // rectangle is right
        else if (this.getX1() >= R.getX2() || this.getX1() >= R.getX2())
            intersect = false;
        // rectangle has no area
        else if (w <= 0 || h <= 0 || R.getHeight() <= 0 || R.getWidth() <= 0)
            intersect = false;
        return intersect;
    }
}
