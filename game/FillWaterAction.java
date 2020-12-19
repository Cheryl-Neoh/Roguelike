package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


public class FillWaterAction extends Action {

    private WaterPistol weapon;

    /**
     * constructor used to create a new fil water action.
     * @param item the item that executes this action
     */
    public FillWaterAction(WaterPistol item) {
        this.weapon = item;
    }

    /**
     * fills water into the Water Pistol, if it is empty.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon.isFilled()){
            return weapon.toString() + " is already full.";
        }
        weapon.fillPistol();
        return weapon.toString() + " is filling up!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fills the Water Pistol";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
