import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public enum action {
        LEFT, RIGHT, UP, DOWN
    }

    public enum observation {
        ZERO_WALLS, ONE_WALL, TWO_WALLS
    }

    public HashMap<String, State> calculateBelief(HashMap<String, State> beliefState) {
        for(HashMap.Entry<String, State> entry : beliefState.entrySet()) {
            String key = entry.getKey(); // the name of the state (e.g., "AA")
            State value = entry.getValue(); // the details of the state
            value.printState();
        }
        return beliefState;
    }

    public static void main(String[] args) {
        ArrayList<action> actions = new ArrayList<action>();
        ArrayList<observation> observations = new ArrayList<observation>();
        final long normalizingConstant = 1/9; // todo check!!
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
//        HashMap<String, State> beliefState = new HashMap<String, State>();
//        beliefState.put("AA", AA);
//        beliefState.put("BA", BA);
//        beliefState.put("CA", CA);
//        beliefState.put("DA", DA);
//
//        beliefState.put("AB", AB);
//        beliefState.put("CB", CB);
//        beliefState.put("DB", DB);
//
//        beliefState.put("AC", AC);
//        beliefState.put("BC", BC);
//        beliefState.put("CC", CC);
//        beliefState.put("DC", DC);

        ArrayList<State> states = new ArrayList<State>(Arrays.asList(AA,BA,CA,DA,AB,CB,DB,AC,BC,CC,DC));
        BeliefState beliefState = new BeliefState(states);


        // Readjust belief state when starting state is specified
        // TODO: eg, if starting state == AA -> AA.setBelief(1); , set all others to 0




        // Actions to input
        // TODO make as actual inputs
        actions.add(action.UP);
        actions.add(action.UP);
        actions.add(action.UP);

        // Observations to input
        observations.add(observation.TWO_WALLS);
        observations.add(observation.TWO_WALLS);
        observations.add(observation.TWO_WALLS);

        // Testing...
        for(HashMap.Entry<String, State> entry : beliefState.getBeliefState().entrySet()) {
            String key = entry.getKey(); // the name of the state (e.g., "AA")
            State value = entry.getValue(); // the details of the state
            value.printState();
        }
        System.out.println(actions);
        System.out.println(observations);
    }


}
