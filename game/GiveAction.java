package game;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class GiveAction extends Action{
    private Item item;
    private Q Q_npc;

    /**
     * @param item item to give to Q
     * @param Q_npc Q, the person to give the rocket body
     */
    public GiveAction(Item item, Q Q_npc){
       this.item = item;
       this.Q_npc = Q_npc;
    }


    /**
     * This function checks if the player's inventory has the plans or not. If the player has the plans, the plans
     * are removed from the players inventory. The rocket body is then dropped by Q who disappears with a cheery wave.
     *
     * @param player the player that wants to give the plans to Q
     * @param map The map the actor is on.
     * @return returns a string saying "You dont have the rocket plans!!! Go get them!" if the plans are not
     * in the players inventory. If it is, a string saying "The rocket body is now dropped! Cheerio!" is returned.
     */
    @Override
    public String execute(Actor player, GameMap map) {
        List<Item> players_inventory =  player.getInventory();

        boolean flag = false;
        for(Item item: players_inventory){
            if(item instanceof RocketPlans){
                this.item = item;
                flag = true;
            }
        }
        if(!flag){
            return "You dont have the rocket plans!!! Go get them!";
        }

        player.removeItemFromInventory(item);
        map.locationOf(Q_npc).addItem(new RocketBody());
        map.removeActor(Q_npc);
        return "The rocket body is now dropped! Cheerio!";
    }

    /**
     *
     * @return returns an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return returns a string saying "Give Q the Rocket Plans" to see if the player wants to give Q the plans
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Give Q the Rocket Plans";
    }
}
