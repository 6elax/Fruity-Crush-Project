import java.util.Scanner;
import java.lang.Math;

public class Moves {
    private static boolean validateInput(int input, int board_size) {
        return (input >= 0 && input <= board_size);
    }

    public static Board move(Board board, Scanner scan) {
        boolean valid_input = false;
        int[] inputs = new int[4];
        int row_diff;
        int col_diff;
        int i;

        while (!valid_input) {
            // get inputs
            System.out.println("Target row: ");
            inputs[0] = scan.nextInt(); // gets the first character inputed
            System.out.println("Target column: ");
            inputs[1] = scan.nextInt();
            System.out.println("Swap row: ");
            inputs[2] = scan.nextInt();
            System.out.println("Swap column: ");
            inputs[3] = scan.nextInt();

            // go through each of the inputs and check that they are valid coordinates
            i = 0;
            while (i < inputs.length) {
                valid_input = validateInput(inputs[i], board.getBoard().length);
                // if one of the inputs aren't valid then exit this for loop and return to the while loop
                if (!valid_input) {
                    System.out.println("Invalid input!");
                    System.out.println();
                    valid_input = false;
                    break;
                }
                i++;
            }

            // check to make sure that the swap location is exactly adjecent from the target location
            row_diff = Math.abs(inputs[0] - inputs[2]);
            col_diff = Math.abs(inputs[1] - inputs[3]);
            if (row_diff + col_diff != 1) {
                System.out.println("Invalid input!");
                valid_input = false;
            }
        }

        // convert all the characters in the array into ints to be easier to work with

        // a temporary variable to hold the target food
        String temp = board.getFoodString(inputs[0], inputs[1]);
        // set the food at the target location to the one at the swap location
        board.setFood(inputs[0], inputs[1], board.getFoodString(inputs[2], inputs[3]));
        // set the food at the swap location to the one at the target location
        board.setFood(inputs[2], inputs[3], temp);
        
        return board;
    }
}  