package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class OxygenTank extends Item {
    private int points = 11;

    public OxygenTank(){
        super("Oxygen Tank",'8');
    }

    /**
     * method to decrease the oxygen tanks points.
     */
    public void decreasePoints(){
        points = points -1;
    }

    /**
     * accessor method to get the points of the oxygen tank.
     * @return returns the points of the oxygen tank
     */
    public int getPoints() {
        return points;
    }
}
