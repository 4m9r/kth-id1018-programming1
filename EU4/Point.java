/*      Programming 1, EU4
        Created:2019-10-24
        Last updated: 2020-11-03
        Author: Amir Ali Safizadeh.
        Purpose of the code: generates a point.
 */


public class  Point {
   private String name;
   private int x;
   private int y;

    /**
     *  Initializes a point.
     * @param name the name of the point
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(String name, int x, int y){
        this.name = name;
        this.x =x;
        this.y = y;
    }

    /**
     * Initializes a point.
     * @param p a point.
     */
    public Point( Point p){
        name = p.name;
        x = p.x;
        y = p.y;
    }

    /**
     * Returns a string representation of a point
     * @return a string representation of a point
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(name).
                append(",").append(x).
                append(",").append(y).
                append(")");
        return String.valueOf(sb);
    }

    /**
     * Returns the name of the point
     * @return the name of the point
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the x coordinate
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x coordinate
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Returns the y coordinate
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x coordinate
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *  Returns the distance from point a to point b{@code p2}
     * @param p2 the point to find the distance to.
     * @return the distanve from point a to point b{@code p2}
     */
    public double distance(Point p2) {
        double temp = (Math.pow(x - p2.x, 2) + Math.pow(y - p2.y, 2));
        return Math.sqrt(temp);
    }

    /**
     * Returns true if two point have same coordinate
     * @param p2 the point to check if its equal to other point
     * @return {@code true} if the two point have the same coordinate, {@code false} otherwise.
     */
    public boolean equals(Point p2){
        if (p2.x == x && p2.y == y)
            return true;
        return false;
    }

}
