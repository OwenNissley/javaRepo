import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int totalPoints = 1000000; // Total points for the simulation
        int pointsInsideCircle = 0;

        Random random = new Random();

        for (int i = 0; i < totalPoints; i++) {
            // Generate x and y points randomly between 0 and 1
            double x = random.nextDouble();
            double y = random.nextDouble();

            // Check if the point is inside the unit circle
            if (x * x + y * y <= 1) {
                pointsInsideCircle++;
            }
        }

        // Calculate the approximate value of pi
        double piEstimate = 4.0 * pointsInsideCircle / totalPoints;

        System.out.println("Estimated value of Pi: " + piEstimate);
    }
}
