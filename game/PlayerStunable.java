package game;

import edu.monash.fit2099.engine.*;

public class PlayerStunable extends Player {
    private boolean stunned = false;
    private int stunTurnCount = 0;

    /**
     * constructor for PlayerStunable, which is a Player which can be stunned by a Ninja.
     * @param name the name of the Player
     * @param displayChar the character of the Player instance to display on the map
     * @param priority the priority of each turn, the Player with a higher priority makes a move first
     * @param hitPoints the health points of the Player instance
     */

    public PlayerStunable(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
    }

    /***
     * a mutator method to change the state of the Player, changes the state from being stunned to not stunned,
     * vice versa
     * @param value
     */
    public void setStunned(boolean value) {
        stunned = value;
    }

    /**
     * the accessor method for the state of the Player, whether the Player is stunned or not.
     * @return
     */
    public boolean getStunned() {
        return stunned;
    }

    /**
     * increment method for when the Player gets stunned, every turn after it gets stunned it increments
     */
    public void incrementStunTurnCount() {
        this.stunTurnCount++;
    }

    /**
     * resets the after stunned counter
     */
    public void resetStunTurnCount() {
        this.stunTurnCount = 0;
    }

    /**
     * accessor method for getting the after stunned counter value
     * @return after stunned turn counter value
     */
    public int getStunTurnCount() {
        return stunTurnCount;
    }

    @Override
    public boolean isConscious() {
        if (hitPoints == 0){
            System.out.println("You have died, you lost. :(");
            System.exit(130);
        }
        return super.isConscious();
    }

    @Override
    /**
     * if the Player is stunned increment to after stunned turn counter. if player runs out of oxygen
     * on moonbase,player is transported back to Earth
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return returns the actions that Q is allowed to do
     */
    public Action playTurn(Actions actions, GameMap map, Display display) {
        if(getStunned()){
            incrementStunTurnCount();
        }
        // if the player is on the moonbase, decrease the oxygen tank points
        if(this.hasSkill(MapPlayerAt.MOON)){
            for(int i=0; i< this.getInventory().size();i++){
                // Gets oxygen tank from the players inventory and decreases the points every turn if the player
                // is on the moon.
                if(this.getInventory().get(i) instanceof OxygenTank){
                    ((OxygenTank) this.getInventory().get(i)).decreasePoints();

                    // Tells player number of points remaining in the oxygen tank
                    System.out.println("Oxygen points remaining: "+(((OxygenTank) this.getInventory().get(i)).getPoints()));

                    // if the points in the oxygen tank is 0, player is transported back to Earth
                    if (((OxygenTank) this.getInventory().get(i)).getPoints() == 0){

                        // puts player at the location of the rocket when transported back
                        Location rocket_location = new Location(map,21,9);
                        Rocket rocket = new Rocket(rocket_location);
                        this.removeItemFromInventory(this.getInventory().get(i));
                        actions.add(new GoToEarth(rocket_location,rocket));
                        return actions.get(actions.size()-1);
                        }
                    }
                    }
                }
        return super.playTurn(actions, map, display);
    }


    @Override
    /**
     * We change it so that if the Player is stunned the Player can only 'do nothing' for 2 turns.
     * @param actions the Actions that the user can choose from
     * @param display the I/O object that will display the map
     * @return the Action selected by the user
     */
    protected Action showMenu(Actions actions, Display display) {
        if (getStunned()) {
            if (getStunTurnCount() > 1) {
                resetStunTurnCount();
                setStunned(false);
            }
            actions.clear();
        actions.add(new SkipTurnAction());

        }
        actions.add(new QuitAction());
        return super.showMenu(actions, display);
    }
}
