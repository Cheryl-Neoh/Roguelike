package game;
import java.util.List;
import edu.monash.fit2099.engine.*;

public class BuildPlaceRocketAction extends Action{
    private Item rocketBody;
    private Item rocketEngine;
    private Location location;

    /**
     * @param location location of the rocket pad to build the rocket
     */
    public BuildPlaceRocketAction(Location location){
        this.location = location;
    }

    /**
     * This function allows the player to build a rocket from the rocket engine and rocket parts
     * If player has both parts, the parts are removed from the players inventory and a rocket is
     * placed at the location of the rocket pad.
     *
     * @param player player that is going to build the rocket
     * @param map The map the actor is on.
     * @return returns a string saying "You do not have the right parts to build a rocket!" if the player
     *         doesn't have the parts, other wise it congragulates the player.
     */
    @Override
    public String execute(Actor player, GameMap map) {
        List<Item> players_inventory =  player.getInventory();
        boolean flag = false;
        boolean flag2 = false;
        for(Item item: players_inventory){
            if(item instanceof RocketBody){
                this.rocketBody = item;
                flag = true;
            }
            if(item instanceof RocketEngine){
                this.rocketEngine = item;
                flag2 = true;
            }
        }
        if (!(flag) || !(flag2)){
            return "You do not have the right parts to build a rocket!";
        }
        player.removeItemFromInventory(rocketBody);
        player.removeItemFromInventory(rocketEngine);
        map.add(new Floor(),location);
        map.addItem(new Rocket(location), location.x(), location.y());
        return "";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return returns a string to see if the player wants to build the rocket
     */

    @Override
    public String menuDescription(Actor actor) {
        /** Overrides the menu description
         *
         */
        return "Builds the rocket with the rocket engine and rocket body";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
