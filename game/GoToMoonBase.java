package game;

import edu.monash.fit2099.engine.*;


public class GoToMoonBase extends Action{

    private Location location;
    private static Actions allowableActions = new Actions();
    private Rocket rocket;

    public GoToMoonBase(Location location, Rocket rocket) {
        this.location = location;
        this.rocket = rocket;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns a string saying "You have reached your destination" if player has the spacesuit
     * and oxygen tank to go to space. returns a string saying "You need to have a spacesuit and oxygen
     * tank to go to the moonbase!" if otherwise.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String retVal = "";
        int flag = 0;
        // checks if player has the spacesuit and oxygen tank
        for (int i = 0; i < actor.getInventory().size(); i++) {
            if (actor.getInventory().get(i) instanceof Spacesuit) {
                flag += 1;
            }
            if (actor.getInventory().get(i) instanceof OxygenTank) {
                flag += 1;
            }
        }
        // player is allowed to go to moonbase if they have the spacesuit and oxygen tank
        if (flag == 2) {
            this.location = map.locationOf(actor);
            map.locationOf(actor).removeItem(rocket);
            map.locationOf(actor).map().add(new RocketPad(), this.location);
            for (Action action : allowableActions) {
                if (action instanceof MoveActorAction && actor.hasSkill(MapPlayerAt.EARTH)) {
                    retVal += action.execute(actor, map);
                    this.location = map.locationOf(actor);
                    this.location.map().add(new Floor(), this.location);
                    this.location.map().addItem(new Rocket(this.location), this.location.x(), this.location.y());

                    // Changes the players skill from earth to Moon
                    actor.removeSkill(MapPlayerAt.EARTH);
                    actor.addSkill(MapPlayerAt.MOON);
                }
            }
            return retVal + "\nYou have reached your destination.";
        } else {
            return retVal + "\nYou need to have a spacesuit and oxygen tank to go to the moonbase!";
        }
    }

    public static void addingNewAction(Action action){
        allowableActions.add(action);
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return returns a string saying "enters the Rocket and propels to the secret Moonbase"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " enters the Rocket and propels to the secret Moonbase ";
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
