public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    // инициализируем цвет фигуры
    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверяем, что ход движения фигуры корректен - в пределах доски и не остается на своем месте
        if (!isCorrectMove(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        // королева (ферзь) может двигаться по диагонали или по прямой в разных направлениях
        boolean isDiagonalStep = (Math.abs(toLine - line) == Math.abs(toColumn - column));
        boolean isStraightStep = (toLine == line || toColumn == column);

        if (isDiagonalStep || isStraightStep) {
            // проверяем свободен ли путь от других фигур
            if (!isPathClear(chessBoard, line, column, toLine, toColumn)) {
                return false; // если путь не свободен, движение невозможно
            }

            // проверяем,есть ли фигура на целевой клетке
            ChessPiece targetPiece = chessBoard.getPieceAt(toLine, toColumn);
            if (targetPiece != null) {
                // и далее проверяем цвет фигуры и то, что она принадлежит противнику
                return !targetPiece.getColor().equals(getColor());
            }

            // если целевая клетка пуста, то фигура выполнит движение
            return true;
        }

        return false; // если условия движения по диагонали и по прямой не выполняются, то ход королевы невозможен
    }

    @Override
    //возвращаем символ фигуры
    public char getSymbol() {
        return 'Q';
    }
}