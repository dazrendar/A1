public class Tile {
    private int x;
    private int y;
    private int belief;

    public Tile(int x, int y, int belief) {
        this.x = x;
        this.y = y;
        this.belief = belief;
    }

    public void setBelief(int newBelief) {
        this.belief = newBelief;
    }

    public void printTile() {
        System.out.println("Tile (" + x + "," + y + "); belief = " + belief);

    }
}
