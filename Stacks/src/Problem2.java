import java.util.Scanner;
import java.util.Stack;

/**
 * A class to take a positive integer as an input and then convert it to its
 * corresponding binary number. Contains a main method and a helper method to
 * convert the number to binary.
 * 
 * @author evangora
 *
 */
public class Problem2 {

    /**
     * This helper methods takes an integer input and converts it to its binary
     * counterpart. It does this by taking the modulus of the input and adding
     * it to a stack until the input cannot be divided anymore. It then turns
     * the int values in the stack to Strings and adds them to a return String.
     * 
     * @param input
     * @return
     */
    public static String convertBinary(int input) {
        // Create a stack to store the binary values for the int.
        Stack<Integer> binStack = new Stack<Integer>();
        // The return string for the method.
        String binary = "";
        int curr = input;
        // Add the remainders to the stack, while dividing current by 2 until
        // you get to the end of the integer.
        while (curr != 0) {
            binStack.add(curr % 2);
            curr = curr / 2;
        }
        // Add the values from the stack to a String to be returned.
        while (!binStack.isEmpty()) {
            binary = binary + Integer.toString(binStack.pop());
        }
        return binary;
    }

    /**
     * The main method of the class. Takes the user input and then calls the
     * helper method convertBinary to convert the given int to binary. If the
     * user does not give a positive int, it will ask for a valid input.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // A loop control variable to allow the user to exit the program.
        boolean loop = true;
        // Create a scanner to take the user input.
        Scanner keyboard = new Scanner(System.in);
        while (loop == true) {
            System.out.print(
                    "Please enter a positive integer (or -1 to exit) : ");
            // Determine if the input is valid, invalid, or -1
            if (keyboard.hasNextInt()) {
                int input = keyboard.nextInt();
                if (input == -1) {
                    System.out.println("Goodbye.");
                    break;
                } else if (input < 0) {
                    System.out.println("Please enter a valid input.");
                } else {
                    System.out.println(convertBinary(input));
                }
            } else {
                // Prevents an infinite loop.
                String input = keyboard.next();
                System.out.println("Please enter a valid input.");
            }

        }
        keyboard.close();
    }
}
