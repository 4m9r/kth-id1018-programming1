import java.util.Scanner;

/*      Programming 1, OU2
        Created:2019-10-24
        Last updated: 2021-11-03
        Author: Amir Safizadeh.
        Purpose of the code: Reads the lengths of a triangle's three sides and returns the
        circumcircle and the incircle.
 */

public class TriangleAndItsCircles {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("insert side a");
        double a = s.nextDouble();
        System.out.println("insert side b");
        double b = s.nextDouble();
        System.out.println("insert side c");
        double c = s.nextDouble();

        double cCircle = Triangle.circumcircle(a, b, c);
        double inCircle = Triangle.incircle(a, b, c);

        System.out.println("The radius of the circumcircle of the triangle = " + cCircle +
                "\n The radius of incircle of the triangle = " + inCircle);


    }
}
