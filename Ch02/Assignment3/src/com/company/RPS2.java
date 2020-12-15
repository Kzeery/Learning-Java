package com.company;
import java.util.Scanner;
// Assignment 2 class
public class RPS2 {
//    Main function that starts program
    public static void main(String[] args) {
        winner();
    }

//    Basic function that opens scanner, and prints the winner using private function winner().
    public static void winner() {
        Scanner sc = new Scanner(System.in);
        System.out.println(winner(sc.nextLine()));
        sc.close();
    }

     /*
     * Assuming the input is valid, a length of 1 indicates a winner of that character.
     * If the length is not 1, it finds the index of the top level '&' using findSplitIndex().
     * Using the split index, it divides the main input into two substrings.
     * It then recursively calls itself to find the winner of each substring.
     * Lastly, it uses the winnerChar() function to find the winner between the two characters
     */
    private static char winner(String input) {
        int len = input.length();
        if (len != 1) {
            int index = findSplitIndex(input, len);
            String sub1 = input.substring(1, index);
            String sub2 = input.substring(index + 1, len - 1);
            return winnerChar(winner(sub1), winner(sub2));
        }
        return input.charAt(0);
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
                case '(':
                    evenBrackets++;
                    break;
                case ')':
                    evenBrackets--;
                    break;
                case '&':
                    if (evenBrackets == 0) {
                        return x;
                    }
            }
        }
        return -1;
    }

    /*
     * This is the logic for determining whether rock, paper, or scissors wins in each situation.
     * By definition, the winner between X and X is X.
     * Uses indexOf to determine which scenario is occurring. 'RP' is Rock Paper.
     * Returns the winner between characters.
     */
    private static char winnerChar(char a, char b) {
        if (a == b) {
            return a;
        }
        if ("RP".indexOf(a) != -1 && "RP".indexOf(b) != -1) {
            return 'P';
        }
        if ("RS".indexOf(a) != -1 && "RS".indexOf(b) != -1) {
            return 'R';
        }
        return 'S';
    }

}
