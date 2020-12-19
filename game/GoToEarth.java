package game;

import edu.monash.fit2099.engine.*;

public class GoToEarth extends Action {

    private Location location;
    private static Actions allowableActions = new Actions();
    private Rocket rocket;

    /**
     *
     * @param location location to go to
     * @param rocket rocket item
     */
    public GoToEarth(Location location, Rocket rocket) {
        this.location = location;
        this.rocket = rocket;
    }

    /**
     * This method transports the actor back to Earth.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String retVal = "";
        this.location = map.locationOf(actor);
        // removes the rocket and adds the rocket pad back (so that other enemies dont stand on the spot
        // when player is trying to transport back
        map.locationOf(actor).removeItem(rocket);
        map.locationOf(actor).map().add(new RocketPad(), this.location);
        for (Action action : allowableActions){
            if (action instanceof MoveActorAction && actor.hasSkill(MapPlayerAt.MOON)){
                retVal += action.execute(actor, map);
                this.location = map.locationOf(actor);
                this.location.map().add(new Floor(),this.location);
                this.location.map().addItem(new Rocket(this.location), this.location.x(), this.location.y());

                // Changes the skill for the player to Earth.
                actor.removeSkill(MapPlayerAt.MOON);
                actor.addSkill(MapPlayerAt.EARTH);
            }
        }

        // Player has won the game if he has defeated Yugo Maxx and brings his body back to earth
        for (Item item : actor.getInventory()){
            if (item.toString().equals("Sleeping Yugo Maxx")){
                System.out.println("You have won the game.");
                System.exit(130);
            }
        }
        return retVal + "\nYou have been transported back to Earth.";
    }

    public static void addingNewAction(Action action){
        allowableActions.add(action);
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return retuns a string allowing player to go back to earth
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " enters the Rocket and propels to Earth ";
    }

    /**
     *
     * @return retuns an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}

