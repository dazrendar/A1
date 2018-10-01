import java.util.ArrayList;

public class Tile {
    private ArrayList<Action> containedWalls;
    private String name;
    public Tile(String name, ArrayList<Action> walls) {
        this.name = name;
        this.containedWalls = walls;
    }
}
