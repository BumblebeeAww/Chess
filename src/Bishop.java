public class Bishop extends ChessPiece {
    public Bishop (String color) {
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

        // слон может двигаться по диагонали в разном направлении (разница по строкам равна разнице по столбцам)
        if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            // проверяем, свободен ли путь
            if (!isPathClear(chessBoard, line, column, toLine, toColumn)) {
                return false; // движения фигуры не будет, если путь не свободен
            }

            // проверка, есть ли фигура на целевой клетке
            ChessPiece targetPiece = chessBoard.getPieceAt(toLine, toColumn);
            if (targetPiece != null) {
                // проверка, что фигура на целевой клетке принадлежит противнику, то фигура другого цвета
                return !targetPiece.getColor().equals(getColor());
            }

            // если целевая клетка пуста, движение возможно
            return true;
        }

        return false; // движение невозможно, если оно не по диагонали
    }

    @Override
    //возвращаем символ фигуры
    public char getSymbol() {
        return 'B';
    }
}
