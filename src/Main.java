import java.util.ArrayList;

public class Main {

    public enum action {
        LEFT, RIGHT, UP, DOWN
    }
    public static void main(String[] args) {
        ArrayList<action> actions = new ArrayList<action>();

        // Set Up Board (default settings of 1/9 for each belief state, except terminal states):
        State AA = new State(1,1,(float) 1/9);
        State BA = new State(2,1,(float) 1/9);
        State CA = new State(3,1,(float) 1/9);
        State DA = new State(4,1,(float) 1/9);

        State AB = new State(1,2,(float) 1/9);
        // State BB = new State(2,2,1/9);  // Not a state
        State CB = new State(3,2,(float) 1/9);
        State DB = new State(4,2,0); // Terminal State

        State AC = new State(1,3,(float) 1/9);
        State BC = new State(2,3,(float) 1/9);
        State CC = new State(3,3,(float) 1/9);
        State DC = new State(4,3,0); // Terminal State

        // Actions to input
        actions.add(action.UP);
        actions.add(action.UP);
        actions.add(action.UP);




        // Testing...
        AA.printTile();
    }
}
