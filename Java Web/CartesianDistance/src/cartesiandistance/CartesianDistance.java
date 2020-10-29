/*
 * Property of Sheridan College (see Copyright Ownership Policy, ref #24)
 */
package cartesiandistance;

import java.util.Scanner;

/**
 *
 * @author mike
 */
public class CartesianDistance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Please enter x and y for one point:");
        double x1 = kbd.nextDouble();
        double y1 = kbd.nextDouble();
        System.out.println("Please enter x and y for another point:");
        double x2 = kbd.nextDouble();
        double y2 = kbd.nextDouble();
        
        double xDiff = x1 - x2;
        double yDiff = y1 - y2;
        double distance = Math.sqrt(Math.pow(yDiff, 2) + xDiff * xDiff);
        
        System.out.println("The distance is " + distance);
    }
    
}
