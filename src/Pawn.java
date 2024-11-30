public class Pawn extends ChessPiece {
    public Pawn(String color){
        super(color);
    }

    // инициализируем цвет фигуры
    @Override
    public String getColor(){
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверяем, что ход движения фигуры корректен - в пределах доски и не остается на своем месте
        if (!isCorrectMove(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        // так как пешка может совершать обычный ход вперед, двойной ход при первоначальном ходе
        // и дигональный захват, то пропишем каждый из ходов
        // обычный ход
        if (toColumn == column) {
            if (Math.abs(toLine - line) == 1) {
                return chessBoard.getPieceAt(toLine, toColumn) == null;
            }
            // двойной ход вперед
            else if (Math.abs(toLine - line) == 2 && line == (getColor().equals("White") ? 1 : 6)) {
                // проверим, что обе клетки впереди свободны
                if (isPathClear(chessBoard, line, column, toLine, toColumn)) {
                    return chessBoard.getPieceAt(toLine, toColumn) == null;
                }
            }
        }

        // реализация захвата по диагонали
        else if (Math.abs(toColumn - column) == 1 && Math.abs(toLine - line) == 1) {
            ChessPiece targetPiece = chessBoard.getPieceAt(toLine, toColumn);
            return targetPiece != null && !targetPiece.getColor().equals(getColor());
        }

        return false; // если условия не выполнены, то движение невозможно
    }

    @Override
    //возвращаем символ фигуры
    public char getSymbol(){
        return 'P';
    }

}



