package game;


import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;

public class    Key extends Item {

    /**
     * constructor for making a key
     * @param name the name of the key
     * @param displayChar the character that will be on the map, which represents the key.
     */
    public Key(String name, char displayChar) {
        super(name, displayChar);
        allowableActions = new Actions(new DropItemAction(this));
    }
}
