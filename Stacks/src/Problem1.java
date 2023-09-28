import java.util.Scanner;
import java.util.Stack;

/**
 * A class that takes an input from the user and checks if the expression is
 * valid. For an expression to be valid, it must contain the same number of left
 * and right parentheses. This class contains some methods to help shorten the
 * length of the main method.
 * 
 * @author evangora
 *
 */
public class Problem1 {

    /**
     * A method to count the parentheses using a stack implementation. If the
     * leftCount and rightCount are equal, return true. Otherwise, return false.
     * 
     * @param The String input given by the user.
     * @return A boolean based on if the counts are equal.
     */
    public static boolean countParentheses(String input) {
        // The count of left and right parentheses
        int leftCount = 0;
        int rightCount = 0;
        // Create a stack and add all values of the String to it.
        Stack<String> exp = new Stack<String>();
        for (int i = input.length() - 1; i >= 0; i--) {
            exp.push(Character.toString(input.charAt(i)));
        }
        // If the expression starts with a right parenthese, it is invalid.
        if (exp.peek().equals(")")) {
            return false;
        }
        // Count the left and right parentheses in the stack and comapre the
        // counts.
        while (!exp.isEmpty()) {
            String next = exp.pop();
            if (next.equals("(")) {
                leftCount++;
            } else if (next.equals(")")) {
                rightCount++;
            }
        }
        if (leftCount == rightCount) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method to take a String input and determine if the expression is valid
     * or not. Returns "Yes" if the expression is valid, "No" of the expression
     * is not valid, or asks for a new input if the input is not valid.
     * 
     * @param The expression that the user gave as an input.
     * @return A String value based on the valdity of the expression.
     */
    public static String processInput(String input) {
        if (countParentheses(input)) {
            return "Yes";
        } else {
            return "No";
        }
    }

    /**
     * The main method for the class. Take the user's input and use the
     * processInput helper method to determine the result of the input.
     * 
     * @param args
     */
    public static void main(String[] args) {
        boolean loop = true;
        // Get the input from the user
        Scanner keyboard = new Scanner(System.in);
        while (loop == true) {
            System.out.print("Please enter an equation. (or -1 to exit) : ");
            // Determine if the input is an expression or -1.
            if (keyboard.hasNextInt()) {
                // If the input is an int
                int input = keyboard.nextInt();
                if (input == -1) {
                    System.out.print("Goodbye.");
                    break;
                } else {
                    System.out.println("Yes");
                }
            } else {
                // If the input is a String
                String input = keyboard.next();
                System.out.println(processInput(input));

            }

        }
        keyboard.close();
    }

}
