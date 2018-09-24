package Chess;

public class Main {

    public static void main(String[] args) {
        Grid game = initializeGame("Matthew");
        System.out.println(game.allObjects);
        System.out.println(game.board);
    }

    private static Grid initializeGame(String name) {
        Grid grid = new Grid(name);
        grid.initializeGame();
        return grid;
    }
}
