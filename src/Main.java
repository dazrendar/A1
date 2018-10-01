import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList<Action> actions = new ArrayList<Action>();
        ArrayList<Observation> observations = new ArrayList<Observation>();
        final double normalizingConstant = 1/9; // todo check!!
        // TODO Starting state initializer flag

        // Set Up Board (default settings of 1/9 for each belief state, except terminal states):
        ArrayList<Action> AAWalls = new ArrayList<Action>(Arrays.asList(Action.LEFT, Action.DOWN));
        State AA = new State(1,1,(float) 1/9, AAWalls);
        ArrayList<Action> BAWalls = new ArrayList<Action>(Arrays.asList(Action.UP, Action.DOWN));
        State BA = new State(2,1,(float) 1/9, BAWalls);
        ArrayList<Action> CAWalls = new ArrayList<Action>(Arrays.asList(Action.DOWN));
        State CA = new State(3,1,(float) 1/9, CAWalls);
        ArrayList<Action> DAWalls = new ArrayList<Action>(Arrays.asList(Action.DOWN, Action.RIGHT));
        State DA = new State(4,1,(float) 1/9, DAWalls);


        ArrayList<Action> ABWalls = new ArrayList<Action>(Arrays.asList(Action.LEFT, Action.RIGHT));
        State AB = new State(1,2,(float) 1/9, ABWalls);
        // State BB // Not a state
        ArrayList<Action> CBWalls = new ArrayList<Action>(Arrays.asList(Action.LEFT));
        State CB = new State(3,2,(float) 1/9, CBWalls);
        ArrayList<Action> DBWalls = new ArrayList<Action>(Arrays.asList(Action.TERMINATE));
        State DB = new State(4,2,0, DBWalls); // Terminal State

        ArrayList<Action> ACWalls = new ArrayList<Action>(Arrays.asList(Action.UP, Action.LEFT));
        State AC = new State(1,3,(float) 1/9, ACWalls);
        ArrayList<Action> BCWalls = new ArrayList<Action>(Arrays.asList(Action.UP, Action.DOWN));
        State BC = new State(2,3,(float) 1/9, BCWalls);
        ArrayList<Action> CCWalls = new ArrayList<Action>(Arrays.asList(Action.UP));
        State CC = new State(3,3,(float) 1/9, CCWalls);
        ArrayList<Action> DCWalls = new ArrayList<Action>(Arrays.asList(Action.TERMINATE));
        State DC = new State(4,3,0, DCWalls ); // Terminal State

        // Set up the walls for each tile

        // Combine all states into Belief State
        ArrayList<State> states = new ArrayList<State>(Arrays.asList(AA,BA,CA,DA,AB,CB,DB,AC,BC,CC,DC));
        BeliefState beliefState = new BeliefState(states);


        // Readjust belief state when starting state is specified
        // TODO: eg, if starting state == AA -> AA.setBelief(1); , set all others to 0



        // ************** Actions to input ***************
        // TODO make as actual inputs
        actions.add(Action.UP);
        actions.add(Action.UP);
        actions.add(Action.UP);

        // Observations to input
        observations.add(Observation.TWO_WALLS);
        observations.add(Observation.TWO_WALLS);
        observations.add(Observation.TWO_WALLS);
        // ***********************************************

        // Testing...

        // TODO LOOP THROUGH EVERY STATE ...
        beliefState.calculateBeliefStateForOne("(1,2)", actions.get(0), observations.get(0), normalizingConstant);

        System.out.println(actions);
        System.out.println(observations);
    }


}
