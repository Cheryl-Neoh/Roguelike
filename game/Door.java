package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Door extends Ground {
    public Door() {
        super('O');
    }

    /**
     * @param actor actor that wants to enter the door
     * @return returns false as it is a locked door
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new UnlockDoorAction(location));
    }

    /**
     *
     * @return returns true as the door can block thrown objects
     */

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }


}
