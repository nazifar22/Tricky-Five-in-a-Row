package fiveinrow;

/**
 * Represents a pair of integer coordinates.
 * This class is used to store and manipulate a pair of integers, typically representing
 * coordinates or positions in the Five in a Row game.
 */

public class Pair {
    public int x;
    public int y;

    /**
     * Constructs a new Pair with specified x and y values.
     * 
     * @param x The first value of the pair.
     * @param y The second value of the pair.
     */
    
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a string representation of the Pair.
     * 
     * @return A string in the format "(x,y)", where x and y are the values of the pair.
     */
    
    @Override
    public String toString(){
        return "(" + x + "," + y + ")" ;
    }
}
