package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Grunt extends Actor {
	protected List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	/**
	 * constructor for Grunt class
	 * @param name the name given to the Grunt
	 * @param player the player that the Grunt acts on
	 */
	public Grunt(String name, Actor player) {
		super(name, 'g', 5, 50);
		addBehaviour(new FollowBehaviour(player));
	}

	protected void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	@Override
	/**
	 * when the NPC becomes unconscious a Key will be added into the inventory
	 */
	public boolean isConscious() {
		if (hitPoints == 0) {
			addItemToInventory(new Key("Master Key", 'K'));
		}
		return super.isConscious();
	}

	@Override
	/**
	 * the NPC can only attack the player
	 * */
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		if (otherActor instanceof Player) {
			return new Actions(new AttackAction(otherActor, this));
		} else {
			return new Actions();
		}
	}

	@Override
	/**
	 * we changed it so that it removes some actions that is unnecessary to the NPC.
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (int i = actions.size() - 1; i >= 0; i--) {
			if (actions.get(i) instanceof DropItemAction || actions.get(i) instanceof PickUpItemAction ||
					actions.get(i) instanceof UnlockDoorAction||actions.get(i) instanceof StunAction) {
				actions.remove(actions.get(i));
			}
			for (ActionFactory factory : actionFactories) {
				Action action = factory.getAction(this, map);
				if (action != null) {
					return action;
				}
			}
		}
		return super.playTurn(actions, map, display);
	}
}
