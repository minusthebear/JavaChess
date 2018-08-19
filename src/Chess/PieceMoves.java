package Chess;

import java.util.Map;

public class PieceMoves {

    public boolean moveStraight(Piece piece, int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        int yToCheck;
        int oldX = piece.position.get("x");
        int oldY = piece.position.get("y");;

        if ((oldX != x && oldY != y) || (oldX == x && oldY == y)) {
            return false;
        }

        if (oldX == x && oldY > y) {
            for (int i = oldY - 1; i > y; i--) {
                int loop = loopThru(piece, x, i, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            piece.setGrid(grid, x, oldX, y, oldY);
            piece.untouched = false;
            return true;
        }

        if (oldX == x && oldY < y) {
            for (int i = oldY + 1; i < y; i++) {
                int loop = loopThru(piece, x, i, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            piece.setGrid(grid, x, oldX, y, oldY);
            piece.untouched = false;
            return true;
        }

        if (oldY == y && oldX > x) {
            for (int i = oldX - 1; i > x; i--) {
                int loop = loopThru(piece, i, y, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            piece.setGrid(grid, x, oldX, y, oldY);
            piece.untouched = false;
            return true;
        }

        if (oldY == y && oldX < x) {
            for (int i = oldX + 1; i <= x; i++) {
                int loop = loopThru(piece, i, y, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            piece.setGrid(grid, x, oldX, y, oldY);
            piece.untouched = false;
            return true;
        }
        return false;
    }

    private int loopThru(Piece piece, int x, int y, int oldX, int oldY, Grid grid) {
        Map<Integer, Piece> row = grid.board.get(x);
        Piece otherPiece = row.get(y);

        if (otherPiece != null) {

            Piece oldObj;

            if (piece.checkIfOppositeColor(x, y, grid)) {
                oldObj = otherPiece;
                piece.setGrid(grid, x, oldX, y, oldY);
                piece.untouched = false;

                /* TODO */

                // grid.splicePiece(oldObj);
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }
}
