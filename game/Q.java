package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class Q extends Grunt{
    private RocketPlans rocketPlan = new RocketPlans();
    protected Actor player;

    /**
     * @param name name of Q
     * @param player the player
     */

   public Q(String name, Actor player){
       super(name, player);
       this.displayChar = 'Q';
       this.hitPoints = 1000;
       this.player = player;
       this.actionFactories = new ArrayList<>();
   }

    public int disappear(){
       return this.hitPoints = 0;
    }

    /**
     * This function prevents Q from attacking other players by removing
     * instances of attackAction from his actions.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return returns the actions that Q is allowed to do
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {

        for(int i=actions.size()-1; i>=0; i--){
            if (actions.get(i) instanceof AttackAction){
                actions.remove(actions.get(i));
            }
        }
        return super.playTurn(actions, map, display);
    }


    /**
     * This function allows the GiveAction and TalkAction to be carried out by the player.
     *
     * @param otherActor the Actor that might be performing the action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor instanceof Player) {
            Actions action = new Actions(new TalkAction());
            action.add(new GiveAction(rocketPlan, this));
            return action;
        } else {
            return new Actions();
        }
    }
}
