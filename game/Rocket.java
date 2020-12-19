package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Rocket extends Item {

    private Location location;
    private static Actions allowableActions = new Actions();

    public Rocket(Location location) {
        super("Rocket", 'A');
        this.location = location;
    }

    @Override
    public Actions getAllowableActions() {
        if (this.location.getActor().hasSkill(MapPlayerAt.EARTH)) {
            allowableActions.clear();
            allowableActions.add(new GoToMoonBase(location, this));
        }
        else if (this.location.getActor().hasSkill(MapPlayerAt.MOON)) {
            allowableActions.clear();
            allowableActions.add((new GoToEarth(location, this)));
        }
        return allowableActions;
    }

}
