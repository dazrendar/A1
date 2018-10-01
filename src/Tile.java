public class Tile {
    private int x;
    private int y;
    private double belief;

    public Tile(int x, int y, double belief) {
        this.x = x;
        this.y = y;
        this.belief = belief;
    }

    public void setBelief(double newBelief) {
        this.belief = newBelief;
    }

    public void printTile() {
        System.out.println("Tile (" + x + "," + y + "); belief = " + belief);

    }
}
