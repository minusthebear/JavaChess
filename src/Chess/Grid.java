package Chess;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Grid {

    String name;
    private static int min = 1;
    private static int max = 8;
    Map<Integer, Map<Integer, Piece>> board = new HashMap<>();
    Map<String, ?> allObjects = new HashMap<>();
    Map<String, Integer> boundary = new HashMap<>();

    public Grid(String name) {
        this.name = name;
        this.boundary.put("min", min);
        this.boundary.put("max", max);
        this.boundary = Collections.unmodifiableMap(this.boundary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Map<Integer, Piece>> getBoard() {
        return board;
    }

    public void setBoard(Map<Integer, Map<Integer, Piece>> board) {
        this.board = board;
    }

    public Map<String, ?> getAllObjects() {
        return allObjects;
    }

    public void setAllObjects(Map<String, ?> allObjects) {
        this.allObjects = allObjects;
    }

    public void setStartPosOnGrid(int x, int y, Piece piece) {
        Map <Integer, Piece> spot = (Map<Integer, Piece>) this.board.get(x);
        spot.put(y, piece);
        this.board.put(x, spot);
    }

    public Piece getPiece(boolean color, Piece piece) {

        return piece;
    }

    public void setPiece(int x, int y, int oldX, int oldY, Piece piece) {
        Map <Integer, Piece> spotOne = (Map<Integer, Piece>) this.board.get(oldX);
        Map <Integer, Piece> spotTwo = (Map<Integer, Piece>) this.board.get(x);
        spotOne.put(oldY, null);
        spotTwo.put(y, piece);
        this.board.put(oldX, spotOne);
        this.board.put(x, spotTwo);
    }

    public boolean boundaryCheck(int x, int y) {
        int min = this.boundary.get("min");
        int max = this.boundary.get("max");

        if (min > x || max < x || min > y || max < y) {
            return false;
        }
        return true;
    }

    public static Grid initializeGrid(String name) {
        Grid grid = new Grid(name);

        for (int numX = min; numX <= max; numX++) {
            Map map = new HashMap<Integer, Piece>();

            for (int numY = min; numY <= max; numY++) {
                map.put(numY, null);
            }

            grid.board.put(numX, map);
        }
        return grid;
    }

    public static Grid initializeGame(String name) {
        Grid grid = new Grid(name);

        boolean homeTeam;
        int initFrontYPosition;
        int initRearYPosition;
        String color;
        Map allObjects = new HashMap<String, Piece[]>();

        return grid;
    }
//
//    public void setAllObjects = function(obj) {
//        this.allObjects = obj;
//    }
//
//    Grid.prototype.setTwoStartPosOnGrid = function(one, two) {
//        this.setStartPosOnGrid.apply(this, one);
//        this.setStartPosOnGrid.apply(this, two);
//    }
//
//    Grid.prototype.setStartPosOnGrid = function(x, y, obj) {
//        this.grid[x][y] = obj;
//    }
//
//    Grid.prototype.getSpecificPiece = function(color, piece, id) {
//        if (!color || !piece) {
//            return false;
//        }
//
//        var arr = this.allObjects[color][piece];
//
//        for (var i = 0; i < arr.length; i++) {
//            if (id === arr[i].id) {
//                return arr[i];
//            }
//        }
//    }
//
//    Grid.prototype.setPiece = function(x, y, oldX, oldY, obj) {
//        this.grid[oldX][oldY] = null;
//        this.grid[x][y] = obj;
//    }
//
//    Grid.prototype.splicePiece = function(obj) {
//        // TODO: see if this works
//        var piece = this.getPieceType.call(this, obj);
//
//        if (piece) {
//            var color = obj.white ? 'white' : 'black',
//                    id = obj.id,
//                    arr = this.allObjects[color][piece];
//
//            for (var i = 0; i < arr.length; i++) {
//                if (arr[i].id === id) {
//                    arr.splice(i, i + 1);
//                }
//            }
//        }
//    }

}
