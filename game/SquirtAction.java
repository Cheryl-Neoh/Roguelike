package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.GameMap;


public class SquirtAction extends AttackAction {

    private Actor subject;
    private WaterPistol weapon;


    /**
     * Constructor to instantiate a new action.
     * @param actor the player doing the action
     * @param subject the subjecct that has the effect on
     * @param item the item that does this action
     */
    public SquirtAction(Actor actor, Actor subject, WaterPistol item) {
        super(actor, subject);
        this.subject = subject;
        this.weapon = item;
    }

    /**
     * has a 70% chance to hit and break Yugo Maxx's exoskeleton if the Water Pistol that the player has is filled with
     * water
     * @param actor player that executes this action
     * @param map the map that the player is on
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (Math.random() < 0.3) {
            weapon.emptyPistol();
            return actor + " misses " + subject + ".\n The Water Pistol is now empty";
        }

        if (weapon.isFilled()) {
            subject.removeSkill(Shield.EXOSKELETON);
            weapon.emptyPistol();
            return actor + " squirts " + subject + "'s exoskeleton. The exoskeleton breaks." + "\nThe Water Pistol is "
                    + "now empty";
        }

        return actor + " tries to squirt but the Water Pistol is empty. :(";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " squirts " + subject;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
