package game;


import edu.monash.fit2099.engine.*;

import java.util.List;

public class TalkAction extends Action {

    public TalkAction(){
    }

    /**
     * This function checks to see if the player has the rocket plans in his inventory already
     *
     * @param player actor that is doing the talking
     * @param map The map the actor is on.
     * @return returns a string saying "Hand them Over! I dont have all day!" if the player does
     *         have the rocket plans. Other wise, it returns a string saying "I can give you something
     *         thats going to help, but I'm going to need the plans"
     */
    @Override
    public String execute(Actor player, GameMap map) {
        List<Item> players_inventory =  player.getInventory();
        for(Item item: players_inventory){
            if(item instanceof RocketPlans){
                return "Hand them Over! I dont have all day!";
            }
        }
        return "I can give you something that is going to help, but I'm going to need the plans";
    }

    /**
     * @param actor The actor performing the action.
     * @return returns a string saying "Talk to Q"
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Talk to Q";
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
