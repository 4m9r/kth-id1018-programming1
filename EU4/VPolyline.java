/*      Programming 1, EU4
        Created:2019-10-24
        Last updated: 2020-11-03
        Author: Amir Ali Safizadeh.
        Purpose of the code:This class creates a planar polyline. The point in this class are to be store in an array of
        points
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class VPolyline implements Polyline {
    private   String color = "black";
    private   int width = 1;
    private   Point[] points; // an array of point to sore the points in polyline in.

    /**
     * Initializes a polyline.
     * @param points an array of points.
     */
    public VPolyline(Point[] points){
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] =new Point(points[i]);
        }

    }

    /**
     * Returns a string representation of polyline
     * @return a string representation of polyline
     */
    public String toString(){
        StringBuilder sb =new StringBuilder();
        sb.append("{[");
        for (int i = 0; i < points.length; i++)
            sb.append(points[i].toString());
        sb.append("],").append(color).append(',').append(width).append('}');
        return String.valueOf(sb);}

    /**
     * Sets the color of polyline
     * @param color set the color of polyline to this
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Sets the width of the polyline
     * @param width set the with of polyline to this
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the width of polyline
     * @return the width of polyline
     */
    public int getWidth() {
        return width;
    }



    /**
     * Returns all the points in the polyline
     * @return all the points in the polyline
     */
    public Point[] getVertices() {
        return points;
    }

    /**
     * Returns the color of polyline
     * @return the color of polyline
     */

    public String getColor() {
        return color;
    }

    /**
     * Returns the length of polyline
     * @return the length of polyline
     */
    public double length(){
        double len = 0;
        for (int i = 0; i < points.length - 1; i++)
            len = len + points[i].distance(points[i+1]);
        return  len;
    }

    /**
     * Adds the point to the end of polyline
     * @param point the point to add the end of polyline
     */
    public void add(Point point){
        Point[] temp = new Point[points.length + 1];
        for (int i = 0; i< points.length; i++)
            temp[i] = points[i];
        temp[points.length] = new Point(point);
        points = temp;
    }
    /**
     * Adds the point before the the point named name.
     * @param point the point to add before the vertex {@code name}.
     * @param pointName name of the vertex that point is added before it.
     * @throws IllegalArgumentException unless found the vertex {@code pointName}.
     */

    public void addBefore(Point point, String pointName){
        int pos = -1;
        for (int i = 0; i < points.length; i++){
            if (points[i].getName().compareTo(pointName) == 0)
                pos = i;
        }
        if (pos<0)
            throw  new IllegalArgumentException("the point does not exist");
        Point[] temp  = new Point[points.length + 1];
        int j =0;
        for (int i = 0; i < points.length; i++) {
            if (i == pos) {
                temp[i] = point;
                j++;
            }
            temp[j] = points[i];
            j++;
        }
        points = temp;
    }

    /**
     * Remove the vertex form the polyline.
     * @param pointName the name of vertex to remove.
     * @throws IllegalArgumentException unless found the vertex {@code pointName}.
     */
    public void remove(String pointName){
        int pos = -1;
        for (int i = 0; i < points.length; i++){
            if (points[i].getName().compareTo(pointName) == 0)
                pos = i;
        }
        if (pos<0)
            throw  new IllegalArgumentException("the point does not exist");
        Point[] temp  = new Point[points.length -1];
        for (int i = 0; i< pos; i++)
            temp[i] = points[i];
        for (int i = pos+1; i < points.length;i++ )
            temp[i - 1] = points[i];
        points = temp;
    }


    /**
     * Returns an iterator that iterates over the points in this polyline
     * @return an iterator that iterates over the points in this polyline
     */
    public Iterator<Point> iterator() {
        return new VPointIterator();}
    private class VPointIterator implements Iterator<Point> {
        int current = -1;

        public VPointIterator() {
            if (points.length > 0)
                current = 0;
        }

        public boolean hasNext() {
            return current != -1;
        }


        public Point next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Point p = new Point(points[current]);
            advance();
            return p;
        }

        public void advance() {
            if (current >= 0 &&
                    current < points.length - 1)
                current++;
            else
                current = -1;

        }
    }
}

