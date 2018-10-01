import java.util.ArrayList;

public class Main {

    public enum action {
        LEFT, RIGHT, UP, DOWN
    }
    public static void main(String[] args) {
        ArrayList<action> actions = new ArrayList<action>();

        // Set Up Board (default settings of 1/9 for each belief state, except terminal states):
        Tile AA = new Tile(1,1,(float) 1/9);
        Tile BA = new Tile(2,1,(float) 1/9);
        Tile CA = new Tile(3,1,(float) 1/9);
        Tile DA = new Tile(4,1,(float) 1/9);

        Tile AB = new Tile(1,2,(float) 1/9);
        // Tile BB = new Tile(2,2,1/9);  // Not a state
        Tile CB = new Tile(3,2,(float) 1/9);
        Tile DB = new Tile(4,2,0); // Terminal State

        Tile AC = new Tile(1,3,(float) 1/9);
        Tile BC = new Tile(2,3,(float) 1/9);
        Tile CC = new Tile(3,3,(float) 1/9);
        Tile DC = new Tile(4,3,0); // Terminal State

        // Actions to input
        actions.add(action.UP);
        actions.add(action.UP);
        actions.add(action.UP);




        // Testing...
        AA.printTile();
    }
}
