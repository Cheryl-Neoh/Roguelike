package game;

import edu.monash.fit2099.engine.*;

public class OxygenDispenser extends Ground {

    public OxygenDispenser(){
        super('D');}

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return returns a new dispense action if the oxygen tank is not at the location of the oxygen dispenser.
     *         does not return the dispense action if the oxygen tank is at the location (meaning it has just
     *         been dispensed or the player has not picked it up)
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        // checks if the oxygen tank is at the location.
        for(int i = 0; i < location.getItems().size();i++){
            if (location.getItems().get(i) instanceof OxygenTank){
                return new Actions();
            }
        }
        return new Actions(new DispenseAction(location));
    }

    /**
     *
     * @param actor actor that can/cannot enter
     * @return returns true (actor can enter)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}

