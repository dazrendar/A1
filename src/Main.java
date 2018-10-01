import java.util.ArrayList;

public class Main {

    public enum action {
        LEFT, RIGHT, UP, DOWN
    }

    public enum observation {
        ZERO_WALLS, ONE_WALL, TWO_WALLS
    }

    public static void main(String[] args) {
        ArrayList<action> actions = new ArrayList<action>();
        ArrayList<observation> observations = new ArrayList<observation>();
        // TODO Starting state initializer flag

        // Set Up Board (default settings of 1/9 for each belief state, except terminal states):
        State AA = new State(1,1,(float) 1/9, 2);
        State BA = new State(2,1,(float) 1/9, 2);
        State CA = new State(3,1,(float) 1/9, 1);
        State DA = new State(4,1,(float) 1/9, 2);

        State AB = new State(1,2,(float) 1/9, 2);
        // State BB // Not a state
        State CB = new State(3,2,(float) 1/9, 1);
        State DB = new State(4,2,0, 0); // Terminal State

        State AC = new State(1,3,(float) 1/9, 2);
        State BC = new State(2,3,(float) 1/9, 2);
        State CC = new State(3,3,(float) 1/9, 1);
        State DC = new State(4,3,0, 0 ); // Terminal State

        // Readjust belief state when starting state is specified
        // TODO

        // Set Probability Distribution of Starting State:
        // TODO: AA.setBelief(1);

        // Actions to input
        actions.add(action.UP);
        actions.add(action.UP);
        actions.add(action.UP);

        // Observations to input
        observations.add(observation.TWO_WALLS);
        observations.add(observation.TWO_WALLS);
        observations.add(observation.TWO_WALLS);



        // Testing...
        AA.printState();
        System.out.println(actions);
        System.out.println(observations);
    }
}
