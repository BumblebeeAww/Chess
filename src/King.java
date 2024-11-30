public class King extends ChessPiece {
    public King(String color) {
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

        int stepLine = Math.abs(toLine - line);
        int stepColumn = Math.abs(toColumn - column);

        // проверяем условие, что король может двигаться только на одну клетку в любом направлении
        if (stepLine <= 1 && stepColumn <= 1) {
            // проверяем свободен ли путь от других фигур
            ChessPiece targetPiece = chessBoard.getPieceAt(toLine, toColumn);
            if (targetPiece != null) {
                // проверяем цвет фигуры на целевой клетке, чтобы понять принадлжеит ли она противнику
                return !targetPiece.getColor().equals(getColor());
            }
            // целевая клетка пуста, то движение возможно
            return true;
        }

        return false; // если движение не соответствует правилам, то оно невозможно
    }

    @Override
    //возвращаем символ фигуры
    public char getSymbol() {
        return 'K';
    }

    // реализуя метод isUnderAttack, проверим, находится ли поле, на котором стоит король (или куда собирается пойти)
    // под атакой (используем два вложенных циклах for, проверяя все клетки шахматной доски)
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
       for (int i = 0; i < chessBoard.getSize(); i++) {
            for (int j = 0; j < chessBoard.getSize(); j++) {
                ChessPiece piece = chessBoard.getPieceAt(i, j);
                    if (piece != null && !piece.getColor().equals(this.getColor())) {
                    // проверяем, что клетка не пуста, а также цвет фигуры на этой клетке, чтобы понять
                    // угрожает ли она нашему королю
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false; // если фигура не может атаковать, то возвращаем false
    }
}


