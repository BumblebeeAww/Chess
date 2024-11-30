public class Rook extends ChessPiece {
    public Rook (String color) {
        super(color);
    }

    // инициализируем цвет фигуры
    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    protected boolean isCorrectMove(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка, что фигура не пытается остаться на той же клетке
        if (toLine == line && toColumn == column) {
            return false;
        }

        // если ладья не пытается остаться на той же клетке, то проверим ход действия ладьи
        // ладья может двигаться только по горизонтали или вертикали
        return toLine == line || toColumn == column;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!isCorrectMove(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        // проверим, что путь свободен от других фигур
        if (!isPathClear(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        ChessPiece targetPiece = chessBoard.getPieceAt(toLine, toColumn);
        if (targetPiece != null) {
            // проверим, что фигура на целевой клетке принадлежит противнику
            return !targetPiece.getColor().equals(getColor());
        }

        // если целевая клетка пуста, то фигура передвигается
        return true;
    }

    @Override
    //возвращаем символ фигуры
    public char getSymbol() {
        return 'R';
    }
}
