/*
 * Property of Sheridan College (see Copyright Ownership Policy, ref #24)
 */
package printstringreversed;

import java.util.Scanner;

/**
 *
 * @author mike
 */
public class PrintStringReversed {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        String text = kbd.nextLine();
        String reversed = "";
        int l = text.length();
        for (int i = 0; i < l; i++) {
            reversed += text.charAt(l - i - 1);
        }
        System.out.println(reversed);
    }
    
}
