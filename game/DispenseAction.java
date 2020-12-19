package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class DispenseAction extends Action {
    private OxygenTank oxygenTank = new OxygenTank();
    private Location location;

    public DispenseAction(Location location){
        this.location = location;
    }

    @Override
    /**
     * @param actor The actor performing the action.
     * @return returns a string saying that the oxygen tank has been dispensed.
     */
    public String execute(Actor actor, GameMap map) {
        map.addItem(oxygenTank,location.x(),location.y());
        return "Oxygen Tank dispensed";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return return a String providing the user the option to get the oxygen tank
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Press button to get oxygen tank";
    }

    /**
     *
     * @return returns an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
