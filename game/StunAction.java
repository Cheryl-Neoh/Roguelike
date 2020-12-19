package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class StunAction extends AttackAction{
    private Actor subject;
    private Random rand = new Random();
    private Display display = new Display();
    private SkipTurnAction skipTurn = new SkipTurnAction();

    /**
     * constructor for StunAction.
     * @param actor the actor which will perform throw the stink bomb
     * @param subject the player which will get hit by the stink bomb
     */
    public StunAction(Actor actor, Actor subject) {
        super(actor, subject);
        this.subject = subject;
    }

    @Override
    /**
     * The stun action has a 50% chance of stunning the player, and of the Player is already stunned there will be no
     * effect.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    public String execute(Actor actor, GameMap map) {
        Actions action = new Actions();
        action.add(skipTurn);
        String result = actor + " throws a stink bomb";
        if (rand.nextBoolean()) {
            result += ", it misses.";
            display.println(result);
            return result;
        }
        if (!((PlayerStunable) subject).getStunned()) {
            ((PlayerStunable) subject).setStunned(true);
            result += ", it hits and stuns the " + this.subject;
            display.println(result);
            return result;
        }
        result += ", it hits but no effect because " + this.subject + " is already stunned.";
        display.println(result);
        return result;
    }


    @Override
    /**
     * Returns the key used in the menu to trigger this Action.
     * @return The key we use for this Action in the menu.
     */
    public String hotKey() {
        return "";
    }
}
