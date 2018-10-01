import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList<Action> actions = new ArrayList<Action>();
        ArrayList<Observation> observations = new ArrayList<Observation>();
        final double normalizingConstant = 1/9; // todo check!!
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
