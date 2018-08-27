package Chess;

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

        int oldX = this.position.get("x")
        int oldY = this.position.get("y");

        if (this.checkIfInCheck(x, y, grid)) {
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
        this.setGridStraight(grid, x, oldX, y, oldY);
        this.untouched = false;
        return true;
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

        if (this.checkIfInCheck(x, y, grid)) {
            return false;
        }

        if (moveDiagonalCheck(x, y)) {
            return king.checkPiece.apply(king, [grid, x, oldX, y, oldY]);
        }
        return false;
    };


    private boolean moveDiagonalCheck(int x, int yk) {

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

}
