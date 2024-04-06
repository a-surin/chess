public class Rook extends ChessPiece {
    public final static String SYMBOL = "R";

    public Rook(String color) {
        super(color);
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isWrongMove(line, column, toLine, toColumn)) {
            return false;
        } else {
            return isCorrectLinearMove(chessBoard, line, column, toLine, toColumn);
        }
    }

    public String getColor() {
        return color;
    }

    public String getSymbol() {
        return SYMBOL;
    }

}
