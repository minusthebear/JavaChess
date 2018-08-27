package Chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Grid {

    String name;
    private static final int min = 1;
    private static final int max = 8;
    Map<Integer, Map<Integer, Piece>> board = new HashMap<>();
    Map<String, Map<String, Piece[]>> allObjects = new HashMap<>();
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

    public void setAllObjects(Map<String, Map<String, Piece[]>> allObjects) {
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

    public Grid initializeGame(String name) {
        Grid grid = new Grid(name);

        boolean homeTeam;
        int initFrontYPosition;
        int initRearYPosition;
        String color;
        Map allObjects = new HashMap<String, Map<String, Piece[]>>();

        for (int i = 0; i < 2; i++) {
            if (i % 2 == 0) {
                initFrontYPosition = 7;
                initRearYPosition = 8;
                color = "white";
                homeTeam = true;
            } else {
                initFrontYPosition = 2;
                initRearYPosition = 1;
                color = "black";
                homeTeam = false;
            }

            Rook[] rookArray = initializeRooks(grid, homeTeam, initRearYPosition);
            Map pieces = new HashMap<String, Rook[]>();
            pieces.put("rooks", rookArray);
            this.allObjects.put(color, pieces);
        }

        return grid;
    }


    private Rook[] initializeRooks(Grid grid, boolean team, int Y) {
        Rook rookOne = new Rook(1, Y, "Rook", team);
        Rook rookTwo = new Rook(8, Y, "Rook", team);
        Rook[] rooks = new Rook[]{rookOne, rookTwo};

        grid.setStartPosOnGrid(1, Y, rookOne);
        grid.setStartPosOnGrid(8, Y, rookTwo);

        return rooks;
    }



    public void splicePiece(Piece piece) {

        if (piece != null) {
            String color;
            int id = piece.getId();

            if (piece.isWhite()) {
                color = "white";
            } else {
                color = "black";
            }
                    arr = this.allObjects[color][piece];

            for (var i = 0; i < arr.length; i++) {
                if (arr[i].id === id) {
                    arr.splice(i, i + 1);
                }
            }
        }
    }
}
