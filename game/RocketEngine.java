package game;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import game.Miniboss;

import java.awt.geom.NoninvertibleTransformException;

public class RocketEngine extends Item {

    public RocketEngine(Actor actor){
        super("Rocket Engine", 'U');
        if (actor instanceof Miniboss) {
            allowableActions = new Actions(new DropItemAction(this));
        }
    }

}
