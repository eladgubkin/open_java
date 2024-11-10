import java.util.Scanner;

/**
 * 
 * MaxLine calculates the maximum distance between three points in 2D space.
 * It prompts for coordinates and outputs the two points that form the longest
 * line.
 * 
 * @author (Elad Gubkin)
 * @version (16-11-2024)
 */

public class MaxLine {
  public static void main(String[] args) {
    final Scanner scan = new Scanner(System.in);

    // Input coordinates for three points
    System.out.println("Enter first point coordinates:");
    final int x1 = scan.nextInt();
    final int y1 = scan.nextInt();

    System.out.println("Enter second point coordinates:");
    final int x2 = scan.nextInt();
    final int y2 = scan.nextInt();

    System.out.println("Enter third point coordinates:");
    final int x3 = scan.nextInt();
    final int y3 = scan.nextInt();

    // Calculate distances between points
    final double distanceAB = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    final double distanceBC = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
    final double distanceAC = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));

    // Find the maximum distance and corresponding points
    double maxDistance = distanceAB;
    String maxLine = "(" + x1 + "," + y1 + "), (" + x2 + "," + y2 + ").";

    if (distanceBC > maxDistance) {
      maxDistance = distanceBC;
      maxLine = "(" + x2 + "," + y2 + "), (" + x3 + "," + y3 + ").";
    }

    if (distanceAC > maxDistance) {
      maxDistance = distanceAC;
      maxLine = "(" + x1 + "," + y1 + "), (" + x3 + "," + y3 + ").";
    }

    System.out.println("Max line created by the following points: " + maxLine);

    // Close the scanner
    scan.close();
  }
}
