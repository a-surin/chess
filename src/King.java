public class King extends ChessPiece {
    public static final String SYMBOL = "K";

    public King(String color) {
        super(color);
    }

    public String getColor() {
        return color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isWrongMove(line, column, toLine, toColumn)) {
            return false;
        } else {
            int distance = Math.max(Math.abs(toLine - line), Math.abs(toColumn - column));

            if(isCorrectDiagonalMove(chessBoard, line, column, toLine, toColumn)) {
                return ((distance == 1) && !isUnderAttack(chessBoard, toLine, toColumn));
            } else if(isCorrectLinearMove(chessBoard, line, column, toLine, toColumn)) {
                return ((distance == 1) && !isUnderAttack(chessBoard, toLine, toColumn));
            } else {
                return false;
            }
        }
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        return false;
    }

    public String getSymbol() {
        return SYMBOL;
    }
}

