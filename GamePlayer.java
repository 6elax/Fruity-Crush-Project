class GamePlayer{

    private String name;
    private Board board;
    private int score;

    public GamePlayer(String n, Board b){
        name = n;
        board = b;
        score = 0;
    }

    public String getName(){
        return name;
    }

    public String[][] getGamePlayerBoard(){
        return board.getBoard();
    }

    public void setBoard(Board new_board) {
        board = new_board;
    }

    public int getScore() {
        return score;
    }

    public int[] score(){
        //returns the pointsAdded, and the total score.
        //           scoreValues[0]       scoreValues[1]
        int[] scoreValues = new int[2];
        //System.out.println("checkboard: " + board.checkBoard()[3]);

        char len = board.checkBoard()[3];

        int pointsAdded = 0;
        if (len != '_'){ //if there is a connection...
            //adding points to regular connections (3/4/5 in a row)
            pointsAdded = 0;

            if (len == '3'){
                pointsAdded += 60;
            }
            if (len == '4'){
                pointsAdded += 80;
            }
            if (len == '5'){
                pointsAdded += 100;
            }

            //adding points based on activated fruits 
            if (board.getBoard().length == 5){
                if (len == '4'){
                    pointsAdded += 100;
                }
                if (len == '5'){
                    pointsAdded += 250;
                }
            }
            if (board.getBoard().length == 8){
                if (len == '4'){
                    pointsAdded += 160;
                }
                if (len == '5'){
                    pointsAdded += 400;
                }
            }
            score += pointsAdded;
        }

        scoreValues[0] = pointsAdded;
        scoreValues[1] = score;

        return (scoreValues);
    }

    public static void main(String[] args) {
        Board thebrd = new Board(5);
        GamePlayer bob = new GamePlayer("bob", thebrd);
        int[] bob1 = bob.score();
        int thePoints = bob1[0];
        int theScore = bob1[1];
        System.out.println(thePoints + " " + theScore);

        int[] bob2 = bob.score();
        int theNEWPoints = bob2[0];
        int theNEWScore = bob2[1];
        System.out.println(theNEWPoints + " " + theNEWScore);
    }

}