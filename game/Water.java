package game;

import edu.monash.fit2099.engine.*;

public class Water extends Ground {

    /**
     * Constructor for creating a new Water object.
     */
    public Water() {
        super('~');
    }

    /**
     * the player can only fill water into the Water Pistol when near the water
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        for ( Item item : actor.getInventory()){
            if (item instanceof WaterPistol){
                return new Actions(new FillWaterAction((WaterPistol) item));
            }
        }
        return new Actions();
    }

    /**
     * player cannot walk on water.
     * @param actor
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
