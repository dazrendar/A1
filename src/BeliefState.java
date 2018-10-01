import com.sun.tools.corba.se.idl.constExpr.ShiftLeft;

import java.util.ArrayList;
import java.util.HashMap;

// Gonna try to ignore this class
public class BeliefState {
    private HashMap<String, State> beliefState = new HashMap<String, State>();

    public HashMap<String, State> getBeliefState() {
        return beliefState;
    }

    public BeliefState(ArrayList<State> states) {
        for (State state : states) {
            String name = "(" + state.getX() + "," + state.getY() + ")";
            beliefState.put(name, state);
        }
    }

    private long calculateTransitionProbability(String stateXY, Action action) {
        switch(action) {
            case UP:
                if (stateXY == "") {

                }
                break;
            case DOWN:
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
        }
        return 0; // todo fix
    }


    public BeliefState calculateBeliefState(String stateXY, Action action, Observation e) {
        // uses current belief state
        // TODO IMPORTANT! Remember: only update the relevent surrounding states: ie., which states COULD end up in stateXY, given the current action
            // so... Do i calculate based on coords, or do I hard code cases?
            //also, where does the observation e come in?
        // todo need transition probabilities?

        ArrayList<State> states = new ArrayList<State>();

        for(HashMap.Entry<String, State> entry : beliefState.entrySet()) {
            // eg. b`(1,1)

            // sum
            String key = entry.getKey(); // the name of the state (e.g., "AA")
            State state = entry.getValue(); // the details of the state
            state.printState();
//            state.getBelief()*
//            state.setBelief();

            states.add(state);

        }
        BeliefState newBeliefState;
        return null; //todo FIX
    }
}
