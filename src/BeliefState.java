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
}
