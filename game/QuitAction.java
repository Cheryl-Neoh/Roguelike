package game;
import java.lang.System;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class QuitAction extends Action {

    public QuitAction(){}

    /**
     * The game stops when the player chooses this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns an empty string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println("You have quit the game :(");
        System.exit(1);
        return "";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return returns a string giving the player the option of quitting the game.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Quit the game";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
