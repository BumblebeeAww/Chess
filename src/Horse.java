public class Horse extends ChessPiece {
    public Horse(String color) {
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

        // проверяем, что клетка, на которую хочет переместиться фигура, пуста
        ChessPiece targetPiece = chessBoard.getPieceAt(toLine, toColumn);
        if (targetPiece == null) {
            return true; // если клетка пуста null, то движение возможно
        }

        // проверяем цвет фигуры на целевой клетке, принадлежит ли она противнику
        return !targetPiece.getColor().equals(getColor());
    }

    private boolean isCorrectMove(int line, int column, int toLine, int toColumn) {
        if (toLine == line && toColumn == column) {
            return false; // нельзя оставаться на клетке, на которой стоит фигура
        }

        int stepLine = Math.abs(toLine - line);
        int stepColumn = Math.abs(toColumn - column);

        // конь может ходить буквой "Г", то есть на 2 строки и 1 стобец в обе стороны
        // поэтому проверяем, соответствует ли движение правилам коня
        return (stepLine == 2 && stepColumn == 1) || (stepColumn == 2 && stepLine == 1);
    }

    @Override
    //возвращаем символ фигуры
    public char getSymbol() {
        return 'H';
    }
}
