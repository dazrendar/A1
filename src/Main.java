public class Main {

    public static void main(String[] args) {
//        for(String str : args) {
//            System.out.println(str);
//        }

        // Arguments:
//        an initial belief state b(s)
//        a sequence of actions a1:n
//        a sequence of observations e1:n


        // Set Up Board:
        Tile AA = new Tile(1,1,1/9);
        Tile BA = new Tile(2,1,1/9);
        Tile CA = new Tile(3,1,1/9);
        Tile DA = new Tile(4,1,1/9);

        Tile AB = new Tile(1,2,1/9);
        Tile BB = new Tile(2,2,1/9);
        Tile CB = new Tile(3,2,1/9);
        Tile DB = new Tile(4,2,1/9);

        Tile AC = new Tile(1,3,1/9);
        Tile BC = new Tile(2,3,1/9);
        Tile CC = new Tile(3,3,1/9);
        Tile DC = new Tile(4,3,1/9);

        // Testing...
        AA.printTile();
    }
}
