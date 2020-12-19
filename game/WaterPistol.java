package game;

import edu.monash.fit2099.engine.Item;

public class WaterPistol extends Item {

    private boolean filled = false;

    /**
     *  Constructor of this class to create a new instance of a Water Pistol object.
     */
    public WaterPistol() {
        super("Water Pistol", 'L');
    }

    /**
     *  function that returns the instance variable filled.
     * @return a boolean the states whether the Water Pistol object is filled with water or not.
     */
    public boolean isFilled(){
        return filled;
    }

    /**
     * function that alters the filled value, this empties the water in the Water Pistol
     */
    public void emptyPistol(){
        filled = false;
    }

    /**
     * function that alters the filled value, this fills water into the Water Pistol
     */
    public void fillPistol(){
        filled = true;
    }
}
