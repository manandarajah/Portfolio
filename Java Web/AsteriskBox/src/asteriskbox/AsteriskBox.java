/*
 * Property of Sheridan College (see Copyright Ownership Policy, ref #24)
 */
package asteriskbox;

import java.util.Scanner;

/**
 *
 * @author mike
 */
public class AsteriskBox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Enter two integers:");
        int m = kbd.nextInt();
        int n = kbd.nextInt();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    
}
