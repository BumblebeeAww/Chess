public abstract class ChessPiece {
    public String color; //цвет фигуры
    public boolean check = true;//для отслеживания возможности рокировки

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);
    // метод, который используется в подклассахдля проверки допустимости перемещения

    protected boolean isCorrectMove (ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка на корректность перемещения: позиция фигуры не находится за пределами доски
        // и фигура не остается на своем месте
        if (toLine < 0 || toLine >= chessBoard.getSize() || toColumn < 0 || toColumn >= chessBoard.getSize()) {
            return false;
        }

        if (toLine == line && toColumn == column) {
            return false;
        }

        return isPathClear(chessBoard, line, column, toLine, toColumn);
    }

    protected boolean isPathClear (ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка, что путь (по горизонтали, вертикали и диагонали) свободен и на нем нет других фигур
        if (line == toLine) {
                // движение по горизонтали
            int minColumn = Math.min(column, toColumn);
            int maxColumn = Math.max(column, toColumn);
            for (int col = minColumn + 1; col < maxColumn; col++) {
                   if (chessBoard.getPieceAt(line, col) != null) {
                       return false; // есть фигура на пути
                   }
            }

        } else if (column == toColumn) {
                // движение по вертикали
            int minLine = Math.min(line, toLine);
            int maxLine = Math.max(line, toLine);
            for (int l = minLine + 1; l < maxLine; l++) {
                if (chessBoard.getPieceAt(l, column) != null) {
                        return false; // есть фигура на пути
                }
            }
        } else if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            // движение по диагонали
            int deltaLine = (toLine > line) ? 1 : -1; // смотрим направление по строкам
            int deltaColumn = (toColumn > column) ? 1 : -1; // смотрим направление по столбцам
            int currentLine = line + deltaLine;
            int currentColumn = column + deltaColumn;

            while (currentLine != toLine && currentColumn != toColumn) {
                if (chessBoard.getPieceAt(currentLine, currentColumn) != null) {
                    return false; // есть фигура на пути
                }
                currentLine += deltaLine;
                currentColumn += deltaColumn;
            }
        }
        return true; // всё хорошо, путь свободен
    }

    public abstract char getSymbol();
    // метод, определяющий получение символа фигуры, который реализуется в подклассах
}








