package game;

import edu.monash.fit2099.engine.*;

public class MoveBehaviour extends FollowBehaviour {

    public MoveBehaviour(Actor subject) {
        super(subject);
    }

    @Override
    /**
     * We changed it so that the actor only moves one square away from the player when the player is within 5 squares away.
     */
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);
        if (currentDistance <= 5) {
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newDistance = distance(destination, there);
                    if (newDistance > currentDistance) {
                        if( actor instanceof Ninja) {
                            return new MoveActorAction(destination, exit.getName());
                        }
                    }
                }
            }
        }
        return null;
    }
}