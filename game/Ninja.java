package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class Ninja extends Grunt {
    protected Actor target;

    /**
     * constructor for making a Ninja character, which has a move behavior.
     * @param name the name of the Ninja
     * @param player the player that the Ninja acts on
     */
    public Ninja(String name, Actor player) {
        super(name, player);
        this.target = player;
        this.hitPoints = 20;
        this.displayChar = '*';
        this.actionFactories = new ArrayList<ActionFactory>();
        addBehaviour(new MoveBehaviour(player));
    }

    @Override
    /**
     * We changed it so that at every turn if the player is within 5 squares away, it will execute an action called
     * StunAction which stuns the player for 2 turns.(The player can't do anything for 2 turns.) and move one square
     * away.
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return returns the actions that Q is allowed to do
     */
    public Action playTurn(Actions actions, GameMap map, Display display) {
        for (ActionFactory factory : actionFactories) {
            Action action = factory.getAction(this, map);
            if (action != null) {
                StunAction stunPlayer = new StunAction(this, this.target);
                stunPlayer.execute(this, map);
                return action;
            }
        }
        return new SkipTurnAction();
    }
}
