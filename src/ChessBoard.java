public class ChessBoard {
    public static final int BOARD_SIZE = 8;
    public static final String EMPTY_CELL = "..";

    // figure lines:
    public static final int WHITE_FIGURES_LINE = 0;
    public static final int WHITE_PAWNS_LINE = 1;
    public static final int BLACK_FIGURES_LINE = 7;
    public static final int BLACK_PAWNS_LINE = 6;

    // figure columns:
    public static final int LEFT_ROOK = 0;
    public static final int LEFT_HORSE = 1;
    public static final int LEFT_BISHOP = 2;
    public static final int QUEEN = 3;
    public static final int KING = 4;
    public static final int RIGHT_BISHOP = 5;
    public static final int RIGHT_HORSE = 6;
    public static final int RIGHT_ROOK = 7;

    // player colors:
    public static final String WHITE = "White";
    public static final String BLACK = "Black";

    public ChessPiece[][] board = new ChessPiece[BOARD_SIZE][BOARD_SIZE]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn) && nowPlayer.equals(board[startLine][startColumn].getColor())) {
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {

                if ( // check position for castling
                    board[startLine][startColumn].getSymbol().equals(King.SYMBOL) 
                    ||
                    board[startLine][startColumn].getSymbol().equals(Rook.SYMBOL)
                ) {
                    board[startLine][startColumn].check = false;
                }

                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals(ChessBoard.WHITE) ? ChessBoard.BLACK : ChessBoard.WHITE;

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2("+ BLACK +")");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = BLACK_FIGURES_LINE; i >= WHITE_FIGURES_LINE; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == null) {
                    System.out.print(EMPTY_CELL + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1("+ WHITE +")");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        return longCastling();
    }

    public boolean castling7() {
        return shortCastling();
    }

    private boolean longCastlingAvailable() {
        int line = (nowPlayer.equals(BLACK)) ? BLACK_FIGURES_LINE : WHITE_FIGURES_LINE;
        return (
            board[line][KING - 1]      == null && 
            board[line][LEFT_ROOK + 1] == null && 
            board[line][LEFT_ROOK + 2] == null
        );
    }

    private boolean longCastling() {
        int line = (nowPlayer.equals(BLACK)) ? BLACK_FIGURES_LINE : WHITE_FIGURES_LINE;

        if (board[line][LEFT_ROOK] == null || board[line][KING] == null) {
            return false;
        }

        if (
            board[line][LEFT_ROOK].getSymbol().equals(Rook.SYMBOL) && 
            board[line][KING].getSymbol().equals(King.SYMBOL) && // check that King and Rook
            longCastlingAvailable()
        ) { // never moved
            if (
                board[line][LEFT_ROOK].getColor().equals(nowPlayer) && 
                board[line][KING].getColor().equals(nowPlayer) &&
                board[line][LEFT_ROOK].check && 
                board[line][KING].check &&
                !new King(nowPlayer).isUnderAttack(this, line, KING - 2) // check that position not is under attack
            ) { 
                board[line][KING] = null;
                board[line][KING - 2] = new King(nowPlayer);   // move King
                board[line][KING - 2].check = false;
                board[line][LEFT_ROOK] = null;
                board[line][KING - 1] = new Rook(nowPlayer);   // move Rook
                board[line][KING - 1].check = false;
                nowPlayer = (nowPlayer.equals(BLACK)) ? WHITE : BLACK;  // next turn
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean shortCastlingAvailable() {
        int line = (nowPlayer.equals(BLACK)) ? BLACK_FIGURES_LINE : WHITE_FIGURES_LINE;
        return (board[line][KING + 1] == null && board[line][RIGHT_ROOK - 1] == null);
    }

    private boolean shortCastling() {
        int line = (nowPlayer.equals(BLACK)) ? BLACK_FIGURES_LINE : WHITE_FIGURES_LINE;

        if (board[line][RIGHT_ROOK] == null || board[line][KING] == null) {
            return false;
        }

        if (
            board[line][RIGHT_ROOK].getSymbol().equals(Rook.SYMBOL) && 
            board[line][KING].getSymbol().equals(King.SYMBOL) && // check that King and Rook
            shortCastlingAvailable()
        ) { // never moved
            if (
                board[line][RIGHT_ROOK].getColor().equals(nowPlayer) && 
                board[line][KING].getColor().equals(nowPlayer) &&
                board[line][RIGHT_ROOK].check && 
                board[line][KING].check &&
                !new King(nowPlayer).isUnderAttack(this, line, RIGHT_ROOK - 1)
            ) { // check that position not in under attack
                board[line][KING] = null;
                board[line][RIGHT_ROOK-1] = new King(nowPlayer);   // move King
                board[line][RIGHT_ROOK-1].check = false;
                board[line][RIGHT_ROOK] = null;
                board[line][RIGHT_ROOK-2] = new Rook(nowPlayer);   // move Rook
                board[line][RIGHT_ROOK-2].check = false;
                nowPlayer = (nowPlayer.equals(BLACK)) ? WHITE : BLACK;  // next turn
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
