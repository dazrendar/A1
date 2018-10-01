public class State {
    private int x;
    private int y;
    private double belief;
    private int numberOfWalls;

    /**
     *
     * @param x
     * @param y
     * @param belief
     * @param numberOfWalls number of walls adjacent to this location "square". 0 means terminal state
     */
    public State(int x, int y, double belief, int numberOfWalls) {
        this.x = x;
        this.y = y;
        this.belief = belief;
        this.numberOfWalls = numberOfWalls;

    }

    // TODO something with the number of walls

    public void setBelief(double newBelief) {
        this.belief = newBelief;
    }

    public void printState() {
        System.out.println("State (" + x + "," + y + "); belief = " + belief + "; #walls = " + numberOfWalls);

    }
}
