public abstract class ChessPiece {
    String color;
    boolean check = true;
    boolean isWhite;
    boolean isBlack;

    public ChessPiece(String color) {
        this.color = color;
        this.isWhite = color.equals(ChessBoard.WHITE);
        this.isBlack = !this.isWhite;
    }

    public abstract String getColor();
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);
    public abstract String getSymbol();

    public boolean isWrongMove(int line, int column, int toLine, int toColumn) {
        if (
            (line == toLine && column == toColumn)
            ||
            (toLine < 0 || toLine > (ChessBoard.BOARD_SIZE - 1) || toColumn < 0 || toColumn > (ChessBoard.BOARD_SIZE - 1))
        ) {
            return true;
        } else {
            return false;
        }
    }

    boolean isCorrectDiagonalMove(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int lineDiff = toLine - line;
        int columnDiff = toColumn - column;

        if (Math.abs(lineDiff) == Math.abs(columnDiff)) { // diagonal move
            int dl = lineDiff / Math.abs(lineDiff);
            int dc = columnDiff / Math.abs(columnDiff);
            int distance = Math.abs(lineDiff);

            for (int i = 1; i <= distance; i++) {
                if (chessBoard.board[line + i * dl][column + i * dc] != null) {
                    boolean result = (i < distance) ? false : 
                        (!chessBoard.board[line + i * dl][column + i * dc].getColor().equals(getColor()));
                    return result;
                }
            }
            return true;
        } else {
            return false;
        }       
    }

    boolean isCorrectLinearMove(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int lineDiff = toLine - line;
        int columnDiff = toColumn - column;

        if (Math.abs(lineDiff * columnDiff) > 0) {
            return false;
        } else {
            int dl = (lineDiff == 0)   ? 0 : lineDiff / Math.abs(lineDiff);
            int dc = (columnDiff == 0) ? 0 : columnDiff / Math.abs(columnDiff);
            int distance = Math.max(Math.abs(lineDiff), Math.abs(columnDiff));

            for (int i = 1; i <= distance; i++) {
                if (chessBoard.board[line + i * dl][column + i * dc] != null) {
                    boolean result = (i < distance) ? false :
                        (!chessBoard.board[line + i * dl][column + i * dc].getColor().equals(getColor()));
                    return result;
                }
            }
        }
        return true;
    }
}
