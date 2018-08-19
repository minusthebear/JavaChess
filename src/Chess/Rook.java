package Chess;

import java.util.Map;

public class Rook extends Piece {

    PieceMoves pieceMoves;
    boolean untouched;

    public Rook (int x, int y, String type, boolean color) {
        super(x, y, type, color);
        this.untouched = true;
    }

    public boolean moveStraight(int x, int y, Grid grid) {

        if (!grid.boundaryCheck(x, y)) {
            return false;
        }

        int yToCheck;
        int oldX = this.position.get("x");
        int oldY = this.position.get("y");;

        if ((oldX != x && oldY != y) || (oldX == x && oldY == y)) {
            return false;
        }

        if (oldX == x && oldY > y) {
            for (int i = oldY - 1; i > y; i--) {
                int loop = loopThru(x, i, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            this.setGrid(grid, x, oldX, y, oldY);
            this.untouched = false;
            return true;
        }

        if (oldX == x && oldY < y) {
            for (int i = oldY + 1; i < y; i++) {
                int loop = loopThru(x, i, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            this.setGrid(grid, x, oldX, y, oldY);
            this.untouched = false;
            return true;
        }

        if (oldY == y && oldX > x) {
            for (int i = oldX - 1; i > x; i--) {
                int loop = loopThru(i, y, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            this.setGrid(grid, x, oldX, y, oldY);
            this.untouched = false;
            return true;
        }

        if (oldY == y && oldX < x) {
            for (int i = oldX + 1; i <= x; i++) {
                int loop = loopThru(i, y, oldX, oldY, grid);

                if (loop == 1) {
                    return true;
                } else if (loop == -1) {
                    return false;
                }
            }
            this.setGrid(grid, x, oldX, y, oldY);
            this.untouched = false;
            return true;
        }
        return false;
    }

    private int loopThru(int x, int y, int oldX, int oldY, Grid grid) {
        Map<Integer, Piece> row = grid.board.get(x);
        Piece piece = row.get(y);

        if (piece != null) {

            Piece oldObj;

            if (this.checkIfOppositeColor(x, y, grid)) {
                oldObj = piece;
                this.setGrid(grid, x, oldX, y, oldY);
                this.untouched = false;

                /* TODO */

                // grid.splicePiece(oldObj);
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }

    public boolean setGrid(Grid grid, int x, int oldX, int y, int oldY) {
        return false;
    }

    public boolean move(int x, int y, Grid grid) {
        return pieceMoves.moveStraight(this, x, y, grid);
    }
//
//    Rook.prototype.setGrid = function(grid, x, oldX, y, oldY) {
//
//        if (!x || !y || !oldX || !oldY) {
//            return false;
//        }
//
//        this.setPosition(x, y);
//        grid.setPiece(x, y, oldX, oldY, this);
//        return true;
//    }

}
