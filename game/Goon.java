package game;

import edu.monash.fit2099.engine.*;


public class Goon extends Grunt {

    /**
     * a constructor for Goon class
     * @param name The name given to the Goon
     * @param player The player which the Goon acts on
     */
    public Goon(String name, Actor player) {
        super(name, player);
        this.hitPoints = 30;
        this.displayChar = '^';
        addBehaviour(new InsultBehaviour(player));
    }

    @Override
    /**
     * changing the weaopon used by the Goon
     */
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "pokes");
    }
}


