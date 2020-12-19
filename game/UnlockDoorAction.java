package game;

import edu.monash.fit2099.engine.*;


public class UnlockDoorAction extends Action {
    private Location doorLocation;

    /**
     * constructor for UnlockDoorAction instance.
     * @param doorLocation the location of this door instance in the map.
     */
    public UnlockDoorAction(Location doorLocation) {
        this.doorLocation = doorLocation;
    }


    /**
     * when the Player is in front of a door, the player can try to unlock the door, if the Player has the key in the
     * inventory then the door will be unlock, if not a message will be printed.
     * @param player The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor player, GameMap map) {
        for (Item item: player.getInventory()){
            if (item instanceof Key){
                map.add(new Floor(), doorLocation);
                player.removeItemFromInventory(item);
                return "The door is unlocked!";
            }
        }
        return "No Master Key obtained to open door. :(";
    }

    /**
     * Returns a string showing what the player can do
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks the door ";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     * @return The key we use for this Action in the menu.
     */
    @Override
    public String hotKey() {
        return "";
    }
}
