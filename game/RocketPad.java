package game;

import edu.monash.fit2099.engine.*;

public class RocketPad extends Ground{

    public RocketPad(){
        super('+');
    }

    /**
     * This function allows the player to build the rocket.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return returns a new BuildPlaceRocketAction that can be performed by the player
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new BuildPlaceRocketAction(location));
    }

    /**
     * @param actor
     * @return returns false, meaning the player cannot enter the rocket pad
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
