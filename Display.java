class Display {

    /* method that clears the screen */
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /* method that allows certain text/strings to change color */
    public String changeColor(String text, String color){
        String colorText = "\u001B[0m";
        if (color == "red"){
            colorText = "\u001B[31m";
        }
        else if (color == "yellow"){
            colorText = "\u001B[33m";
        }
        else if (color == "green"){
            colorText = "\u001B[32m";
        }
        else if (color == "blue"){
            colorText = "\u001B[34m";
        }
        else if (color == "purple"){
            colorText = "\u001B[35m";
        }

        return(colorText + text + "\u001B[0m");
    }

    /* displays the board */
    public void displayBoard(GamePlayer b){

        String[][] board = b.getGamePlayerBoard();

        if (board.length == 5){
            System.out.println("  0  1  2  3  4 ");
        }
        else if (board.length == 8){
            System.out.println("  0  1  2  3  4  5  6  7");
        }

        for (int r = 0; r < board.length; r++) {
            System.out.print(r);
            for (int c = 0; c < board[r].length; c++) {
                System.out.print(" " + board[r][c] );
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Board brd = new Board(5);
        GamePlayer bob = new GamePlayer("bob", brd);
        Display ree = new Display();
        ree.displayBoard(bob);
        Board abrd = new Board(8);
        GamePlayer abob = new GamePlayer("abob", abrd);
        Display aree = new Display();
        aree.displayBoard(abob);
    }

}
