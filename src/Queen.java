public class Queen extends ChessPiece {
    public static final String SYMBOL = "Q";

    public Queen(String color) {
        super(color);
    }

    public String getColor() {
        return color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isWrongMove(line, column, toLine, toColumn)) {
            return false;
        } else {
            if(isCorrectDiagonalMove(chessBoard, line, column, toLine, toColumn)) {
                return true;
            } else if(isCorrectLinearMove(chessBoard, line, column, toLine, toColumn)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String getSymbol() {
        return SYMBOL;
    }


}
