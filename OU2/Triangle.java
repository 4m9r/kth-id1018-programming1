/*      Programming 1, OU2
        Created:2019-10-24
        Last updated: 2021-11-03
        Author: Amir Safizadeh.
        Purpose of the code: To perform different computations
        on the properties of a triangle.
 */


public class Triangle {

    // Calculate the bisectors of a triangle using the sides and angles
    // Using "char s" you can specify which bisector should the method return
    public static double bisector(double a, double c, double b,
                                  double alpha, double beta, double sigma, char s) {

        return switch (s) {
            case 'a' -> (2 * b * c * Math.cos(alpha / 2)) / (b + c);
            case 'b' -> (2 * a * c * Math.cos(beta / 2)) / (a + c);
            default -> (2 * a * b * Math.cos(sigma / 2)) / (a + b);
        };
    }

    // calculating perimeter
    public static double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    // calculating area using base and height
    public static double area(double base, double height) {
        return (base * height) / 2;
    }

    // calculating area using 3 sides
    public static double area(double a, double b, double c) {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    // radius of the circumcirle
    public static double circumcircle(double a, double b, double c) {
        double k = area(a, b, c);
        return (a * b * c) / (k * 4);
    }

    // radius of the incircle
    public static double incircle(double a, double b, double c) {
        double k = area(a, b, c);
        double semiP = perimeter(a, b, c) / 2;
        return k / semiP;
    }


}
