import java.util.ArrayList;

public class State {
    private int x;
    private int y;
    private double belief;
    private ArrayList<Action> walls;
//    private int numberOfWalls;

    public State(int x, int y, double belief, ArrayList<Action> walls) {
        this.x = x;
        this.y = y;
        this.belief = belief;
        this.walls = walls;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getBelief() {
        return belief;
    }

    public ArrayList<Action> getWalls() {
        return walls;
    }

// TODO something with the number of walls

    public void setBelief(double newBelief) {
        this.belief = newBelief;
    }

    public void printState() {
        System.out.println("State (" + x + "," + y + "); belief = " + belief + "; #walls = " + walls);

    }
}
