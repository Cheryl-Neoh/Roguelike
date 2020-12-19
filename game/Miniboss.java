package game;
import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Random;

public class Miniboss extends Grunt {
    private RocketEngine rocketEngine = new RocketEngine(this);
    private Random rand = new Random();

    public Miniboss(String name, Actor player){
        super(name,player);
        this.displayChar = 'M';
        this.hitPoints = 25;
        this.actionFactories = new ArrayList<>();
    }

    /**
     *
     * @return returns a new instance of intristic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(2,"blasts");
    }

    /**
     * This function is overridden to prevent the miniboss from moving. It also checks if it is conscious or not.
     * If Dr Maybe is not conscious (meaning he is defeated by the player, Dr Maybe will drop the rocket engine.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return returns the actions after MoveActorAction has been removed.
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        if (!(isConscious())){
            DropItemAction drop = new DropItemAction(rocketEngine);
            drop.execute(this,map);
        }

        for(int i=actions.size()-1; i>=0; i--){
            if (actions.get(i) instanceof MoveActorAction){
                actions.remove(actions.get(i));
            }
        }

        return actions.get(rand.nextInt(actions.size()));
    }

    /**
     * adds a rocket engine to Dr Maybe's inventory when he is defeated.
     * @return returns a boolean value to see if Dr Maybe is still conscious or not.
     */
    @Override
    public boolean isConscious() {
        if (hitPoints <= 0){
            addItemToInventory(rocketEngine);
        }
        return hitPoints > 0;
    }

}


