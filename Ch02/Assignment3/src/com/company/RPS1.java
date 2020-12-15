package com.company;
import java.util.Scanner;
// Assignment 1 class
public class RPS1 {
//    Main function that starts program
    public static void main(String[] args) {
        isValid();
    }

    /*
     * Basic function that opens scanner and prints the validity of the input.
     * Calls the private isValid() function with user input to check validity.
     */
    public static void isValid() {
        Scanner sc = new Scanner(System.in);
        if (isValid(sc.nextLine())) {
            System.out.println("VALID");
        } else {
            System.out.println("INVALID");
        }
        sc.close();
    }

    /*
     * This is the main logic behind the validity functionality. It first checks the length
     * of the input. It must be greater than 0. If it is 1 is uses isValidCharacter() to validate.
     * Input has to be surrounded by brackets to be valid if not of length 1.
     * Calls findSplitIndex() to determine location of main '&' that splits top level arguments.
     * If there is a valid '&' found, it recursively calls itself with the left and right side
     * arguments (without brackets). Returns true only if all arguments return true.
     */
    private static boolean isValid(String input) {
        int len = input.length();
        if(len != 0) {
            if (len == 1) {
                return isValidCharacter(input.charAt(0));
            }
            if (input.charAt(0) == '(' && input.charAt(len - 1) == ')') {
                int index = findSplitIndex(input, len);
                if (index != -1) {
                    String sub1 = input.substring(1, index);
                    String sub2 = input.substring(index + 1, len - 1);
                    return isValid(sub1) && isValid(sub2);
                }
            }
        }
        return false;
    }

//    Returns true if a character is an R, P, or S.
    private static boolean isValidCharacter(char character) {
        return ("RPS".indexOf(character) != -1);
    }

    /*
     * Returns the index of the main '&' that splits the top level arguments.
     * Uses an integer evenBrackets that increments and decrements when it encounters brackets.
     * The loop goes through each character in the string, ignoring first and last brackets.
     * For each beginning bracket it increments evenBrackets by one and vice versa.
     * Therefore, if a character is not enclosed by brackets, evenBrackets will be 0.
     * It tries to find an '&' that is has no brackets surrounding it.
     * If it finds one, it returns the index of that character.
     * If not, it returns -1.
     */
    private static int findSplitIndex(String input, int len) {
        int evenBrackets = 0;
        for (int x = 1; x < len - 1; x++) {
            switch (input.charAt(x)) {
                case '&':
                    if (evenBrackets == 0) {
                        return x;
                    }
                    break;
                case '(':
                    evenBrackets++;
                    break;
                case ')':
                    evenBrackets--;
            }
        }
        return -1;
    }

}
