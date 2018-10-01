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

//    private double calculateTransitionProbability(String stateXY, Action action) {
//        switch(action) {
//            case UP:
//                if (stateXY == "") {
//
//                }
//                break;
//            case DOWN:
//                break;
//            case LEFT:
//                break;
//            case RIGHT:
//                break;
//        }
//        return 0; // todo fix
//    }


    public BeliefState calculateBeliefStateForOne(String stateXY, Action action,
                                                  Observation e, double normalizingConstant) {
        // uses current belief state
        // TODO IMPORTANT! Remember: only update the relevent surrounding states: ie., which states COULD end up in stateXY, given the current action
            // so... Do i calculate based on coords, or do I hard code cases?
            //also, where does the observation e come in?
        // todo need transition probabilities?

        State destinationState = beliefState.get(stateXY);
        destinationState.printState();

        // grab coordinates of destination tile
        int destX = destinationState.getX();
        int destY = destinationState.getY();

        double sum = 0;
        double probFromBelow = 0;
        double probFromAbove = 0;
        double probFromLeft = 0;
        double probFromRight = 0;

        // Loop through all states
        for(HashMap.Entry<String, State> entry : beliefState.entrySet()) {
            String currKeyXY = entry.getKey(); // the name of the state (e.g., "AA")
            State currentState = entry.getValue(); // the details of the state
            int currX = currentState.getX();
            int currY = currentState.getY();

            //            currentState.printState();


            // check if state neighbours destinationState
            boolean isNextToX = Math.abs(currX - destX) <= 1;
            boolean isNextToY = Math.abs(currY - destY) <= 1;
            System.out.println("nexttoX = " +isNextToX);
            System.out.println("nexttoY = " +isNextToY);

            // XOR
            if (isNextToX ^ isNextToY) {
                System.out.println("YES!! " + currKeyXY);
            }
        }

        switch(action) {
            case UP:
                // P((x,y) | (x+_,y+_),action) * b(x+_,y+_)
                // check if there is a tile oriented below in the BELIEF STATE
                if (destX - 1 > 0) {
                    // then there is a tile below
                    probFromBelow = (0.8) * destinationState.getBelief();
                }

                // then there is a tile above
                if (destX + 1 <= 3) {
                    probFromAbove = (0.1) * destinationState.getBelief();
                }

                // then there is a tile to the left
                if (destY - 1 > 0) {
                    probFromLeft = (0.1) * destinationState.getBelief();
                }

                // then there is a tile to the right
                if (destY + 1 <= 4) {
                    probFromRight = (0.1) * destinationState.getBelief();
                }
                break;
            case DOWN:
                // todo
                break;
            case LEFT:
                // todo
                break;
            case RIGHT:
                // todo
                break;
        }

        sum = probFromAbove + probFromBelow + probFromLeft + probFromRight;

        // todo.. loop here or outside?

        ArrayList<State> states = new ArrayList<State>();

        for(HashMap.Entry<String, State> entry : beliefState.entrySet()) {
            // eg. b`(1,1)

            // sum
//            String key = entry.getKey(); // the name of the state (e.g., "AA")
//            State state = entry.getValue(); // the details of the state
//            state.printState();
//            state.getBelief()*
//            state.setBelief();

            states.add(destinationState);

        }
        BeliefState newBeliefState;
        return null; //todo FIX
    }
}
