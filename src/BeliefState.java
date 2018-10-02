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


    public double calculateBeliefStateForOne(String stateXY, Action action) {
        // uses current belief state
        // TODO IMPORTANT! Remember: only update the relevent surrounding states: ie., which states COULD end up in stateXY, given the current action
        // so... Do i calculate based on coords, or do I hard code cases?
        //also, where does the observation e come in?
        // todo need transition probabilities?

        State destinationState = beliefState.get(stateXY);
        destinationState.printState();

        double totalSum = 0;

        // Loop through all states
        for (HashMap.Entry<String, State> entry : beliefState.entrySet()) {
            String currKeyXY = entry.getKey(); // the name of the state (e.g., "AA")
            State sourceState = entry.getValue(); // the details of the state
            ArrayList<Action> destWalls = destinationState.getWalls();
            ArrayList<Action> sourceWalls = sourceState.getWalls();
//            System.out.println("currXY = " + currKeyXY + " destWalls = " + destWalls);

            // grab coordinates of destination tile
            int destX = destinationState.getX();
            int destY = destinationState.getY();
            int sourceX = sourceState.getX();
            int sourceY = sourceState.getY();


//            System.out.println("SOURCE: " + sourceX + "," + sourceY);
//            System.out.println("DEST: " + destX + "," + destY);


            if (sourceWalls.contains(Action.TERMINATE) && sourceX == destX && sourceY == destY) {
                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                totalSum += sourceState.getBelief();
            } else {
                switch (action) {
                    case UP:
                        // P((x,y) | (x+_,y+_),action) * b(x+_,y+_)
                        // check if there is a tile oriented below in the BELIEF STATE
                        if (destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                            if (sourceX == destX + 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                            if (sourceX == destX && sourceY == destY - 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }

                        }
                        if (destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX + 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY-1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            }
                        }
                        break;
                    case DOWN:
                        if (destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.RIGHT) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.RIGHT) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        break;
                    case LEFT:
                        if (destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }

                        if (destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }

                        if (destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }

                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }

                        if (!destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            }

                        }

                        if (!destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }

                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY-1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        break;
                    case RIGHT:
                        if (destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {
                                totalSum += (0.1) * sourceState.getBelief();
                            }

                        }
                        if (!destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.RIGHT) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {

                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {

                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX - 1 && sourceY == destY) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {
                                System.out.println("SOURCE: " + sourceX + "," + sourceY);
                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        break;
                }
            }
        }

        return totalSum;
    }
}
//
//
//        // todo.. loop here or outside?
//
//        ArrayList<State> states = new ArrayList<State>();
//
////        for(HashMap.Entry<String, State> entry : beliefState.entrySet()) {
////            // eg. b`(1,1)
////
////            // sum
//////            String key = entry.getKey(); // the name of the state (e.g., "AA")
//////            State state = entry.getValue(); // the details of the state
//////            state.printState();
//////            state.getBelief()*
//////            state.setBelief();
////
////            states.add(destinationState);
//
//        }
//        BeliefState newBeliefState;
//        return null; //todo FIX
//    }

