package Chess;

import java.util.HashMap;
import java.util.Map;

public class King extends Piece {
    PieceMoves pieceMoves;
    private boolean untouched;

    public King (int x, int y, String type, boolean color) {
        super(x, y, type, color);
        this.untouched = true;
    }

    public boolean setGrid(Grid grid, int x, int oldX, int y, int oldY) {
        return false;
    }

    public boolean move(int x, int y, Grid grid) {
        return false;
    }

    public boolean moveStraight(int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");

        if (checkIfInCheck(x, y, grid)) {
            return false;
        }

        if (!moveStraightCheck(x, y)) {
            return false;
        }

        Map<Integer, Piece> row = grid.board.get(x);
        Piece piece = row.get(y);

        if (piece != null) {

            Piece oldObj = piece;

            if (this.checkIfOppositeColor(x, y, grid)) {
                grid.splicePiece(oldObj);
            }
            return false;
        }
        this.setGridStraight(grid, x, y);
        this.untouched = false;
        return true;
    };

    private void setGridStraight(Grid grid, int x, int y) {
        int oldX = this.position.get("x");
        int oldY = this.position.get("y");

        this.setPosition(x, y);
        // grid.setPiece(x, y, oldX, oldY);
    };

    private boolean moveStraightCheck(int x, int y) {

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");

        if ((oldX != x && oldY != y) || (oldX == x && oldY == y)) {
            return false;
        }

        if (oldX != x && Math.abs(oldX - x) != 1) {
            return false;
        }

        if (oldY != y && Math.abs(oldY - y) != 1) {
            return false;
        }
        return true;
    }


    private boolean moveDiagonal(int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        if (checkIfInCheck(x, y, grid)) {
            return false;
        }

        if (moveDiagonalCheck(x, y)) {
            return checkPiece(grid, x, y);
        }
        return false;
    };


    private boolean moveDiagonalCheck(int x, int y) {

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");
        int xAbs = Math.abs(oldX - x);
        int yAbs = Math.abs(oldY - y);

        if (oldY == y || oldX == x) {
            return false;
        }

        if (xAbs != yAbs || xAbs != 1) {
            return false;
        }

        return true;
    }

    private void setGridDiagonal(int x, int y, Grid grid) {

        int oldNumX = this.position.get("x");
        int oldNumY = this.position.get("y");

        if (oldNumX != x && oldNumY != y) {
            setPosition(x, y);
            grid.setPiece(x, y, oldNumX, oldNumY, this );
        }
    }


    private boolean checkPiece(Grid grid, int numX, int numY) {
        Map<Integer, Piece> row = grid.board.get(numX);
        Piece otherPiece = row.get(numY);

        if (this.checkIfOppositeColor(numX, numY, grid)) {
            grid.splicePiece(otherPiece);
            setGridDiagonal(numX, numY, grid);
            return true;
        }
        return false;
    };

    private boolean straightLineCheck(int x, int y, int pos, Grid grid) {
        Map<Integer, Piece> row = grid.board.get(x);
        Piece otherPiece = row.get(y);

        boolean isWhite = this.isWhite();

        if (otherPiece != null) {
            if (otherPiece.isWhite() == isWhite) {
                return false;
            }

            if (pos == 1 && otherPiece.getType() == "King") {
                return true;
            } else if (otherPiece.getType() == "Queen" || otherPiece.getType() == "Rook") {
                return true;
            }
        }
        return false;
    }

    private boolean diagonalLineCheck(int x, int y, int pos, Grid grid) {
        Map<Integer, Piece> row = grid.board.get(x);
        Piece otherPiece = row.get(y);

        boolean isWhite = this.isWhite();

        if (otherPiece != null) {
            if (otherPiece.isWhite() == isWhite) {
                return false;
            }

            if (pos == 1 && (otherPiece.getType() == "King" || otherPiece.getType() == "Pawn")) {
                return true;
            } else if (otherPiece.getType() == "Queen" || otherPiece.getType() == "Bishop") {
                return true;
            }
        }
        return false;
    }

    private boolean allKnightChecks(int x, int y, Grid grid) {
        return (knightCheck(x - 2, y - 1, grid) || knightCheck(x + 2, y - 1, grid) ||
                knightCheck(x - 2, y + 1, grid) || knightCheck(x + 2, y + 1, grid) ||
                knightCheck(x - 1, y - 2, grid) || knightCheck(x + 1, y - 2, grid) ||
                knightCheck(x - 1, y + 2, grid) || knightCheck(x + 1, y + 2, grid));
    }

    private boolean knightCheck(int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        Map<Integer, Piece> row = grid.board.get(x);
        Piece otherPiece = row.get(y);

        boolean isWhite = this.isWhite();

        if (otherPiece != null) {
            if (otherPiece.isWhite() == isWhite) {
                return false;
            }

            if (otherPiece.getType() == "Knight") {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfInCheck(int x, int y, Grid grid) {

        int oldX = this.position.get("x");
        int oldY = this.position.get("y");
        int min = grid.boundary.get("min");
        int max = grid.boundary.get("max");

        // Check horizontally, x value descending
        for (int i = x - 1; i >= min; i--) {
            if (straightLineCheck(i, y, (x - i), grid)) {
                return true;
            }
        }

        // Check horizontally, x value ascending
        for (int i = x + 1; i <= max; i++) {
            if (straightLineCheck(i, y, (i - x), grid)) {
                return true;
            }
        }

        // Check verticaly, y value descending
        for (int i = y - 1; i >= min; i--) {
            if (straightLineCheck(x, i, (y - i), grid)) {
                return true;
            }
        }

        // Check verticaly, y value ascending
        for (int i = y + 1; i <= max; i++) {
            if (straightLineCheck(x, i, (i - y), grid)) {
                return true;
            }
        }

        // Check diagonally, x and y value descending
        for (int i = x - 1; i >= min; i--) {
            for (int j = y - 1; j >= min; j--) {
                if ((x - i) == (y - j) && diagonalLineCheck(i, j, (x - i), grid)) {
                    return true;
                }
            }
        }

        // Check diagonally, x ascending and y value descending
        for (int i = x + 1; i <= max; i++) {
            for (int j = y - 1; j >= min; j--) {
                if ((i - x) == (y - j) && diagonalLineCheck(i, j, (i - x), grid)) {
                    return true;
                }
            }
        }

        // Check diagonally, x descending and y value ascending
        for (int i = x - 1; i >= min; i--) {
            for (int j = y + 1; j <= max; j++) {
                if ((x - i) == (j - y) && diagonalLineCheck(i, j, (x - i), grid)) {
                    return true;
                }
            }
        }

        // Check diagonally, x ascending and y value ascending
        for (int i = x + 1; i <= min; i++) {
            for (int j = y + 1; j <= max; j++) {
                if ((i - x) == (j - y) && diagonalLineCheck(i, j, (i - x), grid)) {
                    return true;
                }
            }
        }

        // Check for a knight
        if (allKnightChecks(x, y, grid)) {
            return true;
        }

        return false;
    }

    public boolean castle(Rook rook, Grid grid) {

        if (rook == null || this.isWhite() != rook.isWhite() ||
            this.isUntouched() || rook.isUntouched())
        {
            return false;
        }

        int oldKingPos = this.position.get("x");
        int oldRookPos = rook.position.get("x");
        int posY = this.position.get("y");

        Map<String, Integer> row = new HashMap<>();
        Piece otherPiece;
        Map <Piece, Integer> posX = new HashMap<>();
//
//        if (posY != rook.position.get("y")) {
//            return false;
//        }

        if (oldRookPos > oldKingPos) {
            row.set("max", oldRookPos);
            row.set("min", oldKingPos);




            row = { max: oldRookPos, min: oldKingPos };
            posX = { king: oldKingPos + 2, rook: oldRookPos - 2 };
        } else {
            row = { max: oldKingPos, min: oldRookPos };
            posX = { king: oldKingPos - 2, rook: oldRookPos + 3 };
        }

        for (var i = row.min; i < row.max; i++) {
            if (king.checkIfInCheck(i, posY, grid)) {
                return false;
            }
        }

        // Check chess rules to see if values for min and max are correct
        for (var i = row.min + 1; i < row.max; i++) {
            if (board[i][posY]) {
                return false;
            }
        }

        king.setPosition(posX.king, posY);
        grid.setPiece(posX.king, posY, oldKingPos, posY, king);
        rook.setPosition(posX.rook, posY);
        grid.setPiece(posX.rook, posY, oldRookPos, posY, rook);
        return true;
    }


}
