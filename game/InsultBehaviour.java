package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.Random;

public class InsultBehaviour extends Action implements ActionFactory{
    private Random random = new Random();
    private Actor target;
    private ArrayList<String> insults = new ArrayList<>();

    /**
     * @param subject player to insult
     */
    public InsultBehaviour(Actor subject) {
        target = subject;
        addInsult("'CAN YOU STOP MOVING!'");
        addInsult("'first you see me, now you don't coz you blind.'");
        addInsult("'I don't know you but I'll find you and I'll kill you.'");
        addInsult("Come on! What are you waiting for?");
        addInsult("The world is going to end before you kill me");
        addInsult("You're boring me with your fighting skills");

    }

    /**
     * @param actor
     * @param map
     * @return returns an insult if the probability is 1/10. Otherwise, it returns null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        int chance = random.nextInt(100);
//        int random_num = 1 + (int) (Math.random() * ((10 - 1) + 1));
        if (chance <= 10) {
            return this;

        } else {
            return null;
        }
    }

    /**
     *
     * @param insult insult to add to an array list of insults
     */
    private void addInsult(String insult){
        insults.add(insult);
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns a string of the actor insulting the target with a random insult
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " insults " + target + " : " +  insults.get(random.nextInt(insults.size()));
    }

    /**
     *
     * @return returns a null value
     */

    @Override
    public String hotKey() {
        return null;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return returns a null value
     */

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}
