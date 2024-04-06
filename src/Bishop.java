public class Bishop extends ChessPiece {
    public final static String SYMBOL = "B";

    public Bishop(String color) {
        super(color);
    }

    public String getColor() {
        return color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isWrongMove(line, column, toLine, toColumn)) {
            return false;
        } else {
            return isCorrectDiagonalMove(chessBoard, line, column, toLine, toColumn);
        }
    }

    public String getSymbol() {
        return SYMBOL;
    }
}
