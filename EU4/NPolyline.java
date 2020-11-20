/*      Programming 1, EU4
        Created:2019-10-24
        Last updated: 2020-11-10
        Author: Amir Ali Safizadeh.
        Purpose of the code:This class creates a planar polyline. Points in this class are connected via nodes.
 */





import java.util.Iterator;
import java.util.NoSuchElementException;



public class NPolyline implements Polyline {
    private String color = "black";
    private int width = 1;
    private Node head; // the beginning of polyline, the first point
    private int counter = 0; // number of vertices in the polyline

    // a helper class node
    public class Node{
      private Point point;
      private Node next;
      private Node prev;

      public Node(Point point){
         this.point = point;
         next = null;
         prev = null;
      }

    }

    /**
     * Initializes a polyline.
     * @param points an array of points.
     */
    public NPolyline(Point points[]){
        // store the first point in a node and increment the counter.
        Node node = new Node(points[0]);
        counter++;
        // our head now refers to the node.
        head = node;

        for (int i = 1; i < points.length; i++) {
            // Create other nodes to sore the rest of the points in them.
            node.next = new Node(points[i]);
            Node temp = node;

            // build the connection between the nodes using next and prev
            // increment the counter
            node = node.next;
            node.prev = temp;
            counter++;
        }
    }

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
     * Returns a string representation of polyline
     * @return a string representation of polyline
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{[");
        Node temp = head;
        while (temp !=null) {
            sb.append(temp.point.toString());
            temp=temp.next;
        }
        sb.append("],").append(color).append(',').append(width).append('}');
        return String.valueOf(sb);
    }

    /**
     * Returns all the points in the polyline
     * @return all the points in the polyline
     */
    public Point[] getVertices() {
        Point point[] = new Point[counter]; // Use the counter to create the array
        Node temp = head;
        int i = 0;
        while (temp != null){
            point[i++] = temp.point;
            temp = temp.next;
        }

        return point ;
    }

    /**
     * Returns the color of polyline
     * @return the color of polyline
     */

    public String getColor() {
        return color;
    }

    /**
     * Returns the width of polyline
     * @return the width of polyline
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the length of polyline
     * @return the length of polyline
     */
    public double length(){
        double len = 0;
        Node temp = head;
        while (temp.next !=null) {
            len = len + temp.point.distance(temp.next.point);
            temp = temp.next;
        }
        return len;
    }

    /**
     * Adds the point to the end of polyline
     * @param point the point to add the end of polyline
     */
    public void add(Point point){
        Node node = head;
        while (node.next != null)
            node = node.next;

        Node temp = new Node(point);
        temp.prev = node;
        node.next = temp;
        counter++;
    }

    /**
     * Adds the point before the the point named name.
     * @param point the point to add before the vertex {@code name}.
     * @param name name of the vertex that point is added before it.
     * @throws IllegalArgumentException unless found the vertex {@code name}.
     */
    public void addBefore(Point point ,String name){
        // check if the point we are looking for is stored in our head(first node).
        if (head.point.getName().compareTo(name) == 0) {
            // update the head to the given point
            Node node = new Node(point);
            node.next = head;
            head.prev = node;
            head = node;
            counter++;
            return;
        }
        // otherwise iterate through the nodes find the position of the point that we are looking for add the given
        // point before it and rebuild the connection.
        Node node = head;
        while (node !=null) {
            if (node.point.getName().compareTo(name) == 0){
                Node back = node.prev;
                Node temp = new Node(point);

                temp.next = node;
                node.prev = temp;

                back.next = temp;
                temp.prev = back;
                counter++;
                return;
            }
            node = node.next;
        }
        throw new IllegalArgumentException("The vertex doesn't exist");
    }

    /**
     * Remove the vertex form the polyline.
     * @param pointName the name of vertex to remove.
     * @throws IllegalArgumentException unless found the vertex {@code pointName}.
     */
    public void remove(String pointName){
        Node node = head;
        while (node !=null){
            if (node.point.getName().compareTo(pointName) == 0){
            Node front = node.next;
            Node back = node.prev;
            back.next =front;
            front.prev = back;

            counter--;
            return;
            }
        node = node.next;
        }
        throw new IllegalArgumentException("The vertex doesn't exist");
    }

    /**
     * Returns an iterator that iterates over the points in this polyline
     * @return an iterator that iterates over the points in this polyline
     */
    public Iterator<Point> iterator()  {
        return new NPointIterator(head);
    }
    private class NPointIterator implements Iterator<Point> {
        private Node current;

        public NPointIterator(Node head) {
            current = head;
        }



        public boolean hasNext() {
            return current != null;
        }


        public Point next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Point p = current.point;
            current = current.next;
            return p;
        }

    }


}
