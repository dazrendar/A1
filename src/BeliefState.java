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

    public double calculateSummationForOneState(String stateXY, Action action) {
        State destinationState = beliefState.get(stateXY);
//        destinationState.printState();

        double totalSum = 0;

        // Loop through all states
        for (HashMap.Entry<String, State> entry : beliefState.entrySet()) {
            String currKeyXY = entry.getKey(); // the name of the state (e.g., "AA")
            State sourceState = entry.getValue(); // the details of the state
            ArrayList<Action> destWalls = destinationState.getWalls();
            ArrayList<Action> sourceWalls = sourceState.getWalls();

            // grab coordinates of destination tile
            int destX = destinationState.getX();
            int destY = destinationState.getY();
            int sourceX = sourceState.getX();
            int sourceY = sourceState.getY();


            if (sourceWalls.contains(Action.TERMINATE) && sourceX == destX && sourceY == destY) {

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

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                            if (sourceX == destX + 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                            if (sourceX == destX && sourceY == destY - 1) {

                                totalSum += (0.8) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }

                        }
                        if (destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX + 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY-1) {

                                totalSum += (0.8) * sourceState.getBelief();
                            }
                        }
                        if (destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.RIGHT)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.2) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY-1) {

                                totalSum += (0.8) * sourceState.getBelief();
                            }
                        }
                        break;
                    case DOWN:
                        if (destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.RIGHT) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {

                                totalSum += (0.8) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.RIGHT) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.RIGHT) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.2) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY+1) {

                                totalSum += (0.8) * sourceState.getBelief();
                            }
                        }
                        break;
                    case LEFT:
                        if (destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }

                        if (destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }

                        if (destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.9) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }

                        }
                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }

                        if (!destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.2) * sourceState.getBelief();
                            } else if (sourceX == destX + 1 && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            }

                        }

                        if (!destWalls.contains(Action.LEFT) &&
                                destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }

                        if (!destWalls.contains(Action.LEFT) &&
                                !destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY-1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        break;
                    case RIGHT:
                        if (destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

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

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {


                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {


                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.UP) &&
                                destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (!destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT) &&
                                !destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY + 1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY - 1) {

                                totalSum += (0.1) * sourceState.getBelief();
                            }
                        }
                        if (destWalls.contains(Action.UP) &&
                                !destWalls.contains(Action.RIGHT) &&
                                destWalls.contains(Action.DOWN)) {
                            if (sourceX == destX - 1 && sourceY == destY) {

                                totalSum += (0.8) * sourceState.getBelief();
                            } else if (sourceX == destX && sourceY == destY) {

                                totalSum += (0.2) * sourceState.getBelief();
                            }
                        }
                        break;
                }
            }
        }
        return totalSum;
    }
}
