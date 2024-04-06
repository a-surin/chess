public class Horse extends ChessPiece {
    public final static String SYMBOL = "H";

    public Horse(String color) {
        super(color);
    }

    public String getColor() {
        return color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isWrongMove(line, column, toLine, toColumn)) {
            return false;
        } else if (
            (Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1)
            ||
            (Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2)
        ) {
            boolean result = (chessBoard.board[toLine][toColumn] == null) ? true : 
                (!chessBoard.board[toLine][toColumn].getColor().equals(getColor()));
            return result;
        } else {
            return false;
        }
    }

    public String getSymbol() {
        return SYMBOL;
    }
}
