/*      Programming 1, EU4
        Created:2019-10-24
        Last updated: 2020-11-03
        Author: Amir Ali Safizadeh.
        Purpose of the code: this class contain a static method which takes an array of
         polylines and gives back the shortest yellow polyline.
 */


public class Polylines {
    /**
     * Returns a yellow polyline which has the shortest length.
     * @param polylines an array of polylines.
     * @return a yellow polyline which has the shortest length.
     * @throws IllegalArgumentException unless a yellow polyline is found
     */
    public static Polyline sPL(Polyline[] polylines){
        int j = -1;
        // give the len a big positive value
        double len = Double.MAX_VALUE;
        for (int i = 0; i < polylines.length; i++) {
            if (polylines[i].getColor().equals("yellow") &&
                    polylines[i].length() < len) {
                j = i;
                len = polylines[i].length();
            }
        }
        // If there wasn't any yellow polyline
        if (j < 0)
            throw new IllegalArgumentException("no yellow polyline has been found");
        return polylines[j];
    }
}
