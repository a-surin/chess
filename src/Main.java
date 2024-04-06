import java.util.Scanner;

public class Main {
    private static final String EXIT_COMMAND = "exit";
    private static final String REPLAY_COMMAND = "replay";
    private static final String MOVE_COMMAND = "move";
    private static final String LONG_CASTLING_COMMAND = "castling0";
    private static final String SHORT_CASTLING_COMMAND = "castling7";
    private static final String COORDINATES_SEPARATOR = " ";

    private static final String PLAY_AGAIN = "Заново";
    private static final String CASTLING_SUCCESSFUL = "Рокировка удалась";
    private static final String CASTLING_FAILED = "Рокировка не удалась";
    private static final String MOVE_SUCCESSFUL = "Успешно передвинулись";
    private static final String MOVE_FAILED = "Передвижение не удалось";
    private static final String WRONG_COMMAND = "Вы что-то ввели не так, попробуйте ещё раз";

    private static final String HOW_TO_PLAY = 
        "Чтобы проверить игру надо вводить команды:\n" +
        "'exit' - для выхода\n" +
        "'replay' - для перезапуска игры\n" +
        "'castling0' или 'castling7' - для рокировки по соответствующей линии\n" +
        "'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3 (шахматная доска - это двумерный массив от 0 до 7)\n" +
        "Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?";

    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard(ChessBoard.WHITE);

        board.board[ChessBoard.WHITE_FIGURES_LINE][ChessBoard.LEFT_ROOK] = new Rook(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_FIGURES_LINE][ChessBoard.LEFT_HORSE] = new Horse(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_FIGURES_LINE][ChessBoard.LEFT_BISHOP] = new Bishop(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_FIGURES_LINE][ChessBoard.QUEEN] = new Queen(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_FIGURES_LINE][ChessBoard.KING] = new King(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_FIGURES_LINE][ChessBoard.RIGHT_BISHOP] = new Bishop(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_FIGURES_LINE][ChessBoard.RIGHT_HORSE] = new Horse(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_FIGURES_LINE][ChessBoard.RIGHT_ROOK] = new Rook(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_PAWNS_LINE][ChessBoard.LEFT_ROOK] = new Pawn(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_PAWNS_LINE][ChessBoard.LEFT_HORSE] = new Pawn(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_PAWNS_LINE][ChessBoard.LEFT_BISHOP] = new Pawn(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_PAWNS_LINE][ChessBoard.QUEEN] = new Pawn(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_PAWNS_LINE][ChessBoard.KING] = new Pawn(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_PAWNS_LINE][ChessBoard.RIGHT_BISHOP] = new Pawn(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_PAWNS_LINE][ChessBoard.RIGHT_HORSE] = new Pawn(ChessBoard.WHITE);
        board.board[ChessBoard.WHITE_PAWNS_LINE][ChessBoard.RIGHT_ROOK] = new Pawn(ChessBoard.WHITE);

        board.board[ChessBoard.BLACK_FIGURES_LINE][ChessBoard.LEFT_ROOK] = new Rook(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_FIGURES_LINE][ChessBoard.LEFT_HORSE] = new Horse(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_FIGURES_LINE][ChessBoard.LEFT_BISHOP] = new Bishop(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_FIGURES_LINE][ChessBoard.QUEEN] = new Queen(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_FIGURES_LINE][ChessBoard.KING] = new King(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_FIGURES_LINE][ChessBoard.RIGHT_BISHOP] = new Bishop(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_FIGURES_LINE][ChessBoard.RIGHT_HORSE] = new Horse(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_FIGURES_LINE][ChessBoard.RIGHT_ROOK] = new Rook(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_PAWNS_LINE][ChessBoard.LEFT_ROOK] = new Pawn(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_PAWNS_LINE][ChessBoard.LEFT_HORSE] = new Pawn(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_PAWNS_LINE][ChessBoard.LEFT_BISHOP] = new Pawn(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_PAWNS_LINE][ChessBoard.QUEEN] = new Pawn(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_PAWNS_LINE][ChessBoard.KING] = new Pawn(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_PAWNS_LINE][ChessBoard.RIGHT_BISHOP] = new Pawn(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_PAWNS_LINE][ChessBoard.RIGHT_HORSE] = new Pawn(ChessBoard.BLACK);
        board.board[ChessBoard.BLACK_PAWNS_LINE][ChessBoard.RIGHT_ROOK] = new Pawn(ChessBoard.BLACK);
        return board;
    }

    public static void main(String[] args) {
        ChessBoard board = buildBoard();

        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println(HOW_TO_PLAY);

            System.out.println();
            board.printBoard();
            while (true) {
                String s = scanner.nextLine();
                if (s.equals(EXIT_COMMAND)) {
                    break;
                } else if (s.equals(REPLAY_COMMAND)) {
                    System.out.println(PLAY_AGAIN);
                    board = buildBoard();
                    board.printBoard();
                } else {
                    if (s.equals(LONG_CASTLING_COMMAND)) {
                        if (board.castling0()) {
                            System.out.println(CASTLING_SUCCESSFUL);
                            board.printBoard();
                        } else {
                            System.out.println(CASTLING_FAILED);
                        }
                    } else if (s.equals(SHORT_CASTLING_COMMAND)) {
                        if (board.castling7()) {
                            System.out.println(CASTLING_SUCCESSFUL);
                            board.printBoard();
                        } else {
                            System.out.println(CASTLING_FAILED);
                        }
                    } else if (s.contains(MOVE_COMMAND)) {
                        execMoveCommand(board, s);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void execMoveCommand(ChessBoard board, String input) {
        final int FROM_LINE   = 1;
        final int FROM_COLUMN = 2;
        final int TO_LINE   = 3;
        final int TO_COLUMN = 4;

        String[] coordinates = input.split(COORDINATES_SEPARATOR);
        try {
            int line = Integer.parseInt(coordinates[FROM_LINE]);
            int column = Integer.parseInt(coordinates[FROM_COLUMN]);
            int toLine = Integer.parseInt(coordinates[TO_LINE]);
            int toColumn = Integer.parseInt(coordinates[TO_COLUMN]);
            if (board.moveToPosition(line, column, toLine, toColumn)) {
                System.out.println(MOVE_SUCCESSFUL);
                board.printBoard();
            } else {
                System.out.println(MOVE_FAILED);
            }
        } catch (Exception e) {
            System.out.println(WRONG_COMMAND);
        }
    }
}