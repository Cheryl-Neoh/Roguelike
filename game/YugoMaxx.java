package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class YugoMaxx extends Goon {

    private Actor player;

    /**
     *
     * @param name is the name of the Yugo Maxx instance object created.
     * @param player is the player that the Yugo Maxx acts on.
     */
    public YugoMaxx(String name, Actor player) {
        super(name, player);
        this.displayChar = 'Y';
        this.hitPoints = 5;
        this.player = player;
        this.actionFactories = new ArrayList<>();
        this.addSkill(Shield.EXOSKELETON);
    }

    /**
     * Returns true if the current Actor has positive hit points.
     *
     * Actors on zero hit points are deemed to be unconscious.
     *
     * @return true if and only if hitPoints is positive.
     */
    @Override
    public boolean isConscious() {
        return hitPoints > 0;
    }


    /**
     * the only actions that the player can act on this Yugo Maxx is squirting and attacking
     * @param otherActor the player
     * @param map the map that the current player is on
     * @return the actions that the player can use
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor instanceof Player) {
            if (this.hasSkill(Shield.EXOSKELETON)) {
                for ( Item item : otherActor.getInventory()){
                    if (item instanceof WaterPistol){
                        return new Actions(new SquirtAction(otherActor, this, (WaterPistol)item));
                    }
                }
            }
            else{
                return new Actions(new AttackAction(otherActor, this));
            }
        }
        return new Actions();
    }
}
