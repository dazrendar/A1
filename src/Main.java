import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        ArrayList<Action> actions = new ArrayList<Action>();
        ArrayList<Integer> observations = new ArrayList<Integer>();
        String startingStateXY = ""; // empty string if we don't want to specify a starting state.

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
        ArrayList<Action> DBWalls = new ArrayList<Action>(Arrays.asList(Action.RIGHT, Action.TERMINATE));
        State DB = new State(4,2,0, DBWalls); // Terminal State

        ArrayList<Action> ACWalls = new ArrayList<Action>(Arrays.asList(Action.UP, Action.LEFT));
        State AC = new State(1,3,(float) 1/9, ACWalls);
        ArrayList<Action> BCWalls = new ArrayList<Action>(Arrays.asList(Action.UP, Action.DOWN));
        State BC = new State(2,3,(float) 1/9, BCWalls);
        ArrayList<Action> CCWalls = new ArrayList<Action>(Arrays.asList(Action.UP));
        State CC = new State(3,3,(float) 1/9, CCWalls);
        ArrayList<Action> DCWalls = new ArrayList<Action>(Arrays.asList(Action.UP, Action.RIGHT, Action.TERMINATE));
        State DC = new State(4,3,0, DCWalls ); // Terminal State

        // Set up the walls for each tile

        // Combine all states into Belief State
        ArrayList<State> states = new ArrayList<State>(Arrays.asList(AA,BA,CA,DA,AB,CB,DB,AC,BC,CC,DC));
        BeliefState beliefState = new BeliefState(states);


//        // ************** Actions to input ***************
        actions.add(Action.UP);
        actions.add(Action.UP);
        actions.add(Action.UP);

        // Observations to input
        observations.add(2);
        observations.add(2);
        observations.add(2);

//         Starting State
//        startingStateXY = "(2,3)"; // use only to set a starting state.

//        // ************** Actions to input ***************
//        actions.add(Action.UP);
//        actions.add(Action.UP);
//        actions.add(Action.UP);
//
//        // Observations to input
//        observations.add(1);
//        observations.add(1);
//        observations.add(1);

//         Starting State
//        startingStateXY = "(2,3)"; // use only to set a starting state.

//        // ************** Actions to input ***************
//        actions.add(Action.RIGHT);
//        actions.add(Action.RIGHT);
//        actions.add(Action.UP);
//
//        // Observations to input
//        observations.add(1);
//        observations.add(1);
//        observations.add(0);
//
//        // Starting State
//        startingStateXY = "(2,3)"; // use only to set a starting state.

//        // ************** Actions to input ***************
//        actions.add(Action.UP);
//        actions.add(Action.RIGHT);
//        actions.add(Action.RIGHT);
//        actions.add(Action.RIGHT);
//
//        // Observations to input
//        observations.add(2);
//        observations.add(2);
//        observations.add(1);
//        observations.add(1);
//
//        // Starting State
//        startingStateXY = "(1,1)"; // use only to set a starting state.

//        // ***********************************************

        System.out.println("Actions = " + actions + " ; Observations = " + observations);
        if (!startingStateXY.isEmpty()) {
            System.out.println("Starting State = " + startingStateXY);
        }

        // Readjust belief state when starting state is specified
        if (!startingStateXY.isEmpty()) {
//            System.out.println("Setting starting state to: " + startingStateXY);
            for (HashMap.Entry<String, State> entry : beliefState.getBeliefState().entrySet()) {
                String currKeyXY = entry.getKey(); // the name of the state (e.g., "AA")
                State currDestState = entry.getValue(); // the details of the state
                if (currKeyXY.equals(startingStateXY)) {
                    beliefState.getBeliefState().get(currKeyXY).setBelief(1);
                } else {
                    beliefState.getBeliefState().get(currKeyXY).setBelief(0);
                }
            }
        }

        for (int i = 0; i < actions.size(); i++) {
            Action currentAction = actions.get(i);
            int currentObservation = observations.get(i);

            HashMap<String, Double> newValues = new HashMap<String, Double>();

            for (HashMap.Entry<String, State> entry : beliefState.getBeliefState().entrySet()) {
                String currKeyXY = entry.getKey(); // the name of the state (e.g., "AA")
                State currDestState = entry.getValue(); // the details of the state
//                System.out.println(" " + currKeyXY);

                double currentSum = beliefState.calculateSummationForOneState(currKeyXY, currentAction);

                // get walls
                double probabilityOfEvidence = 1;

                // case for observing terminal state
                if (currentObservation == 0) {
                    if (currDestState.getWalls().contains(Action.TERMINATE)) {
                        probabilityOfEvidence = 1;
                    } else {
                        probabilityOfEvidence = 0;
                    }
                }
                // case for NOT observing terminal state
                else {
                    if (currKeyXY.equals("(4,3)") || currKeyXY.equals("(4,2)")) {
                        probabilityOfEvidence = 0;
                    }
                    else  {
                        if (currDestState.getWalls().size() == currentObservation) {
                            probabilityOfEvidence = 0.9;
                        } else {
                            probabilityOfEvidence = 0.1;
                        }
                    }
                }
//                else {
//                    // we are in a terminate state:
//                    if (currDestState.getWalls().size() - 1 == currentObservation) {
//                        probabilityOfEvidence = 0.9;
//                    } else {
//                        probabilityOfEvidence = 0.1;
//                    }
//                }

                currentSum = probabilityOfEvidence * currentSum; // still need to apply normalization
                newValues.put(currKeyXY, currentSum);
            }

//            System.out.println(newValues);
//            System.out.println(newValues.size());

            double summationForNormalization = 0;

            for (HashMap.Entry<String, Double> entry : newValues.entrySet()) {
                String currKeyXY = entry.getKey(); // the name of the state (e.g., "AA")
                Double currStateValue = entry.getValue(); // the details of the state

                summationForNormalization += currStateValue;
            }

//            System.out.println(summationForNormalization);


            for (HashMap.Entry<String, Double> entry : newValues.entrySet()) {
                String currKeyXY = entry.getKey(); // the name of the state (e.g., "AA")
                Double currStateValue = entry.getValue(); // the details of the state

                // apply normalization
                double finalBeliefValue = currStateValue / summationForNormalization;

                // add to belief state
                beliefState.getBeliefState().get(currKeyXY).setBelief(finalBeliefValue);

            }
            System.out.println("********* \nBelief State at step: " + (i+1));
            for (HashMap.Entry<String, State> entry : beliefState.getBeliefState().entrySet()) {
                State currDestState = entry.getValue(); // the details of the state
                currDestState.printStateFinal();
            }
        }

        // FINAL OUTPUT printing
//        System.out.println("Final Output:");
//        for (HashMap.Entry<String, State> entry : beliefState.getBeliefState().entrySet()) {
//            State currDestState = entry.getValue(); // the details of the state
//            currDestState.printStateFinal();
//        }
    }



}
