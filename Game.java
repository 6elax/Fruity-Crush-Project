import java.util.*;

public class Game {
    public static void game() {
        Scanner scanner = new Scanner(System.in);
        int[] score = new int[2];

        System.out.println();
        String pName;
        System.out.print("Please enter your name: ");
        pName = scanner.next();
        // create player
        System.out.println();
        String brdSize;
        int brdSizeInt;
        int pointsNeeded;
        System.out.print("What size do you want the board to be? (small/big): ");
        brdSize = scanner.next();
        if (brdSize.toLowerCase().equals("big")) {
            brdSizeInt = 8;
            pointsNeeded = 1000;
        } else {
            brdSizeInt = 5;
            pointsNeeded = 750;
        }
        System.out.println("The size will be " + brdSizeInt + "x" + brdSizeInt + ".");
        // create randomboard
        Board randBrd = new Board(brdSizeInt); // can adjust board size feature later
        GamePlayer player = new GamePlayer(pName, randBrd);

        // start up
        System.out.println();
        System.out.println("Ok lets start " + pName + "!");
        System.out.println();
        System.out.println("In order to win, you must acheive "+ pointsNeeded +" points in 15 turns or under.");
        System.out.println();
        System.out.println(
                "You can ONLY switch fruit positions that are horizontally or vertically next to each other.");
        System.out.println();
        System.out.println("The goal is to get 3 or more in a row alignments as possible");
        System.out.println();
        System.out.println("The longer the alignment, the more points.(For EX. 3 in a row ~ 60 points)");
        System.out.println();
        System.out.println("Here are a list of the fruits available in the game:");
        System.out.println("🍉 🍎 🍑 🍋 🥝 🍇 🥥");
        System.out.println();
        System.out.println("Good Luck!");
        System.out.println();
        
        Display brdDisp = new Display();
        // check for pre-existing connections
        char[] preConnection = randBrd.checkBoard();
        // char[] preConnection = { '1', '1', '1', '1' };
        while (preConnection[0] != '_') {
            // method for fill empty board spaces
            preConnection = randBrd.checkBoard();
            randBrd.activatedFruit();
            randBrd.fillBoard();
            
            preConnection = randBrd.checkBoard();
        }
        // System.out.println('a');
        player.setBoard(randBrd);
        // for loop for displaying board, turns, score adding and current points
        for (int i = 1; i < 16; i++) {
            // print out the board
            System.out.println(); 
            System.out.println("Score: " + player.score()[1] + "\tTurn: " + i);
            brdDisp.displayBoard(player);
            
            // input moves
            randBrd = Moves.move(randBrd, scanner);
            player.setBoard(randBrd);
            char[] connection = randBrd.checkBoard();
            while (connection[0] != '_') {
                // method for fill connections made and giving output of score
                score = player.score();
                connection = randBrd.checkBoard();
                randBrd.activatedFruit();
                randBrd.fillBoard();
                player.setBoard(randBrd);
                if (score[0] > 0){
                    System.out.println();
                    System.out.println("You have earned " + score[0] + " points and have a total of " + score[1] + " out of " + pointsNeeded +" points");
                }
            }
        }
        
        // loop checks for points 
        if (score[1] < pointsNeeded){
            System.out.println("You have earned a total of " + score[1] + " out of " + pointsNeeded +" points");
        }
        if (score[1] >= pointsNeeded){
            System.out.println("Woohoo! You beat the game! Come play again next time!");
            return;
        }
        else{
            System.out.println("DUN Dun dunn... Sorry, you were not able to acheive enough points in 15 turns");
            return;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        // Title Screen
        boolean exit_game = false;

        System.out.println();
        System.out.println("WELCOME TO:");
        System.out.println();
        System.out.println(" 🍎🍎🍎🍎   🍑🍑🍑     🍋     🍋    🥝   🍇🍇🍇🍇   🥥    🥥    ");
        System.out.println(" 🍎         🍑   🍑    🍋     🍋    🥝      🍇       🥥  🥥     ");
        System.out.println(" 🍎🍎🍎     🍑🍑🍑     🍋     🍋    🥝      🍇         🥥       ");
        System.out.println(" 🍎         🍑 🍑      🍋     🍋    🥝      🍇         🥥       ");
        System.out.println(" 🍎         🍑  🍑     🍋     🍋    🥝      🍇         🥥       ");
        System.out.println(" 🍎         🍑   🍑     🍋🍋🍋🍋    🥝      🍇         🥥       ");
        System.out.println();
        System.out.println();
        System.out.println(" [][][]    [][][]      []    []     [][][]     []    []    [] ");
        System.out.println(" []        []   []     []    []    []          []    []    [] ");
        System.out.println(" []        [][][]      []    []     [][]       [][][][]    [] ");
        System.out.println(" []        [] []       []    []       [][]     []    []    [] ");
        System.out.println(" []        []   []     []    []          []    []    []       ");
        System.out.println(" [][][]    []    []    [][][][]     [][][]     []    []    [] ");
        System.out.println();
        System.out.println();

        while (!exit_game) {

            System.out.print("Play game (Y/N): ");
            input = scan.nextLine();
            
            if (input.toLowerCase().equals("y")) {
                game();
            } else if (input.toLowerCase().equals("n")) {
                exit_game = true;
            }
        }
        scan.close();
    }
}

/*
 * You can used this code for the part where you call getBoard and want to print
 * it out.
 * String[][] randbrd = brd.getBoard(); // board is default set to 5x5
 * for (int i = 0; i < size; i++) {
 * for (int j = 0; j < size; j++) {
 * System.out.print(randbrd[i][j] + " ");
 * ;
 * }
 * System.out.println();
 * }
 * 
 */