/*      Programming 1, EU4
        Created:2019-10-24
        Last updated: 2020-11-03
        Author: Amir Ali Safizadeh.
        Purpose of the code: The interface
        all the code in here were copied from the  assignment paper.
 */



import java.util.Iterator;


 public interface Polyline extends Iterable <Point >
 {
        Point[] getVertices();
        String getColor();
        int getWidth();
        double length();
        void setColor (String colour);
        void setWidth (int width);
        void add (Point vertex);
        void addBefore (Point vertex , String vertexName);
        void remove (String vertexName);
        Iterator<Point > iterator();
 }
