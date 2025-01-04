import java.util.Random;

class Board {
    private String[][] board;

    public Board(int size) {   

        board = new String[size][size];
        String[] fruits = { "🍉", "🍎", "🍑", "🍋", "🥝", "🍇", "🥥" };
        //String[] fruits = {"a", "b", "c", "d", "e", "f"};
        Random rand = new Random();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = fruits[rand.nextInt(fruits.length)];
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public String getFoodString(int row, int col) {
        return board[row][col];
    }

    public void setFood(int row, int col, String new_food) {
        board[row][col] = new_food;
    }
    
    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col] + ' ');
            }
            System.out.println();
        }
    }

    public int checkConnectionHorizontal(int row_target, int col_target) {
        // keep the search length to 5 and under
        // because 5 is max connection length
        int search_length = board.length - col_target;
        if (search_length > 4) {
            search_length = 4;
        }
        int connection_length = 1;
        
        // go through the pieces to the right
        for (int col = col_target + 1; col < col_target + search_length; col++) {
            if (board[row_target][col] == board[row_target][col_target]) {
                connection_length++;
            } else {
                break; // break if there isn't a match
            }
        }
        return connection_length;
    }

    // same thing as checkConnectionHorizontal but vertical
    public int checkConnectionVertical(int row_target, int col_target) {
        int search_length = board.length - row_target;
        if (search_length > 4) {
            search_length = 4;
        }
        int connection_length = 1;
        for (int row = row_target + 1; row < row_target + search_length; row++) {
            if (board[row][col_target] == board[row_target][col_target]) {
                connection_length++;
            } else {
                break;
            }
        }
        return connection_length;
    }

    // Returns the start of the connection, horizontal/vertical, and length
    public char[] checkBoard() {
        int horizontal_result;
        int vertical_result;
        // [0]: row-position of connection
        // [1]: col-position of connection
        // [2]: horizontal or vertical connection
        // [3]: length of connection
        char[] output = new char[]{'_', '_', '_', '_'};
        // start checks from 3rd row from the top
        // can't have matches starting on the 1st or 2nd rows
        int row = 0;
        int col = 0;
        boolean finished_search = false;
        
        while (row < board.length && !finished_search) {
            col = 0;
            while (col < board.length && !finished_search) {
                horizontal_result = checkConnectionHorizontal(row, col);
                vertical_result = checkConnectionVertical(row, col);
                if (horizontal_result > 2) {
                    finished_search = true;

                    output[0] = (char)(row + 48);
                    output[1] = (char)(col + 48);
                    output[2] = 'h';
                    output[3] = (char)(horizontal_result + 48);
                } else if (vertical_result > 2) {
                    finished_search = true;

                    output[0] = (char)(row + 48);
                    output[1] = (char)(col + 48);
                    output[2] = 'v';
                    output[3] = (char)(vertical_result + 48);
                }
                col++;
            }
            row++;
        }
        return output;
    }

    public void activatedFruit() {
        char[] connections = this.checkBoard();
        char r_char = connections[0];
        int r_int = r_char - '0';
        char c_char = connections[1];
        int c_int = c_char - '0';

        // clear main connection
        if (connections[2] == 'v') {
            for (int row = r_int; row < r_int + (int)connections[3] - 48; row++) {
                board[row][c_int] = "⬜";
            }
        } else if (connections[2] == 'h') {
            for (int col = c_int; col < c_int + (int)connections[3] - 48; col++) {
                board[r_int][col] = "⬜";
            }
        }
        
        // special activations for connections of length 4 and 5
        if (connections[3] == '4'){ //clears the entire row/column of the middle of the fruits connected
            if (connections[2] == 'v'){
                for (int x = 0; x<board.length; x++){
                    board[x][c_int] = "⬜"; //clearing the r,c value for now, will add a fillboard later
                }
            }
            if (connections[2] == 'h'){
                for (int x = 0; x<board.length; x++){
                    board[r_int][x] = "⬜"; //clearing the r,c value for now, will add a fillboard later
                }
            }
        }
        if (connections[3] == '5'){ //clears the entire row/column of the fruits connected
            if (connections[2] == 'v'){
                for (int y = 0; y<board.length; y++){
                    for (int x = 0; x<board.length; x++){
                        board[x][c_int] = "⬜"; //clearing the r,c value for now, will add a fillboard later
                    }
                }
            }
            if (connections[2] == 'h'){
                for (int y = 0; y<5; y++){
                    for (int x = 0; x<board.length; x++){ 
                        board[r_int+y][x] = "⬜"; //clearing the r,c value for now, will add a fillboard later
                    }
                }
            }
        }
    }

    // replaces blank spaces with a random fruit
    public void fillBoard(){
        // fruits to replace blanks with
        String[] fruits = { "🍉", "🍎", "🍑", "🍋", "🥝", "🍇", "🥥" };
        //String[] fruits = {"a", "b", "c", "d", "e", "f"};
        Random rand = new Random();
        // check for connections
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[i][j] == "⬜") {
                    board[i][j] = fruits[rand.nextInt(fruits.length)];
                }
            }
        }
    }
    
    // Test / Calling
    public static void main(String[] args) {
        Board brd = new Board(5);
        String[][] randbrd = brd.getBoard();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(randbrd[i][j] + " ");
                ;
            }
            System.out.println();
        }
        System.out.println();

        
        /*brd.activatedFruit();
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(randbrd[i][j] + " ");
                ;
            }
            System.out.println();
        }*/
    }
}
