import java.util.*; // Scanner , Locale

/*      Programming 1, OU1
        Created:2019-10-24
        Last updated: 2021-11-02
        Author: Amir Safizadeh.
        Purpose of the code: store tmperature in an two dimentional array
        and return the greatest, least and average temperatures.
 */

class Temperatures {
    public static void main(String[] args) {
        System.out.println("TEMPERATURES\n");
// input tools
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);
// enter the number of weeks and measurements
        System.out.print("number of weeks: ");
        int nofWeeks = in.nextInt();
        System.out.print("number of measurements per week: ");
        int nofMeasurementsPerWeek = in.nextInt();
// storage space for temperature data
        double[][] t = new double[nofWeeks + 1][nofMeasurementsPerWeek + 1];
// read the temperatures
        for (int week = 1; week <= nofWeeks; week++) {
            System.out.println("temperatures - week " + week + ":");
            for (int reading = 1; reading <= nofMeasurementsPerWeek; reading++)
                t[week][reading] = in.nextDouble();
        }
        System.out.println();
// show the temperatures
        System.out.println("the temperatures:");
        for (int week = 1; week <= nofWeeks; week++) {
            for (int reading = 1; reading <= nofMeasurementsPerWeek; reading++)
                System.out.print(t[week][reading] + " ");
            System.out.println();
        }
        System.out.println();
// the least , greatest and average temperature - weekly
        double[] minT = new double[nofWeeks + 1];
        double[] maxT = new double[nofWeeks + 1];
        double[] sumT = new double[nofWeeks + 1];
        double[] avgT = new double[nofWeeks + 1];
// compute and store the least , greatest and average
// temperature for each week.
        for (int week = 1; week <= nofWeeks; week++) {
            minT[week] = t[week][1];
            maxT[week] = t[week][1];
            sumT[week] = t[week][1];
            for (int reading = 2; reading <= nofMeasurementsPerWeek; reading++) {

                sumT[week] += t[week][reading];

                if (t[week][reading] > maxT[week])
                    maxT[week] = t[week][reading];

                if (t[week][reading] < minT[week])
                    minT[week] = t[week][reading];
            }

            avgT[week] = sumT[week] / nofMeasurementsPerWeek;
        }

// show the least , greatest and average temperature for
// each week
        for (int i = 1; i <= nofWeeks; i++) {
            System.out.println("week" + i + " :\n");
            System.out.println("max:" + maxT[i]);
            System.out.println("min: " + minT[i]);
            System.out.println("Average: " + avgT[i]);
            System.out.println();
        }
// the least , greatest and average temperature - whole period
        double minTemp = minT[1];
        double maxTemp = maxT[1];
        double sumTemp = sumT[1];
        double avgTemp = 0;
// compute and store the least , greatest and average
        for (int week = 2; week <= nofWeeks; week++) {
            if (maxT[week] > maxTemp)
                maxTemp = maxT[week];

            if (minT[week] < minTemp)
                minTemp = minT[week];

            sumTemp += sumT[week];

        }

        avgTemp = sumTemp / (nofWeeks * nofMeasurementsPerWeek);
// show the least , greatest and average temperature for
// the whole period
        System.out.println("the maximum temperature: " + maxTemp);
        System.out.println("the minimum temperature : " + minTemp);
        System.out.println("average of all the weeks: " + avgTemp);
    }
}
